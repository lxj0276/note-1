#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8
'''
堆排序
介绍：
堆排序在 top K 问题中使用比较频繁。堆排序是采用二叉堆的数据结构来实现的，虽然实质上还是一维数组。二叉堆是一个近似完全二叉树 。
二叉堆具有以下性质：
父节点的键值总是大于或等于（小于或等于）任何一个子节点的键值。
每个节点的左右子树都是一个二叉堆（都是最大堆或最小堆）。
步骤：
构造最大堆（Build_Max_Heap）：若数组下标范围为0~n，考虑到单独一个元素是大根堆，则从下标n/2开始的元素均为大根堆。于是只要从n/2-1开始，向前依次构造大根堆，这样就能保证，构造到某个节点时，它的左右子树都已经是大根堆。
堆排序（HeapSort）：由于堆是用数组模拟的。得到一个大根堆后，数组内部并不是有序的。因此需要将堆化数组有序化。思想是移除根节点，并做最大堆调整的递归运算。第一次将heap[0]与heap[n-1]交换，再对heap[0...n-2]做最大堆调整。第二次将heap[0]与heap[n-2]交换，再对heap[0...n-3]做最大堆调整。重复该操作直至heap[0]和heap[1]交换。由于每次都是将最大的数并入到后面的有序区间，故操作完后整个数组就是有序的了。
最大堆调整（Max_Heapify）：该方法是提供给上述两个过程调用的。目的是将堆的末端子节点作调整，使得子节点永远小于父节点 。

* 算法复杂度
         最坏情况下，接近于最差情况下：O(N*logN)，因此它是一种效果不错的排序算法。
* 稳定性
         堆排序需要不断地调整堆，因此它是一种不稳定的排序！
'''


def heapSort(A):
    n = len(A)
    first = int(n/2-1)  # 最后一个非叶子节点
    for start in range(first, -1, -1):
        maxHeapify(A, start, n-1)   # 构造大根堆
    for end in range(n-1, 0, -1):
        A[end], A[0] = A[0], A[end]
        maxHeapify(A, 0, end-1)  # 堆排，将大根堆转换成有序数组。 选出最大的倒排
    return A


def maxHeapify(B, start, end):
    """
    最大堆调整：将堆的末端子节点作调整，使得子节点永远小于父节点
    start为当前需要调整最大堆的位置，end为调整边界
    """
    root = start
    while True:
        child = root*2 + 1  # 调整节点的子节点
        if child > end:
            break
        if child+1 < end and B[child] < B[child+1]:
            child = child + 1  # 取较大的子节点
        if B[root] < B[child]:  # 较大的子节点成为父节点
            B[root], B[child] = B[child], B[root]
            root = child
        else:
            break


if __name__ == "__main__":
    ar = [10, 14, 73, 23, 13, 27, 94, 33, 39, 25, 59, 94, 65, 82, 45]
    cr = [3, 1, 5, 7, 2, 4, 9, 6]
    print(cr)
    print(heapSort(cr))
    print (ar)
    br = heapSort(ar)
    print (br)
