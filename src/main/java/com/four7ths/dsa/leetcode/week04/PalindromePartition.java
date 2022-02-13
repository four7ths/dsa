package com.four7ths.dsa.leetcode.week04;

import java.util.ArrayList;
import java.util.List;

/**
 * 131 分割回文串
 * 给定一个字符串s，将s分割成一些子串，使每个子串都是回文串
 */
public class PalindromePartition {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return res;
        }
        backtrace(s, 0, new ArrayList<>());
        return res;
    }

    private void backtrace(String s, int idx, ArrayList<String> tmp) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int end = 1; idx + end <= s.length(); end++) {
            String seg = s.substring(idx, idx + end);
            if (isPalindrome(seg)) {
                tmp.add(seg);
                backtrace(s, idx + end, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
