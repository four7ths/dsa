package com.four7ths.dsa.leetcode.week03;

import com.four7ths.dsa.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 102 binary tree level order traversal
 * 给定一个二叉树，返回其按从上到下层次遍历的节点值
 */
public class BinaryTreeLevelOrderTraversalI {

    private final List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return ret;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<Integer> tmp = new ArrayList<>(sz);
            for (int i = 0; i < sz; i++) {
                TreeNode item = queue.removeFirst();
                tmp.add(item.val);
                if (item.left != null) {
                    queue.addLast(item.left);
                }
                if (item.right != null) {
                    queue.addLast(item.right);
                }
            }
            ret.add(tmp);
        }
        return ret;
    }
}
