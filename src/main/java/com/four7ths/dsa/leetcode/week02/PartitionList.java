package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 86 分隔链表
 * 给定一个链表和一个特定值x，对链表进行分隔，使得所有小于x的节点都在大于或等于x的节点之前，
 * 注意保留两个分区中每个节点的初始相对位置
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode lowNode = new ListNode(-1);
        ListNode highNode = new ListNode(-1);

        ListNode low = lowNode;
        ListNode high = highNode;

        while (head != null) {
            if (head.val < x) {
                low.next = head;
                low = low.next;
            } else {
                high.next = head;
                high = high.next;
            }
            head = head.next;
        }

        high.next = null;
        low.next = highNode.next;
        return lowNode.next;
    }
}
