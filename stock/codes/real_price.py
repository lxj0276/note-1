#!/usr/bin/env python
# -*- coding: utf-8 -*-
# encoding: UTF-8

"""This is get real code in TuShare"""

import tushare as ts
import pandas as pd
import time

# ts.get_sina_dd('300053', date='2017-9-13')
pd.set_option('display.height', 1000)
pd.set_option('display.max_rows', 500)
pd.set_option('display.max_columns', 500)
pd.set_option('display.width', 1000)

while True:
    # df = ts.get_realtime_quotes(['300248', 'sh', 'sz', 'hs300', 'sz50', 'zxb', 'cyb'])
    df = ts.get_realtime_quotes('300675')
    df_part = df[['code','name','price','bid', 'b1_v','ask', 'a1_v','b2_p', 'b2_v', 'a2_p', 'a2_v','high', 'low', 'volume','amount','time']]
    # df_part = df[['code','price','bid', 'b1_v', 'ask', 'a1_v','high', 'low', 'volume','amount','time']]
    print df_part
    df_fenbi = ts.get_today_ticks('300675')[-5:]
    print df_fenbi
    time.sleep(0.5)
