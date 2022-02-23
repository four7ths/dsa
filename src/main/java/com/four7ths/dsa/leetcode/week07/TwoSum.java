package com.four7ths.dsa.leetcode.week07;

import java.util.HashMap;
import java.util.Map;

/**
 * 1 两数之和
 * 给定一个整数数组nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // item -> index
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int completion = target - nums[i];
            if (maps.containsKey(completion)) {
                return new int[] {i, maps.get(completion)};
            } else {
                maps.put(nums[i], i);
            }
        }
        return new int[] {-1, -1};
    }

}
