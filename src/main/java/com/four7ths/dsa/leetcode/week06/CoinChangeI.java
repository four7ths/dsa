package com.four7ths.dsa.leetcode.week06;

import java.util.Arrays;

/**
 * 322 零钱找零
 * 给你一个整数数组coins，表示不同面额的硬币，以及一个整数amount，表示总金额。
 * 计算并返回可以凑成总金额所需的最少的硬币个数，如果没有任何一种硬币组合能组成总金额返回-1，每种硬币的数量是无限的。
 */
public class CoinChangeI {

    private int minCnt = Integer.MAX_VALUE;

    // dfs+剪枝
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return minCnt == Integer.MAX_VALUE ? -1 : minCnt;
    }

    private void dfs(int[] coins, int idx, int left, int cnt) {
        if (idx < 0 || left / coins[idx] + cnt > minCnt) {
            return;
        }
        if (left % coins[idx] == 0) {
            minCnt = Math.min(minCnt, cnt + left / coins[idx]);
            return;
        }
        for (int i = left / coins[idx]; i >= 0; i--) {
            dfs(coins, idx - 1, left - i * coins[idx], cnt + i);
        }
    }

    public int coinChangeV2(int[] coins, int amount) {
        // dp[i]: 当持有金币为i时，所需兑换次数的最小值
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
