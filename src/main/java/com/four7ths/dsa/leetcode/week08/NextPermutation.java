package com.four7ths.dsa.leetcode.week08;

/**
 * 31 下一个排列
 * 整数数组的下一个排列 是指其整数的下一个字典序更大的排列
 */
public class NextPermutation {

    // 5 3 9 4 1 -> 5 4 1 3 9
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        // 逆序寻找第一个nums[i] < nums[i+1]
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (i < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        // nums[i+1...len]逆序寻找第一个nums[j] > nums[i]
        int j = nums.length - 1;
        while (j > i && nums[j] <= nums[i]) {
            --j;
        }
        swap(nums, i, j);
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }
}
