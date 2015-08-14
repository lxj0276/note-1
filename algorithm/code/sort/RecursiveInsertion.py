#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8
'''
递归插入排序
'''


def recursiveInsertSort(ary, p, q):
    '''递归将P到Q排序'''
    if p < q:
        recursiveInsertSort(ary, p, q-1)
        insertSort(ary, p, q-1)
    return ary


def insertSort(ary, p, q):
    temp = ary[q]
    j = q
    while (j > 0) and (ary[j-1] > temp):
        ary[j] = ary[j-1]
        j = j - 1
    ary[j] = temp


if __name__ == "__main__":
    befArray = [3, 1, 5, 7, 2, 4, 9, 6]
    print befArray
    aftArray = recursiveInsertSort(befArray, 0, len(befArray))
    print befArray
    print aftArray
