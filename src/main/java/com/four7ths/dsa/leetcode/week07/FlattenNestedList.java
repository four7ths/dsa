package com.four7ths.dsa.leetcode.week07;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 341 扁平化嵌套列表迭代器
 */
interface NestedInteger {
    /**
     * @return true if this NestedInteger holds a single integer, rather than a nested list.
     */
    boolean isInteger();

    /**
     * @return the single integer that this NestedInteger holds, if it holds a single integer
     * Return null if this NestedInteger holds a nested list
     */
    Integer getInteger();

    /**
     * @return the nested list that this NestedInteger holds, if it holds a nested list
     * Return null if this NestedInteger holds a single integer
     */
    List<NestedInteger> getList();
}

public class FlattenNestedList implements Iterator<Integer> {

    private final Deque<NestedInteger> stk = new LinkedList<>();

    public FlattenNestedList(List<NestedInteger> nestedList) {
        // 注意倒序
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stk.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        if (stk.isEmpty()) {
            return false;
        }
        while (!stk.isEmpty() && !stk.peek().isInteger()) {
            NestedInteger tmp = stk.pop();
            List<NestedInteger> tmpList = tmp.getList();
            // 注意倒序
            for (int i = tmpList.size() - 1; i >= 0; --i) {
                stk.push(tmpList.get(i));
            }
        }
        return !stk.isEmpty();
    }

    @Override
    public Integer next() {
        return stk.pop().getInteger();
    }
}
