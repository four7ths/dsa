## week02

### Long substring without repeating characters (3)

给定一个字符串，找出其中不含有重复字符的最长子串的长度:

```java
public int lengthOfLongestSubstring(String s) {
    if (s.length() <= 1) {
        return s.length();
    }
    int l = 0;
    int r = -1;
    int[] freq = new int[256];
    int ret = 0;
    while (l < s.length()) {
        if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
            freq[s.charAt(++r)]++;
        } else {
            freq[s.charAt(l++)]--;
        }
        ret = Math.max(ret, r - l + 1);
    }
    return ret;
}
```

### Find all anagrams in a string (438)

给定一个字符串s和p，找出s中所有是s字母异位词的子串，返回这些子串的索引:

```java
// 把原本需要对s进行排序的操作转换为两个数组的比较
public List<Integer> findAnagrams(String s, String p) {
    List<Integer> ret = new ArrayList<>();
    if (p.length() > s.length()) {
        return ret;
    }

    int l = 0, r = -1;
    int[] freqS = new int[256];
    int[] freqP = new int[256];
    for (char ch : p.toCharArray()) {
        freqP[ch - 'a']++;
        freqS[s.charAt(++r) - 'a']++;
    }
    if (Arrays.equals(freqP, freqS)) {
        ret.add(l);
    }

    while (r < s.length() - 1) {
        ++freqP[s.charAt(++r) - 'a'];
        --freqS[s.charAt(l++) - 'a'];
        if (Arrays.equals(freqP, freqS)) {
            ret.add(l);
        }
    }
    return ret;
}
```


### Minimum window substring (76)

给定一个字符串s、一个字符串t，在字符串s里面找出包含t所有字母的最小子串:

```java
// 'A' -> 'z' ==> 65 -> 122
private static final int LEN = 58;

public String minWindow(String s, String t) {
    if (s.length() < t.length()) {
        return "";
    }
    int l = 0, r = -1;
    int[] freqS = new int[LEN];
    int[] freqT = new int[LEN];
    for (char ch : t.toCharArray()) {
        freqT[ch - 'A']++;
    }

    int start = -1, end = s.length() + 1;
    while (l <= s.length() - t.length()) {
        if ((r - l + 1) < t.length()) {
            if (r + 1 < s.length()) {
                ++freqS[s.charAt(++r) - 'A'];
                continue;
            } else {
                break;
            }
        }
        // 当前区间长度大于等于t.length
        int idx = 0;
        while (idx < LEN) {
            if (freqS[idx] < freqT[idx]) {
                break;
            }
            ++idx;
        }
        // 当前区间并不能包含字符串t，只能尝试s[l...r+1]
        if (idx != LEN) {
            if (r + 1 < s.length()) {
                ++freqS[s.charAt(++r) - 'A'];
            } else {
                break;
            }
        } else { // 当前区间包含字符串t
            if ((r - l + 1) == t.length()) {
                return s.substring(l, r + 1);
            } else {
                if (r - l < end - start) {
                    start = l;
                    end = r;
                }
                --freqS[s.charAt(l++) - 'A'];
            }
        }
    }
    return start == -1 ? "" : s.substring(start, end + 1);
```


### reverse linked list I (206)

翻转链表:

```java
public ListNode reverseList(ListNode head) {
    if (head == null) {
        return null;
    }
    ListNode prev = null;
    ListNode cur = head;
    while (cur != null) {
        ListNode next = cur.next;
        cur.next = prev;
        prev = cur;
        cur = next;
    }
    return prev;
}

public ListNode reverseListRecursive(ListNode head) {
    return reverseListRecursive(null, head);
}

public ListNode reverseListRecursive(ListNode prev, ListNode cur) {
    if (cur == null) {
        return prev;
    }
    ListNode next = cur.next;
    cur.next = prev;
    return reverseListRecursive(cur, next);
}
```

### reverse linked list II (92)

反转位置m到n的链表，1 <= m <= n <= 链表长度:

```java
public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null || head.next == null || m == n) {
        return head;
    }
    ListNode dmyNode = new ListNode(-1);
    dmyNode.next = head;

    ListNode prev = dmyNode;
    ListNode cur = head;

    for (int i = 1; i < m; i++) {
        ListNode next = cur.next;
        prev = cur;
        cur = next;
    }
    ListNode prevMark = prev;
    ListNode curMark = cur;

    for (int i = m; i <= n; i++) {
        ListNode next = cur.next;
        cur.next = prev;
        prev = cur;
        cur = next;
    }

    prevMark.next = prev;
    curMark.next = cur;

    return dmyNode.next;
}
```

