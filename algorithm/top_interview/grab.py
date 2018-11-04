# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")



def solution(A):
    # write your code in Python 3.6
    pseudo_periods = {}
    length = len(A)
    if length < 3:
        return -1
    result = 0
    diff = A[1] - A[0]
    c = 0
    for i in range(length - 1):
        if A[i+1] - A[i] == diff:
            c += 1
            pseudo_periods[i+1-c] = pseudo_periods.get(i+1-c, 0) + 1
        else:
            diff = A[i+1] - A[i]
            c = 1
            pseudo_periods[i+1-c] = pseudo_periods.get(i+1-c, 0) + 1
    for k, v in pseudo_periods.items():
        if v < 2:
            continue
        result += CnkD(v, v - 2)
    return result

from collections import defaultdict


def CnkD(n,k):
    C=defaultdict(int)
    for row in range(n+1):
        C[row,0]=1
        for col in range(1,k+1):
            if col <= row:
                C[row,col]=C[row-1,col-1]+C[row-1,col]
    print(C)
    return C[n,k]


def main():
    A = [-1, 1, 3, 3, 3, 2, 3, 2, 1, 0]
    r = solution(A)
    print(r)
    print(CnkD(5, 3))

    # r = solution([1, 3, 6, 4, 1, 2, -1])
    # print(r)


if __name__ == '__main__':
    main()
