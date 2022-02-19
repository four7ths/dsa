package com.four4ths.dsa.data.struct.heap;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.four7ths.dsa.data.structure.heap.MaxHeap;
import com.four7ths.dsa.data.structure.heap.MinHeap;

/**
 * Heap相关测试类
 */
public class HeapTest {

    private static final int[] data;
    private static final int[] sortedData;

    static {
        data = new int[] {5, 4, 2, 1, 3, 9, 7, 8, 6};
        sortedData = Arrays.copyOf(data, data.length);
        Arrays.sort(sortedData);
    }

    private void maxHeapTest0(MaxHeap maxHeap) {
        Assert.assertEquals(data.length, maxHeap.size());
        Assert.assertTrue(maxHeap.isMaxHeap());

        while (!maxHeap.isEmpty()) {
            int maxData = maxHeap.delMax();
            Assert.assertEquals(sortedData[maxHeap.size()], maxData);
        }

        Assert.assertEquals(0, maxHeap.size());
    }

    private void minHeapTest0(MinHeap minHeap) {
        Assert.assertEquals(data.length, minHeap.size());
        Assert.assertTrue(minHeap.isMinHeap());

        while (!minHeap.isEmpty()) {
            int minData = minHeap.delMin();
            Assert.assertEquals(sortedData[data.length - minHeap.size() - 1], minData);
        }

        Assert.assertEquals(0, minHeap.size());
    }

    @Test
    public void maxHeapTest() {
        MaxHeap maxHeap1 = new MaxHeap();
        for (int n : data) {
            maxHeap1.insert(n);
        }
        maxHeapTest0(maxHeap1);

        MaxHeap maxHeap2 = new MaxHeap();
        maxHeap2.heapify(data);
        maxHeapTest0(maxHeap2);
    }

    @Test
    public void minHeapTest() {
        MinHeap minHeap1 = new MinHeap();
        for (int n : data) {
            minHeap1.insert(n);
        }
        minHeapTest0(minHeap1);

        MinHeap minHeap2 = new MinHeap();
        minHeap2.heapify(data);
        minHeapTest0(minHeap2);
    }
}