### remove duplicates from sorted list (83)

存在一个按升序排列的链表，删除所有重复的元素，使每个元素只出现一次:

```java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
        return null;
    }
    ListNode cur = head;
    while (cur.next != null) {
        if (cur.val == cur.next.val) {
            cur = cur.next.next;
        } else
            cur = cur.next;
    }
    return head;
}
```

### partition list (86)

给定一个链表和一个特定值x，对链表进行分隔，使得所有小于x的节点都在大于或等于x的节点之前， 
注意保留两个分区中每个节点的初始相对位置:

```java
public ListNode partition(ListNode head, int x) {
    ListNode lowNode = new ListNode(-1);
    ListNode highNode = new ListNode(-1);

    ListNode low = lowNode;
    ListNode high = highNode;

    while (head != null) {
        if (head.val < x) {
            low.next = head;
            low = low.next;
        } else {
            high.next = head;
            high = high.next;
        }
        head = head.next;
    }

    high.next = null;
    low.next = highNode.next;
    return lowNode.next;
}
```


### odd even linked list (328)

给定一个单链表，把所有索引位（从1开始）为奇数节点和偶数节点分别排在一起:

```java
public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }

    ListNode oddNode = new ListNode(-1);
    ListNode evenNode = new ListNode(-1);

    ListNode odd = oddNode;
    ListNode even = evenNode;

    int idx = 1;
    while (head != null) {
        if ((idx & 1) == 1) {
            odd.next = head;
            odd = odd.next;
        } else {
            even.next = head;
            even = even.next;
        }
        head = head.next;
        ++idx;
    }
    even.next = null; // 注意这个赋值操作
    odd.next = evenNode.next;
    return oddNode.next;
}
```


### add two numbers (2)

给出两个【非空】的链表用来表示两个非负的整数。其中它们各自的位数是按照【逆序】的方式存储的， 
并且它们的每个节点只能存储一位数字:

```java
// TODO: 增加递归实现
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dmyNode = new ListNode(-1);
    ListNode cur = dmyNode;
    int carry = 0;

    while (l1 != null || l2 != null || carry != 0) {
        int n1 = l1 == null ? 0 : l1.val;
        int n2 = l2 == null ? 0 : l2.val;
        int tmp = n1 + n2 + carry;
        ListNode newNode = new ListNode((tmp) % 10);
        carry = tmp / 10;
        cur.next = newNode;
        cur = cur.next;

        l1 = l1 == null ? null : l1.next;
        l2 = l2 == null ? null : l2.next;
    }
    return dmyNode.next;
}
```

### remove linked list elements (203)

删除链表中等于给定值【val】的所有节点:

```java
public ListNode removeElements(ListNode head, int val) {
    ListNode dmyNode = new ListNode(-1);
    dmyNode.next = head;

    ListNode prev = dmyNode;
    while (prev.next != null) {
        if (prev.next.val == val) {
            prev.next = prev.next.next;
        } else {
            prev = prev.next;
        }
    }

    return dmyNode.next;
}
```


### remove duplicates from sorted list II (82)
给定一个【排序】链表，删除所有含有重复数字的节点，只保留原始链表中【没有重复出现】的数字:
- \>\> 1 -> 2 -> 3 -> 3 > 4 -> 4 -> 5
- \<\< 1 -> 2 -> 5

```java
public ListNode deleteDuplication01(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }

    ListNode dmyNode = new ListNode(-1);
    dmyNode.next = head;

    ListNode prev = dmyNode;
    ListNode cur = head;

    while (cur != null && cur.next != null) {
        if (cur.val == cur.next.val) {
            do {
                cur = cur.next;
            } while (cur.next != null && cur.val == cur.next.val);
            prev.next = cur.next;
        } else {
            prev = cur;
        }
        cur = cur.next;
    }

    return dmyNode.next;
}
```


### merge two sorted list (23)

合并两个有序链表:

