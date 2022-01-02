package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 2 add two numbers
 * 给出两个【非空】的链表用来表示两个非负的整数。其中它们各自的位数是按照【逆序】的方式存储的，
 * 并且它们的每个节点只能存储一位数字
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dmyNode = new ListNode(-1);
        ListNode cur = dmyNode;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            int tmp = n1 + n2 + carry;
            ListNode newNode = new ListNode((tmp) % 10);
            carry = tmp / 10;
            cur.next = newNode;
            cur = cur.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dmyNode.next;
    }
}
