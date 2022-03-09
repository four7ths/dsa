package com.four7ths.dsa.leetcode.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438 给定一个字符串s和p，找出s中所有是p字母异位词的子串，返回这些子串的索引
 */
public class FindAllAnagrams {

    // 把原本需要对s进行排序的操作转换为两个数组的比较
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        if (p.length() > s.length()) {
            return ret;
        }

        int l = 0;
        int r = -1;
        int[] freqS = new int[256];
        int[] freqP = new int[256];
        for (char ch : p.toCharArray()) {
            freqP[ch - 'a']++;
            freqS[s.charAt(++r) - 'a']++;
        }
        if (Arrays.equals(freqP, freqS)) {
            ret.add(l);
        }

        while (r < s.length() - 1) {
            ++freqP[s.charAt(++r) - 'a'];
            --freqS[s.charAt(l++) - 'a'];
            if (Arrays.equals(freqP, freqS)) {
                ret.add(l);
            }
        }
        return ret;
    }
}
