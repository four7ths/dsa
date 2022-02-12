package com.four7ths.dsa.leetcode.week04;

import com.four7ths.dsa.common.TreeNode;

/**
 * 129 求根节点到叶节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个0-9的数字，每条从根到叶子节点的路径都代表一个数字
 * //    1
 * //  /   \
 * // 2     3
 * // 输出：12+13=25
 */
public class SumOfRoot2Leaf {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumNumbers0(root, 0);
    }

    private int sumNumbers0(TreeNode root, int base) {
        if (root.left == null && root.right == null) {
            return 10 * base + root.val;
        }
        int sum = 0;
        if (root.left != null) {
            sum += sumNumbers0(root.left, 10 * base + root.val);
        }
        if (root.right != null) {
            sum += sumNumbers0(root.right, 10 * base + root.val);
        }
        return sum;
    }
}
