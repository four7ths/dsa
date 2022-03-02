package com.four7ths.dsa.offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.ListNode;

/**
 * 从尾到头打印链表的节点值
 */
public class Q03PrintListFromTailToHead {

    // 使用栈实现
    public List<Integer> printListFromTailToHead(ListNode listNode) {
        List<Integer> ret = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }

    List<Integer> list = new ArrayList<>();

    // 使用递归实现
    public List<Integer> printListFromTailToHeadV2(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHeadV2(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}
