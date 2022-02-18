package com.four7ths.dsa.leetcode.week06;

import java.util.Arrays;

/**
 * 62 不同路径 I
 * 一个机器人位于一个m*n网格的左上角，机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角，总共有多少条不同的路径
 */
public class UniquePathsI {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsV2(int m, int n) {
        int[] dp = new int[m];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[m - 1];
    }

    public int uniquePathV3(int m, int n) {
        // C(m+n-2, n-1)
        return 0;
    }
}
