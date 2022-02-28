package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.TreeNode;

/**
 * 平衡二叉树
 * 判断该二叉树是否是平衡二叉树
 */
public class Q39ValidBalancedBinaryTree {
    public boolean isBalancedBiTree(TreeNode root) {
        // 空树默认是平衡二叉树
        if (root == null) {
            return true;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        if (Math.abs((left - right)) > 1) {
            return false;
        }
        return isBalancedBiTree(root.left) && isBalancedBiTree(root.right);
    }

    private int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }
}
