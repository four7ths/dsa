package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.TreeNode;

/**
 * 二叉搜索树的第K个节点
 */
public class Q62RangeKthInBST {
    
    private int idx = 0;

    public TreeNode KthNode(TreeNode root, int k) {
        TreeNode tmp = null;
        if (root != null) {
            if ((tmp = KthNode(root.left, k)) != null) {
                return tmp;
            }
            if (++idx == k) {
                return root;
            }
            if ((tmp = KthNode(root.right, k)) != null) {
                return tmp;
            }
        }
        return null;
    }
}



