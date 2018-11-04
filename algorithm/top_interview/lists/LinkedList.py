# encoding: UTF-8


class Node:
  def __init__(self, data):
    self.data = data
    self.next = None


def print_head(head):
  result = []
  while head:
    result.append(str(head.data))
    head = head.next
  print(', '.join(result))


def _is_present(head, data):
  while head:
    if head.data == data:
      return True
    head = head.next
  return False


def _push_head(head, data):
  node = Node(data)
  node.next = head
  head = node
  return head


class LinkedList:
  def __init__(self):
    self.head = None

  def insert_sorted(self, new_node):
    """insert a sorted linked list."""
    # 插入空的list, 或者插入的节点比头节点还要小, 将新节点插入到头部
    if self.head is None or self.head.data > new_node.data:
      new_node.next = self.head
      self.head = new_node
    else:
      current = self.head
      while current.next is not None and current.next.data < new_node.data:
        current = current.next
      new_node.next = current.next
      current.next = new_node

  def push_head(self, node):
    node.next = self.head
    self.head = node

  def delete_node_more(self, node):
    """删除单链表中值为给定的val的节点。比如：给定链表 1 –> 2 –> 6 –> 3 –> 4 –> 5 –> 6 和值 val = 6 ，返回 1 –> 2 –> 3 –> 4 –> 5 """
    pre = Node(0)
    pre.next = self.head
    slow, fast = pre, self.head
    while fast:
      if fast.data != node.data:
        slow.next.data = fast.data
        slow = slow.next
      fast = fast.next
    slow.next = None
    return pre.next

  def delete_node(self, d_node):
    if self.head is None:
      print('List is None')
    elif self.head.data == d_node.data:
      if self.head.next is None:
        print('Error, Could not delete head of list')
      else:
        self.head.data = self.head.next.data
        self.head.next = self.head.next.next

    else:
      current = self.head
      while current.next is not None and current.next.data != d_node.data:
        current = current.next
      if current.next is None:
        print('Could not find the delete data')
      else:
        current.next = current.next.next
    return self.head

  def print_llist(self):
    current = self.head
    nodes = []
    while current is not None:
      nodes.append(str(current.data))
      current = current.next

    print(', '.join(nodes))

  def reverse(self, head, k):
    """Fix this. Reverse a Linked List in groups of given size. """
    cur = head
    pre = None
    count = 0
    next = None
    while cur and count < k:
      next = cur.next
      cur.next = pre
      pre = cur
      cur = next
      count += 1

    if next:
      head.next = self.reverse(next, k)
    return pre

  @staticmethod
  def intersection(list1, list2):
    result = None
    h1 = list1.head
    h2 = list2.head
    while h1:
      data = h1.data
      if _is_present(h2, data):
        result = _push_head(result, data)
      h1 = h1.next
    return result

  @staticmethod
  def union(list1, list2):
    result = None
    h1 = list1.head
    h2 = list2.head
    if h1 is None:
      return h2
    if h2 is None:
      return h1
    while h1:
      result = _push_head(result, h1.data)
      h1 = h1.next
    while h2:
      if _is_present(result, h2.data):
        h2 = h2.next
        continue
      result = _push_head(result, h2.data)
      h2 = h2.next
    return result


def get_size_llist(node):
  count = 0
  while node:
    count += 1
    node = node.next
  return count


class LinkedListUtil:
  def __init__(self):
    self._carry = 0
    self.result_list = LinkedList()

  def compare_two_strings_llist(l1, l2):
    head1 = l1.head
    head2 = l2.head
    while head1 and head2 and head1.data == head2.data:
      head1 = head1.next
      head2 = head2.next
    if head1 and head2:
      return 1 if head1.data > head2.data else -1
    if head1 and not head2:
      return 1
    if not head1 and head2:
      return -1
    return 0

  @staticmethod
  def merge2llist_alternate(l1, l2):
    h1 = l1.head
    h2 = l2.head
    while h1 and h2:
      h1_next = h1.next
      h2_next = h2.next
      h2.next = h1.next
      h1.next = h2
      h1 = h1_next
      h2 = h2_next
    l2.head = h2
    return l1, l2

  def _add_two_nums_llist_same_size(self, h1, h2):
    if not h1:
      return
    self._add_two_nums_llist_same_size(h1.next, h2.next)
    sum = h1.data + h2.data + self._carry
    self._carry = sum / 10
    sum = sum % 10
    self.result_list.push_head(Node(sum))

  def add_two_nums_llist(self, l1, l2):
    diff_head_point = None

    def propogate_carry(node):
      if node != diff_head_point:
        propogate_carry(node.next)
        sum = node.data + self._carry
        self._carry = sum / 10
        sum = sum % 10
        self.result_list.push_head(Node(sum))

    h1 = l1.head
    h2 = l2.head
    size1 = get_size_llist(h1)
    size2 = get_size_llist(h2)
    if size1 < size2:
      h1, h2 = h2, h1
    if size1 == size2:
      self._add_two_nums_llist_same_size(h1, h2)
    else:
      diff = abs(size1 - size2)
      print(diff)
      while diff != 0:
        diff_head_point = h1.next
        diff -= 1
      self._add_two_nums_llist_same_size(diff_head_point, h2)
      propogate_carry(h1)

    if self._carry == 1:
      self.result_list.push_head(Node(1))
    return self.result_list


def main():
  l1 = LinkedList()
  llu = LinkedListUtil()
  l1.insert_sorted(Node(5))
  l1.insert_sorted(Node(4))
  l1.insert_sorted(Node(3))
  l1.insert_sorted(Node(2))
  l1.insert_sorted(Node(1))
  # l1.print_llist()
  # l1.delete_node(Node(4))
  # head = l1.delete_node(Node(8))
  # l1.print_llist()
  # l1.delete_node_more(Node(4))
  # l1.print_llist()
  # print(head.data)

  l2 = LinkedList()
  l2.insert_sorted(Node(8))
  l2.insert_sorted(Node(8))
  l2.insert_sorted(Node(8))
  l2.insert_sorted(Node(7))
  l2.insert_sorted(Node(6))
  l2.insert_sorted(Node(2))
  # l2.print_llist()

  # print(compare_two_strings_llist(l1, l2))
  # print(get_size_llist(l1.head))

  # for add two nums
  # sum_llst = llu.add_two_nums_llist(l1, l2)
  # l1.print_llist()
  # l2.print_llist()
  # sum_llst.print_llist()

  # for merge two llist
  # l1.print_llist()
  # l2.print_llist()
  # l1, l2 = LinkedListUtil.merge2llist_alternate(l1, l2)
  # l1.print_llist()
  # l2.print_llist()

  # for reverse
  # l1.print_llist()
  # l1.head = l1.reverse(l1.head, 3)
  # l1.print_llist()

  # for intersection and union
  inter_head = LinkedList.intersection(l1, l2)
  print_head(inter_head)
  union_head = LinkedList.union(l1, l2)
  print_head(union_head)


if __name__ == '__main__':
  main()
