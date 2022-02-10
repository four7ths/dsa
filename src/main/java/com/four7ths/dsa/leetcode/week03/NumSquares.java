package com.four7ths.dsa.leetcode.week03;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 279 完全平方数
 * 给定一个整数n，返回和为n的完全平方数的最小数量
 */
public class NumSquares {
    public int numSquares(int n) {

        // dp[i]: 整数i所需要的完全平方数最小数量
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 初始化
            for (int j = 1; ; j++) {
                int cmp = i - j * j;
                if (cmp < 0) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[cmp] + 1);
            }
        }

        return dp[n];
    }

    // bfs & 记忆化搜索
    public int numSquaresV2(int n) {
        int[] cnt = new int[n + 1];
        Deque<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(n);
        visited[n] = true;

        while (!queue.isEmpty()) {
            Integer item = queue.remove();
            for (int i = 1; ; i++) {
                int cmp = item - i * i;
                if (cmp == 0) {
                    return cnt[item] + 1;
                } else if (cmp < 0) {
                    break;
                } else {
                    if (!visited[cmp]) {
                        visited[cmp] = true;
                        cnt[cmp] = cnt[item] + 1;
                        queue.add(cmp);
                    }
                }
            }
        }
        throw new RuntimeException();
    }
}
