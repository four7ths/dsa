package com.four7ths.dsa.leetcode.week04;

import java.util.ArrayList;
import java.util.List;

/**
 * 93 复原IP地址
 * 给定一个字符串，返回可能所有的合法ip地址
 */
public class RestoreIpAddress {

    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }
        dfs(s, 0, new ArrayList<>());
        return res;
    }

    private void dfs(String s, int idx, ArrayList<String> ips) {
        if (idx == s.length() && ips.size() == 4) {
            res.add(String.join(".", ips));
            return;
        }
        // 剪枝操作
        if (s.length() - idx > (4 - ips.size()) * 3) {
            return;
        }
        for (int end = 1; end <= 3; end++) {
            if (idx + end > s.length()) {
                return;
            }
            String seg = s.substring(idx, idx + end);
            if ((seg.startsWith("0") && seg.length() > 1) || (seg.length() == 3 && Integer.parseInt(seg) > 255)) {
                return;
            }
            ips.add(seg);
            // 从idx+end开始进行下一次回溯
            dfs(s, idx + end, ips);
            ips.remove(ips.size() - 1);
        }
    }
}
