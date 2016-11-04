package com.borderx.leecode;

import org.junit.Test;

/**
 * 2. Add Two Numbers 
 * 
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a 
 * single digit. Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * @author borderx
 *
 */
public class Solution2 {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int more = 0;
		ListNode res = new ListNode(0);
		ListNode tmp = res;
		while(l1 != null && l2 != null) {
			int sum = l1.val + l2.val + more;
			tmp.next = new ListNode(sum % 10);
			more = sum / 10;
			l1 = l1.next;
			l2 = l2.next;
			tmp = tmp.next;
		}
		while(l1 != null) {
			int sum = l1.val + more;
			tmp.next = new ListNode(sum % 10);
			more = sum / 10;
			l1 = l1.next;
			tmp = tmp.next;
		}
		while(l2 != null) {
			int sum = l2.val + more;
			tmp.next = new ListNode(sum % 10);
			more = sum / 10;
			l2 = l2.next;
			tmp = tmp.next;
		}
		if(more > 0) {
			tmp.next = new ListNode(more);
			tmp = tmp.next;
		}
		tmp = res.next;
		res.next = null;
		return tmp;
	}
	
	@Test
	public void test() {
		ListNode node1 = new ListNode(6);
		ListNode node2 = new ListNode(1, node1);
		ListNode node3 = new ListNode(9, node2);
		
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(6, node4);
		ListNode node6 = new ListNode(5, node5);
		ListNode node7 = new ListNode(0);
		
		ListNode res = addTwoNumbers(node3, node7);
		
		while(res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
	
	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
