package com.four7ths.dsa.leetcode.week06;

import java.util.List;

/**
 * 120 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上
 * dp[i,j]=Math.min(dp[i+1,j], dp[i+1,j+1]) + arr[i,j]
 */
public class TriangleMinPathSum {

    public int minimumTotal(List<List<Integer>> triangle) {

        Integer[] dp = new Integer[triangle.size()];
        triangle.get(triangle.size() - 1).toArray(dp);

        for (int row = triangle.size() - 2; row >= 0; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                dp[col] = Math.min(dp[col], dp[col + 1]) + triangle.get(row).get(col);
            }
        }

        return dp[0];
    }

    public int minimumTotalV2(List<List<Integer>> triangle) {
        for (int row = triangle.size() - 2; row >= 0; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                int oldVal = triangle.get(row).get(col);
                triangle.get(row).set(col,
                        oldVal + Math.min(triangle.get(row + 1).get(col), triangle.get(row + 1).get(col + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
