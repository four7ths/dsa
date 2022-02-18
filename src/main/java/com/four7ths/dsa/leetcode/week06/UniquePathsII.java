package com.four7ths.dsa.leetcode.week06;

/**
 * 63 不同路径
 * 一个机器人位于一个m*n网格的左上角，数字1代表障碍，无法通过，机器人到达右下角路径所有的可能
 * // 输入: [[0,0,0],
 * //       [0,1,0],
 * //       [0,0,0]]
 * // 输出: 2
 */
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        dp[0][0] = 1;
                    } else if (i == 0) {
                        dp[0][j] = dp[0][j - 1];
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][0];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
