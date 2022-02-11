package com.four7ths.dsa.leetcode.week03;

import java.util.Deque;
import java.util.LinkedList;

import com.four7ths.dsa.common.TreeNode;

/**
 * 111 二叉树的最小深度
 * 最小深度是指从根节点到最近叶子节点最短路径上的节点数量
 * // a
 * //  \
 * //   b
 * // 返回：2
 */
public class MinDepthOfBiTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        return (left != 0 && right != 0)
               ? Math.min(left, right) + 1
               : left + right + 1;
    }

    // 层序遍历
    public int minDepthV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            ++level;
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.remove();
                if (node.left == null && node.right == null) {
                    return level;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        throw new RuntimeException();
    }
}
