import tushare as ts
from os import path
from datetime import datetime
from dateutil import *

def get_stock_code():
    """
    得到股票代码.

    return:
        s_codes: list
    """
    stock = ts.get_stock_basics()
    s_codes = stock.index.tolist()
    return s_codes


def get_datetime_str():
    """返回当前时间

    return:
        字符串形式. eg:
        '2016-06-14 15:14:38'
    """
    now = datetime.now()
    return now.strftime('%Y-%m-%d %H:%M:%S')
