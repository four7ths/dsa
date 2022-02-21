package com.four7ths.dsa.leetcode.week07;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * 20 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stk = new LinkedList<>();
        Map<Character, Character> maps = new HashMap<>(16);
        maps.put(')', '(');
        maps.put(']', '[');
        maps.put('}', '{');
        for (char ch : s.toCharArray()) {
            Character value = maps.get(ch);
            if (value == null) {
                stk.push(ch);
            } else {
                if (stk.isEmpty() || !Objects.equals(stk.pop(), value)) {
                    return false;
                }
            }
        }
        return stk.isEmpty();
    }
}
