stocks = get_fundamentals(query(valuation.code))['code']

#均线纠缠，BRG三线非常接近
def is_struggle(stock,hist,delta):
    blue_line = hist[stock][-21-delta:-8-delta].mean()
    red_line = hist[stock][-13-delta:-5-delta].mean()
    green_line = hist[stock][-8-delta:-3-delta].mean()
    if abs(blue_line/red_line-1)<0.02 and abs(red_line/green_line-1)<0.02:
        return True
    else:
        return False


#睡着的鳄鱼
def is_sleeping_alligator(stock,hist,nday):
    for i in range(nday):
        if is_struggle(stock,hist,i) == False:
            return False
    return True


#判断 向上 或 向下 碎形
def is_fractal(stock, hist, direction):
    if direction == 'high'\
    and hist[stock][-3] > hist[stock][-1]\
    and hist[stock][-3] > hist[stock][-2]\
    and hist[stock][-3] > hist[stock][-4]\
    and hist[stock][-3] > hist[stock][-5]:
        return True
    elif direction == 'low'\
    and hist[stock][-3] < hist[stock][-1]\
    and hist[stock][-3] < hist[stock][-2]\
    and hist[stock][-3] < hist[stock][-4]\
    and hist[stock][-3] < hist[stock][-5]:
        return True
    return False

#通过比较碎形与红线位置，判断碎形是否有效
def is_effective_fractal(stock, hist direction):
    if is_fractal(stock, hist, direction):
        red_line = hist[stock][-5:].mean()
        close_price = hist[stock][-1]
        if direction == 'high':
            if close_price > red_line:
                return True
            else:
                return False
        elif direction == 'low':
            if close_price < red_line:
                return True
            else:
                return False
    else:
        return False


struggle_stocks = []
hist_datas = history(40,'1d','close',stocks,df = False)
for stock in stocks:
    if is_sleeping_alligator(stock, hist_datas, 20):
        struggle_stocks.append(stock)


print(struggle_stocks)
