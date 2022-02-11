package com.four7ths.dsa.leetcode.week03;

import com.four7ths.dsa.common.TreeNode;

/**
 * 101 镜像树
 * 给定一颗二叉树，判断其是否是镜像的
 */
public class SymmetricTRee {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }
}
