package com.four7ths.dsa.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 包含min函数的栈
 */
public class Q20StackWithMin {

    private final Deque<Integer> data = new LinkedList<>();
    private final Deque<Integer> helper = new LinkedList<>();

    public void push(int node) {
        data.push(node);
        if (helper.isEmpty() || helper.peek() > node) {
            helper.push(node);
        } else {
            helper.push(helper.peek());
        }
    }

    public void pop() {
        data.pop();
        helper.pop();
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        return helper.peek();
    }
}
