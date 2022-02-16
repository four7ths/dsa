package com.four7ths.dsa.leetcode.week05;

import com.four7ths.dsa.common.ListNode;

/**
 * 234 回文链表
 * 判断一个链表是否是回文链表
 */
public class PalindromeList {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode s = head;
        ListNode f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        boolean isEven = f.next != null;
        // 避免偶数情况下后面head!=mid的判断
        if (isEven && s.val != s.next.val) {
            return false;
        }

        ListNode mid = s;
        // 后半段链表
        ListNode pre = mid;
        ListNode cur = pre.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        while (head != mid) {
            if (head.val != pre.val) {
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        return true;
    }
}
