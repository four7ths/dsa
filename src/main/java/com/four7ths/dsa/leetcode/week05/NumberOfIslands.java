package com.four7ths.dsa.leetcode.week05;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 200 岛屿的数量
 * 给定一个由'1'（陆地）和'0'（水）组成的的二维网格，计算岛屿的数量，一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的：
 * - 假设网格的四个边均被水包围。
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 */
public class NumberOfIslands {
    private boolean[][] visited;
    private final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int rows;
    int cols;

    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        rows = grid.length;
        cols = grid[0].length;
        if (rows == 0 || cols == 0) {
            return 0;
        }
        int res = 0;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    ++res;
                    dfs(grid, i, j);
                    // bfs(grid, i, j);
                }
            }
        }
        return res;
    }

    // dfs
    private void dfs(char[][] grid, int i, int j) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isValid(x, y) && !visited[x][y] && grid[x][y] == '1') {
                dfs(grid, x, y);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // bfs
    private void bfs(char[][] grid, int i, int j) {
        Deque<Point> queue = new LinkedList<>();
        Point start = new Point(i, j);
        queue.add(start);

        while (!queue.isEmpty()) {
            Point point = queue.remove();
            for (int[] dir : dirs) {
                int x = point.x + dir[0];
                if (x < 0 || x >= rows) {
                    continue;
                }
                int y = point.y + dir[1];
                if (y < 0 || y >= cols) {
                    continue;
                }
                if (!visited[x][y] && grid[x][y] == '1') {
                    visited[x][y] = true;
                    queue.add(new Point(x, y));
                }
            }
        }
    }
}