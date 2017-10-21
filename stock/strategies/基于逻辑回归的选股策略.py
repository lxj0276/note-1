def StockFactorsGet(trading_day,stock):
  data =  DataAPI.MktStockFactorsOneDayGet(tradeDate=trading_day.strftime('%Y%m%d'),secID=stock,field=['secID','tradeDate']+used_factors,pandas="1")

  data['ticker'] = data['secID'].apply(lambda x: x[0:6])

  data.set_index('ticker',inplace=True)

  #数据标准化

  for f in used_factors:

      if data[f].std() == 0:

          continue

      data[f] = (data[f] - data[f].mean()) / data[f].std()

    return data
from CAL.PyCAL import *
import numpy as np
from pandas import DataFrame
from sklearn.feature_extraction.text import TfidfVectorizer 
from sklearn.linear_model.logistic import LogisticRegression 
from sklearn.cross_validation import train_test_split
start = '2011-08-01'                       
end = '2015-08-01'                         
benchmark = 'HS300'                        
universe = set_universe('HS300')  
capital_base = 10000000                      
freq = 'd'                                 
refresh_rate = 30                           
used_factors = ['ROA', 'PE', 'LCAP','ROE','Volatility','HBETA' ]
def initialize(account):                   
    pass
​

def handle_data(account):                  
  cal = Calendar('China.SSE')

  period = Period('-30B')

  today = account.current_date

  today = Date.fromDateTime(account.current_date)  

  train_day = cal.advanceDate(today, period)

  train_day = train_day.toDateTime()

  

  # 提取系数回归所需数据

  train_data=StockFactorsGet(train_day,universe)

  train_data=train_data.dropna()

  # 去极值处理

  for f in used_factors:

      for i in range(len(used_factors)):

          if train_data[f].values[i]>=train_data[f].median()+5.2*train_data[f].mad():

              train_data[f].values[i]=train_data[f].values[i]=train_data[f].median()+5.2*train_data[f].mad()

          elif train_data[f].values[i]<=train_data[f].median()+5.2*train_data[f].mad():

              train_data[f].values[i]=train_data[f].values[i]=train_data[f].median()+5.2*train_data[f].mad()

  # 提取收盘价收益率

  ret_data=DataAPI.MktEqudGet(tradeDate=today.strftime('%Y%m%d'),secID=train_data['secID'],field=['tradeDate','secID','closePrice'],pandas="1")

  preprice_data=DataAPI.MktEqudGet(tradeDate=train_day.strftime('%Y%m%d'),secID=train_data['secID'],field=['tradeDate','secID','closePrice'],pandas="1")

  ret_data['ret']=ret_data['closePrice']/preprice_data['closePrice']-1

 

  # 提取指数收盘价，计算收益率

  hs300_data = DataAPI.MktIdxdGet(tradeDate=train_day.strftime('%Y%m%d'),ticker='000300', field='ticker,tradeDate,closeIndex',pandas="1")

  hs300_today_data=DataAPI.MktIdxdGet(tradeDate=today.strftime('%Y%m%d'),ticker='000300', field='ticker,tradeDate,closeIndex',pandas="1")

  hs300_data['ret']=hs300_today_data['closeIndex']/hs300_data['closeIndex']-1

  # 提取今日因子数据

  today_data = StockFactorsGet(today,train_data['secID'])

  today_data=today_data.fillna(0)

  for index in range(len(ret_data['ret'])):

      if ret_data['ret'].values[index] > hs300_data['ret'].values:

          ret_data['ret'].values[index] = 1

      else:

          ret_data['ret'].values[index] = 0

  # Logistic回归过程

  factors=['ROA', 'PE','ROE','Volatility','HBETA']

  x_train = train_data[factors]

  y_train = ret_data['ret']

  x_test = today_data[factors]

  classifier = LogisticRegression()

  classifier.fit(x_train, y_train)

  predictions = classifier.predict(x_test)

  proba=classifier.predict_proba(x_test)

  today_data['predictions']=predictions

  today_data['proba']=proba[:,1]

  new_data=today_data[today_data['predictions']>0]

  new_data.set_index('secID',inplace=True)

  buy=DataFrame(index=new_data.index, columns=['proba'], data=0)

  buy['proba']=new_data['proba']

  buy=buy.sort(columns=['proba'],ascending=False)

  # 生成列表

  buylist=buy.index.tolist()[:20]

  for stk in account.valid_secpos:

      if stk not in buylist:

          order_to(stk, 0)

  # 等权重买入所选股票

  portfolio_value = account.referencePortfolioValue  #参考投资策略价        

  for stk in buylist:

      if np.isnan(account.referencePrice[stk]) or account.referencePrice[stk] == 0:  # 停牌或是还没有上市等原因不能交易

          continue

      if stk not in account.valid_secpos:

          order(stk, account.referencePortfolioValue / len(buylist) / account.referencePrice[stk])  
