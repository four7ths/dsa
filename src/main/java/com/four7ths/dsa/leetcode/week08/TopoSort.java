package com.four7ths.dsa.leetcode.week08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 207 课程表
 * 假设必须学习n门课程，记为0到n-1，但是课程之间存在先修关系，先修课程数组preRequest数组，其中：
 * preRequest[i]=[a_i, b_i]，表示：学习完b_i课程后才能学习a_i
 * 给定课程数n和对应先修课程数组，判断是否能够完成学习所有课程
 */
public class TopoSort {

    public boolean canFinishCourse(int n, int[][] preRequest) {
        int[] inDegree = new int[n];
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int[] data : preRequest) {
            ++inDegree[data[0]];
            adj.computeIfAbsent(data[1], k -> new ArrayList<>()).add(data[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.remove();
            --n;
            List<Integer> neighbors = adj.getOrDefault(course, new ArrayList<>());
            for (Integer neighbor : neighbors) {
                --inDegree[neighbor];
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return n == 0;
    }
}
