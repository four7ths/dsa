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

### 下一个排列 (31)

整数数组的下一个排列 是指其整数的下一个字典序更大的排列:

```java
// 5 3 9 4 1 -> 5 4 1 3 9
public void nextPermutation(int[] nums) {
    if (nums == null || nums.length <= 1) {
        return;
    }
    int i = nums.length - 2;
    // 逆序寻找第一个nums[i] < nums[i+1]
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        --i;
    }
    if (i < 0) {
        reverse(nums, 0, nums.length - 1);
        return;
    }
    // nums[i+1...len]逆序寻找第一个nums[j] > nums[i]
    int j = nums.length - 1;
    while (j > i && nums[j] <= nums[i]) {
        --j;
    }
    swap(nums, i, j);
    reverse(nums, i + 1, nums.length - 1);
}

private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}

private void reverse(int[] nums, int l, int r) {
    while (l < r) {
        swap(nums, l++, r--);
    }
}
```

### 在排序数组中查找元素的第一个和最后一个位置 (34)

给定一个按照升序排列的整数数组nums，和一个目标值target，找出给定目标值在数组中的开始位置和结束位置:

```java
public int[] searchRange(int[] nums, int target) {
    int[] res = new int[2];
    Arrays.fill(res, -1);
    res[0] = bsLow(nums, target);
    res[1] = bsHigh(nums, target);
    return res;
}

private int bsLow(int[] arr, int t) {
    int l = 0;
    int r = arr.length - 1;
    while (l <= r) {
        int mid = l + ((r - l) >>> 1);
        if (arr[mid] == t) {
            if (mid == 0 || arr[mid - 1] != t) {
                return mid;
            } else {
                r = mid - 1;
            }
        } else if (arr[mid] > t) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    return -1;
}

private int bsHigh(int[] arr, int t) {
    int l = 0;
    int r = arr.length - 1;
    while (l <= r) {
        int mid = l + ((r - l) >>> 1);
        if (arr[mid] == t) {
            if (mid == arr.length - 1 || arr[mid + 1] != t) {
                return mid;
            } else {
                l = mid + 1;
            }
        } else if (arr[mid] > t) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    return -1;
}
```

### 最长有效括号 (32)

只包含'('和')'的字符串，找出最长有效（格式正确且连续）括号子串的长度:

```java
// ")()((())"的mark为[1, 0, 0, 1, 0, 0, 0, 0]
public int longestValidParentheses(String s) {
    int[] mark = new int[s.length()];
    Deque<Integer> stk = new LinkedList<>();

    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '(') {
            stk.push(i);
        } else {
            if (stk.isEmpty()) {
                mark[i] = 1;
            } else {
                stk.pop();
            }
        }
    }
    while (!stk.isEmpty()) {
        mark[stk.pop()] = 1;
    }
    int len = 0;
    int ans = 0;
    for (int n : mark) {
        if (n == 1) {
            len = 0;
            continue;
        }
        ++len;
        ans = Math.max(ans, len);
    }
    return ans;
}

public int longestValidParenthesesV2(String s) {
    int[] dp = new int[s.length()];
    int res = 0;
    for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == '(') {
            if (s.charAt(i - 1) == '(') {
                dp[i] = 2;
                if (i - 2 >= 0) {
                    dp[i] += dp[i - 2];
                }
            } else {
                // dp[i-1] = 2, dp[idx-1] = 2
                //  0       idx       i
                //  )  (  )  (  (  )  )
                int idx = i - dp[i - 1] - 1;
                if (idx >= 0 && s.charAt(idx) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    if (idx - 1 >= 0) {
                        dp[i] += dp[idx - 1];
                    }
                }
            }
            res = Math.max(res, dp[i]);
        }
    }
    return res;
}
```

### 搜索旋转排序数组 (33)

e.g.: [4,5,6,7,0,1,2]中查找5的索引:

```java
public int search(int[] nums, int target) {
    int l = 0;
    int r = nums.length - 1;
    while (l <= r) {
        int mid = l + ((r - l) >> 1);
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[l] <= nums[mid]) {
            if (nums[l] <= target && target <= nums[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        } else {
            if (nums[mid] <= target && target <= nums[r]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
    }
    return -1;
}
```

### 接雨滴 I (42)

```java
public int trap(int[] height) {
    int[] leftMax = new int[height.length];
    int[] rightMax = new int[height.length];

    for (int i = 1; i < height.length; i++) {
        leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
    }

    for (int i = height.length - 2; i >= 0; i--) {
        rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
    }

    int res = 0;
    for (int i = 1; i < height.length - 1; i++) {
        int min = Math.min(leftMax[i], rightMax[i]);
        if (min > height[i]) {
            res += (min - height[i]);
        }
    }
    return res;
}

public int trapV2(int[] height) {
    int ans = 0;
    // 单调栈：栈低元素大于等于栈顶元素
    // 当遍历当第i个元素，且栈中存在至少两个元素时：栈顶元素为top，下一个为left，一定有：
    // height[top] <= height[left]，如果height[i] > height[top]，则存在接雨滴区域:
    // width: (i-left-i), hgt: min(height[left], height[i]) - height[top]
    Deque<Integer> stack = new LinkedList<>();
    int n = height.length;
    for (int i = 0; i < n; ++i) {
        while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
            int top = stack.pop();
            if (stack.isEmpty()) {
                break;
            }
            int left = stack.peek();
            int currWidth = i - left - 1;
            int currHeight = Math.min(height[left], height[i]) - height[top];
            ans += currWidth * currHeight;
        }
        stack.push(i);
    }
    return ans;
}

public int trapV3(int[] height) {
    int res = 0;
    int leftMax = 0;
    int rightMax = 0;
    int l = 0;
    int r = height.length - 1;
    while (l < r) {
        if (height[l] < height[r]) {
            if (height[l] >= leftMax) {
                leftMax = height[l];
            } else {
                res += leftMax - height[l];
            }
            ++l;
        } else {
            if (height[r] >= rightMax) {
                rightMax = height[r];
            } else {
                res += rightMax - height[r];
            }
            --r;
        }
    }
    return res;
}
```

### 接雨滴 II (407)

```java
public int trapRainWater(int[][] heightMap) {
    int m = heightMap.length;
    int n = heightMap[0].length;
    // {x,y,height}
    Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    boolean[][] vis = new boolean[m][n];
    for (int i = 0; i < n; i++) {
        q.add(new int[] {0, i, heightMap[0][i]});
        q.add(new int[] {m - 1, i, heightMap[m - 1][i]});
        vis[0][i] = vis[m - 1][i] = true;
    }
    for (int i = 1; i < m - 1; i++) {
        q.add(new int[] {i, 0, heightMap[i][0]});
        q.add(new int[] {i, n - 1, heightMap[i][n - 1]});
        vis[i][0] = vis[i][n - 1] = true;
    }

    int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int ans = 0;
    while (!q.isEmpty()) {
        int[] poll = q.poll();
        int x = poll[0];
        int y = poll[1];
        int h = poll[2];
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            if (vis[nx][ny]) {
                continue;
            }
            if (h > heightMap[nx][ny]) {
                ans += h - heightMap[nx][ny];
            }
            // 注意这里应该是灌过水之后的高度
            q.add(new int[] {nx, ny, Math.max(heightMap[nx][ny], h)});
            vis[nx][ny] = true;
        }
    }
    return ans;
}
```