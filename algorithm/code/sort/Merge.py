#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8
'''
归并排序
介绍：
归并排序是采用分治法的一个非常典型的应用。归并排序的思想就是先递归分解数组，再合并数组。
先考虑合并两个有序数组，基本思路是比较两个数组的最前面的数，谁小就先取谁，取了后相应的指针就往后移一位。然后再比较，直至一个数组为空，最后把另一个数组的剩余部分复制过来即可。
再考虑递归分解，基本思路是将数组分解成left和right，如果这两个数组内部数据是有序的，那么就可以用上面合并数组的方法将这两个数组合并排序。如何让这两个数组内部是有序的？可以再二分，直至分解出的小组只含有一个元素时为止，此时认为该小组内部已有序。然后合并排序相邻二个小组即可。

* 算法时间复杂度
          最好的情况下：一趟归并需要n次，总共需要logN次，因此为O(N*logN)
          最坏的情况下，接近于平均情况下，为O(N*logN)
          说明：对长度为n的文件，需进行logN 趟二路归并，每趟归并的时间为O(n)，故其时间复杂度无论是在最好情况下还是在最坏情况下均是O(nlgn)。
* 稳定性
         归并排序最大的特色就是它是一种稳定的排序算法。归并过程中是不会改变元素的相对位置的。
* 缺点是，它需要O(n)的额外空间。但是很适合于多链表排序。
'''


def mergeSort(A):
    if len(A) <= 1:
        return A
    num = int(len(A)/2)  # 二分分解
    left = mergeSort(A[:num])
    right = mergeSort(A[num:])
    return merge(left, right)


def merge(left, right):
    '''将两个有序序列left,right 合并成一个较大的序列'''
    result = []
    l, r = 0, 0
    while l < len(left) and r < len(right):
        if left[l] < right[r]:
            result.append(left[l])
            l = l + 1
        else:
            result.append(right[r])
            r = r + 1
    '''why?'''
    result = result + left[l:]
    result = result + right[r:]
    return result


if __name__ == "__main__":
    ar = [10, 14, 73, 23, 13, 27, 94, 33, 39, 25, 59, 94, 65, 82, 45]
    cr = [3, 1, 5, 7, 2, 4, 9, 6]
    print ar
    br = mergeSort(ar)
    print br
    print ar

    print cr
    print mergeSort(cr)
