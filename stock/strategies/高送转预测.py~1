import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import datetime

import matplotlib.pyplot as plt
from sklearn.svm import SVC
from sklearn.model_selection import StratifiedKFold
from sklearn.feature_selection import RFECV
from sklearn.linear_model import LogisticRegression
from sklearn import preprocessing


#####获取年报高送转data
div_data = pd.read_csv(r'Div_data.csv',index_col=0)
##只留年报数据
div_data['type'] = div_data['endDate'].map(lambda x:x[-5:])
div_data['year'] = div_data['endDate'].map(lambda x:x[0:4])
div_data_year = div_data[div_data['type'] == '12-31']
div_data_year = div_data_year[['secID','year','publishDate', 'recordDate','perShareDivRatio',
       'perShareTransRatio']]
div_data_year.columns = ['stock','year','pub_date','execu_date','sg_ratio','zg_ratio']
div_data_year.fillna(0,inplace = True)
div_data_year['sz_ratio'] = div_data_year['sg_ratio']+div_data_year['zg_ratio']
div_data_year['gsz'] = 0 
div_data_year.ix[div_data_year['sz_ratio'] >=1,'gsz'] = 1


####获取q1q2q3已经高送转data
q123_already_szdata = pd.read_csv(r'q1q2q3_already_sz_stock.csv',index_col=0)



###获取因变量因子

"""

每股资本公积、每股未分配利润、每股净资产、每股收益
营业收入同比增速、股本数量、股票价格、是否为次新股、上市日天数

"""
###将一些指标转变为每股数值
def get_perstock_indicator(need_indicator,old_name,new_name,sdate):
    target = get_fundamentals(query(valuation.code,valuation.capitalization,need_indicator),statDate = sdate)
    target[new_name] = target[old_name]/target['capitalization']/10000
    
    
    return target[['code',new_name]]

###获取每股收益、股本数量
def get_other_indicator(sdate):
    target = get_fundamentals(query(valuation.code,valuation.capitalization,\
                                    indicator.inc_revenue_year_on_year,\
                                    indicator.eps
                                    ),statDate = sdate)
    target.rename(columns={'inc_revenue_year_on_year':'revenue_growth'},inplace = True)
    
    target['capitalization'] = target['capitalization']*10000
    
    return target[['code','capitalization','eps','revenue_growth']]

        
###获取一个月收盘价平均值
def get_bmonth_aprice(code_list,startdate,enddate):
    mid_data = get_price(code_list, start_date=startdate, end_date=enddate,\
              frequency='daily', fields='close', skip_paused=False, fq='pre')
    mean_price = pd.DataFrame(mid_data['close'].mean(aixs = 0),columns=['mean_price'])
    mean_price['code'] =mean_price.index
    mean_price.reset_index(drop = True,inplace =True)
    
    return mean_price[['code','mean_price']]



###判断是否为次新股（判断标准为位于上市一年之内）                          
def judge_cxstock(date):
    mid_data = get_all_securities(types=['stock'])
    mid_data['start_date'] = mid_data['start_date'].map(lambda x:x.strftime("%Y-%m-%d"))
    shift_date = str(int(date[0:4])-1)+date[4:]
    mid_data['1year_shift_date'] = shift_date
    mid_data['cx_stock'] = 0
    mid_data.ix[mid_data['1year_shift_date']<=mid_data['start_date'],'cx_stock'] = 1
    mid_data['code'] = mid_data.index
    mid_data.reset_index(drop = True,inplace=True)

    return mid_data[['code','cx_stock']]

###判断是否增发了股票（相比于一年前）
def judge_dz(sdate1,sdate2):
    target1 = get_fundamentals(query(valuation.code,valuation.capitalization,balance.capital_reserve_fund),statDate = sdate1)
    target1['CRF_1'] = target1[ 'capital_reserve_fund']/target1['capitalization']/10000
    target2 = get_fundamentals(query(valuation.code,valuation.capitalization,balance.capital_reserve_fund),statDate = sdate2)
    target2['CRF_2'] = target2[ 'capital_reserve_fund']/target2['capitalization']/10000

    target = target1[['code','CRF_1']].merge(target2[['code','CRF_2']],on=['code'],how='outer')
    target['CRF_change'] = target['CRF_1'] - target['CRF_2']
    target['dz'] = 0
    target.ix[target['CRF_change']>1,'dz']=1
    target.fillna(0,inplace = True)
    
    return target[['code','dz']]

