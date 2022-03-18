package com.four7ths.dsa.leetcode.week03;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 107 binary tree level order traversal II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历
 */
public class BinaryTreeLevelOrderTraversalII {

    private final List<List<Integer>> ret = new LinkedList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return ret;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<Integer> tmp = new ArrayList<>(sz);
            for (int i = 0; i < sz; i++) {
                TreeNode item = queue.remove();
                tmp.add(item.val);
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
            ret.add(0, tmp);
        }
        return ret;
    }
}
