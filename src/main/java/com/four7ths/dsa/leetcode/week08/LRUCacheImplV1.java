package com.four7ths.dsa.leetcode.week08;

import java.util.HashMap;
import java.util.Map;

/**
 * 146 LRU缓存
 * 双向链表+哈希表实现
 */
public class LRUCacheImplV1 {

    private static class Node {
        private int key;
        private int val;
        private Node prev;
        private Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int size;
    private final int capacity;
    private final Node head;
    private final Node tail;
    private final Map<Integer, Node> cache = new HashMap<>();

    public LRUCacheImplV1(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node != null) {
            move2Head(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            node.val = value;
            move2Head(node);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            add2Head(newNode);
            ++size;
            if (size > capacity) {
                Node curTail = removeTail();
                cache.remove(curTail.key);
                --size;
            }
        }
    }

    private Node removeTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void add2Head(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // 先删除，再添加到头节点
    private void move2Head(Node node) {
        removeNode(node);
        add2Head(node);
    }
}
