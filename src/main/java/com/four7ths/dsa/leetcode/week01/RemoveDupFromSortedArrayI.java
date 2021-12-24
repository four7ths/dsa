package com.four7ths.dsa.leetcode.week01;

/**
 * 26 remove from duplicate element from sorted array I
 * 给定一个排序后的数组，删除重复的元素，保证相同元素只出现一次，并返回不重复元素的个数
 */
public class RemoveDupFromSortedArrayI {
    public int removeDuplicatesI(int[] nums) {
        int idx = 0;
        for (int n : nums) {
            if (n != nums[idx]) {
                nums[++idx] = n;
            }
        }
        return idx + 1;
    }
}
