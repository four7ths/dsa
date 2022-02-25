package com.four7ths.dsa.offer;

import java.util.Deque;
import java.util.LinkedList;

import com.four7ths.dsa.common.TreeNode;

/**
 * 二叉树的镜像
 */
public class Q18MirrorOfBinaryTree {
    // 前序遍历，访问到当前树的根节点时，交换左右子节点
    public void mirror(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        if (root.left != null) {
            mirror(root.left);
        }
        if (root.right != null) {
            mirror(root.right);
        }
    }

    // 层序遍历，访问到每个节点直接交换左右子节点即可
    public void mirror01(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();
            TreeNode tmp = head.left;
            head.left = head.right;
            head.right = tmp;
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }
    }
}
