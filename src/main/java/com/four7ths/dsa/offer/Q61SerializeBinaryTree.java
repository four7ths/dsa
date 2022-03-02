package com.four7ths.dsa.offer;

import com.four7ths.dsa.common.TreeNode;

/**
 * 二叉树的序列化和反序列化
 */
public class Q61SerializeBinaryTree {

    private int idx = -1;

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val).append(",");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }

    public TreeNode deserialize(String str) {
        idx++;
        if (idx >= str.length()) {
            return null;
        }
        String[] strs = str.split(",");
        TreeNode node = null;
        if (!strs[idx].equals("#")) {
            node = new TreeNode(Integer.parseInt(strs[idx]));
            node.left = deserialize(str);
            node.right = deserialize(str);
        }
        return node;
    }
}
