package com.four7ths.dsa.leetcode.week07;

import java.util.HashMap;
import java.util.Map;

/**
 * 447 回旋镖的数量
 * 给定平面上n对不同的点，【回旋镖】是由点表示的元组(i, j, k)，其中i和j之间的距离和i和k之间的距离相等（需要考虑元组的顺序）
 */
public class NumberOfBoomerangs {

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; ++i) {
            int[] p = points[i];
            Map<Integer, Integer> maps = new HashMap<>();
            for (int j = 0; j < points.length; ++j) {
                int[] q = points[j];
                if (j != i) {
                    int dist = (int) (Math.pow(p[0] - q[0], 2) + Math.pow(p[1] - q[1], 2));
                    maps.merge(dist, 1, Integer::sum);
                }
            }
            for (Integer value : maps.values()) {
                res += value * (value - 1);
            }
        }
        return res;
    }
}
