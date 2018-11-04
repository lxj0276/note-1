


class Node:
    def __init__(self, val):
        self.val = val
        self.next = None


def get_size(head):
    count = 1
    while head:
        count += 1
        head = head.next
    return count


def print_recursive_head(head):
    result = []
    cur = head
    while cur:
        result.append(str(cur.val))
        cur = cur.next

    print(', '.join(result))


class LList:
    def __init__(self):
        self.head = None

    def push_head(self, val):
        node = Node(val)
        node.next = self.head
        self.head = node

    # @staticmethod
    # def reverse(head):
    #   dummy = Node(0)
    #   if not head or not head.next:
    #     return head
    #   dummy.next = head
    #   cur = head.next
    #   head.next = None
    #   while cur:
    #     next = cur.next
    #     cur.next = dummy.next
    #     dummy.next = cur
    #     cur = next
    #   return dummy.next

    @staticmethod
    def reverse1(head):
        # 1,3,4,6   -> None <-1
        cur = head
        pre = None
        if not head or not head.next:
            return head
        while cur:
            next = cur.next
            cur.next = pre
            pre = cur
            cur = next
        return pre

    @staticmethod
    def push_head_val(head, val):
        node = Node(val)
        node.next = head
        head = node
        return head

    @staticmethod
    def reverse_recursive(head):
        if not head or not head.next:
            return head

        next = head.next
        head.next = None
        rest = LList.reverse_recursive(next)
        next.next = head
        return rest

    @staticmethod
    def reverse2(head, k):
        cur = head
        pre = None
        next = None
        count = 0
        while cur and count < k:
            next = cur.next
            cur.next = pre
            pre = cur
            cur = next
            count += 1
        if next:
            head.next = LList.reverse2(next, k)
        return pre

    def reverseKGroup(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        count = 0
        cur = head
        while cur and count < k:
            cur = cur.next
            count += 1
        if count == k:
            cur = self.reverseKGroup(cur, k)
            while count > 0:
                next = head.next
                head.next = cur
                cur = head
                head = next
                count -= 1
            head = cur
        return head

    def print_list(self):
        result = []
        cur = self.head
        while cur:
            result.append(str(cur.val))
            cur = cur.next

        print(', '.join(result))

    @staticmethod
    def merge2lists(l1, l2):
        h1 = l1.head
        h2 = l2.head
        dummy = Node(0)
        cur = dummy

        if not h1:
            return h2
        if not h2:
            return h1
        while h1 and h2:
            if h1.val < h2.val:
                cur.next = h1
                h1 = h1.next
            else:
                cur.next = h2
                h2 = h2.next
            cur = cur.next
        while h1:
            cur.next = h1
            h1 = h1.next
            cur = cur.next
        while h2:
            cur.next = h2
            h2 = h2.next
            cur = cur.next
        return dummy.next

    @staticmethod
    def had_cycle(head):
        if not head or not head.next:
            return None
        slow = head
        fast = head
        while fast and fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                return slow
        return None

    # @staticmethod
    # def detect_remove_cycle1(head):
    #   def remove_cycle(loop_node):
    #     pointer1 = head
    #     while 1:
    #       pointer2 = loop_node
    #       while pointer2.next != loop_node and pointer2.next != pointer1:
    #         pointer2 = pointer2.next
    #       if pointer2.next == pointer1:
    #         break
    #       pointer1 = pointer1.next
    #     pointer2.next = None

    #   slow = fast = head
    #   while fast and fast.next and fast.next.next:
    #     slow = slow.next
    #     fast = fast.next.next
    #     if slow == fast:
    #       remove_cycle(slow)
    #       return 1
    #   return 0

    @staticmethod
    def get_length(head):
        count = 0
        while head:
            count += 1
        return count

    @staticmethod
    def has_cycle_dict(head):
        d = {}
        node = head
        while node:
            if node not in d:
                d[node] = True
                node = node.next
            else:
                return node
        return None

    @staticmethod
    def detect_remove_cycle(head):
        def remove_cycle1(loop_node):
            p1 = head
            p2 = loop_node
            while 1:
                while p2.next != loop_node and p2.next != p1:
                    p2 = p2.next
                if p2.next == p1:
                    break
                p1 = p1.next
            p2.next = None

        def remove_cycle2(loop_node):
            p1 = loop_node
            count = 1
            while p1.next != loop_node:
                p1 = p1.next
                count += 1
            slow = fast = head
            for _ in range(count):
                fast = fast.next

            while slow != fast:
                slow = slow.next
                fast = fast.next
            while fast.next != slow:
                fast = fast.next
            fast.next = None

        def remove_cycle3(loop_node):
            p1 = head
            p2 = loop_node
            while p1.next != p2.next:
                p1 = p1.next
                p2 = p2.next
            print(p2.val)
            p2.next = None

        node = LList.had_cycle(head)
        if node:
            # remove_cycle1(node)
            # remove_cycle2(node)
            remove_cycle3(node)
            return 1
        return 0

    @staticmethod
    def merge_sort(head):
        def merge(left, right):
            if not left:
                return right
            if not right:
                return left
            if left.val < right.val:
                result = left
                result.next = merge(left.next, right)
            else:
                result = right
                right.next = merge(left, right.next)
            return result

        def get_middle(head):
            if not head or not head.next:
                return head
            slow = fast = head
            while fast.next and fast.next.next:
                slow = slow.next
                fast = fast.next.next
            return slow

        if not head or not head.next:
            return head
        middle = get_middle(head)
        mid_next = middle.next
        middle.next = None
        return merge(LList.merge_sort(head), LList.merge_sort(mid_next))

    @staticmethod
    def get_random(head):
        if not head:
            return head
        import random
        random.seed()
        n = 2
        result = head
        cur = head

        while cur:
            if random.randrange(n) == 0:
                result = cur
                return result
            cur = cur.next
            n += 1
        return result


def main():
    # llist = LList()
    # llist.push_head(1)
    # llist.push_head(2)
    # llist.push_head(3)
    # llist.push_head(4)
    # llist.push_head(5)
    # llist.print_list()
    # # head = llist.reverse(llist.head)
    # # head = llist.reverse1(llist.head)
    # # head = llist.reverse_recursive(llist.head)
    # head = llist.reverse2(llist.head, 3)
    # print_recursive_head(head)

    # For merge two sorted lists
    # l1 = LList()
    # l1.push_head(4)
    # l1.push_head(8)
    # l1.push_head(1)
    # l1.push_head(6)
    # l1.push_head(5)
    # l2 = LList()
    # l2.push_head(3)
    # l2.push_head(2)
    # l2.push_head(7)
    # l2.push_head(9)
    # l2.push_head(5)
    # l1.print_list()
    # l2.print_list()
    # result = LList.merge2lists(l1, l2)
    # print_recursive_head(result)

    # # for has cycle
    llist = LList()
    llist.push_head(1)
    llist.push_head(2)
    llist.push_head(3)
    llist.push_head(4)
    llist.push_head(5)
    # llist.head.next.next.next.next = llist.head.next
    # node = LList.had_cycle(llist.head)
    # # node = LList.has_cycle_dict(llist.head)
    # cycle = 'Cycle node value is: ' + str(node.val) if node else 'No cycle'
    # print(cycle)
    # r = llist.detect_remove_cycle(llist.head)
    # print(r)
    # node = LList.had_cycle(llist.head)
    # cycle = str(node.val) if node else 'No cycle'
    # print(cycle)
    llist.print_list()
    head = llist.reverseKGroup(llist.head, 3)
    print_recursive_head(head)

    # For merge sort
    # l1 = LList()
    # l1.push_head(4)
    # l1.push_head(8)
    # l1.push_head(6)
    # l1.push_head(5)
    # l1.push_head(1)
    # l1.print_list()
    # # head = LList.merge_sort(l1.head)
    # # print_recursive_head(head)
    # # For get random node
    # node = LList.get_random(l1.head)
    # print(str(node.val))
    # node = LList.get_random(l1.head)
    # print(str(node.val))
    # node = LList.get_random(l1.head)
    # print(str(node.val))
    # node = LList.get_random(l1.head)
    # print(str(node.val))
    # node = LList.get_random(l1.head)
    # print(str(node.val))






if __name__ == '__main__':
    main()
