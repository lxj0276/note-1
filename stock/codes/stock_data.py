# -*- coding: utf-8 -*-
# encoding: UTF-8

import logging
import pandas as pd
import numpy as np
import stock_utils as sutils
import mysql_utils as mutils

import tushare as ts

class StockData(object):
    """得到历史数据
    """
    # create logger
    FORMATTER = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
    logging.basicConfig(filename='/var/log/python/stock.log',filemode = 'w', format=FORMATTER)
    logger = logging.getLogger("HistoryData")
    logger.setLevel(logging.DEBUG)

    def __history_data_all_stock(self, ktype='D', engine):
        """ 获取个股历史交易数据（包括均线数据）.
        可以通过参数设置获取日k线、周k线、月k线，以及5分钟、15分钟、30分钟和60分钟k线数据。
        本接口只能获取近3年的日线数据，适合搭配均线数据进行选股和分析.

        参数说明：

        code：股票代码，即6位数字代码，或者指数代码（sh=上证指数 sz=深圳成指 hs300=沪深300
            指数 sz50=上证50 zxb=中小板 cyb=创业板）
        start：开始日期，格式YYYY-MM-DD
        end：结束日期，格式YYYY-MM-DD
        ktype：数据类型，D=日k线 W=周 M=月 5=5分钟 15=15分钟 30=30分钟 60=60分钟，默认为D
        retry_count：当网络异常后重试次数，默认为3
        pause:重试时停顿秒数，默认为0
        返回值说明：

        date：日期
        open：开盘价
        high：最高价
        close：收盘价
        low：最低价
        volume：成交量
        price_change：价格变动
        p_change：涨跌幅
        ma5：5日均价
        ma10：10日均价
        ma20:20日均价
        v_ma5:5日均量
        v_ma10:10日均量
        v_ma20:20日均量
        turnover:换手率[注：指数无此项]
        """
        part_talbe_name_low = str(ktype).lower()
        s_codes = sutils.get_stock_code()
        
        # engine.execute('CREATE INDEX ix_history_data_%s_date ON history_data (date(20))'
        #                % part_talbe_name_low)
        for code in s_codes:
            logger.debug('Stock code now is: ' + code)
            
            hist_data = ts.get_hist_data(code, ktype=ktype, retry_count=10)
            len = len(hist_data.index)
            logger.debug('The history data length of code %s is: %d' % (code, len))
            hist_data['code'] = pd.Series(np.array([code] * len))
            mutils.to_sql(hist_data, table='history_data_%s' % part_talbe_name_low,
                          engine=engine)

    def history_data(self):
        self.__history_data_all_stock(ktype='D', engine)
        self.__history_data_all_stock(ktype='W', engine)
        self.__history_data_all_stock(ktype='M', engine)
        self.__history_data_all_stock(ktype='5', engine)
        self.__history_data_all_stock(ktype='15', engine)
        self.__history_data_all_stock(ktype='30', engine)
        self.__history_data_all_stock(ktype='60', engine)
        
    def real_time_data_all_stock(self):
        now_str = sutils.get_datetime_str()
        now_data = ts.get_today_all()
        now_data_len = len(now_data)
        logger.debug('The length of real time data is: %d' % now_data_len)

        now_data['time'] = pd.Series(np.array([now_str] * now_data_len))
        mutils.to_sql(now_data, table='real_time_data', engine)
        


