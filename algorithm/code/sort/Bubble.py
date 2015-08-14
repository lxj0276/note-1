#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8
'''
冒泡排序
介绍：
冒泡排序的原理非常简单，它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
步骤：
比较相邻的元素。如果第一个比第二个大，就交换他们两个。
对第0个到第n-1个数据做同样的工作。这时，最大的数就“浮”到了数组最后的位置上。
针对所有的元素重复以上的步骤，除了最后一个。
持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。

* 时间复杂度
     最好情况下：正序有序，则只需要比较n次。故，为O(n)
      最坏情况下:  逆序有序，则需要比较(n-1)+(n-2)+……+1，故，为O(N*N)
* 稳定性
      排序过程中只交换相邻两个元素的位置。因此，当两个数相等时，是没必要交换两个数的位置的。所以，它们的相对位置并没有改变，冒泡排序算法是稳定的！
'''


def bubbleSort(A):
    n = len(A)
    for i in range(n):
        for j in range(1, n - i):  # 如果前者比后者大，交换数据
            if A[j-1] > A[j]:
                A[j-1], A[j] = A[j], A[j-1]
    return A


if __name__ == "__main__":
    befArray = [3, 1, 5, 7, 2, 4, 9, 6]
    print befArray
    aftArray = bubbleSort(befArray)
    print befArray
    print aftArray
