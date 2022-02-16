package com.four7ths.dsa.leetcode.week02;

import java.util.Deque;
import java.util.LinkedList;

import com.four7ths.dsa.common.ListNode;

/**
 * 445 两数相加 II
 * 给定两个非空链表来代表两个非负整数，数字最高位位于链表开始位置。它们的每个节点只存储单个数字，将这两数相加会返回一个新的链表。
 * 注意不能对链表中元素进行翻转
 * - 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * - 输出：[7,8,0,7]
 */
public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<ListNode> stk1 = new LinkedList<>();
        Deque<ListNode> stk2 = new LinkedList<>();

        ListNode p = l1;
        while (p != null) {
            stk1.push(p);
            p = p.next;
        }
        p = l2;
        while (p != null) {
            stk2.push(p);
            p = p.next;
        }

        int carry = 0;
        ListNode head = null;
        while (!stk1.isEmpty() || !stk2.isEmpty() || carry == 1) {
            int n1 = stk1.isEmpty() ? 0 : stk1.pop().val;
            int n2 = stk2.isEmpty() ? 0 : stk2.pop().val;
            int val = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) / 10;
            head = new ListNode(val, head);
        }

        return head;
    }

    // 递归：较短链表的值加载较长链表上
    public ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int len1 = 0;
        int len2 = 0;
        ListNode p = l1;
        while (p != null) {
            ++len1;
            p = p.next;
        }
        p = l2;
        while (p != null) {
            ++len2;
            p = p.next;
        }

        ListNode head = len1 >= len2
                        ? addTwoNumbers0(l1, len1, l2, len2)
                        : addTwoNumbers0(l2, len2, l1, len1);
        if (c == 1) {
            head = new ListNode(1, head);
        }

        return head;
    }

    private int c; // 进位

    // len1 >= len2
    private ListNode addTwoNumbers0(ListNode longList, int len1, ListNode shotList, int len2) {
        if (len1 == len2 && len1 == 1) {
            int tmp = longList.val;
            longList.val = (tmp + shotList.val) % 10;
            c = (tmp + shotList.val) / 10;
            return longList;
        }
        if (len1 > len2) {
            int tmp = longList.val;
            longList.next = addTwoNumbers0(longList.next, len1 - 1, shotList, len2);
            longList.val = (tmp + c) % 10;
            c = (tmp + c) / 10;
            return longList;
        }
        longList.next = addTwoNumbers0(longList.next, len1 - 1, shotList.next, len2 - 1);
        int tmp = longList.val;
        longList.val = (tmp + shotList.val + c) % 10;
        c = (tmp + shotList.val + c) / 10;
        return longList;
    }
}
