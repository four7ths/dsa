package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.ListNode;

/**
 * 链表倒数第k个节点，k >= 1，最后一个节点为倒数第一个节点
 */
public class Q14FindRankKFromTail2Head {
    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode slow;
        ListNode fast;
        slow = fast = head;
        while (fast != null && k > 0) {
            fast = fast.next;
            --k;
        }
        if (fast == null && k > 0) {
            return null;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
