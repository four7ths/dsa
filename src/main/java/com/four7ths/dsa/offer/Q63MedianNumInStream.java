package com.four7ths.dsa.offer;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 数据流中的中位数
 */
public class Q63MedianNumInStream {

    private final Queue<Integer> minHeap = new PriorityQueue<>(); // 较大部分，小顶堆
    private final Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 较小部分，大顶堆
    private int cnt = 0;

    public void insert(Integer num) {
        // 偶数个数，此时bigger和smaller平分数据，下一个元素插入会导致bigger多一个元素
        if ((cnt & 1) == 0) {
            // 也可以不判断，直接从弹出maxHeap中最大的数放入到minHeap中，再将num放入到maxHeap中
            if (maxHeap.isEmpty() || num >= maxHeap.peek()) {
                minHeap.add(num);
            } else {
                Integer oldBig = maxHeap.poll();
                maxHeap.add(num);
                minHeap.add(oldBig);
            }
        } else {
            // 也可以不判断，直接从弹出minHeap中最小的数放入到maxHeap中，再讲num放入到minHeap中
            if (minHeap.isEmpty() || num <= minHeap.peek()) {
                maxHeap.add(num);
            } else {
                Integer oldSmall = minHeap.poll();
                minHeap.add(num);
                maxHeap.add(oldSmall);
            }
        }
        ++cnt;
    }

    public Double getMedian() {
        if (cnt == 0) {
            throw new IllegalArgumentException();
        }
        if ((cnt & 1) == 0) {
            return (minHeap.peek() + maxHeap.peek()) * 1.0 / 2;
        } else {
            return minHeap.peek() * 1.0;
        }
    }
}
