package com.four7ths.dsa.leetcode.week08;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32 最长有效括号
 * 只包含'('和')'的字符串，找出最长有效（格式正确且连续）括号子串的长度
 */
public class LongestValidParentheses {

    // ")()((())"的mark为[1, 0, 0, 1, 0, 0, 0, 0]
    public int longestValidParentheses(String s) {
        int[] mark = new int[s.length()];
        Deque<Integer> stk = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stk.push(i);
            } else {
                if (stk.isEmpty()) {
                    mark[i] = 1;
                } else {
                    stk.pop();
                }
            }
        }

        stk.forEach(idx -> mark[idx] = 1);

        int len = 0;
        int ans = 0;
        for (int n : mark) {
            if (n == 1) {
                len = 0;
                continue;
            }
            ++len;
            ans = Math.max(ans, len);
        }
        return ans;
    }

    public int longestValidParenthesesV2(String s) {
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2;
                    if (i - 2 >= 0) {
                        dp[i] += dp[i - 2];
                    }
                } else {
                    // dp[i-1] = 2, dp[idx-1] = 2
                    //  0       idx       i
                    //  )  (  )  (  (  )  )
                    int idx = i - dp[i - 1] - 1;
                    if (idx >= 0 && s.charAt(idx) == '(') {
                        dp[i] = dp[i - 1] + 2;
                        if (idx - 1 >= 0) {
                            dp[i] += dp[idx - 1];
                        }
                    }
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
