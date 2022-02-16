## week05

### 二进制手表 (401)

二进制手表顶部有4个LED代表小时（0-11），底部的6个LED 代表分钟（0-59。给定一个数字n，表示有n个LED灯是亮状态，判断有多少中可能的合法时间

```java
// 前4位：顶部4个LED
// 后6位：底部6个LED
private static final int[] NUMS = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};

List<String> res = new ArrayList<>();

// 回溯
public List<String> readBinaryWatch(int num) {
    if (num < 0 || num >= 9) {
        return res;
    }
    dfs(num, 0, 0, 0);
    return res;
}

private void dfs(int num, int idx, int h, int m) {
    if (h > 11 || m > 59) {
        return;
    }
    if (num == 0) {
        res.add(h + ":" + ((m > 9) ? m : ("0" + m)));
        return;
    }
    for (int i = idx; i < NUMS.length; i++) {
        if (i < 4) {
            dfs(num - 1, i + 1, h + NUMS[i], m);
        } else {
            dfs(num - 1, i + 1, h, m + NUMS[i]);
        }
    }
}

public List<String> readBinaryWatchV2(int num) {
    List<String> res = new ArrayList<>();
    for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 60; j++) {
            if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                res.add(i + ":" + ((j > 9) ? j : ("0" + j)));
            }
        }
    }
    return res;
}
```

### 组合总数 I (39)

给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合:
- 所有数字（包括 target）都是正整数
- 解集中不能包含重复元素 

输入：candidates = [2, 3, 6, 7], target = 7</br>
输出：[[7], [2, 2, 3]]

```java
List<List<Integer>> res = new ArrayList<>();

public List<List<Integer>> combinationSum(int[] candidates, int target) {
    if (candidates == null || candidates.length == 0) {
        return res;
    }
    Arrays.sort(candidates);
    backtrace(candidates, target, 0, 0, new ArrayList<>());
    return res;
}

private void backtrace(int[] candidates, int target, int idx, int curSum, ArrayList<Integer> tmp) {
    if (curSum == target) {
        res.add(new ArrayList<>(tmp));
        return;
    }
    for (int i = idx; i < candidates.length; i++) {
        if (curSum + candidates[i] > target) {
            return;
        }
        curSum += candidates[i];
        tmp.add(candidates[i]);
        backtrace(candidates, target, i, curSum, tmp);
        curSum -= candidates[i];
        tmp.remove(tmp.size() - 1);
    }
}
```

### 组合总数 II (40)

给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合，
candidates中的每个数字在每个组合中只能使用一次:
- 所有数字（包括目标数）都是正整数
- 解集不能包含重复的组合

输入：candidates = [2,5,2,1,2], target = 5</br>
输出：[[1, 2, 2], [5]]

```java
List<List<Integer>> res = new ArrayList<>();
private boolean[] visited;

public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    if (candidates == null || candidates.length == 0) {
        return res;
    }
    visited = new boolean[candidates.length];
    Arrays.sort(candidates);
    dfs(candidates, target, 0, 0, new ArrayList<>());
    return res;
}

private void dfs(int[] candidates, int target, int idx, int curSum, ArrayList<Integer> tmp) {
    if (curSum == target) {
        res.add(new ArrayList<>(tmp));
        return;
    }
    for (int i = idx; i < candidates.length; i++) {
        if (!visited[i]) {
            if (curSum + candidates[i] > target) {
                return;
            }
            if (i > 0 && candidates[i] == candidates[i - 1] && !visited[i - 1]) {
                // 注意是continue，之后还有可能有数据满足要求
                continue;
            }
            curSum += candidates[i];
            visited[i] = true;
            tmp.add(candidates[i]);
            dfs(candidates, target, i + 1, curSum, tmp);
            curSum -= candidates[i];
            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }
}
```

### 组合总数 III (216)

找出所有相加之和为n的k个数的组合。组合中只允许含有1-9的正整数，并且每种组合中不存在重复的数字
- 所有数字都是正整数
- 解集不能包含重复的组合

输入：k=3, n=9</br>
输出：[[1,2,6], [1,3,5], [2,3,4]]

```java
List<List<Integer>> res = new ArrayList<>();
boolean[] visited = new boolean[10];

public List<List<Integer>> combinationSum3(int k, int n) {
    if (k <= 0 || k > 9 || n > 45) {
        return res;
    }
    dfs(k, n, 1, 0, new ArrayList<>());
    return res;
}

private void dfs(int k, int n, int idx, int curSum, ArrayList<Integer> tmp) {
    if (n == curSum && tmp.size() == k) {
        res.add(new ArrayList<>(tmp));
        return;
    }
    if (curSum + idx > n) {
        return;
    }
    for (int i = idx; i <= 9; i++) {
        if (!visited[i]) {
            curSum += i;
            tmp.add(i);
            visited[i] = true;
            dfs(k, n, i + 1, curSum, tmp);
            visited[i] = false;
            tmp.remove(tmp.size() - 1);
            curSum -= i;
        }
    }
}
```

