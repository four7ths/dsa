package com.four7ths.dsa.leetcode.week04;

import java.util.ArrayList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 257 二叉树的所有路径
 * 给定一个二叉树根节点，返回从根节点到叶子节点的所有路径
 */
public class BiTreePath {

    List<String> ret = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return ret;
        }
        binaryTreePaths(root, new ArrayList<>());

        return ret;
    }

    private void binaryTreePaths(TreeNode node, ArrayList<String> tmp) {
        tmp.add(String.valueOf(node.val));
        if (node.left == null && node.right == null) {
            ret.add(String.join("->", tmp.toArray(new String[0])));
        }
        if (node.left != null) {
            binaryTreePaths(node.left, tmp);
        }
        if (node.right != null) {
            binaryTreePaths(node.right, tmp);
        }
        tmp.remove(tmp.size() - 1);
    }
}
