package com.four7ths.dsa.leetcode.week04;

import com.four7ths.dsa.common.TreeNode;

/**
 * 108 将有序数组转换为二叉搜索树
 * 给定一个有序数组，将其转换成二叉搜索树
 */
public class CvtSortedArray2Bst {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST0(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST0(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = l + ((r - l) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST0(nums, l, mid - 1);
        root.right = sortedArrayToBST0(nums, mid + 1, r);
        return root;
    }
}
