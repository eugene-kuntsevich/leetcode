package com.example.leetcode;

import org.junit.jupiter.api.Test;

public class MergeStringsAlternately {

    @Test
    void run() {
        int[] arr = new int[]{3, 2, 4};
        System.out.println(mergeAlternately("abc", "pqr"));
        System.out.println(mergeAlternately("ab", "pqrs"));
        System.out.println(mergeAlternately("abcd", "pq"));
        System.out.println(mergeAlternately("cdf", "a"));
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word1.length() || i < word2.length(); i++) {
            if (i < word1.length()){
                builder.append(word1.charAt(i));
            }
            if (i < word2.length()){
                builder.append(word2.charAt(i));
            }
        }

        return builder.toString();
    }
}
