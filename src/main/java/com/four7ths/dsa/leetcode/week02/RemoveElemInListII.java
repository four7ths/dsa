package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 82 remove duplicates from sorted list II
 *  * 给定一个【排序】链表，删除所有含有重复数字的节点，只保留原始链表中【没有重复出现】的数字
 * >> 1 -> 2 -> 3 -> 3 > 4 -> 4 -> 5
 * << 1 -> 2 -> 5
 */
public class RemoveElemInListII {

    public ListNode deleteDuplication01(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dmyNode = new ListNode(-1);
        dmyNode.next = head;

        ListNode prev = dmyNode;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                do {
                    cur = cur.next;
                } while (cur.next != null && cur.val == cur.next.val);
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }

        return dmyNode.next;
    }
}
