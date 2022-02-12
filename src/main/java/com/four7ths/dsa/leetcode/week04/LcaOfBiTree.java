package com.four7ths.dsa.leetcode.week04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.four7ths.dsa.common.TreeNode;

/**
 * 236 二叉树的最近公共祖先
 * 给定一个【二叉树】，找到二叉树中p、q节点的公共祖先
 */
public class LcaOfBiTree {

    private final List<List<TreeNode>> paths = new ArrayList<>();

    // 获取从root节点到p、q的路径，查找公共节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        findPath(root, p, q, new ArrayList<>());

        // assert (paths.size() == 2)
        List<TreeNode> pPath = paths.get(0);
        List<TreeNode> qPath = paths.get(1);

        int len = Math.min(pPath.size(), qPath.size());

        TreeNode ret = root;
        for (int i = 0; i < len; i++) {
            if (pPath.get(i).val == qPath.get(i).val) {
                ret = pPath.get(i);
            }
        }
        return ret;
    }

    private void findPath(TreeNode root, TreeNode pNode, TreeNode qNode, List<TreeNode> path) {
        if (root == null) {
            return;
        }
        path.add(root);
        if (root.val == pNode.val || root.val == qNode.val) {
            paths.add(new ArrayList<>(path));
        }
        if (root.left != null) {
            findPath(root.left, pNode, qNode, path);
        }
        if (root.right != null) {
            findPath(root.right, pNode, qNode, path);
        }
        path.remove(path.size() - 1);
    }

    // 存储父节点
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    Set<TreeNode> visited = new HashSet<>();

    public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        dfs(root);

        while (p != null) {
            visited.add(p);
            p = parent.get(p);
        }

        while (q != null) {
            if (visited.contains(q)) {
                return q;
            }
            q = parent.get(q);
        }
        throw new RuntimeException();
    }

    private void dfs(/*NonNull*/ TreeNode node) {
        if (node.left != null) {
            parent.put(node.left, node);
            dfs(node.left);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            dfs(node.right);
        }
    }

    // 递归：(inLeft && inRight) || ((x==p || x==q) && (inLeft || inRight))
    public TreeNode lowestCommonAncestorV3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode inLeft = lowestCommonAncestorV3(root.left, p, q);
        TreeNode inRight = lowestCommonAncestorV3(root.right, p, q);
        if (inLeft != null && inRight != null) {
            return root;
        }
        return inLeft != null ? inLeft : inRight;
    }
}
