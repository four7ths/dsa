package com.four7ths.dsa.leetcode.week06;

/**
 * 1143 最长公共子序列
 * 给定两个字符串text1和text2，返回这两个字符串的最长公共子序列
 */
public class LongCommonSeq {

    public int longestCommonSubsequence(String text1, String text2) {
        int row = text1.length();
        int col = text2.length();

        int[][] dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[row][col];
    }

    // 补充：最长公共子字符串问题
    public String longestCommonSubString(String str1, String str2) {
        int row = str1.length();
        int col = str2.length();

        int[][] dp = new int[row][col];
        int startIdx = -1;
        int maxLen = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //   h e l l o w o r l d
                // w 0 0 0 0 0 1 0 0 0 0
                // o 0 0 0 0 1 0 2 0 0 0
                // r 0 0 0 0 0 0 0 3 0 0
                // l 0 0 0 0 0 0 0 0 4 0
                // d 0 0 0 0 0 0 0 0 0 5
                if (str1.charAt(i) == str2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        startIdx = j - maxLen + 1;
                    }
                }
            }
        }
        return str2.substring(startIdx, startIdx + maxLen);
    }
}
