# 导入函数库
import jqdata

#### 配置参数  单票最多持有天数 超过清盘
MAX_HOLD_DAY = 10
#### 配置参数  买入单只股票,最大使用可用资金的几分之1?
CASH_SP_COUNT = 2
#### 配置参数  每天开仓买入,最大使用多少可用资金额
CASH_MAX_USE  = 500000000
#### 配置参数  每只股票买入最大使用多少资金额?
CASH_MAX_USE_PERSTOCK = 50000000
#### 止损比例  0.96 = -4%   0.9 = -10%  etc
CLOSE_RATIO_ON_LOSE = 0.96
#### 止盈比例  1.07 = +7%   1.1 = +10%  etc
CLOSE_RATIO_ON_WIN = 1.20


########## 检测股票是否可进入被选  主函数 return True / False
def IsTarget(context, stockcode, todayopenprice):
    ### 判断函数主体
    df   = attribute_history( stockcode,60, unit='1d',   fields=['close'], df=False)
    dftd = attribute_history( stockcode, g.minbarcount, unit='1m',   fields=['close'], df=False)
    dfh   = attribute_history(stockcode, 200, unit='1d',   fields=['open','high','low','close','volume','money'], df=False)
    dfhtd = attribute_history(stockcode, 234, unit='1m',   fields=['open','high','low','close','volume','money'], df=False)

    ma5  = df['close'][-5:].mean()
    ma10 = df['close'][-10:].mean()
    ma60 = df['close'][-30:].mean()
    #获取昨日均线值
    ydma5  = df['close'][-5:].mean()
    ydma10 = df['close'][-10:].mean()
    ydma30 = df['close'][-30:].mean()
    ydma60 = df['close'][-60:].mean()
    #获取前天30日均线值
    qdma30 = df['close'][-31:-1:].mean()
    qdma10 = df['close'][-11:-1:].mean()
    #获取今日均线值
    tdma5   = (df['close'][-4:].sum() + dftd['close'][-1])/5
    tdma10  = (df['close'][-9:].sum() + dftd['close'][-1])/10
    tdma30   = (df['close'][-29:].sum() + dftd['close'][-1])/30
    tdma60  = (df['close'][-59:].sum() + dftd['close'][-1])/60
    #判断昨日均线情况，昨日ma5最低
    ydtj = False
    if (ydma5 - ydma10) <0 and (ydma5 - ydma30)<0:
        ydtj = True
    #判断今日均线情况，今日ma5最高，也就是形成ma5上穿
    tdtj = False
    if (tdma5 - tdma10)>0 and (tdma5 - tdma30)>0:
        tdtj = True
    #增加判断条件，在均线粘合当日买入
    jxnh = False
    if (tdma5 - max(tdma10,tdma30))/(ydma5 - max(ydma10,ydma30)) <0.3:
        jxnh = True
    #排除平行均线可能
    jxpx = False
    px_30 = False
    px_10 = False
    if tdma10 / ydma10 <= 1.001 and tdma10 / ydma10>0.999:
        px_10 = True
    if tdma30 / ydma30 <= 1.001 and tdma30 / ydma30>0.999:
        px_30 = True
    if px_30 or px_10:
        jxpx = True
        #continue
    #增加当日成交量判断，如果当日成交量过大（成交量高于之前出现最高价当天的成交量）
    Vol_avg = dfh['volume'][-30:-1:].sum()/30
    ydvol = dfh['volume'][-1]
    vmax  = dfh['volume'][-30:].max()
    tdvol = dfhtd['volume'].sum()
    #print('Vol_avg',Vol_avg,' tdvol',tdvol)
    vol_tj = False
    if tdvol < Vol_avg * 1.75  :#and tdvol >Vol_avg * 0.98
        vol_tj = True
    #增加30日均线向上条件
    tj_ma30 = False
    if tdma30 > ydma30 and ydma30 >qdma30:
        tj_ma30 = True
    #增加30日均线向上条件
    tj_ma10 = False
    if tdma10 > ydma10 and ydma10 >qdma10:
        tj_ma10 = True
    #收阳，并且实体柱比例大于75%
    #获取最高价、最低价
    tdmax   = dfhtd['high'].max()
    tdlow   = dfhtd['low'].min()
    tdopen  = dfhtd['open'][0]
    tdclose = dfhtd['close'][-1]
    yxst = (tdclose-tdopen)/(tdmax-tdlow)
    yxtj = False
    if tdclose > tdopen :#and yxst >= 0.75
        yxtj = True
    #增加极大量排除
    ydtj_maxv = False
    if ydvol>Vol_avg*1.5 and ydvol>=vmax:
        ydtj_maxv = True
    tdtj_vmax = False
    if tdvol>Vol_avg *1.5 and tdvol >= vmax:
        tdtj_vmax = True
    if ydtj and tdtj and jxnh and vol_tj and tj_ma30 and yxtj and tj_ma10 and jxpx==False:
        if ydtj_maxv or tdtj_vmax:
            return False
        else:
            return True    
    
    return False


# 初始化函数，设定基准等等
def initialize(context):
    set_benchmark('000300.XSHG')
    set_option('use_real_price', True)
    log.set_level('order', 'error')
    set_slippage(PriceRelatedSlippage(0.004))
    set_order_cost(OrderCost(close_tax=0.001, open_commission=0.00025, close_commission=0.00025, min_commission=5), type='stock')
    run_daily(before_market_open, time='before_open', reference_security='000300.XSHG') 
    run_daily(after_market_close, time='after_close', reference_security='000300.XSHG')
    g.holdday = {}


