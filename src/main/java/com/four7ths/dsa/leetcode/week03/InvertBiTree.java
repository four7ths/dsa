package com.four7ths.dsa.leetcode.week03;

import com.four7ths.dsa.common.TreeNode;

/**
 * 226 翻转二叉树
 * 给定一颗二叉树根节点root，翻转该二叉树，并返回根节点
 */
public class InvertBiTree {
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
