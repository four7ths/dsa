package com.four7ths.dsa.leetcode.week08;

import java.util.Arrays;

/**
 * 34 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组nums，和一个目标值target，找出给定目标值在数组中的开始位置和结束位置
 */
public class BinarySearchRange {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        Arrays.fill(res, -1);
        res[0] = bsLow(nums, target);
        res[1] = bsHigh(nums, target);
        return res;
    }

    private int bsLow(int[] arr, int t) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (arr[mid] == t) {
                if (mid == 0 || arr[mid - 1] != t) {
                    return mid;
                } else {
                    r = mid - 1;
                }
            } else if (arr[mid] > t) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    private int bsHigh(int[] arr, int t) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (arr[mid] == t) {
                if (mid == arr.length - 1 || arr[mid + 1] != t) {
                    return mid;
                } else {
                    l = mid + 1;
                }
            } else if (arr[mid] > t) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
