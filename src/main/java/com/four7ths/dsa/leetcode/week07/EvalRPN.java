package com.four7ths.dsa.leetcode.week07;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 150 逆波兰表达式求值
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stk = new LinkedList<>();
        for (String token : tokens) {
            try {
                int val = Integer.parseInt(token);
                stk.push(val);
            } catch (NumberFormatException e) {
                int num2 = stk.pop();
                int num1 = stk.pop();
                if ("+".equals(token)) {
                    stk.push(num1 + num2);
                } else if ("-".equals(token)) {
                    stk.push(num1 - num2);
                } else if ("*".equals(token)) {
                    stk.push(num1 * num2);
                } else {
                    stk.push(num1 / num2);
                }
            }
        }
        return stk.pop();
    }
}
