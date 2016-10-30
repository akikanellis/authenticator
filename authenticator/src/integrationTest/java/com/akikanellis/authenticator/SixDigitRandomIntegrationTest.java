package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SixDigitRandomIntegrationTest {
    private SixDigitRandom sixDigitRandom;

    @Before public void beforeEach() { sixDigitRandom = new SixDigitRandom(new Random(123456)); }

    @Test public void generatingRandomNumbers_generatesWithSixDigits() {
        IntStream.range(0, 1000000)
                .forEach(ignored -> {
                    int number = sixDigitRandom.next();
                    assertThatNumberIsSixDigits(number);
                });
    }

    private void assertThatNumberIsSixDigits(int number) {
        assertThat(number)
                .isGreaterThanOrEqualTo(100000)
                .isLessThanOrEqualTo(999999);
    }
}
