package com.four7ths.dsa.leetcode.week07;

import java.util.Arrays;

/**
 * 16 最接近的三数之和
 * 给定一个包括n个整数的数组nums和一个目标值target，找出nums中的三个整数，使得它们的和与target最接近
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; ++i) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int curSum = nums[i] + nums[l] + nums[r];
                if (Math.abs(curSum - target) < Math.abs(closest - target)) {
                    closest = curSum;
                }
                if (curSum == target) {
                    return curSum;
                } else if (curSum > target) {
                    --r;
                } else {
                    ++l;
                }
            }
        }
        return closest;
    }
}
