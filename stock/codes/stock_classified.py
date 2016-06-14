# -*- coding: utf-8 -*-
# encoding: UTF-8

import logging
import pandas as pd
import numpy as np

class StockClassified(object):

    def __init__(self):
        self.home_dir = "/home/kay/StockData"

    """得到交易分类
    """
    # create logger
    FORMATTER = '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
    logging.basicConfig(filename='/var/log/python/stockClassified.log',filemode = 'w', format=FORMATTER)
    logger = logging.getLogger("HistoryData")
    logger.setLevel(logging.DEBUG)

    def stock_classified(self, engine):
        """股票分类"""
        # 行业分类
        # industry = ts.get_industry_classified()
        # # 概念分类
        # concept= ts.get_concept_classified()
        # # 地域分类
        # area = ts.get_area_classified()
        # # 中小板分类
        # zxb = ts.get_sme_classified()
        # # 创业板分类
        # cyb = ts.get_gem_classified()
        # # 风险警示板分类
        # fxjsb = ts.get_st_classified()
        # 沪深300成份股及权重
        hs300 = ts.get_hs300s()
        # 上证50成份股
        sz50 =ts.get_sz50s()
        # 终止上市股票列表
        terminated = ts.get_terminated()
        # 暂停上市股票列表
        suspended = ts.get_suspended()

        # industry.to_csv(path.join(home_dir,'stock_classified', 'industry_classfied.csv'), encoding='utf-8')
        # concept.to_csv(path.join(home_dir,'stock_classified', 'concept_classfied.csv'), encoding='utf-8')
        # area.to_csv(path.join(home_dir,'stock_classified', 'area_classfied.csv'), encoding='utf-8')
        # zxb.to_csv(path.join(home_dir,'stock_classified', 'zxb_classfied.csv'), encoding='utf-8')
        # cyb.to_csv(path.join(home_dir,'stock_classified', 'cyb_classfied.csv'), encoding='utf-8')
        # fxjsb.to_csv(path.join(home_dir,'stock_classified', 'fxjsb_classfied.csv'), encoding='utf-8')
        # hs300.to_csv(path.join(home_dir,'stock_classified', 'hs300_classfied.csv'), encoding='utf-8')
        # sz50.to_csv(path.join(home_dir,'stock_classified', 'sz50_classfied.csv'), encoding='utf-8')
        # terminated.to_csv(path.join(home_dir,'stock_classified', 'terminated_classfied.csv'), encoding='utf-8')
        # suspended.to_csv(path.join(home_dir,'stock_classified', 'suspended_classfied.csv'), encoding='utf-8')

        # ts.get_report_data()
        # ts.get_stock_basics()
        # ts.sh_margins(start='2015-01-01', end='2015-04-19')

        # concept.to_sql('concept_classfied', engine, flavor='mysql')
        # area.to_sql('area_classfied', engine, flavor='mysql')
        # zxb.to_sql('zxb_classfied', engine, flavor='mysql')
        # cyb.to_sql('cyb_classfied', engine, flavor='mysql')
        # fxjsb.to_sql('fxjsb_classfied', engine, flavor='mysql')
        hs300.to_sql('hs300_classfied', engine, flavor='mysql')
        sz50.to_sql('sz50_classfied', engine, flavor='mysql')
        terminated.to_sql('terminated_classfied', engine, flavor='mysql')
        suspended.to_sql('suspended_classfied', engine, flavor='mysql')
