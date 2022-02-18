package com.four7ths.dsa.leetcode.week06;

/**
 * 70 爬楼梯 I
 * n阶台阶，每次只能走1或2步，有多少种可能能到达台阶顶部
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int f1 = 1;
        int f2 = 2;
        int fn = 0;
        for (int i = 2; i < n; i++) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }
        return fn;
    }
}
