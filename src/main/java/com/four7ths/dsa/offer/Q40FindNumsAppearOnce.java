package com.four7ths.dsa.offer;

/**
 * 数组中只出现一次的数字
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次，找出这两个只出现一次的数字
 */
public class Q40FindNumsAppearOnce {
    public int[] findNumsAppearOnce(int[] array) {
        int res = 0;
        for (int elem : array) {
            res ^= elem;
        }
        int bit = Integer.lowestOneBit(res);
        int n1 = 0;
        int n2 = 0;
        for (int elem : array) {
            if ((elem & bit) == bit) {
                n1 ^= elem;
            } else {
                n2 ^= elem;
            }
        }
        return new int[] {n1, n2};
    }
}
