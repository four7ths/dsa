package com.four7ths.dsa.leetcode.week07;

import java.util.HashMap;
import java.util.Map;

/**
 * 454 四数之和 II
 * 给定四个包含整数的数组列表A, B, C, D，计算有多少个元组(i, j, k, l)，使得A[i]+B[j]+C[k]+D[l]=0
 * A, B, C, D具有相同的长度N，且 0 ≤ N ≤ 500
 */
public class FourSumII {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int key = a + b;
                maps.merge(key, 1, Integer::sum);
            }
        }
        int res = 0;
        for (int c : C) {
            for (int d : D) {
                Integer value = maps.get(-c - d);
                if (value != null) {
                    res += value;
                }
            }
        }
        return res;
    }

}
