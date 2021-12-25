package com.four7ths.dsa.leetcode.week01;

/**
 * 11 container with most water
 * 给定n个非负整数，分别代表坐标中的点(i, n_i)，找出其中两条线，使得它们和X轴围成的矩形面积最大，返回最大面积
 */
public class ContainWithMostWater {
    public int maxArea(int[] height) {
        int lo = 0, hi = height.length - 1;
        int area = 0;
        while (lo < hi) {
            int hgt = 0;
            int width = hi - lo;
            if (height[lo] < height[hi]) {
                hgt = height[lo++];
            } else {
                hgt = height[hi--];
            }
            area = Math.max(area, hgt * width);
        }
        return area;
    }
}
