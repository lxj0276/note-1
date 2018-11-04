def solution(T):
    # write your code in Python 3.6
    conn_map = {}
    cap = 0
    length = len(T) - 1
    result = [0] * length
    for i, v in enumerate(T):
        if v == i:
            cap = i
        else:
            if v in conn_map:
                conn_list = conn_map.get(v)
                conn_list.append(i)
                conn_map[v] = conn_list
            else:
                conn_list = [i]
                conn_map[v] = conn_list
    index = 0
    cur_conn_list = []
    for i in conn_map[cap]:
        cur_conn_list.append(i)
    flag = True
    while flag:
        flag = False
        for i in cur_conn_list:
            if i in conn_map:
                flag = True
        next_conn_list = []
        for i in cur_conn_list:
            if i in conn_map:
                for c in conn_map[i]:
                    next_conn_list.append(c)
            result[index] += 1
        cur_conn_list = next_conn_list[:]
        index += 1
    return result







def main():
    T = [9, 1, 4, 9, 0, 4, 8, 9, 0, 1]
    print(solution(T))



if __name__ == '__main__':
    main()
