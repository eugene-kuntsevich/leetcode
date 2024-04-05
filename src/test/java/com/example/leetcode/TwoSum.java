package com.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TwoSum {

    @Test
    void run() {
        int[] arr = new int[]{3, 2, 4};
        System.out.println(Arrays.toString(twoSum(arr, 6)));
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int m = i + 1; m < nums.length; m++) {
                if (nums[i] + nums[m] == target) {
                    return new int[]{i, m};
                }
            }
        }
        return new int[0];
    }
}
