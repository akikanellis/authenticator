package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordGenerationAlgorithmIntegrationTest {
    private PasswordGenerationAlgorithm passwordGenerationAlgorithm;

    @Before public void beforeEach() {
        passwordGenerationAlgorithm = new PasswordGenerationAlgorithm(new Random(123456));
    }

    @Test public void generatingRandomNumbers_generatesWithSixDigits() {
        IntStream.range(0, 1000000)
                .forEach(ignored -> {
                    int number = passwordGenerationAlgorithm.next();
                    assertThatNumberIsSixDigits(number);
                });
    }

    private void assertThatNumberIsSixDigits(int number) {
        assertThat(number)
                .isGreaterThanOrEqualTo(100000)
                .isLessThanOrEqualTo(999999);
    }
}
