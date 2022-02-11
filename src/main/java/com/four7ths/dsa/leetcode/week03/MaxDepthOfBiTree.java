package com.four7ths.dsa.leetcode.week03;

import com.four7ths.dsa.common.TreeNode;

/**
 * 104 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度
 */
public class MaxDepthOfBiTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
