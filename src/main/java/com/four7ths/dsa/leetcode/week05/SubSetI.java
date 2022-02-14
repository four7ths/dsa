package com.four7ths.dsa.leetcode.week05;

import java.util.ArrayList;
import java.util.List;

/**
 * 78 子集 I
 * 给定一组不含重复元素的整数数组nums，返回该数组所有可能的子集（幂集）:
 * 输入： nums = [1,2,3]
 * 输出：[[3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], []]
 */
public class SubSetI {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        res.add(new ArrayList<>());
        for (int len = 1; len <= nums.length; ++len) {
            dfs(nums, len, 0, new ArrayList<>());
        }
        return res;
    }

    private void dfs(int[] nums, int len, int idx, ArrayList<Integer> tmp) {
        if (tmp.size() == len) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            tmp.add(nums[i]);
            dfs(nums, len, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
