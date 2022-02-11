package com.four7ths.dsa.leetcode.week03;

import com.four7ths.dsa.common.TreeNode;

/**
 * 222 完全二叉树的节点数量
 * 给定一颗完全二叉树的根节点，求出该树的节点个数
 */
public class CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) + countNodes(root.right);
        } else {
            // assert (rightDepth + 1 == leftDepth)
            return (1 << rightDepth) + countNodes(root.left);
        }
    }

    private int getDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            ++depth;
            node = node.left;
        }
        return depth;
    }
}
