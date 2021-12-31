package com.four7ths.dsa.common;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this(val, null);
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
