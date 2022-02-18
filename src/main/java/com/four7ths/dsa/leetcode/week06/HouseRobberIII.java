package com.four7ths.dsa.leetcode.week06;

import com.four7ths.dsa.common.TreeNode;

/**
 * 337 打家劫舍 III
 * 待偷取的东西是树状结构，要求：相邻节点不能偷取
 * //              3
 * //           /    \
 * //          4      5
 * //        /   \      \
 * //       1     3       1
 * // >> 9 （4+5）
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        postOrder(root);
        return root.val;
    }

    private void postOrder(TreeNode root) {
        if (root.left != null) {
            postOrder(root.left);
        }
        if (root.right != null) {
            postOrder(root.right);
        }
        // 方式1：包含当前root节点的价值
        int res1 = root.val;
        // 方式2：不包含当前root节点的价值
        int res2 = 0;
        if (root.left != null) {
            res2 += root.left.val;
            if (root.left.left != null) {
                res1 += root.left.left.val;
            }
            if (root.left.right != null) {
                res1 += root.left.right.val;
            }
        }
        if (root.right != null) {
            res2 += root.right.val;
            if (root.right.left != null) {
                res1 += root.right.left.val;
            }
            if (root.right.right != null) {
                res1 += root.right.right.val;
            }
        }
        root.val = Math.max(res1, res2);
    }
}
