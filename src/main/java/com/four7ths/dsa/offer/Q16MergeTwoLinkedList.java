package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.ListNode;

/**
 * 合并两个单调递增的链表，合成后的链表满足单调不减规则
 */
public class Q16MergeTwoLinkedList {
    // 非递归形式
    public ListNode merge(ListNode p, ListNode q) {
        ListNode dmyNode = new ListNode(-1);
        ListNode cur = dmyNode;
        while (p != null && q != null) {
            if (p.val < q.val) {
                cur.next = p;
                p = p.next;
            } else {
                cur.next = q;
                q = q.next;
            }
            cur = cur.next;
        }
        // 链表直接使用if即可，不需要使用while循环
        if (p != null) {
            cur.next = p;
        }
        if (q != null) {
            cur.next = q;
        }
        return dmyNode.next;
    }

    // 递归形式
    public ListNode mergeV2(ListNode p, ListNode q) {
        if (p == null) {
            return q;
        }
        if (q == null) {
            return p;
        }
        ListNode head;
        if (p.val < q.val) {
            head = p;
            head.next = mergeV2(p.next, q);
        } else {
            head = q;
            head.next = mergeV2(p, q.next);
        }
        return head;
    }
}
