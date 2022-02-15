package com.four7ths.dsa.data.structure.unionfind;

/**
 * @author tanglei <tanglei@kuaishou.com>
 * Created on 2022-02-15
 */
public class QuickUnion extends AbstractUF {

    public QuickUnion(int size) {
        super(size);
    }

    @Override
    protected void union0(int pRoot, int qRoot) {
        parent[qRoot] = pRoot;
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
