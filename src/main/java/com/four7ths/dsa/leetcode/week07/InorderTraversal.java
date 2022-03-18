package com.four7ths.dsa.leetcode.week07;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 94 二叉树的中序遍历
 */
public class InorderTraversal {
    private List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
        return res;
    }

    private static class DataFrame {
        private final boolean visited;
        private final TreeNode node;

        DataFrame(boolean visited, TreeNode node) {
            this.visited = visited;
            this.node = node;
        }
    }

    public List<Integer> inorderTraversalV2(TreeNode root) {
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

                stk.push(new DataFrame(true, dataFrame.node));

                if (dataFrame.node.left != null) {
                    stk.push(new DataFrame(false, dataFrame.node.left));
                }
            }
        }
        return res;
    }
}
