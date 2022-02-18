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