## week04

### 平衡二叉树 (110)

给定一颗二叉树，判断其是否是高度平衡的二叉树，平衡二叉树：每个节点左右两个子树高度差的绝对值不超过1:

```java
public boolean isBalanced(TreeNode root) {
    if (root == null) {
        return true;
    }
    int leftDepth = getDepth(root.left);
    int rightDepth = getDepth(root.right);
    if (Math.abs(leftDepth - rightDepth) > 1) {
        return false;
    }
    return isBalanced(root.left) && isBalanced(root.right);
}

private int getDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    return Math.max(getDepth(root.left), getDepth((root.right))) + 1;
}
```

### 二叉树的所有路径 (257)

给定一个二叉树根节点，返回从根节点到叶子节点的所有路径:

```java
List<String> ret = new ArrayList<>();

public List<String> binaryTreePaths(TreeNode root) {
    if (root == null) {
        return ret;
    }
    binaryTreePaths(root, new ArrayList<>());

    return ret;
}

private void binaryTreePaths(TreeNode node, ArrayList<String> tmp) {
    tmp.add(String.valueOf(node.val));
    if (node.left == null && node.right == null) {
        ret.add(String.join("->", tmp.toArray(new String[0])));
    }
    if (node.left != null) {
        binaryTreePaths(node.left, tmp);
    }
    if (node.right != null) {
        binaryTreePaths(node.right, tmp);
    }
    tmp.remove(tmp.size() - 1);
}
```

### 将有序数组转换为二叉搜索树 (108)

给定一个有序数组，将其转换成二叉搜索树:

```java
public TreeNode sortedArrayToBST(int[] nums) {
    if (nums == null || nums.length == 0) {
        return null;
    }
    return sortedArrayToBST0(nums, 0, nums.length - 1);
}

private TreeNode sortedArrayToBST0(int[] nums, int l, int r) {
    if (l > r) {
        return null;
    }
    int mid = l + ((r - l) >> 1);
    TreeNode root = new TreeNode(nums[mid]);
    root.left = sortedArrayToBST0(nums, l, mid - 1);
    root.right = sortedArrayToBST0(nums, mid + 1, r);
    return root;
}
```

### 删除二叉搜索树中的节点 (450)

Hibbard Deletion In BST:

```java
public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
        return null;
    }
    int cmp = Integer.compare(key, root.val);
    if (cmp < 0) {
        root.left = deleteNode(root.left, key);
    } else if (cmp > 0) {
        root.right = deleteNode(root.right, key);
    } else {
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }

        // Hibbard Deletion
        // 右子树中最小值 or 左子树中最大值
        TreeNode tmp = root;
        root = getMin(root.right);
        root.right = deleteMin(tmp.right);
        root.left = tmp.left;
    }
    return root;
}

private TreeNode deleteMin(/*@NonNull*/ TreeNode node) {
    if (node.left == null) {
        return node.right;
    }
    node.left = deleteMin(node.left);
    return node;
}

private TreeNode getMin(/*@NonNull*/ TreeNode node) {
    if (node.left == null) {
        return node;
    }
    return getMin(node.left);
}
```

### 二叉搜索树中第K小的元素 (230)

给定一个二叉搜索树，返回二叉搜索树中第K（从1开始计数）小的元素:

```java
public int kthSmallest(TreeNode root, int k) {
    if (root == null) {
        return 0;
    }
    int lNums = countNode(root.left);
    if (lNums + 1 == k) {
        return root.val;
    } else if (k <= lNums) {
        return kthSmallest(root.left, k);
    } else {
        return kthSmallest(root.right, k - lNums - 1);
    }
}

private int countNode(TreeNode node) {
    if (node == null) {
        return 0;
    }
    return countNode(node.left) + countNode(node.right) + 1;
}
```

### 二叉树的最近公共祖先 (236)

给定一个【二叉树】，找到二叉树中p、q节点的公共祖先:

```java
private final List<List<TreeNode>> paths = new ArrayList<>();

// 获取从root节点到p、q的路径，查找公共节点
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
        return null;
    }

    findPath(root, p, q, new ArrayList<>());

    // assert (paths.size() == 2)
    List<TreeNode> pPath = paths.get(0);
    List<TreeNode> qPath = paths.get(1);

    int len = Math.min(pPath.size(), qPath.size());

    TreeNode ret = root;
    for (int i = 0; i < len; i++) {
        if (pPath.get(i).val == qPath.get(i).val) {
            ret = pPath.get(i);
        }
    }
    return ret;
}

private void findPath(TreeNode root, TreeNode pNode, TreeNode qNode, List<TreeNode> path) {
    if (root == null) {
        return;
    }
    path.add(root);
    if (root.val == pNode.val || root.val == qNode.val) {
        paths.add(new ArrayList<>(path));
    }
    if (root.left != null) {
        findPath(root.left, pNode, qNode, path);
    }
    if (root.right != null) {
        findPath(root.right, pNode, qNode, path);
    }
    path.remove(path.size() - 1);
}

// 存储父节点
Map<TreeNode, TreeNode> parent = new HashMap<>();
Set<TreeNode> visited = new HashSet<>();

public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
        return null;
    }

    dfs(root);

    while (p != null) {
        visited.add(p);
        p = parent.get(p);
    }

    while (q != null) {
        if (visited.contains(q)) {
            return q;
        }
        q = parent.get(q);
    }
    throw new RuntimeException();
}

private void dfs(/*NonNull*/ TreeNode node) {
    if (node.left != null) {
        parent.put(node.left, node);
        dfs(node.left);
    }
    if (node.right != null) {
        parent.put(node.right, node);
        dfs(node.right);
    }
}

// 递归：(inLeft && inRight) || ((x==p || x==q) && (inLeft || inRight))
public TreeNode lowestCommonAncestorV3(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
        return null;
    }
    if (root == p || root == q) {
        return root;
    }
    TreeNode inLeft = lowestCommonAncestorV3(root.left, p, q);
    TreeNode inRight = lowestCommonAncestorV3(root.right, p, q);
    if (inLeft != null && inRight != null) {
        return root;
    }
    return inLeft != null ? inLeft : inRight;
}
```

