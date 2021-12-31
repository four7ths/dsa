package com.four7ths.dsa.leetcode.week02;

/**
 * 76 minimum window substring
 * 给定一个字符串s、一个字符串t，请在字符串s里面找出：包含t所有字母的最小子串
 */
public class MinWindowSubstring {

    // 'A' -> 'z' ==> 65 -> 122
    private static final int LEN = 58;

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int l = 0, r = -1;
        int[] freqS = new int[LEN];
        int[] freqT = new int[LEN];
        for (char ch : t.toCharArray()) {
            freqT[ch - 'A']++;
        }

        int start = -1, end = s.length() + 1;
        while (l <= s.length() - t.length()) {
            if ((r - l + 1) < t.length()) {
                if (r + 1 < s.length()) {
                    ++freqS[s.charAt(++r) - 'A'];
                    continue;
                } else {
                    break;
                }
            }
            // 当前区间长度大于等于t.length
            int idx = 0;
            while (idx < LEN) {
                if (freqS[idx] < freqT[idx]) {
                    break;
                }
                ++idx;
            }
            // 当前区间并不能包含字符串t，只能尝试s[l...r+1]
            if (idx != LEN) {
                if (r + 1 < s.length()) {
                    ++freqS[s.charAt(++r) - 'A'];
                } else {
                    break;
                }
            } else { // 当前区间包含字符串t
                if ((r - l + 1) == t.length()) {
                    return s.substring(l, r + 1);
                } else {
                    if (r - l < end - start) {
                        start = l;
                        end = r;
                    }
                    --freqS[s.charAt(l++) - 'A'];
                }
            }
        }
        return start == -1 ? "" : s.substring(start, end + 1);
    }
}
