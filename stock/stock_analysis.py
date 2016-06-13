#!/usr/bin/env python
# -*- coding: utf-8 -*-
# encoding: UTF-8

"""This is test code in TuShare"""

import tushare as ts
from os import path
from sqlalchemy import create_engine

engine = create_engine("mysql://root:123456@127.0.0.1/stock?charset=utf8")
home_dir = "/home/kay/StockData"


def trade_data():
    """交易数据"""
    # 
    today_all = ts.get_today_all()
    print(today_all)


def stock_classified():
    """股票分类"""
    # 行业分类
    industry = ts.get_industry_classified()
    # 概念分类
    # concept= ts.get_concept_classified()
    # 地域分类
    area = ts.get_area_classified()
    # 中小板分类
    zxb = ts.get_sme_classified()
    # 创业板分类
    cyb = ts.get_gem_classified()
    # 风险警示板分类
    fxjsb = ts.get_st_classified()
    # 沪深300成份股及权重
    hs300 = ts.get_hs300s()
    # 上证50成份股
    sz50 =ts.get_sz50s()
    # 终止上市股票列表
    terminated = ts.get_terminated()
    # 暂停上市股票列表
    suspended = ts.get_suspended()

    industry.to_csv(path.join(home_dir,'stock_classified', 'industry_classfied.csv'), encoding='utf-8')
    # concept.to_csv(path.join(home_dir,'stock_classified', 'concept_classfied.csv'), encoding='utf-8')
    area.to_csv(path.join(home_dir,'stock_classified', 'area_classfied.csv'), encoding='utf-8')
    zxb.to_csv(path.join(home_dir,'stock_classified', 'zxb_classfied.csv'), encoding='utf-8')
    cyb.to_csv(path.join(home_dir,'stock_classified', 'cyb_classfied.csv'), encoding='utf-8')
    fxjsb.to_csv(path.join(home_dir,'stock_classified', 'fxjsb_classfied.csv'), encoding='utf-8')
    hs300.to_csv(path.join(home_dir,'stock_classified', 'hs300_classfied.csv'), encoding='utf-8')
    sz50.to_csv(path.join(home_dir,'stock_classified', 'sz50_classfied.csv'), encoding='utf-8')
    terminated.to_csv(path.join(home_dir,'stock_classified', 'terminated_classfied.csv'), encoding='utf-8')
    suspended.to_csv(path.join(home_dir,'stock_classified', 'suspended_classfied.csv'), encoding='utf-8')

    ts.get_report_data()
    ts.get_stock_basics()
    ts.sh_margins(start='2015-01-01', end='2015-04-19')
    # concept.to_sql("concept_classfied", engine, flavor='mysql')



if __name__ == '__main__':
    stock_classified()
