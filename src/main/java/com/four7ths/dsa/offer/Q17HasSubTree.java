package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.TreeNode;

/**
 * 树的子结构
 * 约定：空树不是任意一个树的子结构
 */
public class Q17HasSubTree {
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                result = isContains(root1, root2);
            }
            if (!result) {
                result = hasSubtree(root1.left, root2);
            }
            if (!result) {
                result = hasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    private boolean isContains(TreeNode root1, TreeNode root2) {
        // 注意下面两个if顺序不能调换
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isContains(root1.left, root2.left) && isContains(root1.right, root2.right);
    }
}
