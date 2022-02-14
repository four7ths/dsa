package com.four7ths.dsa.leetcode.week05;

import java.util.ArrayList;
import java.util.List;

/**
 * 216 组合总数 III
 * 找出所有相加之和为n的k个数的组合。组合中只允许含有1-9的正整数，并且每种组合中不存在重复的数字
 * - 所有数字都是正整数
 * - 解集不能包含重复的组合
 * 输入：k=3, n=9
 * 输出：[[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {

    List<List<Integer>> res = new ArrayList<>();
    boolean[] visited = new boolean[10];

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k <= 0 || k > 9 || n > 45) {
            return res;
        }
        dfs(k, n, 1, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int k, int n, int idx, int curSum, ArrayList<Integer> tmp) {
        if (n == curSum && tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        if (curSum + idx > n) {
            return;
        }
        for (int i = idx; i <= 9; i++) {
            if (!visited[i]) {
                curSum += i;
                tmp.add(i);
                visited[i] = true;
                dfs(k, n, i + 1, curSum, tmp);
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
                curSum -= i;
            }
        }
    }
}
