package com.four7ths.dsa.leetcode.week01;

/**
 * 80 merge sorted array
 * 给定两个有序数组num1和nums2，将两个数组合并成同一个有序数组，假设nums1有足够空间容纳nums2
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[idx--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums2[idx--] = nums2[n--];
        }
    }
}
