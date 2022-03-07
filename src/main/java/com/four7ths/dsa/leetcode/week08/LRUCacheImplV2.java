package com.four7ths.dsa.leetcode.week08;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146 LRU缓存
 * 使用LinkedHashMap实现
 */
public class LRUCacheImplV2 extends LinkedHashMap<Integer, Integer> {

    private final int capacity;

    public LRUCacheImplV2(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
