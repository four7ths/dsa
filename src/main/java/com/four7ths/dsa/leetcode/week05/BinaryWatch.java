package com.four7ths.dsa.leetcode.week05;

import java.util.ArrayList;
import java.util.List;

/**
 * 401 二进制手表
 * 二进制手表顶部有4个LED代表小时（0-11），底部的6个LED 代表分钟（0-59）
 * 给定一个数字n，表示有n个LED灯是亮状态，判断有多少中可能的合法时间
 */
public class BinaryWatch {

    // 前4位：顶部4个LED
    // 后6位：底部6个LED
    private static final int[] NUMS = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};

    List<String> res = new ArrayList<>();

    // 回溯
    public List<String> readBinaryWatch(int num) {
        if (num < 0 || num >= 9) {
            return res;
        }
        dfs(num, 0, 0, 0);
        return res;
    }

    private void dfs(int num, int idx, int h, int m) {
        if (h > 11 || m > 59) {
            return;
        }
        if (num == 0) {
            res.add(h + ":" + ((m > 9) ? m : ("0" + m)));
            return;
        }
        for (int i = idx; i < NUMS.length; i++) {
            if (i < 4) {
                dfs(num - 1, i + 1, h + NUMS[i], m);
            } else {
                dfs(num - 1, i + 1, h, m + NUMS[i]);
            }
        }
    }

    public List<String> readBinaryWatchV2(int num) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    res.add(i + ":" + ((j > 9) ? j : ("0" + j)));
                }
            }
        }
        return res;
    }
}
