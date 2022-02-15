package com.four7ths.dsa.data.structure.unionfind;

/**
 * 并查集数据结构API
 */
public interface UF {

    /**
     * 并查集中联通分量数量
     */
    int size();

    /**
     * 节点p和节点q是否是连接状态
     */
    boolean isConnected(int p, int q);

    /**
     * 连接节点p和节点q
     */
    void union(int p, int q);

    /**
     * 查找节点p所处的联通分量
     */
    int find(int p);
}
