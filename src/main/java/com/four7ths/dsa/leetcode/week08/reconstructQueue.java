package com.four7ths.dsa.leetcode.week08;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 406 根据身高重建队列
 * 给定一个数组people，people[i]=[h_i, k_i]，表示：第i个人的升高为h_i，前面正好有k_i个身高大于等于h_i的人
 * 重构输入数组，使得输出数组满足以上特性
 */
public class reconstructQueue {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });

        List<int[]> res = new LinkedList<>();
        int len = people.length;
        for (int[] p : people) {
            res.add(Math.min(res.size(), p[1]), p);
        }
        return res.toArray(new int[len][2]);
    }

}
