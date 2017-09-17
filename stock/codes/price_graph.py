#!/usr/bin/env python
# -*- coding: utf-8 -*-
# encoding: UTF-8

"""This is price trend chart using TuShare data"""

import tushare as ts
import matplotlib.pyplot as plt
import matplotlib.dates as mdate
import pandas as pd
from datetime import datetime
from matplotlib.dates import DayLocator, HourLocator, DateFormatter, drange

# ts.get_sina_dd('300053', date='2017-9-13')
pd.set_option('display.height', 1000)
pd.set_option('display.max_rows', 5000)
pd.set_option('display.max_columns', 500)
pd.set_option('display.width', 1000)
fig = plt.figure()  
ax_price = fig.add_subplot(1,1,1)
def main():
    time_price = ts.get_tick_data('300675', date='2017-09-15')
    print time_price
    ax_price.set_title(u"价格走势图")
    ax_price.set_xlabel('time')
    ax_price.set_ylabel('price')
    # time_price = time_price[['time', 'price']]
    # print time_price
    # ax_price.xaxis.set_major_formatter( DateFormatter('%H:%M:%S') )
    # ax_price.fmt_xdata = DateFormatter('%H:%M:%S')
    ax_volume = ax_price.twinx()

    time_price['time'] = pd.to_datetime(time_price['time'], format='%H:%M:%S')
    plt.gcf().autofmt_xdate()
    ax_price.plot(time_price['time'], time_price['price'])
    ax_volume.set_ylabel('volume')
    ax_volume.plot(time_price['time'], time_price['volume'], 'r')
    plt.grid(True)
    plt.show()



if __name__ == '__main__':
    main()
