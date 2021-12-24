## week01

### Move Zero (238)
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

### Remove Element (27)
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

### Remove duplicate element from a sorted array I (26)
给定一个数组，删除数组中重复的元素，保证所有元素只出现一次，返回不重复元素的个数:
```java
public int removeDuplicates(int[] nums) {
    int idx = 0;
    for (int n : nums) {
        if (n != nums[idx]) {
            nums[++idx] = n;
        }
    }
    return idx + 1;
}
```

### Remove duplicate element from a sorted array II (80)
给定一个有序数组，删除数组中重复元素，使得每个元素最多出现2次，并返回最终数组元素:
```java
public int removeDuplicatesII(int[] nums) {
    int idx = 0;
    for (int n : nums) {
        if (idx < 2 || n > nums[idx - 2]) {
            nums[idx++] = n;
        }
    }
    return idx;
}
```

### Sort color (75)
给定一个只包含0，1和2的数组，原地排序数组:
```java
public void sortColor(int[] nums) {
    if (nums == null || nums.length == 0) {
        return;
    }
    int lo = 0;
    int hi = nums.length - 1;
    int i = lo;
    // arr[0,lo-1]             -> 0
    // arr[lo,hi]              -> 1
    // arr[hi+1,nums.length-1] -> 2
    while (i <= hi) { // [2 0 1]
        if (nums[i] == 1) {
            i++;
        } else if (nums[i] == 0) {
            swap(nums, lo++, i++);
        } else {
            swap(nums, i, hi--);
        }
    }
}

private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}
```

### Merge two sorted arrays (80)
给定两个有序数组num1和nums2，将两个数组合并成同一个有序数组，假设nums1有足够空间容纳nums2:
```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
    int idx = m-- + n-- - 1;
    while (m >= 0 && n >= 0) {
        nums1[idx--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
    }
    while (n >= 0) {
        nums2[idx--] = nums2[n--];
    }
}
```