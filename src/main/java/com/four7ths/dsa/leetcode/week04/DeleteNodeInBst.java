package com.four7ths.dsa.leetcode.week04;

import com.four7ths.dsa.common.TreeNode;

/**
 * 450 删除二叉搜索树中的节点
 */
public class DeleteNodeInBst {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        int cmp = Integer.compare(key, root.val);
        if (cmp < 0) {
            root.left = deleteNode(root.left, key);
        } else if (cmp > 0) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            // Hibbard Deletion
            // 右子树中最小值 or 左子树中最大值
            TreeNode tmp = root;
            root = getMin(root.right);
            root.right = deleteMin(tmp.right);
            root.left = tmp.left;
        }
        return root;
    }

    private TreeNode deleteMin(/*@NonNull*/ TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private TreeNode getMin(/*@NonNull*/ TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }
}
