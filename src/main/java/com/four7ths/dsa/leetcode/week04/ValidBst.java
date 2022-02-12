package com.four7ths.dsa.leetcode.week04;

import com.four7ths.dsa.common.TreeNode;

/**
 * 98 验证二叉搜索树
 * 给定一个二叉树的根节点，判断二叉树是否是二叉搜索树
 */
public class ValidBst {

    private long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean lCond = isValidBST(root.left);

        boolean mCond = false;
        if (root.val > prev) {
            mCond = true;
            prev = root.val;
        }

        boolean rCond = isValidBST(root.right);

        // 中序遍历思想体现，实际可以短路优化逻辑
        return lCond && mCond && rCond;
    }
}
