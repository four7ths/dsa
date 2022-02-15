package com.four7ths.dsa.data.structure.unionfind;

/**
 * QuickUnion优化：根据并联通分量高度rank，rank小的联通分量往rank大的联通分量合并
 */
public class RankQuickUnion extends AbstractUF {

    private final int[] rank;

    public RankQuickUnion(int size) {
        super(size);
        rank = new int[size];
    }


    @Override
    protected void union0(int pRoot, int qRoot) {
        // make shorter root point to taller one
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            ++rank[pRoot];
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
