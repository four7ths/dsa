package com.four7ths.dsa.leetcode.week07;

import java.util.Arrays;

/**
 * 242 有效的字母异位词
 * 判断两个字符串是否是有效的【异位词】
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] maps = new int[26];
        for (int i = 0; i < t.length(); ++i) {
            maps[s.charAt(i) - 'a'] += 1;
            maps[t.charAt(i) - 'a'] -= 1;
        }
        return Arrays.stream(maps).allMatch(e -> e == 0);
    }
}
