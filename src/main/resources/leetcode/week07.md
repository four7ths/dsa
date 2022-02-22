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



### 两个数组的交集 II (350)

给定两个数组，编写一个函数来计算它们的交集:

* input: nums1=[1, 2, 2, 1], nums2=[2, 2]
* output: [2, 2]

```java
public int[] intersect(int[] nums1, int[] nums2) {
  if (nums1.length > nums2.length) {
    return intersect(nums2, nums1);
  }

  Map<Integer, Integer> freq = new HashMap<>();
  for (int item : nums1) {
    freq.merge(item, 1, Integer::sum);
  }
  int idx = 0;
  for (int item : nums2) {
    int cnt = freq.getOrDefault(item, 0);
    if (cnt > 0) {
      nums1[idx++] = item;
      freq.put(item, cnt - 1);
    }
  }
  return Arrays.copyOfRange(nums1, 0, idx);
}
```



### 快乐水 (202)

判断一个数是否是【快乐数】

快乐数定义：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为1，也可能是无限循环但始终变不到1。如果可以变为1，那么这个数就是快乐数

```java
public boolean isHappy(int n) {
  Map<Integer, Boolean> maps = new HashMap<>();
  while (n != 1) {
    maps.put(n, true);
    n = calcSumSquare(n);
    if (maps.containsKey(n)) {
      return false;
    }
  }
  return true;
}

private int calcSumSquare(int n) {
  int sum = 0;
  while (n != 0) {
    int remainder = n % 10;
    sum += remainder * remainder;
    n /= 10;
  }
  return sum;
}
```



### 同构字符串 (205)

给定两个字符串s和t，判断它们是否是【同构的】。同构：如果s中的字符可以被替换得到t，那么这两个字符串是同构的。所有出现的字符都必须用另一个字符替换，同时保留字符的顺序，两个字符不能映射到同一个字符上，但字符可以映射自己本身。例如：

* s = "egg", t = "add"      -> true
* s = "foo", t = "bar"      -> false
* s = "paper", t = "title"  -> true

```java
public boolean isIsomorphic(String s, String t) {
  if (s.length() != t.length()) {
    return false;
  }
  Map<Character, Character> maps = new HashMap<>();
  for (int i = 0; i < s.length(); i++) {
    char ch = s.charAt(i);
    if (!maps.containsKey(ch)) {
      if (maps.containsValue(t.charAt(i))) {
        return false;
      }
      maps.put(ch, t.charAt(i));
    } else {
      if (maps.get(ch) != t.charAt(i)) {
        return false;
      }
    }
  }
  return true;
}
```



### 根据字符出现频率排序 (451)

给定一个字符串，请将字符串里的字符按照出现的频率降序排列，注意：区分大小写，相同频率字符可以不考虑顺序:

```java
public String frequencySort(String s) {
  Map<Character, Integer> maps = new HashMap<>();
  for (char ch : s.toCharArray()) {
    maps.put(ch, maps.getOrDefault(ch, 0) + 1);
  }
  List<Character> keys = new ArrayList<>(maps.keySet());
  keys.sort((a, b) -> maps.get(b) - maps.get(a));
  StringBuilder sb = new StringBuilder();
  for (Character ch : keys) {
    for (int i = 0; i < maps.get(ch); ++i) {
      sb.append(ch);
    }
  }
  return sb.toString();
}

// Java8 Stream
public String frequencySortV2(String s) {
  Map<Character, Integer> maps = new HashMap<>();
  for (char ch : s.toCharArray()) {
    maps.put(ch, maps.getOrDefault(ch, 0) + 1);
  }
  LinkedHashMap<Character, Integer> sortedMap = maps.entrySet().stream()
    .sorted(Map.Entry.<Character, Integer> comparingByValue().reversed())
    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                              (old, now) -> old, LinkedHashMap::new));
  StringBuilder sb = new StringBuilder();
  for (Map.Entry<Character, Integer> entry : sortedMap.entrySet()) {
    for (int i = 0; i < entry.getValue(); i++) {
      sb.append(entry.getKey());
    }
  }
  return sb.toString();
}
```



### 有效的字母异位词 (242)

判断两个字符串是否是有效的【异位词】:

```java
public boolean isAnagram(String s, String t) {
  if (s.length() != t.length()) {
    return false;
  }
  int[] maps = new int[26];
  for (int i = 0; i < t.length(); ++i) {
    maps[s.charAt(i) - 'a'] += 1;
    maps[t.charAt(i) - 'a'] -= 1;
  }
  return Arrays.stream(maps).allMatch(e -> e == 0);
}
```



### 单词规律 (290)

给定一种规律【pattern】和一个字符串【str】，判断str是否遵循相同的规律:

* pattern="abba", str="dog cat cat dog"  -> true
* pattern="abba", str="dog cat cat fish" -> false
* pattern="abba", str="dog dog dog dog"  -> false

```java
public boolean wordPattern(String pattern, String str) {
  Map<Character, String> maps = new HashMap<>();
  String[] strings = str.split(" ");
  if (pattern.length() != strings.length) {
    return false;
  }
  for (int i = 0; i < pattern.length(); ++i) {
    char ch = pattern.charAt(i);
    if (!maps.containsKey(ch)) {
      if (maps.containsValue(strings[i])) {
        return false;
      }
      maps.put(ch, strings[i]);
    } else {
      if (!maps.get(ch).equals(strings[i])) {
        return false;
      }
    }
  }
  return true;
}
```

