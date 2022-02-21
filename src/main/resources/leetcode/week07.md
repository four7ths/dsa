## week07

### 逆波兰表达式求值 (150)

```java
public int evalRPN(String[] tokens) {
  Deque<Integer> stk = new LinkedList<>();
  for (String token : tokens) {
    try {
      int val = Integer.parseInt(token);
      stk.push(val);
    } catch (NumberFormatException e) {
      int num2 = stk.pop();
      int num1 = stk.pop();
      if ("+".equals(token)) {
        stk.push(num1 + num2);
      } else if ("-".equals(token)) {
        stk.push(num1 - num2);
      } else if ("*".equals(token)) {
        stk.push(num1 * num2);
      } else {
        stk.push(num1 / num2);
      }
    }
  }
  return stk.pop();
}
```



### 扁平化嵌套列表迭代器 (341)

```java
interface NestedInteger {
    /**
     * @return true if this NestedInteger holds a single integer, rather than a nested list.
     */
    boolean isInteger();

    /**
     * @return the single integer that this NestedInteger holds, if it holds a single integer
     * Return null if this NestedInteger holds a nested list
     */
    Integer getInteger();

    /**
     * @return the nested list that this NestedInteger holds, if it holds a nested list
     * Return null if this NestedInteger holds a single integer
     */
    List<NestedInteger> getList();
}

public class FlattenNestedList implements Iterator<Integer> {

    private final Deque<NestedInteger> stk = new LinkedList<>();

    public FlattenNestedList(List<NestedInteger> nestedList) {
        // 注意倒序
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stk.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        if (stk.isEmpty()) {
            return false;
        }
        while (!stk.isEmpty() && !stk.peek().isInteger()) {
            NestedInteger tmp = stk.pop();
            List<NestedInteger> tmpList = tmp.getList();
            // 注意倒序
            for (int i = tmpList.size() - 1; i >= 0; --i) {
                stk.push(tmpList.get(i));
            }
        }
        return !stk.isEmpty();
    }

    @Override
    public Integer next() {
        return stk.pop().getInteger();
    }
}
```



### 二叉树的中序遍历 (94)

```java
private List<Integer> res = new ArrayList<>();

public List<Integer> inorderTraversal(TreeNode root) {
  if (root != null) {
    inorderTraversal(root.left);
    res.add(root.val);
    inorderTraversal(root.right);
  }
  return res;
}

private static class DataFrame {
  private boolean visited;
  private TreeNode node;

  DataFrame(boolean visited, TreeNode node) {
    this.visited = visited;
    this.node = node;
  }
}

public List<Integer> inorderTraversalV2(TreeNode root) {
  if (root == null) {
    return res;
  }
  Deque<DataFrame> stk = new LinkedList<>();
  DataFrame rootDataFrame = new DataFrame(false, root);
  stk.push(rootDataFrame);

  while (!stk.isEmpty()) {
    DataFrame dataFrame = stk.pop();
    if (dataFrame.visited) {
      res.add(dataFrame.node.val);
    } else {

      if (dataFrame.node.right != null) {
        stk.push(new DataFrame(false, dataFrame.node.right));
      }
      stk.push(new DataFrame(true, dataFrame.node));
      if (dataFrame.node.left != null) {
        stk.push(new DataFrame(false, dataFrame.node.left));
      }
    }
  }
  return res;
}
```



### 二叉树的后序遍历 (145)

```java
private List<Integer> res = new ArrayList<>();

public List<Integer> postorderTraversal(TreeNode root) {
  if (root != null) {
    postorderTraversal(root.left);
    postorderTraversal(root.right);
    res.add(root.val);
  }
  return res;
}

private static class DataFrame {
  private boolean visited;
  private TreeNode node;

  DataFrame(boolean visited, TreeNode node) {
    this.visited = visited;
    this.node = node;
  }
}

public List<Integer> postorderTraversalV2(TreeNode root) {
  if (root == null) {
    return res;
  }
  Deque<DataFrame> stk = new LinkedList<>();
  DataFrame rootDataFrame = new DataFrame(false, root);
  stk.push(rootDataFrame);

  while (!stk.isEmpty()) {
    DataFrame dataFrame = stk.pop();
    if (dataFrame.visited) {
      res.add(dataFrame.node.val);
    } else {
      stk.push(new DataFrame(true, dataFrame.node));
      if (dataFrame.node.right != null) {
        stk.push(new DataFrame(false, dataFrame.node.right));
      }
      if (dataFrame.node.left != null) {
        stk.push(new DataFrame(false, dataFrame.node.left));
      }
    }
  }
  return res;
}
```



