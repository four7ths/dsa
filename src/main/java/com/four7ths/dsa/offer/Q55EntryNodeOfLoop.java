package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.ListNode;

/**
 * 链表中环的入口节点
 * 给一个链表，若其中包含环，请找出该链表的环的入口节点，否则返回null
 */
public class Q55EntryNodeOfLoop {
    public ListNode entryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        // 没有环
        if (fast == null) {
            return null;
        }
        // 寻找环
        slow = pHead;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
