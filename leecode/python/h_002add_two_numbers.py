"""
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
"""
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        sum_ln = cal_ln = ListNode(0) 
        carry = 0
        while l1 or l2:
            v1 = v2 = 0
            if l1:
                v1 = l1.val
                l1 = l1.next
            if l2:
                v2 = l2.val
                l2 = l2.next
            v = (v1 + v2 + carry) % 10
            carry = 1 if v1 + v2 >= 10 else 0
            cal_ln.next = ListNode(v)
            cal_ln = cal_ln.next
        return sum_ln.next


if __name__ == "__main__":
    l1s = [ListNode(1), ListNode(2)]
    l2s = [ListNode(9), ListNode(9),ListNode(9)]
    for i in range(len(l1s)-1):
        l1s[i].next = l1s[i+1]
    for i in range(len(l2s)-1):
        l2s[i].next = l2s[i+1]
    sum_listnode = Solution().addTwoNumbers(l1s[0], l2s[0])
    while sum_listnode:
        print(sum_listnode.val)
        sum_listnode = sum_listnode.next
