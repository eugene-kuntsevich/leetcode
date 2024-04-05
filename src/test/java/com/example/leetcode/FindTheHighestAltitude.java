package com.example.leetcode;

import org.junit.jupiter.api.Test;

public class FindTheHighestAltitude {

    @Test
    void run() {
        int[] gain = new int[]{-5, 1, 5, 0, -7};
        int[] gain2 = new int[]{-4, -3, -2, -1, 4, 3, 2};
        System.out.println(largestAltitude(gain));
        System.out.println(largestAltitude(gain2));
    }

    public int largestAltitude(int[] gain) {
        int res = 0;
        int sum = 0;

        for (int i = 0; i < gain.length; i++) {
            sum += gain[i];
            if (res < sum) {
                res = sum;
            }
        }

        return res;
    }
}
