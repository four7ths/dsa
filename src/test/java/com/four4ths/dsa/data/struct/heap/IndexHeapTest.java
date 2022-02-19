package com.four4ths.dsa.data.struct.heap;

import org.junit.Assert;
import org.junit.Test;

import com.four7ths.dsa.data.structure.heap.IndexMaxHeap;

/**
 * 索引堆测试用例
 */
public class IndexHeapTest {

    @Test
    public void indexMaxHeapTest() {
        String[] keys = {
                "hello",
                "ping",
                "pong",
                "word",
                "sherman",
                "alex"
        };

        IndexMaxHeap<String> indexMaxHeap = new IndexMaxHeap<>(keys.length);
        Assert.assertTrue(indexMaxHeap.isEmpty());

        for (int i = 0; i < keys.length; i++) {
            indexMaxHeap.insert(i, keys[i]);
        }
        Assert.assertEquals(keys.length, indexMaxHeap.size());

        Assert.assertEquals("word", indexMaxHeap.maxKey());
        Assert.assertEquals(3, indexMaxHeap.maxIndex());

        String newMaxKey = "zzz";
        indexMaxHeap.change(keys.length - 1, newMaxKey);
        Assert.assertEquals(newMaxKey, indexMaxHeap.maxKey());
        Assert.assertEquals(keys.length - 1, indexMaxHeap.maxIndex());

        while (!indexMaxHeap.isEmpty()) {
            String maxKey = indexMaxHeap.maxKey();
            int idx = indexMaxHeap.delMax();
            System.out.println("idx: " + idx + ", maxKey: " + maxKey);
        }
    }
}
