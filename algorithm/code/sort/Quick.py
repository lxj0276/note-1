#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8
'''
快速排序
介绍：
它是由冒泡排序改进而来的。
快速排序通常明显比同为Ο(n log n)的其他算法更快，因此常被采用，而且快排采用了分治法的思想，所以在很多笔试面试中能经常看到快排的影子。可见掌握快排的重要性。
步骤：
从数列中挑出一个元素作为基准数。
分区过程，将比基准数大的放到右边，小于或等于它的数都放到左边。
再对左右区间递归执行第二步，直至各区间只有一个数。

* 算法复杂度
      最好的情况下：因为每次都将序列分为两个部分(一般二分都复杂度都和logN相关)，故为 O(N*logN)
      最坏的情况下：基本有序时，退化为冒泡排序，几乎要比较N*N次，故为O(N*N)
* 稳定性
      由于每次都需要和中轴元素交换，因此原来的顺序就可能被打乱。如序列为 5 3 3 4 3 8 9 10 11会将3的顺序打乱。所以说，快速排序是不稳定的！
'''


def quickSort(A):
    return quick(A, 0, len(A) - 1)

def quick(B, left, right):
    # 快排函数，ary为待排序数组，left为待排序的左边界，right为右边界
    if left >= right:
        return B
    key = B[left]  # 取最左边的为基准
    lp = left  # 左指针
    rp = right  # 右指针
    while lp < rp:  # 从两边交替向中间扫描，直到lp=rp为止
        while B[rp] >= key and lp < rp:  # 必须从右边开始查找小于key的值，否则错误
            rp = rp - 1
        while B[lp] <= key and lp < rp:
            lp = lp + 1
        B[lp], B[rp] = B[rp], B[lp]

    B[left], B[lp] = B[lp], B[left]
    quick(B, left, lp-1)
    quick(B, rp+1, right)
    return B


if __name__ == "__main__":
    ar = [10, 14, 73, 23, 13, 27, 94, 33, 39, 25, 59, 94, 65, 82, 45]
    cr = [3, 1, 5, 7, 2, 4, 9, 6]
    print ar
    br = quickSort(ar)
    print br

    print cr
    print(cr)
    print quickSort(cr)

    print(quick2(cr))
