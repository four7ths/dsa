package com.four7ths.dsa.offer;

/**
 * 调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分
 * 并保证奇数之间、偶数之间【稳定性】
 */
public class Q13ReOrderOddAndEven {
    public void reOrderArray(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int evenIdx = 0;
        // 找到第一个偶数，下标记为i
        while (evenIdx < array.length && (array[evenIdx] & 1) == 1) {
            ++evenIdx;
        }
        if (evenIdx == array.length) {
            return;
        }
        int oddIdx = evenIdx + 1;
        while (oddIdx < array.length) {
            // 找到第一个为奇数的下标，记为j
            while (oddIdx < array.length && (array[oddIdx] & 1) == 0) {
                ++oddIdx;
            }
            if (oddIdx == array.length) {
                break;
            }
            int val = array[oddIdx];
            System.arraycopy(array, evenIdx, array, evenIdx + 1, oddIdx - evenIdx);
            array[evenIdx] = val;
            ++evenIdx;
            ++oddIdx;
        }
    }
}
