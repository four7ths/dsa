package com.four7ths.dsa.leetcode.week01;

/**
 * 209 min sub array length
 * 给定一个数组arr和目标值s，找出连续子数组和大于等于s的最小长度，如果不存在返回-1
 */
public class MinSubArray {
    public int minSubArrayLen(int s, int[] nums) {
        int ret = nums.length + 1;
        int lo = 0, hi = 0;
        int sum = 0;
        while (lo < nums.length) {
            if (hi < nums.length && sum < s) {
                sum += nums[hi++];
            } else {
                sum -= nums[lo++];
            }
            if (sum >= s) {
                // 注意这里长度是: hi-lo，因为hi已经++过了
                ret = Math.min(ret, hi - lo);
            }
        }
        return ret == nums.length + 1 ? 0 : ret;
    }
}
