package com.four7ths.dsa.leetcode.week05;

import java.util.ArrayList;
import java.util.List;

/**
 * 417 太平洋大西洋水流问题
 * 给定一个m*n的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标
 * // 输入：
 * // 太平洋 ~   ~   ~   ~   ~
 * //        ~  1   2   2   3  (5) *
 * //        ~  3   2   3  (4) (4) *
 * //        ~  2   4  (5)  3   1  *
 * //        ~ (6) (7)  1   4   5  *
 * //        ~ (5)  1   1   2   4  *
 * //           *   *   *   *   * 大西洋
 * // 输出：
 * // [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
 */
public class PacificAtlanticWaterFlow {
    private final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private int rows;
    private int cols;
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        rows = matrix.length;
        cols = matrix[0].length;
        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            dfs(matrix, i, 0, pac);
            dfs(matrix, i, cols - 1, atl);
        }
        for (int i = 0; i < cols; i++) {
            dfs(matrix, 0, i, pac);
            dfs(matrix, rows - 1, i, atl);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pac[i][j] && atl[i][j]) {
                    List<Integer> cord = new ArrayList<>();
                    cord.add(i);
                    cord.add(j);
                    res.add(cord);
                }
            }
        }

        return res;
    }

    private void dfs(int[][] matrix, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isValid(x, y) && matrix[x][y] >= matrix[i][j] && !visited[x][y]) {
                dfs(matrix, x, y, visited);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