###判断上市了多少个自然日
def get_dayslisted(year,month,day):
    
    mid_data = get_all_securities(types=['stock'])
    date = datetime.date(year,month,day)
    mid_data['days_listed'] = mid_data['start_date'].map(lambda x:(date -x).days)
    mid_data['code'] = mid_data.index
    mid_data.reset_index(drop = True,inplace=True)
    
    return mid_data[['code','days_listed']]


    
def get_yearly_totaldata(statDate,statDate_before,mp_startdate,mp_enddate,year,month,day):
    
    """
    输入：所需财务报表期、20日平均股价开始日期、20日平均股价结束日期
    输出：合并好的高送转数据 以及 财务指标数据
    """
    per_zbgj = get_perstock_indicator(balance.capital_reserve_fund,'capital_reserve_fund','per_CapitalReserveFund',statDate)
    per_wflr = get_perstock_indicator(balance.retained_profit,'retained_profit','per_RetainProfit',statDate)
    per_jzc = get_perstock_indicator(balance.equities_parent_company_owners,'equities_parent_company_owners','per_TotalOwnerEquity',statDate) 
    
    other_indicator = get_other_indicator(statDate)
    code_list = other_indicator['code'].tolist()
    mean_price = get_bmonth_aprice(code_list,mp_startdate,mp_enddate)
    cx_signal = judge_cxstock(mp_enddate)
    dz_signal = judge_dz(statDate,statDate_before)
    days_listed = get_dayslisted(year,month,day)

    chart_list = [per_zbgj,per_wflr,per_jzc,other_indicator,mean_price,cx_signal,dz_signal,days_listed]
    for chart in chart_list:
        chart.set_index('code',inplace = True)

    independ_vari = pd.concat([per_zbgj,per_wflr,per_jzc,other_indicator,mean_price,cx_signal,dz_signal,days_listed],axis = 1)
    independ_vari['year'] = str(int(statDate[0:4]))
    independ_vari['stock'] = independ_vari.index
    independ_vari.reset_index(drop=True,inplace =True)

    total_data = pd.merge(div_data_year,independ_vari,on = ['stock','year'],how = 'inner')
    total_data['per_zbgj_wflr'] = total_data['per_CapitalReserveFund']+total_data['per_RetainProfit']
    
    
    return total_data


gsz_2016 = get_yearly_totaldata('2016q3','2015q3','2016-10-01','2016-11-01',2016,11,1)
gsz_2015 = get_yearly_totaldata('2015q3','2014q3','2015-10-01','2015-11-01',2015,11,1)
gsz_2014 = get_yearly_totaldata('2014q3','2013q3','2014-10-01','2014-11-01',2014,11,1)
gsz_2013 = get_yearly_totaldata('2013q3','2012q3','2013-10-01','2013-11-01',2013,11,1)
gsz_2012 = get_yearly_totaldata('2012q3','2011q3','2012-10-01','2012-11-01',2012,11,1)
gsz_2011 = get_yearly_totaldata('2011q3','2010q3','2011-10-01','2011-11-01',2011,11,1)


###不希望过大的营业收入增长影响结果，实际上营收增长2000%和增长300%对是否送转结果影响差别不大
for data in [gsz_2011,gsz_2012,gsz_2013,gsz_2014,gsz_2015,gsz_2016]:
    data.ix[data['revenue_growth']>300,'revenue_growth'] =300


    ###基于树的判断
traindata = pd.concat([gsz_2011,gsz_2012,gsz_2013,gsz_2014,gsz_2015,gsz_2016],axis = 0)
traindata.dropna(inplace = True)

x_traindata = traindata[['per_zbgj_wflr',\
       'per_TotalOwnerEquity', 'capitalization', 'eps', 'revenue_growth',\
       'mean_price', 'days_listed']]

y_traindata = traindata[['gsz']]
X_trainScale = preprocessing.scale(x_traindata)

from sklearn.ensemble import ExtraTreesClassifier

