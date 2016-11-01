package com.akikanellis.authenticator;

import java.util.Random;

/**
 * Generates a password for a user which is a 6-digit random integer.
 */
class PasswordGenerationAlgorithm {
    private static final int LOWER_BOUND = 100000;
    private static final int UPPER_BOUND = 999999;

    private final Random random;

    PasswordGenerationAlgorithm(Random random) { this.random = random; }

    int next() { return random.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND; }
}
