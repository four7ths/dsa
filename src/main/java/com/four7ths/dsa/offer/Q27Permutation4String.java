package com.four7ths.dsa.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串全排列，注意字符串中可能包含重复字符，返回结果中不能包含重复结果
 */
public class Q27Permutation4String {
    private final List<String> res = new ArrayList<>();
    private boolean[] visited;

    public List<String> permutation(String str) {

        if (str == null || str.length() == 0) {
            return res;
        }
        visited = new boolean[str.length()];
        List<Character> tmp = new ArrayList<>();
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        dfs(chars, 0, tmp);
        return res;
    }

    private void dfs(char[] chars, int idx, List<Character> tmp) {
        if (idx == chars.length) {
            StringBuilder sb = new StringBuilder();
            for (Character ch : tmp) {
                sb.append(ch);
            }
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; ++i) {
            if (!visited[i]) {
                if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                    continue;
                }
                tmp.add(chars[i]);
                visited[i] = true;
                dfs(chars, idx + 1, tmp);
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
