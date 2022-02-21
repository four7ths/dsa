## week06

### 爬楼梯 I (70)

n阶台阶，每次只能走1或2步，有多少种可能能到达台阶顶部:

```java
public int climbStairs(int n) {
    if (n <= 2) {
        return n;
    }

    int f1 = 1;
    int f2 = 2;
    int fn = 0;
    for (int i = 2; i < n; i++) {
        fn = f1 + f2;
        f1 = f2;
        f2 = fn;
    }
    return fn;
}
```

### 解码方法 (91)

一条包含字母A-Z的消息通过以下方式进行了编码：
- 'A' -> 1
- 'B' -> 2
- ...
- 'Z' -> 26

给定一个只包含数字的非空字符串，请计算解码方法的总数

```java
public int numDecodings(String s) {
    if (s == null || "".equals(s) || s.charAt(0) == '0') {
        return 0;
    }

    int g = 1;
    int f = 1;
    for (int i = 1; i < s.length(); i++) {
        int tmp = f;
        int cur = s.charAt(i) - '0';
        int prev = s.charAt(i - 1) - '0';
        if (cur == 0) {
            if (prev > 2 || prev == 0) {
                return 0;
            } else {
                f = g;
            }
        } else if (prev == 1 || (prev == 2 && cur >= 0 && cur <= 6)) {
            f += g;

        }
        g = tmp;
    }
    return f;
}
```

### 打家劫舍 I (198)

给定一个整数数组，偷取数组中数字达到最大值，要求所偷取的数字不能相邻:

```java
public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    if (nums.length == 1) {
        return nums[0];
    }
    if (nums.length == 2) {
        return Math.max(nums[0], nums[1]);
    }
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);

    for (int i = 2; i < nums.length; i++) {
        dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
    }
    return dp[nums.length - 1];
}
```

### 打家劫舍 II (213)

给定一个整数数组，偷取数组中数字达到最大值，要求所偷取的数字不能相邻，首尾也不能相连:

```java
public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    if (nums.length == 1) {
        return nums[0];
    }
    if (nums.length == 2) {
        return Math.max(nums[0], nums[1]);
    }
    return Math.max(doRob(Arrays.copyOfRange(nums, 1, nums.length)),
            doRob(Arrays.copyOfRange(nums, 0, nums.length - 1)));
}

private int doRob(int[] arr) {
    int prev = 0;
    int cur = 0;
    for (int n : arr) {
        int tmp = cur;
        cur = Math.max(cur, prev + n);
        prev = tmp;
    }
    return cur;
}
```

### 打家劫舍 III (337)

待偷取的东西是树状结构，要求：相邻节点不能偷取:

```java
public int rob(TreeNode root) {
    if (root == null) {
        return 0;
    }
    postOrder(root);
    return root.val;
}

private void postOrder(TreeNode root) {
    if (root.left != null) {
        postOrder(root.left);
    }
    if (root.right != null) {
        postOrder(root.right);
    }
    // 方式1：包含当前root节点的价值
    int res1 = root.val;
    // 方式2：不包含当前root节点的价值
    int res2 = 0;
    if (root.left != null) {
        res2 += root.left.val;
        if (root.left.left != null) {
            res1 += root.left.left.val;
        }
        if (root.left.right != null) {
            res1 += root.left.right.val;
        }
    }
    if (root.right != null) {
        res2 += root.right.val;
        if (root.right.left != null) {
            res1 += root.right.left.val;
        }
        if (root.right.right != null) {
            res1 += root.right.right.val;
        }
    }
    root.val = Math.max(res1, res2);
}
```

### 整数拆分 (343)

给定一个正整数n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化:

```java
public int integerBreak(int n) {
    // dp[i]: 数字i能够拆分的最大值
    int[] dp = new int[n + 1];
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
        for (int j = i - 1; j >= 1; j--) {
            dp[i] = Math.max(dp[i], dp[j] * (i - j));
            dp[i] = Math.max(dp[i], j * (i - j));
        }
    }
    return dp[n];
}
```

