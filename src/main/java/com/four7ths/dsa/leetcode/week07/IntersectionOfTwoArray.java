package com.four7ths.dsa.leetcode.week07;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class IntersectionOfTwoArray {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int n : nums1) {
            s1.add(n);
        }
        for (int n : nums2) {
            s2.add(n);
        }

        s1.retainAll(s2);

        int[] ret = new int[s1.size()];
        int idx = 0;
        for (Integer n : s1) {
            ret[idx++] = n;
        }
        return ret;
    }

    public int[] intersectionV2(int[] nums1, int[] nums2) {
        Set<Integer> s1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> s2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        s1.retainAll(s2);
        return s1.stream().mapToInt(Integer::valueOf).toArray();
    }
}
