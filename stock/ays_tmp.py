import pandas as pd
from pandas import DataFrame as df
import tushare as ts
import numpy as np

np.sort()


data = ts.get_hist_data('600848')

data3 = data[:3]

data23 = pd.concat([data3, data3])


data3.index

def f(x):
    two_ops = []
    factor = 0
    for i in range(len(x)):
        print(x)
        factor += x[i]
        two_ops.append(factor)
        print(factor)
    print(two_ops)
    return two_ops

data_open = data23['open']

data3.apply(f)

data23.groupby('date').apply(f)

test = pd.DataFrame({'key1':['a', 'a', 'b', 'b', 'a'],
    'key2':['one', 'two', 'one', 'two', 'one'],
    'data1':np.random.randn(5),
    'data2':np.random.randn(5)})


def my_test(df):
    cum_diff = 0
    for ix in df.index:
        tmp = df[ix]
        df[ix] += cum_diff
        cum_diff += tmp
