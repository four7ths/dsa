package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 83 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，删除所有重复的元素，使每个元素只出现一次
 */
public class RemoveDupInSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
