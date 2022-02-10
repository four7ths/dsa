## week03

### Binary tree level order traversal (102)

给定一个二叉树，返回其按从上到下层次遍历的节点值:

```java
private List<List<Integer>>ret=new ArrayList<>();

public List<List<Integer>>levelOrder(TreeNode root){
        if(root==null){
        return ret;
        }
        Deque<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
        int sz=queue.size();
        List<Integer> tmp=new ArrayList<>(sz);
        for(int i=0;i<sz; i++){
        TreeNode item=queue.removeFirst();
        tmp.add(item.val);
        if(item.left!=null){
        queue.addLast(item.left);
        }
        if(item.right!=null){
        queue.addLast(item.right);
        }
        }
        ret.add(tmp);
        }
        return ret;
        }
```

### Binary tree level order traversal II (107)

给定一个二叉树，返回其节点值自底向上的层次遍历:

```java
private List<List<Integer>>ret=new LinkedList<>();

public List<List<Integer>>levelOrderBottom(TreeNode root){
        if(root==null){
        return ret;
        }
        Deque<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
        int sz=queue.size();
        List<Integer> tmp=new ArrayList<>(sz);
        for(int i=0;i<sz; i++){
        TreeNode item=queue.remove();
        tmp.add(item.val);
        if(item.left!=null){
        queue.add(item.left);
        }
        if(item.right!=null){
        queue.add(item.right);
        }
        }
        ret.add(0,tmp);
        }
        return ret;
        }
```

### Longest palindrome (5)

给定一个字符串，找到字符串中最长回文字符串：

```java
public String longestPalindrome(String s){
        int len=s.length();
        if(len<=1){
        return s;
        }

        boolean[][]dp=new boolean[len][len];
        for(int i=0;i<len; i++){
        dp[i][i]=true;
        }

        int startIdx=0;
        int maxLen=1;
        for(int j=1;j<len; j++){
        for(int i=0;i<j; i++){
        if(s.charAt(i)!=s.charAt(j)){
        dp[i][j]=false;
        }else if(j-i<=2){
        dp[i][j]=true;
        }else{
        dp[i][j]=dp[i+1][j-1];
        }
        if(dp[i][j]&&(j-i+1)>maxLen){
        startIdx=i;
        maxLen=j-i+1;
        }
        }
        }

        return s.substring(startIdx,startIdx+maxLen);
        }
```

### 二叉树的锯齿形层序遍历 (103)

给定一个二叉树根节点root，根据锯齿形状层序遍历二叉树:

```java
public List<List<Integer>>zigzagLevelOrder(TreeNode root){
        List<List<Integer>>ret=new ArrayList<>();
        if(root==null){
        return ret;
        }

        Deque<TreeNode> stk1=new LinkedList<>();
        Deque<TreeNode> stk2=new LinkedList<>();

        stk1.push(root);

        while(!stk1.isEmpty()||!stk2.isEmpty()){
        int sz=stk1.isEmpty()?stk2.size():stk1.size();
        List<Integer> tmp=new ArrayList<>(sz);

        if(!stk1.isEmpty()){
        while(!stk1.isEmpty()){
        TreeNode node=stk1.pop();
        tmp.add(node.val);
        if(node.left!=null){
        stk2.push(node.left);
        }
        if(node.right!=null){
        stk2.push(node.right);
        }
        }
        }else{
        while(!stk2.isEmpty()){
        TreeNode node=stk2.pop();
        tmp.add(node.val);
        if(node.right!=null){
        stk1.push(node.right);
        }
        if(node.left!=null){
        stk1.push(node.left);
        }
        }
        }

        ret.add(tmp);
        }

        return ret;
        }
```

### 二叉树的右视图 (199)

给定一个二叉树的根节点，返回从右侧能看到的节点视图:

```java
public List<Integer> rightSideView(TreeNode root){
        List<Integer> ret=new ArrayList<>();
        if(root==null){
        return ret;
        }

        Deque<TreeNode> queue=new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
        int sz=queue.size();
        for(int i=0;i<sz; i++){
        TreeNode node=queue.remove();
        if(i==sz-1){
        ret.add(node.val);
        }
        if(node.left!=null){
        queue.add(node.left);
        }
        if(node.right!=null){
        queue.add(node.right);
        }
        }
        }

        return ret;
        }
```

### 完全平方数 (279)

给定一个整数n，返回和为n的完全平方数的最小数量:

