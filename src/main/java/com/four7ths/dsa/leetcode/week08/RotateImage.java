package com.four7ths.dsa.leetcode.week08;

/**
 * 48 旋转图像
 * 原地将图像旋转90度
 */
public class RotateImage {

    public void rotate(int[][] matrix) {

        // 左上-右下对角线
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 垂直图像中间镜像
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = tmp;

            }
        }
    }
}
