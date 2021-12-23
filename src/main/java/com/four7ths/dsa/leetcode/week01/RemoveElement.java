package com.four7ths.dsa.leetcode.week01;

/**
 * 27 remove element
 * 给定一个数组和目标值target，【原地】删除数组中所有等于target的元素，并返回删除后数组长度
 *
 * @author tanglei <tanglei@kuaishou.com>
 * Created on 2021-12-21
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int idx = -1;
        for (int n : nums) {
            if (n != val) {
                nums[++idx] = n;
            }
        }
        for (int i = idx + 1; i < nums.length; i++) {
            nums[i] = 0;
        }
        return idx + 1;
    }
}
