package com.four7ths.dsa.leetcode.week01;

/**
 * 80 remove duplicate element from sorted array II
 * 给定一个有序数组，删除数组中重复元素，使得每个元素最多出现2次，并返回最终数组元素
 *
 * @author tanglei <tanglei@kuaishou.com>
 * Created on 2021-12-24
 */
public class RemoveDupFromSortedArrayII {
    public int removeDuplicatesII(int[] nums) {
        int idx = 0;
        for (int n : nums) {
            if (idx < 2 || n > nums[idx - 2]) {
                nums[idx++] = n;
            }
        }
        return idx;
    }
}
