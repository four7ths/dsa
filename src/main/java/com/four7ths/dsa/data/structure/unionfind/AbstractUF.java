package com.four7ths.dsa.data.structure.unionfind;

/**
 * 联通分量公共逻辑抽取
 */
public abstract class AbstractUF implements UF {

    protected int[] parent;
    int ccCount;

    protected AbstractUF(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        ccCount = size;
    }

    @Override
    public int size() {
        return ccCount;
    }

    @Override
    public boolean isConnected(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        return pRoot == qRoot;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        --ccCount;
        union0(pRoot, qRoot);
    }

    protected void check(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 以qRoot为根节点联通分量合并成为以pRoot为根节点连通分量的一部分
     *
     * @param pRoot 节点p的根节点
     * @param qRoot 节点q的根节点
     */
    protected abstract void union0(int pRoot, int qRoot);
}
