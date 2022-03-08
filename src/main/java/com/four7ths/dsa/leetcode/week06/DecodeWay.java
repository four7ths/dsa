package com.four7ths.dsa.leetcode.week06;

/**
 * 91 解码方法
 * 一条包含字母A-Z的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数
 */
public class DecodeWay {
    public int numDecodings(String s) {
        if (s == null || "".equals(s) || s.charAt(0) == '0') {
            return 0;
        }

        int g = 1;
        int f = 1;
        for (int i = 1; i < s.length(); i++) {
            int tmp = f;
            int cur = s.charAt(i) - '0';
            int prev = s.charAt(i - 1) - '0';
            if (cur == 0) {
                if (prev > 2 || prev == 0) {
                    return 0;
                } else {
                    f = g;
                }
            } else if (prev == 1 || (prev == 2 && cur >= 0 && cur <= 6)) {
                f += g;
            }
            g = tmp;
        }
        return f;
    }
}
