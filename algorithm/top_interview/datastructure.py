l1 = ['a', 'b']


def lists():
    l1 = ['a', 'b']
    print(','.join(l1))

    l2 = list('c')
    l2.append('d')
    print(','.join(l2))

    l3 = l1 + l2
    l3.insert(2, 'W')
    print(','.join(l3))

    l1.append(l2)
    print(l1)

    l1.extend(l2)
    print(l1)

    nums = [4, 2, 6, 3, 8]


def strings():
    # for c in 'hello world!':
    #     print(c)
    pass


def dicts():
    pass


def main():
    lists()
    strings()



if __name__ == '__main__':
    main()
    print(3/2)
