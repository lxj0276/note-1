#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8
'''
选择排序
介绍：
选择排序无疑是最简单直观的排序。它的工作原理如下。
步骤：
在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
以此类推，直到所有元素均排序完毕。

'''


def selectionSort(A):
    n = len(A)
    for i in range(0, n):
        min = i
        for j in range(i+1, n):
            if A[min] > A[j]:
                min = j
        A[i], A[min] = A[min],  A[i]
    return A


if __name__ == "__main__":
    ar = [10, 14, 73, 25, 23, 13, 27, 94, 33, 39, 25, 59, 94, 65, 82, 45]
    cr = [3, 1, 5, 7, 2, 4, 9, 6]
    print ar
    br = selectionSort(ar)
    print br

    print cr
    print selectionSort(cr)
