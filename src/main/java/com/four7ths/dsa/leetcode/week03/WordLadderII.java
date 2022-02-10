package com.four7ths.dsa.leetcode.week03;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 126 成语接龙 II
 * 给定两个单词（beginWord 和 endWord）和一个字典，找出所有从beginWord到endWord的最短转换序列。转换需遵循如下规则:
 * - 每次转换只能改变一个字母
 * - 转换过程中的中间单词必须是字典中的单词
 * 说明:
 * - 如果不存在这样的转换序列，返回0
 * - 所有单词具有相同的长度
 * - 所有单词只由小写字母组成
 * - 字典中不存在重复的单词
 * 你可以假设beginWord和endWord是非空的，且二者不相同
 */
public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ret = new ArrayList<>();
        HashSet<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return ret;
        }

        bfs(beginWord, endWord, words, ret);

        return ret;
    }

    private void bfs(String beginWord, String endWord, HashSet<String> words, List<List<String>> ret) {
        Deque<List<String>> queue = new LinkedList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        queue.add(path);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        boolean isFound = false;

        while (!queue.isEmpty()) {
            int sz = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < sz; i++) {
                List<String> p = queue.remove();
                String lastStr = p.get(p.size() - 1);
                List<String> neighbors = getNeighbors(lastStr, words);
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        // 如果在当前层发现转换路径，只有可能在当前层存在其他路径，之后层直接忽略break
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                            p.add(neighbor);
                            ret.add(new ArrayList<>(p));
                            break;
                        }

                        p.add(neighbor);
                        queue.add(new ArrayList<>(p));
                        p.remove(p.size() - 1);
                        //! visited.add(neighbor);
                        // 当前层还不能认为neighbor是被访问的
                        subVisited.add(neighbor);
                    }
                }
            }
            if (isFound) {
                break;
            }
            visited.addAll(subVisited);
        }
    }

    private List<String> getNeighbors(String str, HashSet<String> words) {
        ArrayList<String> ret = new ArrayList<>();
        char chs[] = str.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) {
                    continue;
                }
                char oldCh = chs[i];
                chs[i] = ch;
                if (words.contains(String.valueOf(chs))) {
                    ret.add(String.valueOf(chs));
                }
                chs[i] = oldCh;
            }
        }
        return ret;
    }
}