### 岛屿的数量 (200)

给定一个由'1'（陆地）和'0'（水）组成的的二维网格，计算岛屿的数量，一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的：
- 假设网格的四个边均被水包围

输入:</br>
11000</br>
11000</br>
00100</br>
00011</br>
输出: 3

```java
private boolean[][] visited;
private final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
int rows;
int cols;

public int numIslands(char[][] grid) {
    if (grid == null) {
        return 0;
    }
    rows = grid.length;
    cols = grid[0].length;
    if (rows == 0 || cols == 0) {
        return 0;
    }
    int res = 0;
    visited = new boolean[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (grid[i][j] == '1' && !visited[i][j]) {
                ++res;
                dfs(grid, i, j);
                // bfs(grid, i, j);
            }
        }
    }
    return res;
}

// dfs
private void dfs(char[][] grid, int i, int j) {
    visited[i][j] = true;
    for (int[] dir : dirs) {
        int x = i + dir[0];
        int y = j + dir[1];
        if (isValid(x, y) && !visited[x][y] && grid[x][y] == '1') {
            dfs(grid, x, y);
        }
    }
}

private boolean isValid(int x, int y) {
    return x >= 0 && x < rows && y >= 0 && y < cols;
}

static class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// bfs
private void bfs(char[][] grid, int i, int j) {
    Deque<Point> queue = new LinkedList<>();
    Point start = new Point(i, j);
    queue.add(start);

    while (!queue.isEmpty()) {
        Point point = queue.remove();
        for (int[] dir : dirs) {
            int x = point.x + dir[0];
            if (x < 0 || x >= rows) {
                continue;
            }
            int y = point.y + dir[1];
            if (y < 0 || y >= cols) {
                continue;
            }
            if (!visited[x][y] && grid[x][y] == '1') {
                visited[x][y] = true;
                queue.add(new Point(x, y));
            }
        }
    }
}
```

### 太平洋大西洋水流问题 (417)

给定一个m*n的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界
规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动
请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标

// 输入：</br>
// 太平洋 ~   ~   ~   ~   ~</br>
//        ~  1   2   2   3  (5) *</br>
//        ~  3   2   3  (4) (4) *</br>
//        ~  2   4  (5)  3   1  *</br>
//        ~ (6) (7)  1   4   5  *</br>
//        ~ (5)  1   1   2   4  *</br>
//           *   *   *   *   * 大西洋</br>
// 输出：</br>
// [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]

```java
private final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
  private int rows;
  private int cols;
  List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> pacificAtlantic(int[][] matrix) {
      if (matrix == null || matrix.length == 0) {
          return res;
      }
      rows = matrix.length;
      cols = matrix[0].length;
      boolean[][] pac = new boolean[rows][cols];
      boolean[][] atl = new boolean[rows][cols];

      for (int i = 0; i < rows; i++) {
          dfs(matrix, i, 0, pac);
          dfs(matrix, i, cols - 1, atl);
      }
      for (int i = 0; i < cols; i++) {
          dfs(matrix, 0, i, pac);
          dfs(matrix, rows - 1, i, atl);
      }

      for (int i = 0; i < rows; i++) {
          for (int j = 0; j < cols; j++) {
              if (pac[i][j] && atl[i][j]) {
                  List<Integer> cord = new ArrayList<>();
                  cord.add(i);
                  cord.add(j);
                  res.add(cord);
              }
          }
      }

      return res;
  }

  private void dfs(int[][] matrix, int i, int j, boolean[][] visited) {
      visited[i][j] = true;
      for (int[] dir : dirs) {
          int x = i + dir[0];
          int y = j + dir[1];
          if (isValid(x, y) && matrix[x][y] >= matrix[i][j] && !visited[x][y]) {
              dfs(matrix, x, y, visited);
          }
      }
  }

  private boolean isValid(int x, int y) {
      return x >= 0 && x < rows && y >= 0 && y < cols;
  }
```

### 子集 I (78)

给定一组不含重复元素的整数数组nums，返回该数组所有可能的子集（幂集）:
- 输入： nums = [1,2,3]
- 输出：[[3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], []]

