package com.four7ths.dsa.leetcode.week04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47 全排列 II
 * 给定一个可包含重复数字的序列，按任意顺序返回所有不重复的全排列
 */
public class PermutationII {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        backtrace(nums, 0, new ArrayList<>(), visited);
        return res;
    }

    private void backtrace(int[] nums, int idx, ArrayList<Integer> tmp, boolean[] visited) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    continue;
                }
                tmp.add(nums[i]);
                visited[i] = true;
                backtrace(nums, idx + 1, tmp, visited);
                tmp.remove(tmp.size() - 1);
                visited[i] = false;
            }
        }
    }
}
