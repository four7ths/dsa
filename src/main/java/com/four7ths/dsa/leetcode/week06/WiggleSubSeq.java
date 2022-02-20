package com.four7ths.dsa.leetcode.week06;

/**
 * 376 摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度
 */
public class WiggleSubSeq {

    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int res = 1;
        int[] diff = new int[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            diff[i - 1] = nums[i] - nums[i - 1];
        }

        int idx = 0;
        while (idx < diff.length && diff[idx] == 0) {
            ++idx;
        }
        if (idx == diff.length) {
            return res;
        }

        // [1,1,7,4,9,2,5]
        // 0, 6, -3, 5, -7, 3
        boolean isPos = diff[idx] > 0;
        ++res;
        for (int i = idx + 1; i < diff.length; i++) {
            if (isPos && diff[i] > 0 || !isPos && diff[i] < 0 || diff[i] == 0) {
                continue;
            }
            isPos = !isPos;
            ++res;
        }
        return res;
    }

    public int wiggleMaxLengthV2(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] down = new int[nums.length];
        int[] up = new int[nums.length];
        down[0] = 1;
        up[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    public int wiggleMaxLengthV3(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int down = 1;
        int up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(down, up);
    }
}
