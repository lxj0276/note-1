#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding=utf-8

# '''
# 1、正整数序列Q中的每个元素都至少能被正整数a和b中的一个整除，现给定a和b，需要计算出Q中的前几项，例如，当a=3，b=5，N=6时，序列为3，5，6，9，10，12
# 1. 设计一个函数void generate（int a,int b,int N ,int * Q）计算Q的前几项
# 2. 设计测试数据来验证函数程序在各种输入下的正确性。
# '''
# def generate(a, b, N):
#     gapA = a
#     gapB = b
#     result = []
#     while len(result) < N:
#         if a <= b:
#             result.append(a)
#             a = a + gapA
#         if a > b:
#             result.append(b)
#             b = b + gapB
#     return result

'''
2、有一个由大小写组成的字符串，现在需要对他进行修改，将其中的所有小写字母排在大写字母的前面（大写或小写字母之间不要求保持原来次序），如有可能尽量选择时间和空间效率高的算法。
'''
import re


def sortCaseWord(ar):
    lp = 0
    rp = len(ar) - 1
    while lp < rp:
        if re.match('[a-z]', ar[lp]) and lp < rp:
            lp = lp + 1
        elif re.match('[A-Z]', ar[rp]) and lp < rp:
            rp = rp - 1
        else:
            ar[lp], ar[rp] = ar[rp], ar[lp]
    return ar

if __name__ == "__main__":
    a = ['A', 'a', 'z', "M", 'k', 'w', 'G']
    print a
    print sortCaseWord(a)
    print a