model = ExtraTreesClassifier() 
model.fit(X_trainScale,y_traindata)
print(pd.DataFrame(model.feature_importances_.tolist(),index =['per_zbgj_wflr',\
       'per_TotalOwnerEquity', 'capitalization', 'eps', 'revenue_growth',\
       'mean_price', 'days_listed'],columns = ['importance'] ))


x_traindata.corr()


###基于RFE（递归特征消除） 判断
traindata = pd.concat([gsz_2011,gsz_2012,gsz_2013,gsz_2014,gsz_2015,gsz_2016],axis = 0)
traindata.dropna(inplace = True)
x_traindata = traindata[['per_zbgj_wflr',\
        'capitalization', 'eps', 'revenue_growth',\
       'mean_price', 'days_listed']]
y_traindata = traindata[['gsz']]
X_trainScale = preprocessing.scale(x_traindata)

svc = SVC(C=1.0,class_weight='balanced',kernel='linear',probability=True)
rfecv = RFECV(estimator=svc, step=1, cv=StratifiedKFold(2),
              scoring='accuracy')
X_trainScale = preprocessing.scale(x_traindata)
rfecv.fit(X_trainScale,y_traindata)

plt.figure()
plt.xlabel("Number of features selected")
plt.ylabel("Cross validation score (nb of correct classifications)")
plt.plot(range(1, len(rfecv.grid_scores_) + 1), rfecv.grid_scores_)
plt.show()



def get_prediction(x_traindata,y_traindata,x_testdata,standard='scale'):
    if standard == 'scale':
        #均值方差标准化
        X_trainScale = preprocessing.scale(x_traindata)
        scaler = preprocessing.StandardScaler().fit(x_traindata)
        X_testScale = scaler.transform(x_testdata) 
    elif standard =='minmax':
        #min_max标准化
        min_max_scaler = preprocessing.MinMaxScaler()
        X_trainScale = min_max_scaler.fit_transform(x_traindata)
        X_testScale = min_max_scaler.transform(x_testdata)
    elif standard =='no':
        #不标准化
        X_trainScale = x_traindata
        X_testScale = x_testdata

    ###考虑到样本中高送转股票与非高送转股票样本的不平衡问题，这里选用调整的class_weight
    model = LogisticRegression(class_weight='balanced',C=1e9)
    model.fit(X_trainScale, y_traindata)
    predict_y = model.predict_proba(X_testScale)
    
    return predict_y

def assess_classification_result(traindata,testdata,variable_list,q123_sz_data,date1,date2,function = get_prediction):
  
    traindata.dropna(inplace = True)
    testdata.dropna(inplace = True)

    x_traindata = traindata.loc[:,variable_list]
    y_traindata = traindata.loc[:,'gsz']
    x_testdata = testdata.loc[:,variable_list]
    y_testdata = testdata.loc[:,'gsz']

    total = testdata.loc[:,['stock','gsz']]
    for method in ['scale','minmax','no']:
        predict_y = function(x_traindata,y_traindata,x_testdata,standard=method)
        total['predict_prob_'+method] = predict_y[:,1]
    
    ###过滤今年前期已经送转过的股票
    q123_stock = q123_sz_data['stock'].tolist()
    total_filter = total.loc[total['stock'].isin(q123_stock)==False]
    
    ###过滤ST股票
    stock_list = total_filter['stock'].tolist()
    st_data = pd.DataFrame(get_extras('is_st',stock_list , start_date=date1, end_date=date2, df=True).iloc[-1,:])
    st_data.columns =['st_signal']
    st_list = st_data[st_data['st_signal']==True]
    total_filter = total_filter[total_filter['stock'].isin(st_list)==False]
    
    ###衡量不同选股个数、不同标准化方法下的 预测准度
    result_dict ={}
    for stock_num in [10,25,50,100,200]:
        accuracy_list = []
        for column in total_filter.columns[2:]:
            total_filter.sort(column,inplace = True,ascending = False)
            dd = total_filter[:stock_num]
            accuracy = len(dd[dd['gsz']==1])/len(dd)
            accuracy_list.append(accuracy)
        result_dict[stock_num] = accuracy_list
    
    result = pd.DataFrame(result_dict,index =['accuracy_scale','accuracy_minmax','accuracy_no'])
    
    return result,total_filter


