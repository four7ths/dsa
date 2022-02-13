package com.four7ths.dsa.leetcode.week04;

import java.util.ArrayList;
import java.util.List;

/**
 * 77 组合
 * 给定两个整数n和k，返回1...n中所有可能的k个数的组合
 */
public class Combination {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrace(n, k, 1, new ArrayList<>());
        return res;
    }

    private void backtrace(int n, int k, int idx, ArrayList<Integer> tmp) {
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        // 搜索上界 + 还需要元素个数 - 1 = n
        for (int i = idx; i <= n - (k - tmp.size()) + 1; i++) {
            tmp.add(i);
            backtrace(n, k, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
