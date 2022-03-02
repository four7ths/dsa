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

    private boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
    }
}
