package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.TreeNode;

/**
 * 二叉搜索树转换成双向链表
 */
public class Q26ConvertBST2LinkedList {

    TreeNode pre = null;

    public TreeNode convert(TreeNode node) {
        if (node == null) {
            return null;
        }
        convert(node.right);
        if (pre != null) {
            node.right = pre;
            pre.left = node;
        }
        pre = node;
        convert(node.left);
        return pre;
    }
}
