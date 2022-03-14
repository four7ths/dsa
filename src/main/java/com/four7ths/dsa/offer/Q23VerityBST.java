package com.four7ths.dsa.offer;

import java.util.Arrays;

/**
 * 二叉树搜索树的后序遍历序列
 * 判断数组是不是某二叉搜索树的后序遍历的结果
 */
public class Q23VerityBST {

    public boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return verifySequenceOfBST0(sequence);
    }

    private boolean verifySequenceOfBST0(int[] sequence) {
        if (sequence.length <= 1) {
            return true;
        }
        int i = 0;
        int length = sequence.length;
        int root = sequence[length - 1];
        for (; i < length - 1; ++i) {
            if (sequence[i] > root) {
                break;
            }
        }
        // sequence[0...i) < root && sequence[i...sequence.length - 1) > root
        for (int j = i; j < length - 1; ++j) {
            if (sequence[j] < root) {
                return false;
            }
        }
        return verifySequenceOfBST0(Arrays.copyOfRange(sequence, 0, i))
                && verifySequenceOfBST0(Arrays.copyOfRange(sequence, i, length - 1));
    }
}
