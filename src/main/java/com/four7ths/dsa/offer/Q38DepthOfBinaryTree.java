package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.TreeNode;

/**
 * 二叉树的深度
 */
public class Q38DepthOfBinaryTree {
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }
}
