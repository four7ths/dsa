package com.four7ths.dsa.leetcode.week07;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 71 简化路径
 * 以Unix风格给出一个文件的绝对路径，你需要简化它，即将其转换为规范路径
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        String[] strs = path.replaceAll("//+", "/").split("/");
        Deque<String> stk = new LinkedList<>();

        for (String str : strs) {
            if (".".equals(str)) {
                continue;
            }
            if ("..".equals(str)) {
                if (!stk.isEmpty()) {
                    stk.pop();
                }
            } else {
                stk.push(str);
            }
        }
        if (stk.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.insert(0, stk.pop()).insert(0, '/');
        }
        return sb.toString();
    }
}
