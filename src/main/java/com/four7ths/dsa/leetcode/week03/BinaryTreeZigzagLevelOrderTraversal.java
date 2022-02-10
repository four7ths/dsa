package com.four7ths.dsa.leetcode.week03;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 103 二叉树的锯齿形层序遍历
 * 给定一个二叉树根节点root，根据锯齿形状层序遍历二叉树
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Deque<TreeNode> stk1 = new LinkedList<>();
        Deque<TreeNode> stk2 = new LinkedList<>();

        stk1.push(root);

        while (!stk1.isEmpty() || !stk2.isEmpty()) {
            int sz = stk1.isEmpty() ? stk2.size() : stk1.size();
            List<Integer> tmp = new ArrayList<>(sz);

            if (!stk1.isEmpty()) {
                while (!stk1.isEmpty()) {
                    TreeNode node = stk1.pop();
                    tmp.add(node.val);
                    if (node.left != null) {
                        stk2.push(node.left);
                    }
                    if (node.right != null) {
                        stk2.push(node.right);
                    }
                }
            } else {
                while (!stk2.isEmpty()) {
                    TreeNode node = stk2.pop();
                    tmp.add(node.val);
                    if (node.right != null) {
                        stk1.push(node.right);
                    }
                    if (node.left != null) {
                        stk1.push(node.left);
                    }
                }
            }

            ret.add(tmp);
        }

        return ret;
    }
}
