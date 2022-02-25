package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.ListNode;

/**
 * 反转一个单向链表
 */
public class Q15ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
