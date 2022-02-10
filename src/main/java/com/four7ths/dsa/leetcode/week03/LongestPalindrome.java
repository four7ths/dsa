package com.four7ths.dsa.leetcode.week03;

/**
 * 5 最长回文字符串
 * * 给定一个二叉树，返回其节点值自底向上的层次遍历
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }

        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        // 注意初始化结果
        int startIdx = 0;
        int maxLen = 1;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else if (j - i <= 2) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i + 1][j - 1];
                }
                if (dp[i][j] && (j - i + 1) > maxLen) {
                    startIdx = i;
                    maxLen = j - i + 1;
                }
            }
        }

        return s.substring(startIdx, startIdx + maxLen);
    }
}
