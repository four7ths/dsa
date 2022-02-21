package com.four7ths.dsa.leetcode.week06;

/**
 * 494 目标和
 * 找到nums一个正子集（P）和一个负子集（N），使得总和等于target
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 */
public class TargetSum {
    //  找到nums一个正子集（P）和一个负子集（N），使得总和等于target
    //  sum(P) - sum(N) = target
    //  sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
    //  2 * sum(P) = target + sum(nums)
    //  原来的问题已转化为一个求子集的和问题
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return sum < target || (target + sum) % 2 > 0 ? 0 : subsetSum(nums, (target + sum) >>> 1);
    }

    public int subsetSum(int[] nums, int s) {
        // dp[i]: 从nums选择若干数字，使得和为i的方案个数
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = s; i >= n; --i) {
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }

    private int cnt = 0;

    public int findTargetSumWaysV2(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return cnt;
    }

    // O(2^n): n=nums.length
    private void dfs(int[] nums, int target, int idx, int sum) {
        if (idx == nums.length) {
            if (sum == target) {
                ++cnt;
            }
        } else {
            dfs(nums, target, idx + 1, sum + nums[idx]);
            dfs(nums, target, idx + 1, sum - nums[idx]);
        }
    }
}
