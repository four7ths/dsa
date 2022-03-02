package com.four7ths.dsa.offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.four7ths.dsa.common.TreeNode;

/**
 * 【之字形】打印二叉树
 * 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，其它行以此类推。
 */
public class Q59PrintBinaryTree {
    public List<List<Integer>> print(TreeNode pRoot) {
        List<List<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        int current = 0;
        int next = 1;

        // 通过更新current、next的值来不断交换两个栈
        // stacks.get(current) -> current stack
        // stacks.get(next) -> next stack

        List<Deque<TreeNode>> stacks = new ArrayList<>();
        stacks.add(new LinkedList<>()); // current stack
        stacks.add(new LinkedList<>()); // next stack

        stacks.get(current).push(pRoot);
        ArrayList<Integer> line = new ArrayList<>();
        while (!stacks.get(current).isEmpty() || !stacks.get(next).isEmpty()) {
            TreeNode top = stacks.get(current).pop();
            line.add(top.val);
            if (current == 0) {
                if (top.left != null) {
                    stacks.get(next).push(top.left);
                }
                if (top.right != null) {
                    stacks.get(next).push(top.right);
                }
            } else {
                if (top.right != null) {
                    stacks.get(next).push(top.right);
                }
                if (top.left != null) {
                    stacks.get(next).push(top.left);
                }
            }
            if (stacks.get(current).isEmpty()) {
                res.add(new ArrayList<>(line));
                line.clear();
                current = 1 - current;
                next = 1 - next;
            }
        }
        return res;
    }

    public List<List<Integer>> printV2(TreeNode pRoot) {
        List<List<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        stack1.push(pRoot);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                ArrayList<Integer> tmp = new ArrayList<>();
                while (!stack1.isEmpty()) {
                    TreeNode top = stack1.pop();
                    tmp.add(top.val);
                    if (top.left != null) {
                        stack2.add(top.left);
                    }
                    if (top.right != null) {
                        stack2.add(top.right);
                    }
                }
                res.add(tmp);
            } else {
                ArrayList<Integer> tmp = new ArrayList<>();
                while (!stack2.isEmpty()) {
                    TreeNode top = stack2.pop();
                    tmp.add(top.val);
                    if (top.right != null) {
                        stack1.push(top.right);
                    }
                    if (top.left != null) {
                        stack1.push(top.left);
                    }
                }
                res.add(tmp);
            }
        }
        return res;
    }
}
