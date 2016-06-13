#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8

from sklearn import linear_model

# # linear regression
# clf = linear_model.LinearRegression()
# clf.fit([[0, 0], [1, 1], [2, 2]], [0, 1, 2])
# print(clf.coef_)


# # ridge regression
# clf = linear_model.RidgeCV(alphas=[0.1, 1.0, 10.0])
# clf.fit([[0, 0], [0, 0], [1, 1]], [0, .1, 1])
# print(clf.alpha_)
# print(clf.alphas)
# print(clf.coef_)


# # laso regression
# clf = linear_model.Lasso(alpha=0.1)
# clf.fit([[0,0], [1, 1]], [0, 1])
# print(clf.coef_)
# print(clf.predict([[1, 1]]))


# # lasso lars
# clf = linear_model.LassoLars(alpha=.1)
# clf.fit([[0, 0], [1, 1]], [0, 1])
# print(clf.coef_)


# bayesian ridge regression
X = [[0., 0.], [1., 1.], [2., 2.], [3., 3.]]
Y = [0., 1. ,2., 3.]
clf = linear_model.BayesianRidge()
clf.fit(X, Y)
print(clf.predict([[1, 0.]]))
