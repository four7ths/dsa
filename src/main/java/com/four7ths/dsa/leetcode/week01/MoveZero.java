package com.four7ths.dsa.leetcode.week01;

/**
 * 283 move zero
 * 给定一个数组，将所有元素0全部移动到数组末尾，非0元素相对位置保持不变
 */
public class MoveZero {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int idx = -1;
        for (int n : nums) {
            if (n != 0) {
                nums[++idx] = n;
            }
        }
        for (int i = idx + 1; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
