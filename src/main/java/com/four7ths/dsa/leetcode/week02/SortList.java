package com.four7ths.dsa.leetcode.week02;

import com.four7ths.dsa.common.ListNode;

/**
 * 148 O(nlogn)时间复杂度内完成链表排序
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return mergeSort(head);
    }

    private ListNode mergeSort(/*@NonNull*/ ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode s = head;
        ListNode f = head;
        ListNode prev = null;
        while (f != null && f.next != null) {
            prev = s;
            s = s.next;
            f = f.next.next;
        }
        prev.next = null;
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(s);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dmyNode = new ListNode(-1);
        ListNode prev = dmyNode;
        while (left != null && right != null) {
            if (left.val < right.val) {
                prev.next = left;
                left = left.next;
            } else {
                prev.next = right;
                right = right.next;
            }
            prev = prev.next;
        }
        prev.next = left == null ? right : left;
        return dmyNode.next;
    }

    // 自底向上归并排序
    public ListNode sortListV2(ListNode head) {
        ListNode dmyNode = new ListNode(-1);
        dmyNode.next = head;

        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            ++len;
            cur = cur.next;
        }

        for (int sz = 1; sz < len; sz *= 2) {
            ListNode start = dmyNode.next;
            // 指向局部有序链表的最后一个节点
            ListNode end = dmyNode;
            while (start != null) {
                ListNode left = start;
                ListNode right = cut(start, sz);
                start = cut(right, sz);
                end.next = merge(left, right);

                while (end.next != null) {
                    end = end.next;
                }
            }
        }

        return dmyNode.next;
    }

    // 输入：head -> n1 -> n2 -> ... -> null, size = 2
    // 1.head -> n1 -> null
    // 2.return: n2 -> ... -> null
    private ListNode cut(ListNode head, int size) {
        ListNode cur = head;
        while (--size > 0 && cur != null) {
            cur = cur.next;
        }
        if (cur == null) {
            return null;
        }
        ListNode next = cur.next;
        cur.next = null;
        return next;
    }
}
