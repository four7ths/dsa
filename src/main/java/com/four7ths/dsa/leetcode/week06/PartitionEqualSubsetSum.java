package com.four7ths.dsa.leetcode.week06;

/**
 * 416 分割等和子集
 * 给定一个只包含正整数的非空数组nums，判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        int sum = 0;
        int maxN = 0;
        for (int n : nums) {
            sum += n;
            maxN = Math.max(maxN, n);
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        if (maxN > target) {
            return false;
        }
        int n = nums.length;
        // dp[i][j]: 从nums[0...i]中选择若干（可以是0）个数字，且只能选择一次，使得它们总和为j
        boolean[][] dp = new boolean[n][target + 1];

        // dp[0...i][0]: 从nums[0...i]中不选择任何数字，总和为0成立
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        // dp[0][nums[0]]: 从nums[0]中选择nums[0]，总和为nums[0]成立
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                // 当前值小于目标值：当前值可以选也可以不选择
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

    public boolean canPartitionV2(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        int sum = 0;
        int maxN = 0;
        for (int n : nums) {
            sum += n;
            maxN = Math.max(maxN, n);
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        if (maxN > target) {
            return false;
        }
        int n = nums.length;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
