package com.example.generator;

import java.util.Random;
import java.util.UUID;

public class StringGenerator {

    private static final Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            if (random.nextBoolean()) {
                generate("basic_card_data:4");
            } else {
                generate("basic_card_data:5");
            }
        }
    }

    private static void generate(String prefix) {
        String key = prefix + UUID.randomUUID().toString().substring(0, 15);
        String formatted = String.format("set %s test", key);
        System.out.println(formatted);
    }
}