```java
List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> subsets(int[] nums) {
      if (nums == null || nums.length == 0) {
          return res;
      }
      res.add(new ArrayList<>());
      for (int len = 1; len <= nums.length; ++len) {
          dfs(nums, len, 0, new ArrayList<>());
      }
      return res;
  }

  private void dfs(int[] nums, int len, int idx, ArrayList<Integer> tmp) {
      if (tmp.size() == len) {
          res.add(new ArrayList<>(tmp));
          return;
      }
      for (int i = idx; i < nums.length; i++) {
          tmp.add(nums[i]);
          dfs(nums, len, i + 1, tmp);
          tmp.remove(tmp.size() - 1);
      }
  }
```

### 子集 II (90)

给定一个可能包含重复元素的整数数组nums，返回该数组所有可能的子集（幂集）:
- 输入: [1,2,2]
- 输出: [[2], [1], [1,2,2], [2,2], [1,2], []]

```java
private List<List<Integer>> res = new ArrayList<>();
  private boolean[] visited;

  public List<List<Integer>> subsetsWithDup(int[] nums) {
      if (nums == null || nums.length == 0) {
          return res;
      }
      visited = new boolean[nums.length];
      Arrays.sort(nums);
      res.add(new ArrayList<>());
      for (int len = 1; len <= nums.length; ++len) {
          dfs(nums, len, 0, new ArrayList<>());
      }
      return res;
  }

  private void dfs(int[] nums, int len, int idx, ArrayList<Integer> tmp) {
      if (tmp.size() == len) {
          res.add(new ArrayList<>(tmp));
          return;
      }
      for (int i = idx; i < nums.length; i++) {
          if (!visited[i]) {
              if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                  continue;
              }
              tmp.add(nums[i]);
              visited[i] = true;
              dfs(nums, len, i + 1, tmp);
              visited[i] = false;
              tmp.remove(tmp.size() - 1);
          }
      }
  }
```

### 被围绕的区域 (130)

找到所有被'X'围绕的区域，并将这些区域里所有的'O'用'X'填充：
- 任何边界上的'O'都不会被填充为'X'

输入: </br>
X X X X</br>
X O O X</br>
X X O X</br>
X O X X</br>
输出: </br>
X X X X</br>
X X X X</br>
X X X X</br>
X O X X</br>

```java
private boolean[][] visited;
  private final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
  int rows;
  int cols;

  public void solve(char[][] board) {
      if (board == null) {
          return;
      }
      rows = board.length;
      cols = board[0].length;
      if (rows < 3 || cols < 3) {
          return;
      }
      visited = new boolean[rows][cols];
      for (int i = 0; i < rows; i++) {
          if (board[i][0] == 'O') {
              dfs(board, i, 0);
          }
          if (board[i][cols - 1] == 'O') {
              dfs(board, i, cols - 1);
          }
      }
      for (int j = 0; j < cols; j++) {
          if (board[0][j] == 'O') {
              dfs(board, 0, j);
          }
          if (board[rows - 1][j] == 'O') {
              dfs(board, rows - 1, j);
          }
      }

      for (int i = 1; i < rows - 1; i++) {
          for (int j = 1; j < cols - 1; j++) {
              if (!visited[i][j]) {
                  board[i][j] = 'X';
              }
          }
      }
  }

  private void dfs(char[][] board, int i, int j) {
      visited[i][j] = true;
      for (int[] dir : dirs) {
          int x = i + dir[0];
          int y = j + dir[1];
          if (isValid(x, y) && !visited[x][y] && board[x][y] == 'O') {
              dfs(board, x, y);
          }
      }
  }

  private boolean isValid(int x, int y) {
      return x >= 0 && x < rows && y >= 0 && y < cols;
  }
```

### 单词搜索 (79)

定一个二维网格和一个单词，找出该单词是否存在于网格中：

// board = [['A','B','C','E'],</br>
//          ['S','F','C','S'],</br>
//          ['A','D','E','E']]</br>
输入：word = "ABCCED", 输出：true</br>
输入：word = "SEE", 输出：true</br>
输入：word = "ABCB", 输出：false</br>

```java
int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
boolean[][] visited;
private int rows;
private int cols;

public boolean exist(char[][] board, String word) {
    if (board == null || word == null) {
        return false;
    }
    rows = board.length;
    cols = board[0].length;
    visited = new boolean[rows][cols];

    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}

private boolean dfs(char[][] board, String word, int i, int j, int idx) {
    if (idx == word.length() - 1) {
        return board[i][j] == word.charAt(idx);
    }
    if (word.charAt(idx) == board[i][j]) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isValid(x, y) && !visited[x][y] && dfs(board, word, x, y, idx + 1)) {
                return true;
            }
        }
        visited[i][j] = false;
    }
    return false;
}

private boolean isValid(int x, int y) {
    return x >= 0 && x < rows && y >= 0 && y < cols;
}
```

### N皇后问题 I (51) 

给定一个数字n，返回n皇后可能的排列方式：

- 输入: n=4
- 输出: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]

