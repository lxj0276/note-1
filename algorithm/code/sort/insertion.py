#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8
'''

插入排序 (stable sort, In-place sort(不占用额外内存))

介绍：
mark first element as sorted
for each unsorted element X
  'extract' the element X
  for j = lastSortedIndex down to 0
    if current element j > X
      move sorted element to the right by 1
    break loop and insert X here

插入排序的工作原理是，对于每个未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
步骤：
1.从第一个元素开始，该元素可以认为已经被排序
2.取出下一个元素，在已经排序的元素序列中从后向前扫描
3.如果被扫描的元素（已排序）大于新元素，将该元素后移一位
4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
5.将新元素插入到该位置后
重复步骤2~5

* 算法时间复杂度。  
        最好的情况下：正序有序(从小到大)，这样只需要比较n次，不需要移动。因此时间复杂度为O(n)  
        最坏的情况下：逆序有序,这样每一个元素就需要比较n次，共有n个元素，因此实际复杂度为O(n­2)  
        平均情况下：O(n­2)
* 稳定性。  
     理解性记忆比死记硬背要好。因此，我们来分析下。稳定性，就是有两个相同的元素，排序先后的相对位置是否变化，主要用在排序时有多个排序规则的情况下。在插入排序中，K1是已排序部分中的元素，当K2和K1比较时，直接插到K1的后面(没有必要插到K1的前面，这样做还需要移动！！)，因此，插入排序是稳定的。
'''

def sort(array):
    """ insert sort"""
    length = len(array)
    for i in range(1, length):
        j = i - 1
        while  j >= 0 and array[i] < array[j]:
            array[i], array[j] = array[j], array[i]
            j = j - 1
            i = i - 1
            print i, j

    return array


def sort2(array):
    """ insert sort"""
    length = len(array)
    for i in range(1, length):
        j = i - 1
        temp = array[i]
        while  j >= 0 and temp < array[j]:
            array[j+1] = array[j]
            j = j - 1
        array[j+1] = temp

    return array


def insertSort(ary):
    n = len(ary)
    for i in range(1, n):
        if ary[i] < ary[i-1]:
            temp = ary[i]
            index = i
            for j in range(i, 0, -1):
                if ary[j-1] > temp:
                    ary[j] = ary[j-1]
                    index = j-1
                else:
                    break
            ary[index] = temp
    return ary


if __name__ == "__main__":
    bef_array = [3, 1, 5, 7, 2, 4, 9, 6]
    print (bef_array)
    aft_array = insertSort(bef_array)
    print (aft_array)
