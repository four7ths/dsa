package com.four7ths.dsa.leetcode.week08;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 42 接雨滴 I
 */
public class TrapRainWaterI {

    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if (min > height[i]) {
                res += (min - height[i]);
            }
        }
        return res;
    }

    public int trapV2(int[] height) {
        int ans = 0;
        // 单调栈：栈低元素大于等于栈顶元素
        // 当遍历当第i个元素，且栈中存在至少两个元素时：栈顶元素为top，下一个为left，一定有：
        // height[top] <= height[left]，如果height[i] > height[top]，则存在接雨滴区域:
        // width: (i-left-1), hgt: min(height[left], height[i]) - height[top]
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    public int trapV3(int[] height) {
        int res = 0;
        int leftMax = 0;
        int rightMax = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] >= leftMax) {
                    leftMax = height[l];
                } else {
                    res += leftMax - height[l];
                }
                ++l;
            } else {
                if (height[r] >= rightMax) {
                    rightMax = height[r];
                } else {
                    res += rightMax - height[r];
                }
                --r;
            }
        }
        return res;
    }
}
