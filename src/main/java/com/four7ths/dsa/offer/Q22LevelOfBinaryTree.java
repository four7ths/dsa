package com.four7ths.dsa.offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 二叉树的层序遍历
 */
public class Q22LevelOfBinaryTree {
    private final List<Integer> lists = new ArrayList<>();
    private Deque<TreeNode> queue = new LinkedList<>();

    public List<Integer> printFromTopToBottom(TreeNode root) {
        if (root == null) {
            return lists;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();
            lists.add(head.val);
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }
        return lists;
    }
}
