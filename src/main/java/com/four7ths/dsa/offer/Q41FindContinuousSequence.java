package com.four7ths.dsa.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 和为SUM的连续正数序列
 * 输出所有和为SUM的连续正数序列，序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class Q41FindContinuousSequence {

    private final List<List<Integer>> resList = new ArrayList<>();

    // 滑动窗口思路
    public List<List<Integer>> findContinuousSequence(int sum) {
        int lo = 1;
        int hi = 2;
        int curSum = 3;
        int mid = (sum + 1) / 2;

        while (lo < mid) {
            if (curSum == sum) {
                getRange(lo, hi);
            }
            while (curSum > sum && lo < mid) {
                curSum -= lo;
                lo++;
                if (curSum == sum) {
                    getRange(lo, hi);
                }
            }
            ++hi;
            curSum += hi;
        }
        return resList;
    }

    private void getRange(int lo, int hi) {
        List<Integer> lists = new ArrayList<>();
        for (int i = lo; i <= hi; ++i) {
            lists.add(i);
        }
        resList.add(lists);
    }
}
