package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 203 remove linked list elements
 * 删除链表中等于给定值【val】的所有节点
 */
public class RemoveElemInListI {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dmyNode = new ListNode(-1);
        dmyNode.next = head;

        ListNode prev = dmyNode;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dmyNode.next;
    }
}
