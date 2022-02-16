package com.four7ths.dsa.leetcode.week05;

/**
 * 125 验证回文字符串
 * 给定一个字符串，判断其是否是回文字符串（忽略大小写，且只考虑字母或者数字），空字符串认为是合法的回文字符串
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if ("".equals(s)) {
            return true;
        }
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                ++l;
            }
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) {
                --r;
            }
            if (l < r) {
                if (s.charAt(l) != s.charAt(r)) {
                    return false;
                }
                ++l;
                --r;
            }
        }
        return false;
    }
}
