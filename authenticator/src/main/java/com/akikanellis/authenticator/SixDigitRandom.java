package com.akikanellis.authenticator;

import java.util.Random;

class SixDigitRandom {
    private static final int LOWER_BOUND = 100000;
    private static final int UPPER_BOUND = 999999;

    private final Random random;

    SixDigitRandom(Random random) { this.random = random; }

    int next() { return random.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND; }
}