```java
public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
    if (l1 == null) {
        return l2;
    }
    if (l2 == null) {
        return l1;
    }
    ListNode head;
    if (l1.val < l2.val) {
        head = l1;
        head.next = mergeTwoLists(l1.next, l2);
    } else {
        head = l2;
        head.next = mergeTwoLists(l1, l2.next);
    }
    return head;
}

public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dmyNode = new ListNode(-1);
    ListNode cur = dmyNode;

    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            cur.next = l1;
            l1 = l1.next;
        } else {
            cur.next = l2;
            l2 = l2.next;
        }
        cur = cur.next;
    }
    if (l1 != null) {
        cur.next = l1;
    }
    if (l2 != null) {
        cur.next = l2;
    }
    return dmyNode.next;
}
```

### 两数相加 II (445)

给定两个非空链表来代表两个非负整数，数字最高位位于链表开始位置。它们的每个节点只存储单个数字，将这两数相加会返回一个新的链表。
注意不能对链表中元素进行翻转:
- 输入：l1 = [7,2,4,3], l2 = [5,6,4]
- 输出：[7,8,0,7]

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Deque<ListNode> stk1 = new LinkedList<>();
    Deque<ListNode> stk2 = new LinkedList<>();

    ListNode p = l1;
    while (p != null) {
        stk1.push(p);
        p = p.next;
    }
    p = l2;
    while (p != null) {
        stk2.push(p);
        p = p.next;
    }

    int carry = 0;
    ListNode head = null;
    while (!stk1.isEmpty() || !stk2.isEmpty() || carry == 1) {
        int n1 = stk1.isEmpty() ? 0 : stk1.pop().val;
        int n2 = stk2.isEmpty() ? 0 : stk2.pop().val;
        int val = (n1 + n2 + carry) % 10;
        carry = (n1 + n2 + carry) / 10;
        head = new ListNode(val, head);
    }

    return head;
}

// 递归：较短链表的值加载较长链表上
public ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
    if (l1 == null) {
        return l2;
    }
    if (l2 == null) {
        return l1;
    }
    int len1 = 0;
    int len2 = 0;
    ListNode p = l1;
    while (p != null) {
        ++len1;
        p = p.next;
    }
    p = l2;
    while (p != null) {
        ++len2;
        p = p.next;
    }

    ListNode head = len1 >= len2
                    ? addTwoNumbers0(l1, len1, l2, len2)
                    : addTwoNumbers0(l2, len2, l1, len1);
    if (c == 1) {
        head = new ListNode(1, head);
    }

    return head;
}

private int c; // 进位

// len1 >= len2
private ListNode addTwoNumbers0(ListNode longList, int len1, ListNode shotList, int len2) {
    if (len1 == len2 && len1 == 1) {
        int tmp = longList.val;
        longList.val = (tmp + shotList.val) % 10;
        c = (tmp + shotList.val) / 10;
        return longList;
    }
    if (len1 > len2) {
        int tmp = longList.val;
        longList.next = addTwoNumbers0(longList.next, len1 - 1, shotList, len2);
        longList.val = (tmp + c) % 10;
        c = (tmp + c) / 10;
        return longList;
    }
    longList.next = addTwoNumbers0(longList.next, len1 - 1, shotList.next, len2 - 1);
    int tmp = longList.val;
    longList.val = (tmp + shotList.val + c) % 10;
    c = (tmp + shotList.val + c) / 10;
    return longList;
}
```

### O(1)删除链表中节点 237

```java
public void deleteNode(ListNode node) {
    if (node == null) {
        return;
    }
    if (node.next == null) {
        node = null;
        return;
    }
    node.val = node.next.val;
    node.next = node.next.next;
}
```

### 链表插入排序 (147) 

```java
public ListNode insertionSortList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }

    ListNode dmyNode = new ListNode(-1);
    dmyNode.next = head;

    ListNode prev = head;
    ListNode cur = prev.next;
    while (cur != null) {
        if (cur.val >= prev.val) {
            prev = cur;
            cur = cur.next;
            continue;
        }
        // dmyNode.next -> ... -> prev -> cur -> ...
        // [dmyNode.next...prev]都是有序的
        ListNode pp = dmyNode;
        ListNode p = pp.next;
        while (cur.val > p.val) {
            pp = p;
            p = p.next;
        }
        // 将cur插入到pp -> p之间: pp -> cur -> p -> ... -> prev -> cur.next
        prev.next = cur.next;
        cur.next = p;
        pp.next = cur;

        cur = prev.next;
    }

    return dmyNode.next;
}
```

### 删除链表的倒数第n个结点 (19)

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dmyNode = new ListNode(-1);
    dmyNode.next = head;

    ListNode f = dmyNode;
    for (int i = 0; i < n + 1; i++) {
        if (f == null) {
            return null;
        }
        f = f.next;
    }

    ListNode s = dmyNode;
    while (f != null) {
        s = s.next;
        f = f.next;
    }

    s.next = s.next.next;
    return dmyNode.next;
}
```

