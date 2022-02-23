package com.four7ths.dsa.leetcode.week07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串
 * >>> strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * <<< [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> maps = new HashMap<>();
        for (String str : strs) {
            String key = sortString(str);
            maps.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(maps.values());
    }

    private String sortString(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
