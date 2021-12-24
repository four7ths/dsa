package com.four7ths.dsa.leetcode.week01;

import static com.four7ths.dsa.common.CommonUtils.swap;

/**
 * 75 sort color
 * 给定一个只包含0，1和2的数组，原地排序数组
 */
public class SortColor {
    public void sortColor(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int lo = 0;
        int hi = nums.length - 1;
        int i = lo;
        // arr[0,lo-1]             -> 0
        // arr[lo,hi]              -> 1
        // arr[hi+1,nums.length-1] -> 2
        while (i <= hi) { // [2 0 1]
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 0) {
                swap(nums, lo++, i++);
            } else {
                swap(nums, i, hi--);
            }
        }
    }
}
