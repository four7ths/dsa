package com.four7ths.dsa.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 和为S的两个数
 * 一个递增排序的数组和一个数字S，在数组中查找两个数，使得它们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的
 */
public class Q42FindNumbersWithSum {

    public List<Integer> findNumbersWithSum(int[] array, int sum) {
        List<Integer> arrayList = new ArrayList<>();
        int lo = 0;
        int hi = array.length - 1;
        while (lo < hi) {
            int curSum = array[lo] + array[hi];
            if (curSum == sum) {
                arrayList.add(array[lo]);
                arrayList.add(array[hi]);
                break;
            } else if (curSum > sum) {
                --hi;
            } else {
                ++lo;
            }
        }
        return arrayList;
    }
}
