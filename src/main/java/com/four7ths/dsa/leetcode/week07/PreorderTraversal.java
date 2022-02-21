package com.four7ths.dsa.leetcode.week07;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 144 二叉树的前序遍历
 */
public class PreorderTraversal {

    private List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root != null) {
            res.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return res;
    }

    private static class DataFrame {
        private boolean visited;
        private TreeNode node;

        DataFrame(boolean visited, TreeNode node) {
            this.visited = visited;
            this.node = node;
        }
    }

    public List<Integer> preorderTraversalV2(TreeNode root) {
        if (root == null) {
            return res;
        }
        Deque<DataFrame> stk = new LinkedList<>();
        DataFrame rootDataFrame = new DataFrame(false, root);
        stk.push(rootDataFrame);

        while (!stk.isEmpty()) {
            DataFrame dataFrame = stk.pop();
            if (dataFrame.visited) {
                res.add(dataFrame.node.val);
            } else {
                if (dataFrame.node.right != null) {
                    stk.push(new DataFrame(false, dataFrame.node.right));
                }
                if (dataFrame.node.left != null) {
                    stk.push(new DataFrame(false, dataFrame.node.left));
                }
                stk.push(new DataFrame(true, dataFrame.node));
            }
        }
        return res;
    }
}