### 最小路径总和 (64)

给定一个包含非负整数的m*n网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小，每次只能向下或者向右移动一步:

```java
public int minPathSum(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    for (int i = 1; i < rows; i++) {
        grid[i][0] += grid[i - 1][0];
    }
    for (int i = 1; i < cols; i++) {
        grid[0][i] += grid[0][i - 1];
    }

    for (int i = 1; i < rows; i++) {
        for (int j = 1; j < cols; j++) {
            grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
        }
    }

    return grid[rows - 1][cols - 1];
}
```

### 三角形最小路径和 (120)

给定一个三角形，找出自顶向下的最小路径和，每一步只能移动到下一行中相邻的结点上:
`dp[i,j]=Math.min(dp[i+1,j], dp[i+1,j+1]) + arr[i,j]`

```java
public int minimumTotal(List<List<Integer>> triangle) {

    Integer[] dp = new Integer[triangle.size()];
    triangle.get(triangle.size() - 1).toArray(dp);

    for (int row = triangle.size() - 2; row >= 0; row--) {
        for (int col = 0; col < triangle.get(row).size(); col++) {
            dp[col] = Math.min(dp[col], dp[col + 1]) + triangle.get(row).get(col);
        }
    }

    return dp[0];
}

public int minimumTotalV2(List<List<Integer>> triangle) {
    for (int row = triangle.size() - 2; row >= 0; row--) {
        for (int col = 0; col < triangle.get(row).size(); col++) {
            int oldVal = triangle.get(row).get(col);
            triangle.get(row).set(col,
                    oldVal + Math.min(triangle.get(row + 1).get(col), triangle.get(row + 1).get(col + 1)));
        }
    }
    return triangle.get(0).get(0);
}
```

### 不同路径 I (62)

```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
        dp[i][0] = 1;
    }
    for (int i = 0; i < n; i++) {
        dp[0][i] = 1;
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
        }
    }
    return dp[m - 1][n - 1];
}

public int uniquePathsV2(int m, int n) {
    int[] dp = new int[m];
    Arrays.fill(dp, 1);

    for (int i = 0; i < n; i++) {
        for (int j = 1; j < m; j++) {
            dp[j] += dp[j - 1];
        }
    }

    return dp[m - 1];
}

public int uniquePathV3(int m, int n) {
    // C(m+n-2, n-1)
    return 0;
}
```

### 不同路径 II (63)

一个机器人位于一个m*n网格的左上角，数字1代表障碍，无法通过，机器人到达右下角路径所有的可能<br/>
// 输入: [[0,0,0],<br/>
//       [0,1,0],<br/>
//       [0,0,0]]<br/>
// 输出: 2<br/>

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid[0][0] == 1) {
        return 0;
    }

    int rows = obstacleGrid.length;
    int cols = obstacleGrid[0].length;

    int[][] dp = new int[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (obstacleGrid[i][j] == 1) {
                dp[i][j] = 0;
            } else {
                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                } else if (i == 0) {
                    dp[0][j] = dp[0][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][0];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
    }
    return dp[rows - 1][cols - 1];
}
```

### 零钱找零 I (322)

给你一个整数数组coins，表示不同面额的硬币，以及一个整数amount，表示总金额。计算并返回可以凑成总金额所需的最少的硬币个数，如果没有任何一种硬币组合能组成总金额返回-1，每种硬币的数量是无限的。

```java
private int minCnt = Integer.MAX_VALUE;

// dfs+剪枝
public int coinChange(int[] coins, int amount) {
    Arrays.sort(coins);
    dfs(coins, coins.length - 1, amount, 0);
    return minCnt == Integer.MAX_VALUE ? -1 : minCnt;
}

private void dfs(int[] coins, int idx, int left, int cnt) {
    if (idx < 0 || left / coins[idx] + cnt > minCnt) {
        return;
    }
    if (left % coins[idx] == 0) {
        minCnt = Math.min(minCnt, cnt + left / coins[idx]);
        return;
    }
    for (int i = left / coins[idx]; i >= 0; i--) {
        dfs(coins, idx - 1, left - i * coins[idx], cnt + i);
    }
}

