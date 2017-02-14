package main

import (
	"fmt"
)

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/
type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	var calLn ListNode = ListNode{0, nil}
	sumLn := &calLn
	carry := 0
	for l1.Next != nil || l2.Next != nil {
		v1 := 0
		v2 := 0
		if l1.Next != nil {
			v1 = l1.Val
			l1 = l1.Next
		}
		if l2.Next != nil {
			v2 = l2.Val
			l2 = l2.Next
		}
		v := (v1 + v2 + carry) % 10
		calLn.Next = &ListNode{v, nil}
		calLn = *calLn.Next
		switch v >= 10 {
		case true:
			carry = 1
		case false:
			carry = 0
		}
	}
	return sumLn.Next
}
