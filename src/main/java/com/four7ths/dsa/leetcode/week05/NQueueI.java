package com.four7ths.dsa.leetcode.week05;

import java.util.ArrayList;
import java.util.List;

/**
 * 51 N皇后问题 I
 * 给定一个数字n，返回n皇后可能的排列方式：
 * 输入: n=4
 * 输出: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 */
public class NQueueI {

    private final List<List<String>> res = new ArrayList<>();

    // cols[i]==true：第i列存在皇后
    private boolean[] cols;
    // cols[i+j]==true: i+j上存在皇后
    private boolean[] dia1;
    //cols[i-j]上存在皇后，但是i-j可能为负数，需要做一次加上n-1进行平移
    private boolean[] dia2;

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return res;
        }
        cols = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];

        solveNQueens0(n, 0, new ArrayList<>());

        return res;
    }

    private void solveNQueens0(int n, int row, ArrayList<Integer> queue) {
        if (row == n) {
            generateBoard(n, queue);
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

    private void generateBoard(int n, ArrayList<Integer> queue) {
        List<String> tmp = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        for (int i = 0; i < n; i++) {
            sb.setCharAt(queue.get(i), 'Q');
            tmp.add(sb.toString());
            sb.setCharAt(queue.get(i), '.');
        }

        res.add(tmp);
    }
}