### 二叉搜索树的最近公共祖先 (235)

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
        return null;
    }

    if (p.val < root.val && q.val < root.val) {
        return lowestCommonAncestor(root.left, p, q);
    } else if (p.val > root.val && q.val > root.val) {
        return lowestCommonAncestor(root.right, p, q);
    } else {
        return root;
    }
}
```

### 路径总和 I (112)

给定二叉树根节点root和目标值target，判断树中是否存在从根节点到叶子节点的路径，且该路径上所有节点值之和等于target:

```java
public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) {
        return false;
    }
    if (root.val == targetSum && root.left == null && root.right == null) {
        return true;
    }
    return hasPathSum(root.left, targetSum - root.val) ||
            hasPathSum(root.right, targetSum - root.val);
}
```

### 路径总和 II (113)

给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径:

```java
List<List<Integer>> ret = new ArrayList<>();

public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    if (root == null) {
        return ret;
    }
    pathSum(root, targetSum, new ArrayList<>());
    return ret;
}

private void pathSum(TreeNode root, int targetSum, ArrayList<Integer> tmp) {
    if (root == null) {
        return;
    }
    tmp.add(root.val);
    if (root.left == null && root.right == null && root.val == targetSum) {
        ret.add(new ArrayList<>(tmp));
    }
    if (root.left != null) {
        pathSum(root.left, targetSum - root.val, tmp);
    }
    if (root.right != null) {
        pathSum(root.right, targetSum - root.val, tmp);
    }
    tmp.remove(tmp.size() - 1);
}
```

### 路径总和 III (437)

给定一个二叉树，它的每个结点都存放着一个整数值，找出路径和等于给定数值的路径总数。

注意：路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）:
```java
public int pathSum(TreeNode root, int targetSum) {
    if (root == null) {
        return 0;
    }
    int ret = 0;
    // root节点不在当前路径中
    ret += pathSum(root.left, targetSum);
    ret += pathSum(root.right, targetSum);

    // root节点在当前路径中
    ret += pathSum0(root, targetSum);

    return ret;
}

private int pathSum0(TreeNode root, int targetSum) {
    if (root == null) {
        return 0;
    }
    int ret = 0;
    if (root.val == targetSum) {
        ret += 1;
    }
    ret += pathSum0(root.left, targetSum - root.val);
    ret += pathSum0(root.right, targetSum - root.val);
    return ret;
}
```

### 左叶子之和 (404)

给定一个二叉树根节点，返回所有左叶子之和:

```java
public int sumOfLeftLeaves(TreeNode root) {
    int cnt = 0;
    if (root != null) {
        if (root.left != null && (root.left.left == null && root.left.right == null)) {
            cnt += root.left.val;
        } else {
            cnt += sumOfLeftLeaves(root.left);
        }
        cnt += sumOfLeftLeaves(root.right);
    }
    return cnt;
}
```

### 求根节点到叶节点数字之和 (129)

给定一个二叉树，它的每个结点都存放一个0-9的数字，每条从根到叶子节点的路径都代表一个数字:

```java
public int sumNumbers(TreeNode root) {
    if (root == null) {
        return 0;
    }
    return sumNumbers0(root, 0);
}

private int sumNumbers0(TreeNode root, int base) {
    if (root.left == null && root.right == null) {
        return 10 * base + root.val;
    }
    int sum = 0;
    if (root.left != null) {
        sum += sumNumbers0(root.left, 10 * base + root.val);
    }
    if (root.right != null) {
        sum += sumNumbers0(root.right, 10 * base + root.val);
    }
    return sum;
}
```

### 验证二叉搜索树 (98)

给定一个二叉树的根节点，判断二叉树是否是二叉搜索树:

```java
private long prev = Long.MIN_VALUE;

public boolean isValidBST(TreeNode root) {
    if (root == null) {
        return true;
    }

    boolean lCond = isValidBST(root.left);

    boolean mCond = false;
    if (root.val > prev) {
        mCond = true;
        prev = root.val;
    }

    boolean rCond = isValidBST(root.right);

    // 中序遍历思想体现，实际可以短路优化逻辑
    return lCond && mCond && rCond;
}
```