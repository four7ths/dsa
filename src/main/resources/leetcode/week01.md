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

### Kth-largest element in an array (215)
给定给一个数组（非有序），求出数组中第k大的元素，1<=k<=len(nums):
```java
// 使用快排partition
public int findKthLargest(int[] nums, int k) {
    return findKthLargest(nums, k, 0, nums.length - 1);
}

// len=nums.length
// 第k大元素 <=> 第len-k+1小元素
// partition操作中，返回值idx表示：pivot左边有idx数据小于pivot，即nums[idx]是第idx+1小的元素
// 等式成立条件：len-k+1=idx+1 <=> len-k=idx
private int findKthLargest(int[] nums, int k, int lo, int hi) {

    int idx = partition(nums, lo, hi);
    while (idx != nums.length - k) {
        if (idx > nums.length - k) {
            idx = partition(nums, lo, idx - 1);
        } else {
            idx = partition(nums, idx + 1, hi);
        }
    }
    return nums[idx];
}

private int partition(int[] nums, int lo, int hi) {
    int rnd = new Random().nextInt(hi - lo + 1) + lo;
    swap(nums, rnd, hi);
    int pivot = nums[hi];

    int idx = lo;
    for (int i = lo; i < hi; i++) {
        if (nums[i] < pivot) {
            swap(nums, idx++, i);
        }
    }
    swap(nums, idx, hi);
    return idx;
}

// 使用大顶堆
public int findKthLargestUsingHeap(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    for (int n : nums) {
        pq.add(n);
    }
    for (int i = 0; i < k - 1; i++) {
        pq.poll();
    }
    return pq.poll();
}
```

### two sum II (167)
给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数，返回两个数对应索引坐标:
```java    
public int[] twoSum(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    while (lo < hi) {
        int tmp = nums[lo] + nums[hi];
        if (tmp == target) {
            return new int[]{lo, hi};
        } else if (tmp > target) {
            lo++;
        } else {
            hi--;
        }
    }
    return new int[]{-1, -1};
}
```

### reverse string (344)
反转字符串:
```java
public void reverseString(char[] s) {
    if (s == null || s.length <= 1) {
        return;
    }
    int lo = 0, hi = s.length - 1;
    while (lo < hi) {
        swap(s, lo++, hi--);
    }
}
```

### reverse vowels of a string (345)
反转字符串中元音字母:
```java
public String reverseVowels(String s) {
    if (s == null || s.length() <= 1) {
        return s;
    }
    char[] chars = s.toCharArray();
    int lo = 0, hi = chars.length - 1;
    while (lo < hi) {
        while (lo < hi && !isVowel(chars[lo])) {
            lo++;
        }
        while (lo < hi && !isVowel(chars[hi])) {
            hi--;
        }
        swap(chars, lo++, hi--);
    }
    return new String(chars);
}

private boolean isVowel(char ch) {
    return ch == 'a' || ch == 'A' ||
            ch == 'e' || ch == 'E' ||
            ch == 'i' || ch == 'I' ||
            ch == 'o' || ch == 'O' ||
            ch == 'u' || ch == 'U';
}
```

### container with most water (11)
给定n个非负整数，分别代表坐标中的点(i, n_i)，找出其中两条线，使得它们和X轴围成的矩形面积最大，返回最大面积:
```java
public int maxArea(int[] height) {
    int lo = 0, hi = height.length - 1;
    int area = 0;
    while (lo < hi) {
        int hgt = 0;
        int width = hi - lo;
        if (height[lo] < height[hi]) {
            hgt = height[lo++];
        } else {
            hgt = height[hi--];
        }
        area = Math.max(area, hgt * width);
    }
    return area;
}
```

### min sub array length (209)
给定一个数组arr和目标值s，找出连续子数组和大于等于s的最小长度，如果不存在返回-1:
```java
public int minSubArrayLen(int s, int[] nums) {
    int ret = nums.length + 1;
    int lo = 0, hi = 0;
    int sum = 0;
    while (lo < nums.length) {
        if (hi < nums.length && sum < s) {
            sum += nums[hi++];
        } else {
            sum -= nums[lo++];
        }
        if (sum >= s) {
            // 注意这里长度是: hi-lo，因为hi已经++过了
            ret = Math.min(ret, hi - lo);
        }
    }
    return ret == nums.length + 1 ? 0 : ret;
}
```