package com.four7ths.dsa.data.structure.unionfind;

import java.util.Arrays;

/**
 * QuickUnion优化：根据权重合并联通分量，权重小的联通分量往权重大的联通分量合并
 */
public class WeightQuickUnion extends AbstractUF {

    private final int[] wgt;

    public WeightQuickUnion(int size) {
        super(size);
        wgt = new int[size];
        Arrays.fill(wgt, 1);
    }

    @Override
    protected void union0(int pRoot, int qRoot) {
        // make smaller root point to larger one
        if (wgt[pRoot] < wgt[qRoot]) {
            parent[pRoot] = qRoot;
            wgt[qRoot] += wgt[pRoot];
        } else {
            parent[qRoot] = pRoot;
            wgt[pRoot] += wgt[qRoot];
        }
    }

    @Override
    public int find(int p) {
        check(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