public int coinChangeV2(int[] coins, int amount) {
    // dp[i]: 当持有金币为i时，所需兑换次数的最小值
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;

    for (int coin : coins) {
        for (int i = coin; i <= amount; i++) {
            if (dp[i - coin] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
}
```

### 零钱找零 II (518)
【组合问题】

给定一个整数数组coins表示不同面额的硬币，另给一个整数amount表示总金额，计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回0，
假设每一种面额的硬币有无限个:

```java
public int change(int amount, int[] coins) {
    // dp[i][j]: 从i种硬币中选择若干个（可为0），使得总和恰好是j
    int n = coins.length;
    int[][] dp = new int[n + 1][amount + 1];
    dp[0][0] = 1;

    for (int i = 1; i <= n; i++) {
        int coin = coins[i - 1];
        for (int j = 0; j <= amount; j++) {
            // k*coin可以看做是从[coin...amount]
            for (int k = 0; k * coin <= j; k++) {
                dp[i][j] += dp[i - 1][j - k * coin];
            }
        }
    }
    return dp[n][amount];
}

public int changeV2(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int coin : coins) {
        for (int i = coin; i <= amount; i++) {
            dp[i] += dp[i - coin];
        }
    }
    return dp[amount];
}

private int cnt = 0;

// dfs：超时，当amount较大，coins中coin数值较小时，递归树过深
public int changeV3(int amount, int[] coins) {
    Arrays.sort(coins);
    dfs(coins, 0, amount);
    return cnt;
}

private void dfs(int[] coins, int idx, int left) {
    if (left == 0) {
        ++cnt;
        return;
    }
    if (idx == coins.length) {
        return;
    }
    for (int i = left / coins[idx]; i >= 0; --i) {
        dfs(coins, idx + 1, left - i * coins[idx]);
    }
}
```

### 最长公共子序列 (1143)

给定两个字符串text1和text2，返回这两个字符串的最长公共子序列:

```java
public int longestCommonSubsequence(String text1, String text2) {
    int row = text1.length();
    int col = text2.length();

    int[][] dp = new int[row + 1][col + 1];
    for (int i = 1; i <= row; i++) {
        for (int j = 1; j <= col; j++) {
            if (text1.charAt(i) == text2.charAt(j)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[row][col];
}

// 补充：最长公共子字符串问题
public String longestCommonSubString(String str1, String str2) {
    int row = str1.length();
    int col = str2.length();

    int[][] dp = new int[row][col];
    int startIdx = -1;
    int maxLen = 0;
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            //   h e l l o w o r l d
            // w 0 0 0 0 0 1 0 0 0 0
            // o 0 0 0 0 1 0 2 0 0 0
            // r 0 0 0 0 0 0 0 3 0 0
            // l 0 0 0 0 0 0 0 0 4 0
            // d 0 0 0 0 0 0 0 0 0 5
            if (str1.charAt(i) == str2.charAt(j)) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    startIdx = j - maxLen + 1;
                }
            }
        }
    }
    return str2.substring(startIdx, startIdx + maxLen);
}
```

### 最长递增子序列 (300)

找到无序数组中最长严格递增子序列:

```java
public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    for (int i = 1; i < nums.length; i++) {
        for (int j = i - 1; j >= 0; j--) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
        }
    }
    int max = Integer.MIN_VALUE;
    for (int n : dp) {
        max = Math.max(n, max);
    }
    return max;
}

