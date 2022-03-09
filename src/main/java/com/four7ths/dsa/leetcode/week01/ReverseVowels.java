package com.four7ths.dsa.leetcode.week01;

import static com.four7ths.dsa.common.CommonUtils.swap;

/**
 * 345 reverse vowels of a string
 * 反转字符串中元音字母
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int lo = 0;
        int hi = chars.length - 1;
        while (lo < hi) {
            while (lo < hi && !isVowel(chars[lo])) {
                lo++;
            }
            while (lo < hi && !isVowel(chars[hi])) {
                hi--;
            }
            swap(chars, lo++, hi--);
        }
        return new String(chars);
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'A' ||
                ch == 'e' || ch == 'E' ||
                ch == 'i' || ch == 'I' ||
                ch == 'o' || ch == 'O' ||
                ch == 'u' || ch == 'U';
    }
}
