package com.four7ths.dsa.data.structure.heap;

/**
 * 小顶堆实现
 */
public class MinHeap {

    private static final int DEFAULT_CAPACITY = 10;

    private int[] pq;

    private int size;

    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(int cap) {
        pq = new int[cap + 1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int delMin() {
        int min = pq[1];
        exch(1, size--);
        sink(1);
        if (size > 0 && size == (pq.length - 1) / 4) {
            resize(pq.length / 2);
        }
        return min;
    }

    public void insert(int val) {
        if (size == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++size] = val;
        swim(size);
    }

    public void heapify(int[] input) {
        size = input.length;
        pq = new int[input.length + 1];
        System.arraycopy(input, 0, pq, 1, input.length);
        for (int i = size / 2; i >= 0; i--) {
            sink(i);
        }
    }

    public boolean isMinHeap() {
        // pq[1...size] NotNull
        // pq[size+1...pq.length-1] IsNull

        if (pq[0] != 0) {
            return false;
        }
        return isMinHeap(1);
    }

    private boolean isMinHeap(int k) {
        if (k > size) {
            return true;
        }
        int left = 2 * k;
        int right = left + 1;
        if (left <= size && !less(k, left)) {
            return false;
        }
        if (right <= size && !less(k, right)) {
            return false;
        }
        return isMinHeap(left) && isMinHeap(right);
    }

    // 上游
    private void swim(int k) {
        while (k / 2 >= 1 && less(k, k / 2)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    // 下沉
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j + 1, j)) {
                ++j;
            }
            if (!less(j, k)) {
                break;
            }
            exch(k, j);
            k = j;
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
        if (size >= newCapacity) {
            throw new RuntimeException();
        }
        int[] tmp = new int[newCapacity];
        System.arraycopy(pq, 1, tmp, 1, size);
        pq = tmp;
    }
}
