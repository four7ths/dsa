package com.four7ths.dsa.leetcode.week05;

import java.util.ArrayList;

/**
 * 52 N皇后问题 II
 * 给定一个数字n，返回n皇后问题所有的方案数量
 */
public class NQueueII {

    private int total;

    // cols[i]==true：第i列存在皇后
    private boolean[] cols;
    // cols[i+j]==true: i+j上存在皇后
    private boolean[] dia1;
    //cols[i-j]上存在皇后，但是i-j可能为负数，需要做一次加上n-1进行平移
    private boolean[] dia2;

    public int totalNQueens(int n) {
        if (n <= 0) {
            return total;
        }
        cols = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];

        solveNQueens0(n, 0, new ArrayList<>());

        return total;
    }

    private void solveNQueens0(int n, int row, ArrayList<Integer> queue) {
        if (row == n) {
            ++total;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!cols[col] && !dia1[row + col] && !dia2[row - col + n - 1]) {
                queue.add(col);
                cols[col] = true;
                dia1[row + col] = true;
                dia2[row - col + n - 1] = true;
                solveNQueens0(n, row + 1, queue);
                dia2[row - col + n - 1] = false;
                dia1[row + col] = false;
                cols[col] = false;
                queue.remove(queue.size() - 1);
            }
        }
    }
}
