package com.four7ths.dsa.leetcode.week08;

/**
 * 392 判断子序列
 * 给定字符串s和t，判断s是否为t的子序列
 */
public class IsSubSequence {

    public boolean isSubsequence(String s, String t) {
        int idx = -1;
        for (char ch : s.toCharArray()) {
            idx = t.indexOf(ch, idx + 1);
            if (idx < 0) {
                return false;
            }
        }
        return true;
    }
}
