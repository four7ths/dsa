package com.four7ths.dsa.leetcode.week01;

import com.four7ths.dsa.common.CommonUtils;

import static com.four7ths.dsa.common.CommonUtils.swap;

/**
 * 344 reverse string
 * 反转字符串
 */
public class ReverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int lo = 0, hi = s.length - 1;
        while (lo < hi) {
            swap(s, lo++, hi--);
        }
    }
}
