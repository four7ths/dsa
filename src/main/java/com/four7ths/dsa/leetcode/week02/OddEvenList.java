package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 328 奇偶链表
 * 给定一个单链表，把所有索引位（从1开始）为奇数节点和偶数节点分别排在一起
 */
public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddNode = new ListNode(-1);
        ListNode evenNode = new ListNode(-1);

        ListNode odd = oddNode;
        ListNode even = evenNode;

        int idx = 1;
        while (head != null) {
            if ((idx & 1) == 1) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }
            head = head.next;
            ++idx;
        }
        even.next = null; // 注意这个赋值操作
        odd.next = evenNode.next;
        return oddNode.next;
    }
}
