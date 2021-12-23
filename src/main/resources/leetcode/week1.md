## week01

### Move Zero(238)
给定一个数组，将所有元素0全部移动到数组末尾，非0元素相对位置保持不变:
```java
public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) {
        return;
    }
    int idx = -1;
    for (int n : nums) {
        if (n != 0) {
            nums[++idx] = n;
        }
    }
    for (int i = idx + 1; i < nums.length; i++) {
        nums[i] = 0;
    }
}
```

### Remove Element(27)
给定一个数组和目标值target，【原地】删除数组中所有等于target的元素，并返回删除后数组长度:
```java
public int removeElement(int[] nums, int val) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int idx = -1;
    for (int n : nums) {
        if (n != val) {
            nums[++idx] = n;
        }
    }
    for (int i = idx + 1; i < nums.length; i++) {
        nums[i] = 0;
    }
    return idx + 1;
}
```