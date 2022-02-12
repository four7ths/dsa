package com.four7ths.dsa.leetcode.week04;

import com.four7ths.dsa.common.TreeNode;

/**
 * 437 路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值，找出路径和等于给定数值的路径总数
 * 注意：路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）
 */
public class PathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        // root节点不在当前路径中
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);

        // root节点在当前路径中
        ret += pathSum0(root, targetSum);

        return ret;
    }

    private int pathSum0(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        if (root.val == targetSum) {
            ret += 1;
        }
        ret += pathSum0(root.left, targetSum - root.val);
        ret += pathSum0(root.right, targetSum - root.val);
        return ret;
    }
}
