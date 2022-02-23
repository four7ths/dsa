package com.four7ths.dsa.leetcode.week07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18 四数之和 I
 */
public class FourSumI {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> tmp =
                    threeSum0(Arrays.copyOfRange(nums, i + 1, nums.length), target - nums[i]);
            for (List<Integer> list : tmp) {
                list.add(nums[i]);
                res.add(list);
            }

        }
        return res;
    }

    private List<List<Integer>> threeSum0(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[l]);
                    tmp.add(nums[r]);
                    res.add(tmp);
                    do {
                        ++l;
                    } while (l < r && nums[l] == nums[l - 1]);
                    do {
                        --r;
                    } while (l < r && nums[r] == nums[r + 1]);
                } else if (sum > target) {
                    do {
                        --r;
                    } while (l < r && nums[r] == nums[r + 1]);
                } else {
                    do {
                        ++l;
                    } while (l < r && nums[l] == nums[l - 1]);
                }
            }
        }
        return res;
    }
}
