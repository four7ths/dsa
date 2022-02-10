package com.four7ths.dsa.leetcode.week03;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 127 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从beginWord到endWord的最短转换序列的长度。转换需遵循如下规则:
 * - 每次转换只能改变一个字母
 * - 转换过程中的中间单词必须是字典中的单词
 * 说明:
 * - 如果不存在这样的转换序列，返回0
 * - 所有单词具有相同的长度
 * - 所有单词只由小写字母组成
 * - 字典中不存在重复的单词
 * 你可以假设beginWord和endWord是非空的，且二者不相同
 */
public class WordLadderI {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }

        Map<String, Boolean> visited = new HashMap<>(wordList.size());
        wordList.forEach(w -> visited.put(w, false));

        Deque<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.put(beginWord, true);

        int depth = 0;

        while (!queue.isEmpty()) {
            ++depth;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String word = queue.remove();
                for (int idx = 0; idx < word.length(); idx++) {
                    char[] chars = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[idx] = ch;
                        String tmpStr = String.valueOf(chars);
                        if (tmpStr.equals(endWord)) {
                            return depth + 1;
                        }
                        if (!tmpStr.equals(word) && words.contains(tmpStr) && !visited.getOrDefault(tmpStr, false)) {
                            queue.add(tmpStr);
                            visited.put(tmpStr, true);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
