## week08

### 分配饼干 (455)

假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是每个孩子最多只能给一块饼干。对每个孩子i，都有一个胃口值gi，
这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干j，都有一个尺寸sj。如果sj>=gi，我们可以将这个饼干j分配给孩子i，
这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
- 你可以假设胃口值为正
- 每个小朋友最多只能拥有一块饼干

```java
public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);

    int idx = 0;
    for (int cookie : s) {
        if (idx == g.length) {
            break;
        }
        // 当前饼干要么满足当前最小需求，被使用掉，且只能使用一次
        // 要么不满足当前最小需求，直接被丢弃
        if (cookie >= g[idx]) {
            ++idx;
        }
    }
    return idx;
}
```

### 判断子序列 (392)

给定字符串s和t，判断s是否为t的子序列:

```java
public boolean isSubsequence(String s, String t) {
    int idx = -1;
    for (char ch : s.toCharArray()) {
        idx = t.indexOf(ch, idx + 1);
        if (idx < 0) {
            return false;
        }
    }
    return true;
}
```

### 无重叠区间 (435)

给定一个区间的集合intervals，其中intervals[i]=[start_i, end_i]，返回需要移除区间的最小数量，使剩余区间互不重:

```java
public int eraseOverlapInterval(int[][] intervals) {
    if (intervals.length == 0) {
        return 0;
    }

    // 区间右侧从小到大排序，相同右侧按照左侧从小到大排序
    Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
    int cnt = 1;
    int pre = 0;
    for (int i = 1; i < intervals.length; ++i) {
        // 下一个区间的左侧尽可能大于等于上一个区间的右侧
        if (intervals[i][0] > intervals[pre][1]) {
            ++cnt;
            pre = i;
        }
    }
    return intervals.length - cnt;
}

public int eraseOverlapIntervalV2(int[][] intervals) {
    if (intervals.length == 0) {
        return 0;
    }

    // 区间右侧从小到大排序，相同右侧按照左侧从小到大排序
    Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);

    int[] dp = new int[intervals.length];
    for (int i = 1; i < intervals.length; i++) {
        for (int j = 0; j < i; j++) {
            if (intervals[i][0] >= intervals[j][1]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    int res = -1;
    for (int n : dp) {
        res = Math.max(res, n);
    }
    return intervals.length - res;
}
```