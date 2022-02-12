package com.four7ths.dsa.leetcode.week04;

import com.four7ths.dsa.common.TreeNode;

/**
 * 404 左叶子之和
 * 给定一个二叉树根节点，返回所有左叶子之和
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        int cnt = 0;
        if (root != null) {
            if (root.left != null && (root.left.left == null && root.left.right == null)) {
                cnt += root.left.val;
            } else {
                cnt += sumOfLeftLeaves(root.left);
            }
            cnt += sumOfLeftLeaves(root.right);
        }
        return cnt;
    }
}
