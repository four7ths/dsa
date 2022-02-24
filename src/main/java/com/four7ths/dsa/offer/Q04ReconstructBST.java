package com.four7ths.dsa.offer;

import java.util.Arrays;

import com.four7ths.dsa.common.TreeNode;

/**
 * 给定二叉树的前序遍历和中序遍历（两个序列都不含重复数字），重构二叉树
 * - 前序遍历: {1,2,4,7,3,5,6,8}
 * - 中序遍历: {4,7,2,1,5,3,8,6}
 */
public class Q04ReconstructBST {
    public TreeNode reconstructBinaryTree(int[] pre, int[] in) {
        if ((pre.length | in.length) == 0 || pre.length != in.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        int len = in.length;
        for (int i = 0; i < len; ++i) {
            if (in[i] == pre[0]) {
                root.left = reconstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reconstructBinaryTree(Arrays.copyOfRange(pre, i + 1, len), Arrays.copyOfRange(in, i + 1, len));
                break;
            }
        }
        return root;
    }
}
