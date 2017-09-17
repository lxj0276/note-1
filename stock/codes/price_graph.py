#!/usr/bin/env python
# -*- coding: utf-8 -*-
# encoding: UTF-8

"""This is price trend chart using TuShare data"""

import tushare as ts
import matplotlib.pyplot as plt
import matplotlib.dates as mdate
import pandas as pd
from matplotlib.dates import DayLocator, HourLocator, DateFormatter, drange

fig = plt.figure()  
ax = fig.add_subplot(1,1,1)
def main():
    time_price = ts.get_tick_data('300675', date='2017-09-12')
    print time_price
    ax.set_title(u"价格走势图")
    ax.set_xlabel('time')
    ax.set_ylabel('price')
    # time_price = time_price[['time', 'price']]
    # print time_price
    # ax.xaxis.set_major_formatter( DateFormatter('%H:%M:%S') )
    # ax.fmt_xdata = DateFormatter('%H:%M:%S')
    
    time_price['time'] = pd.to_datetime(time_price['time'], format='%H:%M:%S')
    plt.gcf().autofmt_xdate()
    ax.plot(time_price['time'], time_price['price'])
    plt.grid(True)
    plt.show()



if __name__ == '__main__':
    main()
