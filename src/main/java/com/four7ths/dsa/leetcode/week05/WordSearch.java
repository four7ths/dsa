package com.four7ths.dsa.leetcode.week05;

/**
 * 79 单词搜索
 * 定一个二维网格和一个单词，找出该单词是否存在于网格中：
 * // board = [['A','B','C','E'],
 * //          ['S','F','C','S'],
 * //          ['A','D','E','E']]
 * 输入：word = "ABCCED", 输出：true
 * 输入：word = "SEE", 输出：true
 * 输入：word = "ABCB", 输出：false
 */
public class WordSearch {

    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean[][] visited;
    private int rows;
    private int cols;

    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }
        rows = board.length;
        cols = board[0].length;
        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length() - 1) {
            return board[i][j] == word.charAt(idx);
        }
        if (word.charAt(idx) == board[i][j]) {
            visited[i][j] = true;
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (isValid(x, y) && !visited[x][y] && dfs(board, word, x, y, idx + 1)) {
                    return true;
                }
            }
            visited[i][j] = false;
        }
        return false;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
