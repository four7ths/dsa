package com.four7ths.dsa.leetcode.week05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39 组合总和 I
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合:
 * - 所有数字（包括 target）都是正整数
 * - 解集中不能包含重复元素
 * 输入：candidates = [2, 3, 6, 7], target = 7
 * 输出：[[7], [2, 2, 3]]
 */
public class CombinationSumI {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        backtrace(candidates, target, 0, 0, new ArrayList<>());
        return res;
    }

    private void backtrace(int[] candidates, int target, int idx, int curSum, ArrayList<Integer> tmp) {
        if (curSum == target) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (curSum + candidates[i] > target) {
                return;
            }
            curSum += candidates[i];
            tmp.add(candidates[i]);
            backtrace(candidates, target, i, curSum, tmp);
            curSum -= candidates[i];
            tmp.remove(tmp.size() - 1);
        }
    }
}
