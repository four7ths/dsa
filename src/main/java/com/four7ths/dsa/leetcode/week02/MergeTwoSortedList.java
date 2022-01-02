package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 21 merge two sorted list
 * 合并两个有序链表
 */
public class MergeTwoSortedList {

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head;
        if (l1.val < l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dmyNode = new ListNode(-1);
        ListNode cur = dmyNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dmyNode.next;
    }
}
