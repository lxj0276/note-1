#!/usr/bin/env python
# -*- coding: utf-8 -*-
# encoding: UTF-8

"""This is price trend chart using TuShare data"""

import tushare as ts
import matplotlib.pyplot as plt
import matplotlib.dates as mdate
import pandas as pd
from datetime import datetime
import os
from matplotlib.dates import DayLocator, HourLocator, DateFormatter, drange

# ts.get_sina_dd('300053', date='2017-9-13')
# pd.set_option('display.height', 1000)
# pd.set_option('display.max_rows', 5000)
# pd.set_option('display.max_columns', 500)
# pd.set_option('display.width', 1000)

# output_path = "~/Pictures/stock"

def plot_gragh(time_price, title):
    fig = plt.figure()  
    ax_price = fig.add_subplot(2,2,1)
    print time_price
    ax_price.set_title(title)
    ax_price.set_xlabel('time')
    ax_price.set_ylabel('price')
    # time_price = time_price[['time', 'price']]
    # print time_price
    # ax_price.xaxis.set_major_formatter( DateFormatter('%H:%M:%S') )
    # ax_price.fmt_xdata = DateFormatter('%H:%M:%S')
    ax_volume = ax_price.twinx()

    time_price['time'] = pd.to_datetime(time_price['time'], format='%H:%M:%S')
    
    amount = 0
    p = []
    volume = 0
    for k, v in time_price['amount'].iteritems():
        amount += v
        volume += time_price['volume'][k] 
        p.append(float(amount) / (volume * 100))

    print p
    ax_price.plot(time_price['time'], p, color='c', lw=2, label='MA')
    plt.gcf().autofmt_xdate()
    ax_price.plot(time_price['time'], time_price['price'])
    ax_volume.set_ylabel('volume')
    ax_volume.plot(time_price['time'], time_price['volume'], 'r')
    plt.grid(True)
    file = ('%s.png' % title)
    print file
    # open(file,"w+").close()
    plt.savefig(file)
    # plt.show()

def main():
    dates = ['2017-09-15', '2017-09-14', '2017-09-13', '2017-09-12', '2017-09-11', '2017-09-08','2017-09-07' , '2017-09-06']
    stocks = ['300675', '300682', '600846']
    for date in dates:
        for stock in stocks:
            time_price = ts.get_tick_data(stock, date=date).iloc[::-1].reset_index(drop=True)
            plot_gragh(time_price, stock + '_' + date)

    # time_price = ts.get_tick_data('300675', date=).iloc[::-1].reset_index(drop=True)
    # plot_gragh(time_price, '2017-09-15')
    # time_price = ts.get_tick_data('300675', date='2017-09-14').iloc[::-1].reset_index(drop=True)
    # plot_gragh(time_price, '2017-09-14')



if __name__ == '__main__':
    main()
