package com.four7ths.dsa.data.struct.unionfind;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.four7ths.dsa.data.structure.unionfind.PathCompressWeightQuickUnion;
import com.four7ths.dsa.data.structure.unionfind.QuickFind;
import com.four7ths.dsa.data.structure.unionfind.QuickUnion;
import com.four7ths.dsa.data.structure.unionfind.RankQuickUnion;
import com.four7ths.dsa.data.structure.unionfind.UF;
import com.four7ths.dsa.data.structure.unionfind.WeightQuickUnion;

/**
 * UnionFind各类实现测试类
 */
public class UFTest {

    private static int size;

    @BeforeClass
    public static void init() {
        size = 10;
    }

    @Test
    public void QuickFindTest() {
        UF uf = new QuickFind(size);
        UFTest(uf, size);
    }

    @Test
    public void QuickUnionTest() {
        UF uf = new QuickUnion(size);
        UFTest(uf, size);
    }

    @Test
    public void WeightQuickUnionTest() {
        UF uf = new WeightQuickUnion(size);
        UFTest(uf, size);
    }

    @Test
    public void RankQuickUnionTest() {
        UF uf = new RankQuickUnion(size);
        UFTest(uf, size);
    }

    @Test
    public void PathCompressWeightQuickUnionTest() {
        UF uf = new PathCompressWeightQuickUnion(size);
        UFTest(uf, size);
    }

    private void UFTest(UF uf, int n) {

        Assert.assertEquals(n, uf.size());

        Assert.assertFalse(uf.isConnected(0, n - 1));

        uf.union(0, n - 1);
        Assert.assertEquals(n - 1, uf.size());

        uf.union(0, n - 2);
        Assert.assertEquals(n - 2, uf.size());

        Assert.assertTrue(uf.isConnected(n - 1, n - 2));

        uf.union(n - 1, n - 2);
        Assert.assertEquals(n - 2, uf.size());

        for (int i = 0; i < n - 1; i++) {
            uf.union(i, i + 1);
        }
        Assert.assertEquals(1, uf.size());
    }

}
