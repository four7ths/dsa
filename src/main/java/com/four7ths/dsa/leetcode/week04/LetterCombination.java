package com.four7ths.dsa.leetcode.week04;

import java.util.ArrayList;
import java.util.List;

/**
 * 17 电话号码的字母组合
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合，答案可以按任意顺序返回
 */
public class LetterCombination {

    private final List<String> res = new ArrayList<>();

    private static final String[] LETTERS = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) {
            return res;
        }
        dfs(digits, 0, new StringBuilder());
        return res;
    }

    private void dfs(String digits, int idx, StringBuilder sb) {
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letter = LETTERS[digits.charAt(idx) - '0'];
        for (int i = 0; i < letter.length(); i++) {
            dfs(digits, idx + 1, sb.append(letter.charAt(i)));
            // 注意移除
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