### k组反转链表 (25)

给定一个链表，每k个节点一组进行翻转，返回翻转后的链表，k是一个正整数，它的值小于或等于链表的长度:

```java
public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null || k == 1) {
        return head;
    }

    // prev -> rangeStart -> ... -> rangeEnd -> next -> ...
    ListNode dmyNode = new ListNode(-1);
    dmyNode.next = head;
    ListNode prev = dmyNode;

    ListNode rangeStart;
    ListNode rangeEnd;
    ListNode next;

    while ((rangeEnd = hasNextKNode(prev, k)) != null) {
        next = rangeEnd.next;
        rangeStart = prev.next;

        // 翻转[rangeStart, rangeEnd]
        ListNode cur = rangeStart;
        ListNode tmpPrev = prev;
        while (cur != next) {
            ListNode tmpNext = cur.next;
            cur.next = tmpPrev;
            tmpPrev = cur;
            cur = tmpNext;
        }

        prev.next = tmpPrev;
        rangeStart.next = next;
        prev = rangeStart;
    }

    return dmyNode.next;
}

// 递归实现
public ListNode reverseKGroupV2(ListNode head, int k) {
    ListNode tail = head;
    for (int i = 0; i < k; i++) {
        if (tail == null) {
            return head;
        }
        tail = tail.next;
    }
    ListNode newHead = reverseRange(head, tail);
    head.next = reverseKGroupV2(tail, k);
    return newHead;
}

// 反转[head, tail)之间节点
private ListNode reverseRange(ListNode head, ListNode tail) {
    ListNode prev = null;
    ListNode cur = head;
    ListNode next;
    while (cur != tail) {
        next = cur.next;
        cur.next = prev;
        prev = cur;
        cur = next;
    }
    return prev;
}


private ListNode hasNextKNode(ListNode prev, int k) {
    ListNode cur = prev;
    for (int i = 0; i < k; i++) {
        if (cur.next == null) {
            return null;
        }
        cur = cur.next;
    }
    return cur;
}
```

### O(nlogn)时间复杂度内完成链表排序 (148)

```java
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    return mergeSort(head);
}

private ListNode mergeSort(/*@NonNull*/ ListNode head) {
    if (head.next == null) {
        return head;
    }
    ListNode s = head;
    ListNode f = head;
    ListNode prev = null;
    while (f != null && f.next != null) {
        prev = s;
        s = s.next;
        f = f.next.next;
    }
    prev.next = null;
    ListNode left = mergeSort(head);
    ListNode right = mergeSort(s);
    return merge(left, right);
}

private ListNode merge(ListNode left, ListNode right) {
    ListNode dmyNode = new ListNode(-1);
    ListNode prev = dmyNode;
    while (left != null && right != null) {
        if (left.val < right.val) {
            prev.next = left;
            left = left.next;
        } else {
            prev.next = right;
            right = right.next;
        }
        prev = prev.next;
    }
    prev.next = left == null ? right : left;
    return dmyNode.next;
}

// 自底向上归并排序
public ListNode sortListV2(ListNode head) {
    ListNode dmyNode = new ListNode(-1);
    dmyNode.next = head;

    ListNode cur = head;
    int len = 0;
    while (cur != null) {
        ++len;
        cur = cur.next;
    }

    for (int sz = 1; sz < len; sz *= 2) {
        ListNode start = dmyNode.next;
        // 指向局部有序链表的最后一个节点
        ListNode end = dmyNode;
        while (start != null) {
            ListNode left = start;
            ListNode right = cut(start, sz);
            start = cut(right, sz);
            end.next = merge(left, right);

            while (end.next != null) {
                end = end.next;
            }
        }
    }

    return dmyNode.next;
}

// 输入：head -> n1 -> n2 -> ... -> null, size = 2
// 1.head -> n1 -> null
// 2.return: n2 -> ... -> null
private ListNode cut(ListNode head, int size) {
    ListNode cur = head;
    while (--size > 0 && cur != null) {
        cur = cur.next;
    }
    if (cur == null) {
        return null;
    }
    ListNode next = cur.next;
    cur.next = null;
    return next;
}
```
