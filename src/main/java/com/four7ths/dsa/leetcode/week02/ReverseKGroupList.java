package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 25 k组反转链表
 * 给定一个链表，每k个节点一组进行翻转，返回翻转后的链表，k是一个正整数，它的值小于或等于链表的长度
 */
public class ReverseKGroupList {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        // prev -> rangeStart -> ... -> rangeEnd -> next -> ...
        ListNode dmyNode = new ListNode(-1);
        dmyNode.next = head;
        ListNode prev = dmyNode;

        ListNode rangeStart;
        ListNode rangeEnd;
        ListNode next;

        while ((rangeEnd = hasNextKNode(prev, k)) != null) {
            next = rangeEnd.next;
            rangeStart = prev.next;

            // 翻转[rangeStart, rangeEnd]
            ListNode cur = rangeStart;
            ListNode tmpPrev = prev;
            while (cur != next) {
                ListNode tmpNext = cur.next;
                cur.next = tmpPrev;
                tmpPrev = cur;
                cur = tmpNext;
            }

            prev.next = tmpPrev;
            rangeStart.next = next;
            prev = rangeStart;
        }

        return dmyNode.next;
    }

    // 递归实现
    public ListNode reverseKGroupV2(ListNode head, int k) {
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        ListNode newHead = reverseRange(head, tail);
        head.next = reverseKGroupV2(tail, k);
        return newHead;
    }

    // 反转[head, tail)之间节点
    private ListNode reverseRange(ListNode head, ListNode tail) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next;
        while (cur != tail) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }


    private ListNode hasNextKNode(ListNode prev, int k) {
        ListNode cur = prev;
        for (int i = 0; i < k; i++) {
            if (cur.next == null) {
                return null;
            }
            cur = cur.next;
        }
        return cur;
    }
}
