package com.four7ths.dsa.leetcode.week06;

import java.util.Arrays;

/**
 * 518 零钱兑换 II
 * 【组合问题】
 * 给定一个整数数组coins表示不同面额的硬币，另给一个整数amount表示总金额，计算并返回可以凑成总金额的硬币组合数。
 * 如果任何硬币组合都无法凑出总金额，返回0，假设每一种面额的硬币有无限个
 */
public class CoinChangeII {

    public int change(int amount, int[] coins) {
        // dp[i][j]: 从i种硬币中选择若干个（可为0），使得总和恰好是j
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            int coin = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                // k*coin可以看做是从[coin...amount]
                for (int k = 0; k * coin <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * coin];
                }
            }
        }
        return dp[n][amount];
    }

    public int changeV2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    private int cnt = 0;

    // dfs：超时，当amount较大，coins中coin数值较小时，递归树过深
    public int changeV3(int amount, int[] coins) {
        Arrays.sort(coins);
        dfs(coins, 0, amount);
        return cnt;
    }

    private void dfs(int[] coins, int idx, int left) {
        if (left == 0) {
            ++cnt;
            return;
        }
        if (idx == coins.length) {
            return;
        }
        for (int i = left / coins[idx]; i >= 0; --i) {
            dfs(coins, idx + 1, left - i * coins[idx]);
        }
    }
}
