package com.four7ths.dsa.leetcode.week05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40 组合总和 II
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合，
 * candidates中的每个数字在每个组合中只能使用一次:
 * - 所有数字（包括目标数）都是正整数
 * - 解集不能包含重复的组合
 * 输入：candidates = [2,5,2,1,2], target = 5
 * 输出：[[1, 2, 2], [5]]
 */
public class CombinationSumII {

    List<List<Integer>> res = new ArrayList<>();
    private boolean[] visited;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int[] candidates, int target, int idx, int curSum, ArrayList<Integer> tmp) {
        if (curSum == target) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (!visited[i]) {
                if (curSum + candidates[i] > target) {
                    return;
                }
                if (i > 0 && candidates[i] == candidates[i - 1] && !visited[i - 1]) {
                    // 注意是continue，之后还有可能有数据满足要求
                    continue;
                }
                curSum += candidates[i];
                visited[i] = true;
                tmp.add(candidates[i]);
                dfs(candidates, target, i + 1, curSum, tmp);
                curSum -= candidates[i];
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
