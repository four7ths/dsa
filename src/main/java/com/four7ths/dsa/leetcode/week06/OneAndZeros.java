package com.four7ths.dsa.leetcode.week06;

/**
 * 474 零和一
 * 给你一个二进制字符串数组strs和两个整数m和n，找出并返回strs的最大子集的长度，该子集中最多有m个0和n个1
 */
public class OneAndZeros {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeros = 0;
            int ones = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '1') {
                    ++ones;
                } else {
                    ++zeros;
                }
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