public int lengthOfLISV2(int[] nums) {
    int[] dp = new int[nums.length];
    int len = 0;
    for (int n : nums) {
        int expectedIdx = Arrays.binarySearch(dp, 0, len, n);
        if (expectedIdx < 0) {
            expectedIdx = -(expectedIdx + 1);
        }
        dp[expectedIdx] = n;
        if (expectedIdx == len) {
            ++len;
        }
    }
    return len;
}
```

### 分割等和子集 (416)

给定一个只包含正整数的非空数组nums，判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等:

```java
public boolean canPartition(int[] nums) {
    if (nums.length <= 1) {
        return false;
    }
    int sum = 0;
    int maxN = 0;
    for (int n : nums) {
        sum += n;
        maxN = Math.max(maxN, n);
    }
    if ((sum & 1) == 1) {
        return false;
    }
    int target = sum / 2;
    if (maxN > target) {
        return false;
    }
    int n = nums.length;
    // dp[i][j]: 从nums[0...i]中选择若干（可以是0）个数字，且只能选择一次，使得它们总和为j
    boolean[][] dp = new boolean[n][target + 1];

    // dp[0...i][0]: 从nums[0...i]中不选择任何数字，总和为0成立
    for (int i = 0; i < n; i++) {
        dp[i][0] = true;
    }
    // dp[0][nums[0]]: 从nums[0]中选择nums[0]，总和为nums[0]成立
    if (nums[0] <= target) {
        dp[0][nums[0]] = true;
    }
    for (int i = 1; i < n; i++) {
        for (int j = 0; j <= target; j++) {
            // 当前值小于目标值：当前值可以选也可以不选择
            if (nums[i] < j) {
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            } else {
                dp[i][j] = dp[i - 1][j];
            }
        }
    }
    return dp[n - 1][target];
}

public boolean canPartitionV2(int[] nums) {
    if (nums.length <= 1) {
        return false;
    }
    int sum = 0;
    int maxN = 0;
    for (int n : nums) {
        sum += n;
        maxN = Math.max(maxN, n);
    }
    if ((sum & 1) == 1) {
        return false;
    }
    int target = sum / 2;
    if (maxN > target) {
        return false;
    }
    int n = nums.length;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int i = 0; i < n; i++) {
        for (int j = target; j >= nums[i]; j--) {
            dp[j] = dp[j] || dp[j - nums[i]];
        }
    }
    return dp[target];
}
```

### 摆动序列 (376)

如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
给定一个整数序列，返回作为摆动序列的最长子序列的长度:

```java
public int wiggleMaxLength(int[] nums) {
    if (nums.length <= 1) {
        return nums.length;
    }
    int res = 1;
    int[] diff = new int[nums.length - 1];
    for (int i = 1; i < nums.length; i++) {
        diff[i - 1] = nums[i] - nums[i - 1];
    }

    int idx = 0;
    while (idx < diff.length && diff[idx] == 0) {
        ++idx;
    }
    if (idx == diff.length) {
        return res;
    }

    // [1,1,7,4,9,2,5]
    // 0, 6, -3, 5, -7, 3
    boolean isPos = diff[idx] > 0;
    ++res;
    for (int i = idx + 1; i < diff.length; i++) {
        if (isPos && diff[i] > 0 || !isPos && diff[i] < 0 || diff[i] == 0) {
            continue;
        }
        isPos = !isPos;
        ++res;
    }
    return res;
}

public int wiggleMaxLengthV2(int[] nums) {
    if (nums.length <= 1) {
        return nums.length;
    }
    int[] down = new int[nums.length];
    int[] up = new int[nums.length];
    down[0] = 1;
    up[0] = 1;
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > nums[i - 1]) {
            up[i] = down[i - 1] + 1;
            down[i] = down[i - 1];
        } else if (nums[i] < nums[i - 1]) {
            down[i] = up[i - 1] + 1;
            up[i] = up[i - 1];
        } else {
            up[i] = up[i - 1];
            down[i] = down[i - 1];
        }
    }
    return Math.max(down[nums.length - 1], up[nums.length - 1]);
}

public int wiggleMaxLengthV3(int[] nums) {
    if (nums.length <= 1) {
        return nums.length;
    }
    int down = 1;
    int up = 1;
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > nums[i - 1]) {
            up = down + 1;
        } else if (nums[i] < nums[i - 1]) {
            down = up + 1;
        }
    }
    return Math.max(down, up);
}
```

### 组合总和IV (377)

给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数，注意顺序不同认为是不同的组合:

```java
public int combinationSum4(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int i = 0; i <= target; i++) {
        for (int num : nums) {
            if (i >= num) {
                dp[i] += dp[i - num];
            }
        }
    }
    return dp[target];
}

