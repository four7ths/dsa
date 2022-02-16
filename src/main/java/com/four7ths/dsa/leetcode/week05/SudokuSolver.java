package com.four7ths.dsa.leetcode.week05;

/**
 * 19 解数独
 * 编写一个程序解决数独问题（2维数组中非数字用.表示）
 * 一个数独的解法需遵循如下规则：
 * - 数字1-9在每一行只能出现一次
 * - 数字1-9在每一列只能出现一次
 * - 数字1-9在每一个3x3宫内只能出现一次
 */
public class SudokuSolver {

    // rows[i][j]=true: 第i行中存在数字j
    private final boolean[][] rows = new boolean[9][10];
    // cols[i][j]=true: 第i列中存在数字j
    private final boolean[][] cols = new boolean[9][10];
    // blocks[i][j]=true: 第i个3*3block块中存在数字j
    private final boolean[][] blocks = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int n = board[i][j] - '0';
                    rows[i][n] = true;
                    cols[j][n] = true;
                    blocks[i / 3 * 3 + j / 3][n] = true;
                }
            }
        }

        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int x, int y) {
        while (board[x][y] != '.') {
            if (++y == 9) {
                y = 0;
                ++x;
            }
            if (x == 9) {
                return true;
            }
        }

        // Assert (board[x][y]=='.')
        for (int num = 1; num <= 9; num++) {
            int blockIdx = x / 3 * 3 + y / 3;
            if (!rows[x][num] && !cols[y][num] && !blocks[blockIdx][num]) {
                board[x][y] = (char) (num + '0');
                rows[x][num] = true;
                cols[y][num] = true;
                blocks[blockIdx][num] = true;

                if (dfs(board, x, y)) {
                    return true;
                }

                blocks[blockIdx][num] = false;
                cols[y][num] = false;
                rows[x][num] = false;
                board[x][y] = '.';
            }
        }

        return false;
    }
}
