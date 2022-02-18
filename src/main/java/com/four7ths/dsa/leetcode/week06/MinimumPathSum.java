package com.four7ths.dsa.leetcode.week06;

/**
 * 64 最小路径总和
 * 给定一个包含非负整数的m*n网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小，每次只能向下或者向右移动一步
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 1; i < rows; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < cols; i++) {
            grid[0][i] += grid[0][i - 1];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[rows - 1][cols - 1];
    }
}
