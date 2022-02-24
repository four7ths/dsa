package com.four7ths.dsa.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 两个栈来实现一个队列
 */
public class Q05TwoStkImplQueue {
    Deque<Integer> data = new LinkedList<>();
    Deque<Integer> helper = new LinkedList<>();

    public void push(int node) {
        data.push(node);
    }

    public int pop() {
        if (!helper.isEmpty()) {
            return helper.pop();
        } else {
            while (!data.isEmpty()) {
                helper.push(data.pop());
            }
        }
        return helper.pop();
    }
}