### 2013年预测结果
traindata = pd.concat([gsz_2011,gsz_2012],axis=0)
testdata = gsz_2013.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2013)&(q123_already_szdata['gs']>0)]
result_2013,total_2013 =  assess_classification_result(traindata,testdata,variable_list,q123_sz_data,'2013-10-25','2013-11-01')
print (result_2013)


###2014年预测结果
traindata = pd.concat([gsz_2012,gsz_2013],axis=0)
testdata = gsz_2014.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2014)&(q123_already_szdata['gs']>0)]
result_2014,total_2014 =  assess_classification_result(traindata,testdata,variable_list,q123_sz_data,'2014-10-25','2014-11-01')
print (result_2014)


####2015年预测结果
traindata = pd.concat([gsz_2013,gsz_2014],axis=0)
testdata = gsz_2015.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2015)&(q123_already_szdata['gs']>0)]
result_2015,total_2015=  assess_classification_result(traindata,testdata,variable_list,q123_sz_data,'2015-10-25','2015-11-01')
print (result_2015)


####2016年预测结果
traindata = pd.concat([gsz_2014,gsz_2015],axis=0)
testdata = gsz_2016.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2016)&(q123_already_szdata['gs']>0)]
result_2016,total_2016 =  assess_classification_result(traindata,testdata,variable_list,q123_sz_data,'2016-10-25','2017-11-01')
print (result_2016)


### svm
from sklearn.svm import SVC

def get_prediction_SVM(x_traindata,y_traindata,x_testdata,standard='scale'):
    if standard == 'scale':
        #均值方差标准化
        standard_scaler = preprocessing.StandardScaler()
        X_trainScale = standard_scaler.fit_transform(x_traindata)
        X_testScale = standard_scaler.transform(x_testdata) 
    elif standard =='minmax':
        #min_max标准化
        min_max_scaler = preprocessing.MinMaxScaler()
        X_trainScale = min_max_scaler.fit_transform(x_traindata)
        X_testScale = min_max_scaler.transform(x_testdata)
    elif standard =='no':
        #不标准化
        X_trainScale = x_traindata
        X_testScale = x_testdata

    ###考虑到样本中高送转股票与非高送转股票样本的不平衡问题，这里选用调整的class_weight
    
    clf = SVC(C=1.0,class_weight='balanced',gamma='auto',kernel='rbf',probability=True)
    clf.fit(X_trainScale, y_traindata) 
    predict_y=clf.predict_proba(X_testScale)
    return predict_y


### 2013年预测结果
traindata = pd.concat([gsz_2011,gsz_2012],axis=0)
testdata = gsz_2013.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2013)&(q123_already_szdata['gs']>0)]
result_2013,total_2013 =  assess_classification_result(traindata,testdata,variable_list,q123_sz_data,'2013-10-25',\
                                            '2013-11-01',function = get_prediction_SVM)
print (result_2013)


###2014年预测结果
traindata = pd.concat([gsz_2012,gsz_2013],axis=0)
testdata = gsz_2014.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2014)&(q123_already_szdata['gs']>0)]
result_2014,total_2014 =  assess_classification_result(traindata,testdata,variable_list,q123_sz_data,\
                                            '2014-10-25','2014-11-01',function = get_prediction_SVM)
print (result_2014)


####2015年预测结果
traindata = pd.concat([gsz_2013,gsz_2014],axis=0)
testdata = gsz_2015.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2015)&(q123_already_szdata['gs']>0)]
result_2015,total_2015 =  assess_classification_result(traindata,testdata,variable_list,q123_sz_data,\
                                            '2015-10-25','2015-11-01',function = get_prediction_SVM)
print (result_2015)



####2016年预测结果
traindata = pd.concat([gsz_2014,gsz_2015],axis=0)
testdata = gsz_2016.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2016)&(q123_already_szdata['gs']>0)]
result_2016,total_2016 =  assess_classification_result(traindata,testdata,variable_list,q123_sz_data,\
                                            '2016-10-25','2017-11-01',function = get_prediction_SVM)
print (result_2016)


