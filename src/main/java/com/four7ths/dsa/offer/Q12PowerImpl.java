package com.four7ths.dsa.offer;

/**
 * 数值的整数次方，保证base和exponent不同时为0
 */
public class Q12PowerImpl {
    public double powerV1(double base, int exp) {
        boolean flag = exp < 0;
        if (flag) {
            exp = -exp;
        }
        double ans = powerInternal(base, exp);
        return flag ? (1 / ans) : ans;
    }

    //递归
    private double powerInternal(double base, int exp) {
        if (exp == 0) {
            return 1;
        }
        if (exp == 1) {
            return base;
        }
        double ans = powerInternal(base, exp >> 1);
        ans *= ans;
        if ((exp & 1) == 1) {
            ans *= base;
        }
        return ans;
    }

    // 迭代
    public double powerV2(double base, int exp) {
        boolean flag = exp < 0;
        if (flag) {
            exp = -exp;
        }
        double ans = 1.0;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                ans *= base;
            }
            exp >>= 1;
            base *= base;
        }
        return flag ? (1 / ans) : ans;
    }
}
