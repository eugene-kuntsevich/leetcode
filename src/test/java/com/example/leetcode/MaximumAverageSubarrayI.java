package com.example.leetcode;

import org.junit.jupiter.api.Test;

public class MaximumAverageSubarrayI {

    @Test
    void run() {
        int[] arr = new int[]{1, 12, -5, -6, 50, 3};
        int[] arr2 = new int[]{5};
        int[] arr3 = new int[]{-1};
        System.out.println(findMaxAverage(arr, 4));
        System.out.println(findMaxAverage(arr2, 1));
        System.out.println(findMaxAverage(arr3, 1));
    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i];

        int maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        return (double) maxSum / k;
    }
}
