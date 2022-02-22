package com.four7ths.dsa.leetcode.week07;

import java.util.HashMap;
import java.util.Map;

/**
 * 290 单词规律
 * 给定一种规律【pattern】和一个字符串【str】，判断str是否遵循相同的规律:
 * pattern="abba", str="dog cat cat dog"  -> true
 * pattern="abba", str="dog cat cat fish" -> false
 * pattern="abba", str="dog dog dog dog"  -> false
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> maps = new HashMap<>();
        String[] strings = str.split(" ");
        if (pattern.length() != strings.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); ++i) {
            char ch = pattern.charAt(i);
            if (!maps.containsKey(ch)) {
                if (maps.containsValue(strings[i])) {
                    return false;
                }
                maps.put(ch, strings[i]);
            } else {
                if (!maps.get(ch).equals(strings[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
