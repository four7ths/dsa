package com.four7ths.dsa.leetcode.week07;

import java.util.HashSet;
import java.util.Set;

/**
 * 219 存在重复元素 II
 * 给定一个整数数组和一个整数k，判断数组中是否存在两个不同的索引i和j，使得nums[i]=nums[j]，并且i和j的差的绝对值最大为k
 * 即：滑动窗口k+1范围内，是否有重复的元素
 */
public class ContainDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (sets.contains(nums[i])) {
                return true;
            }
            sets.add(nums[i]);
            if (sets.size() == k + 1) {
                sets.remove(nums[i - k]);
            }
        }
        return false;
    }
}
