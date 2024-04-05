package com.example.leetcode;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MoveZeroes {

    @Test
    void run() {
        moveZeroes(new int[]{0, 1, 0, 3, 12});
        moveZeroes(new int[]{0, 1});
        moveZeroes(new int[]{1, 2});
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) nums[insertPos++] = num;
            System.out.println(Arrays.toString(nums));
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
            System.out.println(Arrays.toString(nums));
        }
    }
}
