package com.four7ths.dsa.common;

/**
 * 复杂链表
 */
public class RandomListNode {

    public int label;

    public RandomListNode next = null;

    // 随机指向链表中某个节点
    public RandomListNode random = null;

    public RandomListNode(int label) {
        this.label = label;
    }
}
