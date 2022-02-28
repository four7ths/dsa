package com.four7ths.dsa.offer;

import java.util.HashMap;
import java.util.Map;

import com.four7ths.dsa.common.ListNode;

/**
 * 两个链表的第一个公共节点
 */
public class Q36FindFirstCommonNode {
    public ListNode findFirstCommonNode(ListNode p, ListNode q) {
        if (p == null || q == null) {
            return null;
        }
        Map<ListNode, Integer> maps = new HashMap<>();
        ListNode probe = p;
        while (probe != null) {
            maps.put(probe, 1);
            probe = probe.next;
        }
        probe = q;
        while (probe != null) {
            if (maps.get(probe) != null) {
                return probe;
            }
            probe = probe.next;
        }
        return null;
    }

    public ListNode findFirstCommonNodeV2(ListNode p, ListNode q) {
        if (p == null || q == null) {
            return null;
        }
        ListNode p1 = p;
        ListNode p2 = q;
        while (p1 != p2) {
            p1 = p1 == null ? q : p1.next;
            p2 = p2 == null ? p : p2.next;
        }
        return p1;
    }
}
