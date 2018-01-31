# 导入聚宽函数库
import jqdata
# 导入alpha191 因子函数库
from jqlib.alpha191 import *

# 初始化函数，设定基准等等
def initialize(context):
    # 设定沪深300作为基准
    set_benchmark('000300.XSHG')
    # 开启动态复权模式(真实价格)
    set_option('use_real_price', True)
    # 输出内容到日志 log.info()
    log.info('初始函数开始运行且全局只运行一次')
    g.i = 0 
    
    ### 股票相关设定 ###
    # 股票类每笔交易时的手续费是：买入时佣金万分之三，卖出时佣金万分之三加千分之一印花税, 每笔交易佣金最低扣5块钱
    set_order_cost(OrderCost(close_tax=0.001, open_commission=0.0003, close_commission=0.0003, min_commission=5), type='stock')
    
    ## 运行函数（reference_security为运行时间的参考标的；传入的标的只做种类区分，因此传入'000300.XSHG'或'510300.XSHG'是一样的）
      # 开盘前运行
    run_daily(before_market_open, time='before_open')
      # 开盘时运行
    run_daily(market_open, time='open')

## 开盘前运行函数     
def before_market_open(context):
    if g.i%8 == 0:
        # 输出运行时间
        log.info('函数运行时间(before_market_open)：'+str(context.current_dt.time()))
        current_security = context.portfolio.positions.keys()
        current_date =  context.previous_date
        
        codes = get_index_stocks('000300.XSHG')
        
        # 获取沪深300股票成分股的alpha_011因子值，剔除NaN值后按照因子值做升序排列
        alpha_stocks = alpha_011(codes,current_date).dropna().order(ascending=True)
        alpha_head = alpha_stocks.head(10).index
        # alpha_head = alpha_stocks.tail(10).index
        # 输出因子值最高的前5只股票代码及其因子值
        log.info('\n',alpha_stocks.head(10))
        
        g.stocks_to_buy = list(set(alpha_head)-set(current_security))
        g.stocks_to_sell = list(set(current_security)-set(alpha_head))
        g.i += 1
    else:
        g.stocks_to_buy = []
        g.stocks_to_sell = []
        g.i += 1

## 开盘时运行函数
def market_open(context):
    
    for stock in g.stocks_to_sell:
        order_target(stock,0)
        log.info("卖出 %s" % (stock))
    
    try:
        g.cash = context.portfolio.available_cash/len(g.stocks_to_buy)
    except:
        g.cash = 0
    
    for stock in g.stocks_to_buy:
        order_value(stock, g.cash)
        log.info("买入 %s" % (stock))


