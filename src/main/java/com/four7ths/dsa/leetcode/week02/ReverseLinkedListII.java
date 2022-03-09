package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 92 反转链表 II
 * 反转位置m到n的链表，1 <= m <= n <= 链表长度
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }
        ListNode dmyNode = new ListNode(-1);
        dmyNode.next = head;

        ListNode prev = dmyNode;
        ListNode cur = head;

        for (int i = 1; i < m; i++) {
            ListNode next = cur.next;
            prev = cur;
            cur = next;
        }
        ListNode prevMark = prev;
        ListNode curMark = cur;

        for (int i = m; i <= n; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        prevMark.next = prev;
        curMark.next = cur;

        return dmyNode.next;
    }
}
