package com.four7ths.dsa.leetcode.week07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 350 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集
 * input: nums1=[1, 2, 2, 1], nums2=[2, 2]
 * output: [2, 2]
 */
public class IntersectionOfTwoArrayII {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (int item : nums1) {
            freq.merge(item, 1, Integer::sum);
        }
        int idx = 0;
        for (int item : nums2) {
            int cnt = freq.getOrDefault(item, 0);
            if (cnt > 0) {
                nums1[idx++] = item;
                freq.put(item, cnt - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, idx);
    }
}
