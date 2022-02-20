package com.four7ths.dsa.leetcode.week06;

import java.util.Arrays;

/**
 * 300 最长递增子序列
 * 找到无序数组中最长严格递增子序列
 */
public class LongestIncrSubSeq {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int n : dp) {
            max = Math.max(n, max);
        }
        return max;
    }

    public int lengthOfLISV2(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int n : nums) {
            int expectedIdx = Arrays.binarySearch(dp, 0, len, n);
            if (expectedIdx < 0) {
                expectedIdx = -(expectedIdx + 1);
            }
            dp[expectedIdx] = n;
            if (expectedIdx == len) {
                ++len;
            }
        }
        return len;
    }
}
