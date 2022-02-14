package com.four7ths.dsa.leetcode.week05;

/**
 * 130 被围绕的区域
 * 找到所有被'X'围绕的区域，并将这些区域里所有的'O'用'X'填充：
 * - 任何边界上的'O'都不会被填充为'X'
 * 输入：
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 输出：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */
public class SurroundedRegions {

    private boolean[][] visited;
    private final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int rows;
    int cols;

    public void solve(char[][] board) {
        if (board == null) {
            return;
        }
        rows = board.length;
        cols = board[0].length;
        if (rows < 3 || cols < 3) {
            return;
        }
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][cols - 1] == 'O') {
                dfs(board, i, cols - 1);
            }
        }
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[rows - 1][j] == 'O') {
                dfs(board, rows - 1, j);
            }
        }

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (!visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isValid(x, y) && !visited[x][y] && board[x][y] == 'O') {
                dfs(board, x, y);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