### 二叉树的前序遍历 (144)

```java
private List<Integer> res = new ArrayList<>();

public List<Integer> preorderTraversal(TreeNode root) {
  if (root != null) {
    res.add(root.val);
    preorderTraversal(root.left);
    preorderTraversal(root.right);
  }
  return res;
}

private static class DataFrame {
  private boolean visited;
  private TreeNode node;

  DataFrame(boolean visited, TreeNode node) {
    this.visited = visited;
    this.node = node;
  }
}

public List<Integer> preorderTraversalV2(TreeNode root) {
  if (root == null) {
    return res;
  }
  Deque<DataFrame> stk = new LinkedList<>();
  DataFrame rootDataFrame = new DataFrame(false, root);
  stk.push(rootDataFrame);

  while (!stk.isEmpty()) {
    DataFrame dataFrame = stk.pop();
    if (dataFrame.visited) {
      res.add(dataFrame.node.val);
    } else {
      if (dataFrame.node.right != null) {
        stk.push(new DataFrame(false, dataFrame.node.right));
      }
      if (dataFrame.node.left != null) {
        stk.push(new DataFrame(false, dataFrame.node.left));
      }
      stk.push(new DataFrame(true, dataFrame.node));
    }
  }
  return res;
}
```



### 简化路径 (71)

以Unix风格给出一个文件的绝对路径，你需要简化它，即将其转换为规范路径:

```java
public String simplifyPath(String path) {
  String[] strs = path.replaceAll("//+", "/").split("/");
  Deque<String> stk = new LinkedList<>();

  for (String str : strs) {
    if (".".equals(str)) {
      continue;
    }
    if ("..".equals(str)) {
      if (!stk.isEmpty()) {
        stk.pop();
      }
    } else {
      stk.push(str);
    }
  }
  if (stk.isEmpty()) {
    return "/";
  }

  StringBuilder sb = new StringBuilder();
  while (!stk.isEmpty()) {
    sb.insert(0, stk.pop()).insert(0, '/');
  }
  return sb.toString();
}
```



### 有效的括号 (20)

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效:

```java
public boolean isValid(String s) {
  Deque<Character> stk = new LinkedList<>();
  Map<Character, Character> maps = new HashMap<>(16);
  maps.put(')', '(');
  maps.put(']', '[');
  maps.put('}', '{');
  for (char ch : s.toCharArray()) {
    Character value = maps.get(ch);
    if (value == null) {
      stk.push(ch);
    } else {
      if (stk.isEmpty() || !Objects.equals(stk.pop(), value)) {
        return false;
      }
    }
  }
  return stk.isEmpty();
}
```



### 两数组交集 I (349)

给定两个数组，编写一个函数来计算它们的交集:

* input: nums1=[1, 2, 2, 1], nums2=[2, 2]
* output: [2]

```java
public int[] intersection(int[] nums1, int[] nums2) {
  Set<Integer> s1 = new HashSet<>();
  Set<Integer> s2 = new HashSet<>();
  for (int n : nums1) {
    s1.add(n);
  }
  for (int n : nums2) {
    s2.add(n);
  }

  s1.retainAll(s2);

  int[] ret = new int[s1.size()];
  int idx = 0;
  for (Integer n : s1) {
    ret[idx++] = n;
  }
  return ret;
}

public int[] intersectionV2(int[] nums1, int[] nums2) {
  Set<Integer> s1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
  Set<Integer> s2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
  s1.retainAll(s2);
  return s1.stream().mapToInt(Integer::valueOf).toArray();
}
```

