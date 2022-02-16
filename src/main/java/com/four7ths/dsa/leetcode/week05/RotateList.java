package com.four7ths.dsa.leetcode.week05;

import com.four7ths.dsa.common.ListNode;

/**
 * 61 旋转链表
 * 给定一个链表，旋转该链表，将链表中每个节点向右移动k个位置，其中k是非负数
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode tail = head;
        int len = 0;
        while (cur != null) {
            ++len;
            if (cur.next == null) {
                tail = cur;
            }
            cur = cur.next;
        }

        // Assert (len > 0)
        k %= len;
        if (k == 0) {
            return head;
        }

        ListNode dmyNode = new ListNode(-1);
        dmyNode.next = head;
        ListNode s = dmyNode;
        ListNode f = dmyNode;
        for (int i = 0; i < k + 1; i++) {
            f = f.next;
        }

        while (f != null) {
            s = s.next;
            f = f.next;
        }
        ListNode newHead = s.next;
        s.next = null;
        tail.next = head;

        return newHead;
    }
}
