package com.four7ths.dsa.leetcode.week02;

/**
 * 3 long substring without repeating characters
 * 给定一个字符串，找出其中不含有重复字符的最长子串的长度
 */
public class LongSubStringWithoutRepeat {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int l = 0;
        int r = -1;
        int[] freq = new int[256];
        int ret = 0;
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)]++;
            } else {
                freq[s.charAt(l++)]--;
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }
}
