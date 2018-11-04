# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")



def solution(S):
    # write your code in Python 3.6
    if not S:
        return S
    while 'AA' in S or 'BB' in S or 'CC' in S:
        S = S.replace('AA', '')
        S = S.replace('BB', '')
        S = S.replace('CC', '')
        print(S)
    return S


def main():
    S = 'ABCBBCBA'
    print(solution(S))
    S = 'BABABA'
    print(solution(S))


    # r = solution([1, 3, 6, 4, 1, 2, -1])
    # print(r)


if __name__ == '__main__':
    main()
