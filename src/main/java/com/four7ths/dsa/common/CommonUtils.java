package com.four7ths.dsa.common;

public class CommonUtils {
    private CommonUtils() {
    }

    public static void swap(int[] arr, int i, int j) {
        validIdx(arr.length, i);
        validIdx(arr.length, j);

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void swap(char[] arr, int i, int j) {
        validIdx(arr.length, i);
        validIdx(arr.length, j);

        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void validIdx(int len, int idx) {
        if (idx < 0 || idx >= len) {
            throw new IndexOutOfBoundsException();
        }
    }
}
