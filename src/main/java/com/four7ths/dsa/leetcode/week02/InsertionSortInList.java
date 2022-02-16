package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 147 链表插入排序
 * 给定一个链表，使用插入排序对链表进行排序
 */
public class InsertionSortInList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dmyNode = new ListNode(-1);
        dmyNode.next = head;

        ListNode prev = head;
        ListNode cur = prev.next;
        while (cur != null) {
            if (cur.val >= prev.val) {
                prev = cur;
                cur = cur.next;
                continue;
            }
            // dmyNode.next -> ... -> prev -> cur -> ...
            // [dmyNode.next...prev]都是有序的
            ListNode pp = dmyNode;
            ListNode p = pp.next;
            while (cur.val > p.val) {
                pp = p;
                p = p.next;
            }
            // 将cur插入到pp -> p之间: pp -> cur -> p -> ... -> prev -> cur.next
            prev.next = cur.next;
            cur.next = p;
            pp.next = cur;

            cur = prev.next;
        }

        return dmyNode.next;
    }
}
