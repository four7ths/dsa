package com.four7ths.dsa.leetcode.week03;

/**
 * 334 递增的三元子序列
 * 给定一个整数数组，判断数组中是否存在长度为3的递增子序列
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n > mid) {
                return true;
            } else if (n < min) {
                min = n;
            } else if (n > min && n < mid) {
                mid = n;
            }
        }

        return false;
    }
}
