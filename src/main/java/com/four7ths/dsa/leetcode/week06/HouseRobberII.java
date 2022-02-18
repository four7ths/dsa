package com.four7ths.dsa.leetcode.week06;

import java.util.Arrays;

/**
 * 213 打家劫舍 II
 * 给定一个整数数组，偷取数组中数字达到最大值，要求所偷取的数字不能相邻，首尾也不能相连
 */
public class HouseRobberII {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(doRob(Arrays.copyOfRange(nums, 1, nums.length)),
                doRob(Arrays.copyOfRange(nums, 0, nums.length - 1)));
    }

    private int doRob(int[] arr) {
        int prev = 0;
        int cur = 0;
        for (int n : arr) {
            int tmp = cur;
            cur = Math.max(cur, prev + n);
            prev = tmp;
        }
        return cur;
    }
}
