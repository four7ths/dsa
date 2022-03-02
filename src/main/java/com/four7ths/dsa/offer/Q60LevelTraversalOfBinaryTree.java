package com.four7ths.dsa.offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 二叉树的层序遍历，并保留每一层节点值
 */
public class Q60LevelTraversalOfBinaryTree {
    List<List<Integer>> print(TreeNode pRoot) {
        List<List<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode elem = queue.poll();
                tmp.add(elem.val);
                if (elem.left != null) {
                    queue.add(elem.left);
                }
                if (elem.right != null) {
                    queue.add(elem.right);
                }
            }
            res.add(new ArrayList<>(tmp));
        }
        return res;
    }
}