```java
public int numSquares(int n){

        // dp[i]: 整数i所需要的完全平方数最小数量
        int[]dp=new int[n+1];

        for(int i=1;i<=n;i++){
        dp[i]=i; // 初始化
        for(int j=1;;j++){
        int cmp=i-j*j;
        if(cmp< 0){
        break;
        }
        dp[i]=Math.min(dp[i],dp[cmp]+1);
        }
        }

        return dp[n];
        }

// bfs & 记忆化搜索
public int numSquaresV2(int n){
        int[]cnt=new int[n+1];
        Deque<Integer> queue=new LinkedList<>();
        boolean[]visited=new boolean[n+1];
        queue.add(n);
        visited[n]=true;

        while(!queue.isEmpty()){
        Integer item=queue.remove();
        for(int i=1;;i++){
        int cmp=item-i*i;
        if(cmp==0){
        return cnt[item]+1;
        }else if(cmp< 0){
        break;
        }else{
        if(!visited[cmp]){
        visited[cmp]=true;
        cnt[cmp]=cnt[item]+1;
        queue.add(cmp);
        }
        }
        }
        }
        throw new RuntimeException();
        }
```

### 单词接龙 (127)

给定两个单词（beginWord 和 endWord）和一个字典，找到从beginWord到endWord的最短转换序列的长度。转换需遵循如下规则:
- 每次转换只能改变一个字母
- 转换过程中的中间单词必须是字典中的单词
- 
说明:
- 如果不存在这样的转换序列，返回0
- 所有单词具有相同的长度
- 所有单词只由小写字母组成
- 字典中不存在重复的单词
假设beginWord和endWord是非空的，且二者不相同

```java
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    HashSet<String> words = new HashSet<>(wordList);
    if (!words.contains(endWord)) {
        return 0;
    }

    Map<String, Boolean> visited = new HashMap<>(wordList.size());
    wordList.forEach(w -> visited.put(w, false));

    Deque<String> queue = new LinkedList<>();
    queue.add(beginWord);
    visited.put(beginWord, true);

    int depth = 0;

    while (!queue.isEmpty()) {
        ++depth;
        int sz = queue.size();
        for (int i = 0; i < sz; i++) {
            String word = queue.remove();
            for (int idx = 0; idx < word.length(); idx++) {
                char[] chars = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[idx] = ch;
                    String tmpStr = String.valueOf(chars);
                    if (tmpStr.equals(endWord)) {
                        return depth + 1;
                    }
                    if (!tmpStr.equals(word) && words.contains(tmpStr) && !visited.getOrDefault(tmpStr, false)) {
                        queue.add(tmpStr);
                        visited.put(tmpStr, true);
                    }
                }
            }
        }
    }
    return 0;
}
```

### 成语接龙 II (126)

给定两个单词（beginWord 和 endWord）和一个字典，找到从beginWord到endWord的最短转换序列的长度。转换需遵循如下规则:
- 每次转换只能改变一个字母
- 转换过程中的中间单词必须是字典中的单词
说明:
- 如果不存在这样的转换序列，返回0
- 所有单词具有相同的长度
- 所有单词只由小写字母组成
- 字典中不存在重复的单词
假设beginWord和endWord是非空的，且二者不相同

```java
public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> ret = new ArrayList<>();
    HashSet<String> words = new HashSet<>(wordList);
    if (!words.contains(endWord)) {
        return ret;
    }

    bfs(beginWord, endWord, words, ret);

    return ret;
}

private void bfs(String beginWord, String endWord, HashSet<String> words, List<List<String>> ret) {
    Deque<List<String>> queue = new LinkedList<>();
    List<String> path = new ArrayList<>();
    path.add(beginWord);
    queue.add(path);

    Set<String> visited = new HashSet<>();
    visited.add(beginWord);
    boolean isFound = false;

    while (!queue.isEmpty()) {
        int sz = queue.size();
        Set<String> subVisited = new HashSet<>();
        for (int i = 0; i < sz; i++) {
            List<String> p = queue.remove();
            String lastStr = p.get(p.size() - 1);
            List<String> neighbors = getNeighbors(lastStr, words);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    // 如果在当前层发现转换路径，只有可能在当前层存在其他路径，之后层直接忽略break
                    if (neighbor.equals(endWord)) {
                        isFound = true;
                        p.add(neighbor);
                        ret.add(new ArrayList<>(p));
                        break;
                    }

                    p.add(neighbor);
                    queue.add(new ArrayList<>(p));
                    p.remove(p.size() - 1);
                    //! visited.add(neighbor);
                    // 当前层还不能认为neighbor是被访问的
                    subVisited.add(neighbor);
                }
            }
        }
        if (isFound) {
            break;
        }
        visited.addAll(subVisited);
    }
}

private List<String> getNeighbors(String str, HashSet<String> words) {
    ArrayList<String> ret = new ArrayList<>();
    char chs[] = str.toCharArray();
    for (char ch = 'a'; ch <= 'z'; ch++) {
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ch) {
                continue;
            }
            char oldCh = chs[i];
            chs[i] = ch;
            if (words.contains(String.valueOf(chs))) {
                ret.add(String.valueOf(chs));
            }
            chs[i] = oldCh;
        }
    }
    return ret;
}
```