```java
private final List<List<String>> res = new ArrayList<>();

// cols[i]==true：第i列存在皇后
private boolean[] cols;
// cols[i+j]==true: i+j上存在皇后
private boolean[] dia1;
//cols[i-j]上存在皇后，但是i-j可能为负数，需要做一次加上n-1进行平移
private boolean[] dia2;

public List<List<String>> solveNQueens(int n) {
    if (n <= 0) {
        return res;
    }
    cols = new boolean[n];
    dia1 = new boolean[2 * n - 1];
    dia2 = new boolean[2 * n - 1];

    solveNQueens0(n, 0, new ArrayList<>());

    return res;
}

private void solveNQueens0(int n, int row, ArrayList<Integer> queue) {
    if (row == n) {
        generateBoard(n, queue);
        return;
    }
    for (int col = 0; col < n; col++) {
        if (!cols[col] && !dia1[row + col] && !dia2[row - col + n - 1]) {
            queue.add(col);
            cols[col] = true;
            dia1[row + col] = true;
            dia2[row - col + n - 1] = true;
            solveNQueens0(n, row + 1, queue);
            dia2[row - col + n - 1] = false;
            dia1[row + col] = false;
            cols[col] = false;
            queue.remove(queue.size() - 1);
        }
    }
}

private void generateBoard(int n, ArrayList<Integer> queue) {
    List<String> tmp = new ArrayList<>();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
        sb.append('.');
    }
    for (int i = 0; i < n; i++) {
        sb.setCharAt(queue.get(i), 'Q');
        tmp.add(sb.toString());
        sb.setCharAt(queue.get(i), '.');
    }

    res.add(tmp);
}
```

### N皇后问题 II (52)

给定一个数字n，返回n皇后问题所有的方案数量:

```java
private int total;

// cols[i]==true：第i列存在皇后
private boolean[] cols;
// cols[i+j]==true: i+j上存在皇后
private boolean[] dia1;
//cols[i-j]上存在皇后，但是i-j可能为负数，需要做一次加上n-1进行平移
private boolean[] dia2;

public int totalNQueens(int n) {
    if (n <= 0) {
        return total;
    }
    cols = new boolean[n];
    dia1 = new boolean[2 * n - 1];
    dia2 = new boolean[2 * n - 1];

    solveNQueens0(n, 0, new ArrayList<>());

    return total;
}

private void solveNQueens0(int n, int row, ArrayList<Integer> queue) {
    if (row == n) {
        ++total;
        return;
    }
    for (int col = 0; col < n; col++) {
        if (!cols[col] && !dia1[row + col] && !dia2[row - col + n - 1]) {
            queue.add(col);
            cols[col] = true;
            dia1[row + col] = true;
            dia2[row - col + n - 1] = true;
            solveNQueens0(n, row + 1, queue);
            dia2[row - col + n - 1] = false;
            dia1[row + col] = false;
            cols[col] = false;
            queue.remove(queue.size() - 1);
        }
    }
}
```

### 解数独 (19) 

编写一个程序解决数独问题（2维数组中非数字用.表示），一个数独的解法需遵循如下规则：
- 数字1-9在每一行只能出现一次
- 数字1-9在每一列只能出现一次
- 数字1-9在每一个3x3宫内只能出现一次

```java
// rows[i][j]=true: 第i行中存在数字j
private final boolean[][] rows = new boolean[9][10];
// cols[i][j]=true: 第i列中存在数字j
private final boolean[][] cols = new boolean[9][10];
// blocks[i][j]=true: 第i个3*3block块中存在数字j
private final boolean[][] blocks = new boolean[9][10];

public void solveSudoku(char[][] board) {
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] != '.') {
                int n = board[i][j] - '0';
                rows[i][n] = true;
                cols[j][n] = true;
                blocks[i / 3 * 3 + j / 3][n] = true;
            }
        }
    }

    dfs(board, 0, 0);
}

private boolean dfs(char[][] board, int x, int y) {
    while (board[x][y] != '.') {
        if (++y == 9) {
            y = 0;
            ++x;
        }
        if (x == 9) {
            return true;
        }
    }

    // Assert (board[x][y]=='.')
    for (int num = 1; num <= 9; num++) {
        int blockIdx = x / 3 * 3 + y / 3;
        if (!rows[x][num] && !cols[y][num] && !blocks[blockIdx][num]) {
            board[x][y] = (char) (num + '0');
            rows[x][num] = true;
            cols[y][num] = true;
            blocks[blockIdx][num] = true;

            if (dfs(board, x, y)) {
                return true;
            }

            blocks[blockIdx][num] = false;
            cols[y][num] = false;
            rows[x][num] = false;
            board[x][y] = '.';
        }
    }

    return false;
}
```