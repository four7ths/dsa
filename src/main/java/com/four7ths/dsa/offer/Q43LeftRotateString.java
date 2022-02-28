package com.four7ths.dsa.offer;

/**
 * 左旋转字符串
 */
public class Q43LeftRotateString {
    public String leftRotateString(String str, int n) {
        if ("".equals(str)) {
            return "";
        }
        return str.substring(n) + str.substring(0, n % str.length());
    }
}