### 逻辑回归与SVM联合选择
def assess_unite_logit_SVM(traindata,testdata,variable_list,q123_sz_data,method_use,date1,date2):
    ###Logit 部分
    traindata.dropna(inplace = True)
    testdata.dropna(inplace = True)
    x_traindata = traindata[variable_list]
    y_traindata = traindata[['gsz']]
    x_testdata = testdata[variable_list]
    y_testdata = testdata[['gsz']]
    
    total_logit = testdata[['stock','gsz']].copy()
    for method in ['scale','minmax','no']:
        predict_y = get_prediction(x_traindata,y_traindata,x_testdata,standard=method)
        total_logit['predict_prob_'+method] = predict_y[:,1]
    
    
    ###########SVM部分
    traindata.ix[traindata['gsz']==0,'gsz']=-1
    testdata.ix[testdata['gsz']==0,'gsz']=-1
    x_traindata = traindata[variable_list]
    y_traindata = traindata[['gsz']]
    x_testdata = testdata[variable_list]
    y_testdata = testdata[['gsz']]

    total_SVM = testdata[['stock','gsz']].copy()
    for method in ['scale','minmax','no']:
        predict_y = get_prediction_SVM(x_traindata,y_traindata,x_testdata,standard=method)
        total_SVM['predict_prob_'+method] = predict_y[:,1]
                             
    ###合并
    columns = ['stock','gsz','predict_prob_scale','predict_prob_minmax','predict_prob_no']
    total = total_logit[columns].merge(total_SVM[['stock','predict_prob_scale','predict_prob_minmax',\
                                                  'predict_prob_no']],on=['stock'])
    for method in ['scale','minmax','no']:
        total['score_logit'] = total['predict_prob_'+method+'_x'].rank(ascending = False)
        total['score_SVM'] = total['predict_prob_'+method+'_y'].rank(ascending = False)
        total['score_' + method] = total['score_logit']+total['score_SVM']

    ###过滤今年前期已经送转过的股票
    q123_stock = q123_sz_data['stock'].tolist()
    total_filter = total.loc[total['stock'].isin(q123_stock)==False]
    
    ###过滤ST股票
    stock_list = total_filter['stock'].tolist()
    st_data = pd.DataFrame(get_extras('is_st',stock_list , start_date=date1, end_date=date2, df=True).iloc[-1,:])
    st_data.columns =['st_signal']
    st_list = st_data[st_data['st_signal']==True]
    total_filter = total_filter[total_filter['stock'].isin(st_list)==False]
    
    result_dict ={}
    for stock_num in [10,25,50,100,200]:
        accuracy_list = []
        for column in ['score_scale','score_minmax','score_no']:
            total_filter.sort(column,inplace = True,ascending = True)
            dd = total_filter[:stock_num]
            accuracy = len(dd[dd['gsz']==1])/len(dd)
            accuracy_list.append(accuracy)
        result_dict[stock_num] = accuracy_list
    
    result = pd.DataFrame(result_dict,index =['score_scale','score_minmax','score_no'])
                             
    return result



###2013年
traindata = pd.concat([gsz_2011,gsz_2012],axis=0)
testdata = gsz_2013.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2013)&(q123_already_szdata['gs']>0)]
result_2013_unite =  assess_unite_logit_SVM(traindata,testdata,variable_list,q123_sz_data,'minmax',\
                                           '2013-10-25','2013-11-01')
print (result_2013_unite)



###2014年预测结果
traindata = pd.concat([gsz_2012,gsz_2013],axis=0)
testdata = gsz_2014.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2014)&(q123_already_szdata['gs']>0)]
result_2014_unite =  assess_unite_logit_SVM(traindata,testdata,variable_list,q123_sz_data,'minmax',\
                                           '2014-10-25','2014-11-01')
print (result_2014_unite)

####2015年预测结果
traindata = pd.concat([gsz_2013,gsz_2014],axis=0)
testdata = gsz_2015.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price', 'days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2015)&(q123_already_szdata['gs']>0)]
result_2015_unite =   assess_unite_logit_SVM(traindata,testdata,variable_list,q123_sz_data,'minmax',\
                                            '2015-10-25','2015-11-01')
print (result_2015_unite)


####2016年预测结果
traindata = pd.concat([gsz_2014,gsz_2015],axis=0)
testdata = gsz_2016.copy()
variable_list = ['per_zbgj_wflr','capitalization', 'eps', 'revenue_growth',\
                   'mean_price','days_listed']
