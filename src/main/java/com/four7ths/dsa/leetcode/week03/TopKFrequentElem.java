package com.four7ths.dsa.leetcode.week03;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 347 前K个高频元素
 * 给定一个整数数组和数字k，返回数组中出现频率前k高的元素
 */
public class TopKFrequentElem {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        // 按照频率的小顶堆
        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparing(freq::get));
        for (int n : freq.keySet()) {
            if (pq.size() < k) {
                pq.add(n);
            } else {
                if (freq.get(pq.element()) < freq.get(n)) {
                    pq.remove();
                    pq.add(n);
                }
            }
        }

        return pq.stream().mapToInt(Integer::valueOf).toArray();
    }

    public int[] topKFrequentV2(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        for (int n : nums) {
            freq.merge(n, 1, Integer::sum);
        }

        // 按照频率的大顶堆
        Queue<Integer> pq = new PriorityQueue<>((x, y) -> freq.get(y) - freq.get(x));
        pq.addAll(freq.keySet());

        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = pq.remove();
        }

        return ret;
    }

    // Map按照value排序，使用Stream流
    public int[] topKFrequentV3(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        for (int n : nums) {
            freq.merge(n, 1, Integer::sum);
        }

        LinkedHashMap<Integer, Integer> maps = freq.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer> comparingByValue().reversed())
                .limit(k)
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (old, now) -> old, LinkedHashMap::new
                ));
        return maps.keySet().stream().mapToInt(Integer::valueOf).toArray();
    }

    // Map的key按照频率逆序，取前k个元素
    public int[] topKFrequentV4(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        for (int n : nums) {
            freq.merge(n, 1, Integer::sum);
        }

        return freq.keySet().stream()
                .sorted((x, y) -> freq.get(y) - freq.get(x))
                .limit(k)
                .mapToInt(Integer::valueOf)
                .toArray();
    }
}
