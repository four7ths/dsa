package com.four7ths.dsa.common;

import com.four7ths.dsa.leetcode.week01.KthLargestElemInArray;

/**
 * @author tanglei <tanglei@kuaishou.com>
 * Created on 2021-12-24
 */
public class CommonUtils {
    private CommonUtils() {
    }

    public static void swap(int[] arr, int i, int j) {
        validIdx(arr, i);
        validIdx(arr, j);

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void validIdx(int[] arr, int idx) {
        if (idx < 0 || idx >= arr.length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