q123_sz_data =  q123_already_szdata[(q123_already_szdata['year']==2016)&(q123_already_szdata['gs']>0)]
result_2016_unite =  assess_unite_logit_SVM(traindata,testdata,variable_list,q123_sz_data,'minmax',\
                                           '2016-10-25','2016-11-01')
print (result_2016_unite)



###取出2017年数据
statDate = '2017q3'
mp_startdate = '2017-10-01' 
mp_enddate = '2017-11-01'
year = 2017 
month = 11 
day = 1

per_zbgj = get_perstock_indicator(balance.capital_reserve_fund,'capital_reserve_fund','per_CapitalReserveFund',statDate)
per_wflr = get_perstock_indicator(balance.retained_profit,'retained_profit','per_RetainedProfit',statDate)
per_jzc = get_perstock_indicator(balance.total_owner_equities,'total_owner_equities','per_TotalOwnerEquity',statDate)
other_indicator = get_other_indicator(statDate)
code_list = other_indicator['code'].tolist()
mean_price = get_bmonth_aprice(code_list,mp_startdate,mp_enddate)
cx_signal = judge_cxstock(mp_enddate)
days_listed = get_dayslisted(year,month,day)

chart_list = [per_zbgj,per_wflr,per_jzc,other_indicator,mean_price,cx_signal,days_listed]
for chart in chart_list:
    chart.set_index('code',inplace = True)

independ_vari = pd.concat([per_zbgj,per_wflr,per_jzc,other_indicator,mean_price,cx_signal,days_listed],axis = 1)
independ_vari['year'] = str(int(statDate[0:4]))
independ_vari['stock'] = independ_vari.index
independ_vari.reset_index(drop=True,inplace =True)

independ_vari['per_zbgj_wflr'] = independ_vari['per_CapitalReserveFund']+independ_vari['per_RetainedProfit']

gsz_2017 = independ_vari
gsz_2017.ix[gsz_2017['revenue_growth']>300,'revenue_growth'] = 300


traindata = pd.concat([gsz_2015,gsz_2016],axis=0)
testdata = gsz_2017
q123_sz_data = q123_already_szdata[(q123_already_szdata['year']==2017)&(q123_already_szdata['gs']>0)]
###Logit 部分
traindata.dropna(inplace = True)
testdata.dropna(inplace = True)
x_traindata = traindata[variable_list]
y_traindata = traindata[['gsz']]
x_testdata = testdata[variable_list]

    
total_logit = testdata[['stock']].copy()
method='scale'
predict_y = get_prediction(x_traindata,y_traindata,x_testdata,standard=method)
total_logit['predict_prob_'+method] = predict_y[:,1]
    
    
###########SVM部分
traindata.ix[traindata['gsz']==0,'gsz']=-1
x_traindata = traindata[variable_list]
y_traindata = traindata[['gsz']]
x_testdata = testdata[variable_list]


total_SVM = testdata[['stock']].copy()
method = 'scale'
predict_y = get_prediction_SVM(x_traindata,y_traindata,x_testdata,standard=method)
total_SVM['predict_prob_'+method] = predict_y[:,1]
                             
###合并
columns = ['stock','predict_prob_'+method]
total = total_logit[columns].merge(total_SVM[['stock','predict_prob_'+method]],on=['stock'])
                             
total['score_logit'] = total['predict_prob_'+method+'_x'].rank(ascending = False)
total['score_SVM'] = total['predict_prob_'+method+'_y'].rank(ascending = False)
total['score'] = total['score_logit']+total['score_SVM']

###过滤今年前期已经送转过的股票
q123_stock = q123_sz_data['stock'].tolist()
total_filter = total.loc[total['stock'].isin(q123_stock)==False]
###过滤ST股票
stock_list = total_filter['stock'].tolist()
st_data = pd.DataFrame(get_extras('is_st',stock_list ,start_date='2017-10-25', end_date='2017-11-01', df=True).iloc[-1,:])
st_data.columns =['st_signal']
st_list = st_data[st_data['st_signal']==True]
total_filter = total_filter[total_filter['stock'].isin(st_list)==False]



total_filter.sort('score',inplace = True,ascending = True)
total_filter.reset_index(drop=True,inplace = True)
total_filter[:50]


total_filter[:100]
