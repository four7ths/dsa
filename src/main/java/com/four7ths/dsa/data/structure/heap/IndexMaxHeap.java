package com.four7ths.dsa.data.structure.heap;

/**
 * 索引最大堆
 */
public class IndexMaxHeap<Key extends Comparable<Key>> {
    private int maxN; // 索引堆限制大小
    private int size; // 索引堆当前元素
    private int[] pq; // pq[j]=(i,key)
    private int[] qp; // qp[pq[i]]=i, pq[qp[i]]=i
    private Key[] keys;

    public IndexMaxHeap(int maxN) {
        this.maxN = maxN;
        this.size = 0;
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        keys = (Key[]) new Comparable[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    // 给定索引i是否在索引队列中
    public boolean contain(int i) {
        valid(i);
        return qp[i] != -1;
    }

    // 索引队列中最大Key对应的索引
    public int maxIndex() {
        return pq[1];
    }

    // 索引队列中最大Key
    public Key maxKey() {
        return keys[maxIndex()];
    }

    // 删除索引队列中最大Key，并返回其对应的索引值
    public int delMax() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        int maxIdx = maxIndex();
        exch(1, size--);
        sink(1);
        qp[maxIdx] = -1;
        keys[maxIdx] = null;
        pq[size + 1] = -1;
        return maxIdx;
    }

    // 向索引队列中插入新数据[i, key]
    public void insert(int i, Key key) {
        valid(i);
        if (contain(i)) {
            throw new RuntimeException();
        }
        ++size;
        pq[size] = i;
        qp[i] = size;
        keys[i] = key;
        swim(size);
    }

    // 在索引队列中删除给定索引对应的值
    public void delete(int i) {
        valid(i);
        if (!contain(i)) {
            throw new RuntimeException();
        }
        int idx = qp[i];
        exch(idx, size--);
        swim(idx);
        sink(idx);
        keys[i] = null;
        qp[i] = -1;
    }

    // 给指定索引的值增加到newKey
    public void incrKey(int i, Key newKey) {
        valid(i);
        if (keys[i].compareTo(newKey) >= 0) {
            throw new RuntimeException();
        }
        keys[i] = newKey;
        swim(qp[i]);
    }

    // 给指定索引的值减少到newKey
    public void descKey(int i, Key newKey) {
        valid(i);
        if (keys[i].compareTo(newKey) <= 0) {
            throw new RuntimeException();
        }
        keys[i] = newKey;
        sink(qp[i]);
    }

    // 更新指定索引的Key值
    public void change(int i, Key newKey) {
        valid(i);
        if (!contain(i)) {
            throw new RuntimeException();
        }
        keys[i] = newKey;
        swim(qp[i]);
        sink(qp[i]);
    }

    @Deprecated
    public void changeWithoutQP(int i, Key newKey) {
        valid(i);
        if (!contain(i)) {
            throw new RuntimeException();
        }
        keys[i] = newKey;
        // O(n): finding [idx] in pq for giving i
        for (int idx = 1; idx <= size; idx++) {
            if (pq[idx] == i) {
                swim(idx);
                sink(idx);
            }
        }
    }

    public Key keyOf(int i) {
        valid(i);
        return keys[i];
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j + 1 < size && less(j, j + 1)) {
                ++j;
            }
            if (!less(k, j)) {
                break;
            }
            k = j;
        }
    }

    private void exch(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void valid(int i) {
        if (i < 0 || i >= maxN) {
            throw new RuntimeException();
        }
    }
}
