package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.TreeNode;

/**
 * 树的子结构
 * 约定：空树不是任意一个树的子结构
 */
public class Q17HasSubTree {
    public boolean hasSubtree(TreeNode p, TreeNode q) {
        boolean result = false;
        if (p != null && q != null) {
            if (p.val == q.val) {
                result = isContains(p, q);
            }
            if (!result) {
                result = hasSubtree(p.left, q);
            }
            if (!result) {
                result = hasSubtree(p.right, q);
            }
        }
        return result;
    }

    private boolean isContains(TreeNode p, TreeNode q) {
        // 注意下面两个if顺序不能调换
        if (q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isContains(p.left, q.left) && isContains(p.right, q.right);
    }
}
