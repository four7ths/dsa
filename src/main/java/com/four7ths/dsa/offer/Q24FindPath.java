package com.four7ths.dsa.offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 二叉树中和为某一值的路径
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径
 * 路径定义为从树的根结点开始往下一直到【叶子结点】所经过的结点形成一条路径
 */
public class Q24FindPath {
    private final List<List<Integer>> paths = new ArrayList<>();
    private final Deque<Integer> stk = new LinkedList<>();

    public List<List<Integer>> findPath(TreeNode root, int target) {
        if (root == null) {
            return paths;
        }
        int curSum = 0;
        findPath0(root, curSum, target);
        return paths;
    }

    private void findPath0(TreeNode root, int curSum, int target) {
        curSum += root.val;
        stk.push(root.val);
        if (root.left == null && root.right == null && (curSum == target)) {
            paths.add(new ArrayList<>(stk));
        }
        if (root.left != null) {
            findPath0(root.left, curSum, target);
        }
        if (root.right != null) {
            findPath0(root.right, curSum, target);
        }
        stk.pop();
    }
}
