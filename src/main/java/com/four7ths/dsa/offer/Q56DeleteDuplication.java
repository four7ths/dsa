package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.ListNode;

/**
 * 删除链表中重复的节点
 * 在一个排序链表中，存在重复节点，删除该链表中重复节点，重复的节点不保留
 * 1->2->3->3->4->4->5   ->   1->2->5
 */
public class Q56DeleteDuplication {
    public ListNode deleteDuplication01(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = pHead;
        ListNode pre = dummyNode;
        ListNode cur = pre.next;
        while (cur != null) {
            if (cur.next != null && cur.next.val == cur.val) {
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }
}
