package com.four7ths.dsa.leetcode.week01;

/**
 * 167 two sum II
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数，返回两个数对应索引坐标
 */
public class TwoSumII {
    public int[] twoSum(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int tmp = nums[lo] + nums[hi];
            if (tmp == target) {
                return new int[] {lo, hi};
            } else if (tmp > target) {
                lo++;
            } else {
                hi--;
            }
        }
        return new int[] {-1, -1};
    }
}