// memo[i]：当和为i时，组合的个数
private int[] memo;

// 记忆化搜索
public int combinationSum4V2(int[] nums, int target) {
    memo = new int[target + 1];
    Arrays.fill(memo, -1);
    memo[0] = 1;
    return dfsOptimized01(nums, target);
}

private int dfsOptimized01(int[] nums, int target) {
    if (memo[target] != -1) {
        return memo[target];
    }
    int res = 0;
    for (int num : nums) {
        if (target >= num) {
            res += dfsOptimized01(nums, target - num);
        }
    }
    memo[target] = res;
    return res;
}

private int res = 0;

// dfs: 超时
public int combinationSum4V3(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return res;
    }
    dfs(nums, 0, target);
    return res;
}

private void dfs(int[] arr, int cur, int target) {
    if (cur > target) {
        return;
    }
    if (cur == target) {
        ++res;
        return;
    }
    for (int value : arr) {
        cur += value;
        dfs(arr, cur, target);
        cur -= value;
    }
}
```

### 零和一 (474)

给你一个二进制字符串数组strs和两个整数m和n，找出并返回strs的最大子集的长度，该子集中最多有m个0和n个1:

```java
public int findMaxForm(String[] strs, int m, int n) {
    int[][] dp = new int[m + 1][n + 1];
    for (String str : strs) {
        int zeros = 0;
        int ones = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '1') {
                ++ones;
            } else {
                ++zeros;
            }
        }
        for (int i = m; i >= zeros; i--) {
            for (int j = n; j >= ones; j--) {
                dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
            }
        }
    }
    return dp[m][n];
}
```

### 目标和 (494)
找到nums一个正子集（P）和一个负子集（N），使得总和等于target
- 输入: nums: [1, 1, 1, 1, 1], S: 3
- 输出: 5

解释:</br>
-1+1+1+1+1 = 3 </br>
+1-1+1+1+1 = 3 </br>
+1+1-1+1+1 = 3 </br>
+1+1+1-1+1 = 3 </br>
+1+1+1+1-1 = 3 </br>

```java
//  找到nums一个正子集（P）和一个负子集（N），使得总和等于target
//  sum(P) - sum(N) = target
//  sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
//  2 * sum(P) = target + sum(nums)
//  原来的问题已转化为一个求子集的和问题
public int findTargetSumWays(int[] nums, int target) {
    int sum = 0;
    for (int n : nums) {
        sum += n;
    }
    return sum < target || (target + sum) % 2 > 0 ? 0 : subsetSum(nums, (target + sum) >>> 1);
}

public int subsetSum(int[] nums, int s) {
    // dp[i]: 从nums选择若干数字，使得和为i的方案个数
    int[] dp = new int[s + 1];
    dp[0] = 1;
    for (int n : nums) {
        for (int i = s; i >= n; --i) {
            dp[i] += dp[i - n];
        }
    }
    return dp[s];
}

private int cnt = 0;

public int findTargetSumWaysV2(int[] nums, int target) {
    dfs(nums, target, 0, 0);
    return cnt;
}

// O(2^n): n=nums.length
private void dfs(int[] nums, int target, int idx, int sum) {
    if (idx == nums.length) {
        if (sum == target) {
            ++cnt;
        }
    } else {
        dfs(nums, target, idx + 1, sum + nums[idx]);
        dfs(nums, target, idx + 1, sum - nums[idx]);
    }
}
```

### 单词拆分 (139)

给定一个非空字符串s和一个包含非空单词列表的字典wordDict，判定s是否可以被空格拆分为一个或多个在字典中出现的单词:

```java
public boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;
    Set<String> sets = new HashSet<>(wordDict);

    for (int i = 1; i <= s.length(); i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && sets.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[n];
}
```