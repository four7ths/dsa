package com.four7ths.dsa.data.structure.unionfind;

/**
 * Quick Find
 */
public class QuickFind extends AbstractUF {

    public QuickFind(int size) {
        super(size);
    }

    @Override
    protected void union0(int pRoot, int qRoot) {
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == qRoot) {
                parent[i] = pRoot;
            }
        }
    }

    @Override
    public int find(int p) {
        check(p);
        return parent[p];
    }

}
