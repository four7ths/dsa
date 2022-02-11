package com.four7ths.dsa.leetcode.week03;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import com.four7ths.dsa.common.ListNode;

/**
 * 23 合并K个升序链表
 * 给定一个链表数组，每个链表都已按照升序排列，将所有链表合并成一个升序链表
 */
public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dmyNode = new ListNode(-1);
        ListNode probe = dmyNode;

        Queue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.remove();
            probe.next = node;
            probe = probe.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }

        return dmyNode.next;
    }
}
