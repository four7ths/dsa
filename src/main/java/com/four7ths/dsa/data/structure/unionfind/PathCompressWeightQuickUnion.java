package com.four7ths.dsa.data.structure.unionfind;

import java.util.Arrays;

/**
 * QuickUnion优化：根据权重合并联通分量 && find方法包含路径压缩，权重小的联通分量往权重大的联通分量合并
 */
public class PathCompressWeightQuickUnion extends AbstractUF {

    private final int[] wgt;

    public PathCompressWeightQuickUnion(int size) {
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
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }

        // 路径压缩：节点p的根节点为root，p到root路径上所有的节点都直接指向p
        while (p != root) {
            int newP = parent[p];
            parent[p] = root;
            p = newP;
        }

        return root;
    }
}
