package com.four7ths.dsa.leetcode.week06;

import java.util.Arrays;

/**
 * 377 组合总和IV
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数，注意顺序不同认为是不同的组合
 */
public class CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    // memo[i]：当和为i时，组合的个数
    private int[] memo;

    // 记忆化搜索
    public int combinationSum4V2(int[] nums, int target) {
        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return dfsOptimized01(nums, target);
    }

    private int dfsOptimized01(int[] nums, int target) {
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += dfsOptimized01(nums, target - num);
            }
        }
        memo[target] = res;
        return res;
    }

    private int res = 0;

    // dfs: 超时
    public int combinationSum4V3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        dfs(nums, 0, target);
        return res;
    }

    private void dfs(int[] arr, int cur, int target) {
        if (cur > target) {
            return;
        }
        if (cur == target) {
            ++res;
            return;
        }
        for (int value : arr) {
            cur += value;
            dfs(arr, cur, target);
            cur -= value;
        }
    }
}
