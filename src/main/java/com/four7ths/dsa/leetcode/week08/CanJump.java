package com.four7ths.dsa.leetcode.week08;

/**
 * 55 跳跃游戏
 */
public class CanJump {

    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = dp[j] && ((i - j) <= nums[j]);
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[nums.length - 1];
    }

    public boolean canJumpV2(int[] nums) {
        int mostRight = 0;
        for (int i = 0; i < nums.length; i++) {
            if (mostRight < i) {
                return false;
            }
            mostRight = Math.max(mostRight, i + nums[i]);
        }
        return true;
    }
}
