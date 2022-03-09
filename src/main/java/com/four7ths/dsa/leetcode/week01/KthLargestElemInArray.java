package com.four7ths.dsa.leetcode.week01;

import static com.four7ths.dsa.common.CommonUtils.swap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * 215 Kth-largest element in an array
 * 给定给一个数组（非有序），求出数组中第k大的元素，1<=k<=len(nums)
 */
public class KthLargestElemInArray {

    // 使用快排partition
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, k, 0, nums.length - 1);
    }

    // len=nums.length
    // 第k大元素 <=> 第len-k+1小元素
    // partition操作中，返回值idx表示：pivot左边有idx数据小于pivot，即nums[idx]是第idx+1小的元素
    // 等式成立条件：len-k+1=idx+1 <=> len-k=idx
    private int findKthLargest(int[] nums, int k, int lo, int hi) {

        int idx = partition(nums, lo, hi);
        while (idx != nums.length - k) {
            if (idx > nums.length - k) {
                idx = partition(nums, lo, idx - 1);
            } else {
                idx = partition(nums, idx + 1, hi);
            }
        }
        return nums[idx];
    }

    private int partition(int[] nums, int lo, int hi) {
        int rnd = new Random().nextInt(hi - lo + 1) + lo;
        swap(nums, rnd, hi);
        int pivot = nums[hi];

        int idx = lo;
        for (int i = lo; i < hi; i++) {
            if (nums[i] < pivot) {
                swap(nums, idx++, i);
            }
        }
        swap(nums, idx, hi);
        return idx;
    }

    // 使用大顶堆
    public int findKthLargestUsingHeap(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int n : nums) {
            pq.add(n);
        }
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return pq.poll();
    }
}
