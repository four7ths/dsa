package com.four7ths.dsa.leetcode.week06;

/**
 * 343 整数拆分
 * 给定一个正整数n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        // dp[i]: 数字i能够拆分的最大值
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j >= 1; j--) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j));
                dp[i] = Math.max(dp[i], j * (i - j));
            }
        }
        return dp[n];
    }
}
