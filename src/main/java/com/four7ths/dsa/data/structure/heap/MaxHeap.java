package com.four7ths.dsa.data.structure.heap;

/**
 * 大顶堆实现
 */
public class MaxHeap {

    private static final int DEFAULT_CAPACITY = 10;

    private int[] pq;

    private int size;

    public MaxHeap() {
        this(DEFAULT_CAPACITY + 1);
    }

    public MaxHeap(int cap) {
        pq = new int[cap];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void heapify(int[] input) {
        size = input.length;
        pq = new int[size + 1];
        System.arraycopy(input, 0, pq, 1, input.length);
        for (int i = size / 2; i >= 1; i--) {
            sink(i);
        }
    }

    // 是否是合法的堆
    public boolean isMaxHeap() {
        // pq[i,...size] NotNull
        // pq[size+1,pq.length-1] IsNull

        if (pq[0] != 0) {
            return false;
        }
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {
        if (k > size) {
            return true;
        }
        int left = 2 * k;
        int right = left + 1;
        if (left <= size && less(k, left)) {
            return false;
        }
        if (right <= size && less(k, right)) {
            return false;
        }
        return isMaxHeap(left) && isMaxHeap(right);
    }

    // 插入一个新元素
    public void insert(int val) {
        if (pq.length - 1 == size) {
            resize(pq.length * 2);
        }
        pq[++size] = val;
        swim(size);
    }

    // 删除最大元素
    public int delMax() {
        if (size == 0) {
            throw new RuntimeException();
        }
        int max = pq[1];
        exch(1, size--);
        sink(1);
        // pq[sz + 1] = null; // for Reference Type
        if (size > 0 && (size == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return max;
    }

    // 下沉
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) {
                ++j;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    // 上游
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void exch(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    private boolean less(int i, int j) {
        return pq[i] < pq[j];
    }

    private void resize(int newCapacity) {
        if (newCapacity <= size) {
            throw new IllegalArgumentException();
        }
        int[] tmp = new int[newCapacity];
        System.arraycopy(pq, 1, tmp, 1, size);
        pq = tmp;
    }
}
