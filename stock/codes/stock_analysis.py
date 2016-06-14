#!/usr/bin/env python
# -*- coding: utf-8 -*-
# encoding: UTF-8

"""This is test code in TuShare"""

import tushare as ts
from os import path
from sqlalchemy import create_engine
from stock_data import StockData
from stock_classified import StockClassified
import mysql_utils as mutils

class StockAnalysis(object):

    def __init__(self):
        self.engine = mutils.get_engine()


    def deal_trade_data(self):
        stock_data = StockData()
        # 获取历史数据
        stock_data.history_data()
        # 获取实时数据
        stock_data.real_time_data_all_stock()



    def deal_stock_classified(self):
        stock_classified = StockClassified()
        stockClassified.stock_classified(engine)



def main():
    sa =  StockAnalysis()
    sa.deal_trade_data()


if __name__ == '__main__':
    main()
