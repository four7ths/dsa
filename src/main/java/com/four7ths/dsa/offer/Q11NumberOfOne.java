package com.four7ths.dsa.offer;

/**
 * 二进制中1的个数，负数用补码表示
 */
public class Q11NumberOfOne {
    public int numberOf1(int n) {
        int cnd = 0;
        while (n != 0) {
            ++cnd;
            n &= (n - 1);
        }
        return cnd;
    }
}
