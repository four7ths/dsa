package com.four7ths.dsa.leetcode.week03;

import com.four7ths.dsa.common.TreeNode;

/**
 * 100 相同的树
 * 给定两个二叉树根节点p和q，验证两棵树是否完全相同
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
