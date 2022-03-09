package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 206 翻转链表
 */
public class ReverseLinkedListI {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNode reverseListRecursive(ListNode head) {
        return reverseListRecursive(null, head);
    }

    public ListNode reverseListRecursive(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode next = cur.next;
        cur.next = prev;
        return reverseListRecursive(cur, next);
    }
}
