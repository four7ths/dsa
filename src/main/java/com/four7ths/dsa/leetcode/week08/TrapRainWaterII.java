package com.four7ths.dsa.leetcode.week08;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 407 接雨滴 II
 */
public class TrapRainWaterII {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        // {x,y,height}
        Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        boolean[][] vis = new boolean[m][n];
        for (int i = 0; i < n; i++) {
            q.add(new int[] {0, i, heightMap[0][i]});
            q.add(new int[] {m - 1, i, heightMap[m - 1][i]});
            vis[0][i] = vis[m - 1][i] = true;
        }
        for (int i = 1; i < m - 1; i++) {
            q.add(new int[] {i, 0, heightMap[i][0]});
            q.add(new int[] {i, n - 1, heightMap[i][n - 1]});
            vis[i][0] = vis[i][n - 1] = true;
        }

        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int ans = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int h = poll[2];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (vis[nx][ny]) {
                    continue;
                }
                ans += Math.max(0, h - heightMap[nx][ny]);
                // 注意这里应该是灌过水之后的高度
                q.add(new int[] {nx, ny, Math.max(heightMap[nx][ny], h)});
                vis[nx][ny] = true;
            }
        }
        return ans;
    }
}
