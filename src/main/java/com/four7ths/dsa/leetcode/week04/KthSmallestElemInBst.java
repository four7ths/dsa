package com.four7ths.dsa.leetcode.week04;

import com.four7ths.dsa.common.TreeNode;

/**
 * 230 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树，返回二叉搜索树中第K（从1开始计数）小的元素
 */
public class KthSmallestElemInBst {
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        int lNums = countNode(root.left);
        if (lNums + 1 == k) {
            return root.val;
        } else if (k <= lNums) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - lNums - 1);
        }
    }

    private int countNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return countNode(node.left) + countNode(node.right) + 1;
    }
}