def MakeMACD(pricelist):
    #计算MACD
    DIF=[]
    DEA=[]
    MACD=[]
    price =pricelist
    for index in range(0,len(price)):
        pnow = price[index]
        if index==0:
            ema12 = pnow
            ema26 = pnow
        ema12= ema12*11/13 + pnow*2/13
        ema26= ema26*25/27 + pnow*2/27
        difv = ema12-ema26 
        if index==0:
            deav=difv
        deav=deav*8/10 + difv*2/10
        mcadv =(difv-deav)*2 
        
        DIF.append(difv)
        DEA.append(deav)
        MACD.append(mcadv)
    return DIF,DEA,MACD


######## 扫描今天的股票保存在 tdbuy
def ScanTodaySignal(context):
    
    g.tdbuy = []

    ##主函数需要完成
    current_data = get_current_data()
    codelist = get_index_stocks('000906.XSHG' )
    
    for code in  codelist:
        if  current_data[code].paused  or code in context.portfolio.positions:
            continue
        
        if IsTarget(context, code, current_data[code].day_open):
            g.tdbuy.append(code)
            
    
    log.info('今日选出 ',g.tdbuy)
    
            
    return



########## 累计计算各仓位的连续持有日
def CountPositionHoldDay(context):
        
    for code in  context.portfolio.positions :
        if code in g.holdday.keys():
            g.holdday[code]+=1
        else:
            g.holdday[code]=1
            
    coderec = g.holdday.keys()
    for code in coderec:
        if not code in context.portfolio.positions:
            del(g.holdday[code])

    return
    

    
########## 开盘前运行函数 ，初始化数据
def before_market_open(context):
    
    CountPositionHoldDay(context)
    g.minbarcount = 0
    g.tdsell      = 0
    g.tdbuy       = []
    
    
########### 盘后绘制持仓数，卖出数
def after_market_close(context):
    record (pc=GetCurrentPositionCount(context))
    record (sellcount=-g.tdsell  )
    
    
########  获得持仓票数
def GetCurrentPositionCount(context):
    c=0
    for code in  context.portfolio.positions:
        if context.portfolio.positions[code].total_amount>0:
            c+=1        
    return c

########## 检测股票是否可进入被选  主函数 return True / False
def IsSellTarget(context, stockcode):
    ### 判断函数主体
    dfh   = attribute_history(stockcode, 200, unit='1d',   fields=['open','high','low','close','volume','money'], df=False)
    dfhtd = attribute_history(stockcode, 234, unit='1m',   fields=['open','high','low','close','volume','money'], df=False)
    #增加当日成交量判断，如果当日成交量过大（成交量高于之前出现最高价当天的成交量）
    Vol_avg = dfh['volume'][-30:-1:].sum()/30
    ydvol = dfh['volume'][-1]
    vmax  = dfh['volume'][-30:].max()*1.75
    tdvol = dfhtd['volume'].sum()
    #增加极大量排除
    ydtj_maxv = False
    if ydvol>Vol_avg*1.5 and ydvol>=vmax:
        ydtj_maxv = True
    tdtj_vmax = False
    if tdvol>Vol_avg *1.5 and tdvol >= vmax:
        tdtj_vmax = True
    if  tdtj_vmax:#ydtj_maxv or
        return True
    return False
    

########  自动止盈损 或者 强制清仓
def AutoClose(context, closeall=False):

    current_data = get_current_data()

    for code in context.portfolio.positions:
        if not current_data[code].paused and context.portfolio.positions[code].closeable_amount>0:
            cost = context.portfolio.positions[code].avg_cost
            nowp = current_data[code].last_price
            if nowp>cost*CLOSE_RATIO_ON_WIN or nowp<cost*CLOSE_RATIO_ON_LOSE:
                order_target(code,0)
                g.tdsell+=1
                log.info('今日止损止盈条件卖出：',code)
            elif IsSellTarget(context,code):
                order_target(code,0)
                g.tdsell+=1
                log.info('今日极大量条件卖出：',code)
            elif closeall:
                if g.holdday[code]>=MAX_HOLD_DAY:
                    order_target(code,0)
                    g.tdsell+=1

                
######## 买入今日股票
def OpenToday(context,data):

    #资金分配策略 均分当日择出股票,最大分几份
    buylist = g.tdbuy
    if len(buylist)>0:
        subcash = min(CASH_MAX_USE,context.portfolio.available_cash)/max(len(buylist),CASH_SP_COUNT)
    else:
        subcash = 0

    codelist=get_index_stocks('000906.XSHG')
        
    for code in buylist:
        usecash = min(subcash,CASH_MAX_USE_PERSTOCK)
        buycount = usecash//( data[code].close*100) * 100
        if buycount > 100:
            order_target(code, buycount)  
    
    return


######## 每分钟调用
def handle_data(context, data):
    
    xtime = context.current_dt
    current_data = get_current_data()
    g.minbarcount = g.minbarcount +1
    
    #平时自动止盈损
    AutoClose(context)
    
    #14：54 扫描获得今日信号
    if xtime.hour == 14 and xtime.minute==54:
        ScanTodaySignal(context)
        
    #14：54 清仓
    if xtime.hour == 14 and xtime.minute ==54:
        AutoClose(context,closeall=True)

    #14:55买入今日票
    if xtime.hour == 14 and xtime.minute == 55:
        OpenToday(context,data)
        
        

   
    
    
    
