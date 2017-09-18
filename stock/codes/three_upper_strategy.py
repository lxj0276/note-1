#!/usr/bin/env python
# -*- coding: utf-8 -*-

import tushare as ts

df_data = ts.get_hist_data('300675', start='2017-08-01', end='2017-09-18')
before_data = df_data[:-1].reset_index(drop=True)
after_data = df_data[1:].reset_index(drop=True)
sub_data = after_data - before_data
print df_data
print before_data
print after_data
print sub_data
count = 0
ascend_3 = 0
descend_3 = 0
ascend_4 = 0
descend_4 = 0
# for k, v in sub_data['ma5'].iteritems():
#     if v >= 0:
#         count += 1
#     else:
#         count = 0
#     if count >= 3:
#         if v > 0:
#             ascend_3 += 1
#             count = 0
#         else:
#             descend_3 += 1
#             count = 0

for k, v in sub_data['open'].iteritems():
    if v < 0:
        count += 1
    else:
        count = 0
    if count >= 3:
        if v < 0:
            descend_3 += 1
        else:
            ascend_3 += 1
        if count >= 4:
            if v < 0:
                descend_4 += 1
            else:
                ascend_4 += 1

percent_3 = 100
if ascend_3 != 0:
    percent_3 = float(descend_3)/ascend_3 * 100
if descend_3 == 0 and ascend_3 == 0:
    print "No three continue ascend_3."
else:
    print "ascend_3 is %d, descend_3 is %d, percent_3 is %f" % (ascend_3, descend_3, percent_3)

percent_4 = 100
if ascend_4 != 0:
    percent_4 = float(descend_4)/ascend_4 * 100
if descend_4 == 0 and ascend_4 == 0:
    print "No three continue ascend_4."
else:
    print "ascend_4 is %d, descend_4 is %d, percent_4 is %f" % (ascend_4, descend_4, percent_4)
