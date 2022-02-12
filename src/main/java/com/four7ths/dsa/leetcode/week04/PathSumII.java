package com.four7ths.dsa.leetcode.week04;

import java.util.ArrayList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 113 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径
 */
public class PathSumII {

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return ret;
        }
        pathSum(root, targetSum, new ArrayList<>());
        return ret;
    }

    private void pathSum(TreeNode root, int targetSum, ArrayList<Integer> tmp) {
        if (root == null) {
            return;
        }
        tmp.add(root.val);
        if (root.left == null && root.right == null && root.val == targetSum) {
            ret.add(new ArrayList<>(tmp));
        }
        if (root.left != null) {
            pathSum(root.left, targetSum - root.val, tmp);
        }
        if (root.right != null) {
            pathSum(root.right, targetSum - root.val, tmp);
        }
        tmp.remove(tmp.size() - 1);
    }
}
