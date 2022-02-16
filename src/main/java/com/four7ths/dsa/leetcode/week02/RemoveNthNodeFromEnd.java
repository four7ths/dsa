package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 19 删除链表的倒数第n个结点
 */
public class RemoveNthNodeFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dmyNode = new ListNode(-1);
        dmyNode.next = head;

        ListNode f = dmyNode;
        for (int i = 0; i < n + 1; i++) {
            if (f == null) {
                return null;
            }
            f = f.next;
        }

        ListNode s = dmyNode;
        while (f != null) {
            s = s.next;
            f = f.next;
        }

        s.next = s.next.next;
        return dmyNode.next;
    }
}
