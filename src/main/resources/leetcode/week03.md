## week03

### Binary tree level order traversal (102)

给定一个二叉树，返回其按从上到下层次遍历的节点值:

```java
private List<List<Integer>> ret = new ArrayList<>();

public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
        return ret;
    }
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int sz = queue.size();
        List<Integer> tmp = new ArrayList<>(sz);
        for (int i = 0; i < sz; i++) {
            TreeNode item = queue.removeFirst();
            tmp.add(item.val);
            if (item.left != null) {
                queue.addLast(item.left);
            }
            if (item.right != null) {
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
private List<List<Integer>> ret = new LinkedList<>();

public List<List<Integer>> levelOrderBottom(TreeNode root) {
    if (root == null) {
        return ret;
    }
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int sz = queue.size();
        List<Integer> tmp = new ArrayList<>(sz);
        for (int i = 0; i < sz; i++) {
            TreeNode item = queue.remove();
            tmp.add(item.val);
            if (item.left != null) {
                queue.add(item.left);
            }
            if (item.right != null) {
                queue.add(item.right);
            }
        }
        ret.add(0, tmp);
    }
    return ret;
}
```
