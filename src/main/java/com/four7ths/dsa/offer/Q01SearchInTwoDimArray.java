package com.four7ths.dsa.offer;

/**
 * 在一个二维数组中查找target
 * - 每一行都按照从左到右递增的顺序排序
 * - 每一列都按照从上到下递增的顺序排序
 */
public class Q01SearchInTwoDimArray {
    public boolean findNumInMatrix(int target, int[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        // 右上角
        int i = 0;
        int j = cols - 1;

        while (i < rows && j >= 0) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                --j;
            } else {
                ++i;
            }
        }
        return false;
    }
}
