package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.TreeNode;

/**
 * 对称二叉树
 * 判断一个二叉树是不是对称的，如果一个二叉树和其镜像是相同的，定义为对称二叉树
 */
public class Q58SymmetricalBinaryTree {
    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    private boolean isSymmetrical(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSymmetrical(p.left, q.right) && isSymmetrical(p.right, q.left);
    }
}
