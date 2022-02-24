package com.four7ths.dsa.leetcode.week08;

import java.util.Arrays;

/**
 * 435 无重叠区间
 * 给定一个区间的集合intervals，其中intervals[i]=[start_i, end_i]，返回需要移除区间的最小数量，使剩余区间互不重
 */
public class NonOverlappingIntervals {

    public int eraseOverlapInterval(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 区间右侧从小到大排序，相同右侧按照左侧从小到大排序
        Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        int cnt = 1;
        int pre = 0;
        for (int i = 1; i < intervals.length; ++i) {
            // 下一个区间的左侧尽可能大于等于上一个区间的右侧
            if (intervals[i][0] > intervals[pre][1]) {
                ++cnt;
                pre = i;
            }
        }
        return intervals.length - cnt;
    }

    public int eraseOverlapIntervalV2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 区间右侧从小到大排序，相同右侧按照左侧从小到大排序
        Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);

        int[] dp = new int[intervals.length];
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[i][0] >= intervals[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = -1;
        for (int n : dp) {
            res = Math.max(res, n);
        }
        return intervals.length - res;
    }
}
