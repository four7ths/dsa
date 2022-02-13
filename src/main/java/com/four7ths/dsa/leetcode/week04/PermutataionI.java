package com.four7ths.dsa.leetcode.week04;

import static com.four7ths.dsa.common.CommonUtils.swap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 46 全排列 I
 * 给定一个不含重复数字的数组，返回其所有可能的全排列
 */
public class PermutataionI {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
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
                visited[i] = true;
                tmp.add(nums[i]);
                backtrace(nums, idx + 1, tmp, visited);
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public List<List<Integer>> permuteV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        permuteV2(nums, 0, nums.length - 1);
        return res;
    }

    private void permuteV2(int[] nums, int l, int r) {
        if (l == r) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = l; i <= r; i++) {
            swap(nums, i, l);
            permuteV2(nums, l + 1, r);
            swap(nums, i, l);
        }
    }
}
