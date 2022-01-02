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