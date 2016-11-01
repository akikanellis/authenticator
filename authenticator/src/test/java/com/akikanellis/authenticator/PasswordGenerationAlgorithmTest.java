package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PasswordGenerationAlgorithmTest {
    @Mock private Random random;
    private PasswordGenerationAlgorithm passwordGenerationAlgorithm;

    @Before public void beforeEach() { passwordGenerationAlgorithm = new PasswordGenerationAlgorithm(random); }

    @Test public void gettingNext_usesRandom() {
        passwordGenerationAlgorithm.next();

        verify(random).nextInt(anyInt());
        verifyNoMoreInteractions(random);
    }

    @Test public void gettingNext_withLowestPossibleValue_generatesANumberWithSixDigits() {
        when(random.nextInt(anyInt())).thenReturn(0);

        int number = passwordGenerationAlgorithm.next();

        assertThatNumberIsSixDigits(number);
    }

    @Test public void gettingNext_withHighestPossibleValue_generatesANumberWithSixDigits() {
        when(random.nextInt()).thenReturn(899999);

        int number = passwordGenerationAlgorithm.next();

        assertThatNumberIsSixDigits(number);
    }

    @Test public void gettingNext_withAMediumPossibleValue_generatesANumberWithSixDigits() {
        when(random.nextInt()).thenReturn(50000);

        int number = passwordGenerationAlgorithm.next();

        assertThatNumberIsSixDigits(number);
    }

    private void assertThatNumberIsSixDigits(int number) {
        assertThat(number)
                .isGreaterThanOrEqualTo(100000)
                .isLessThanOrEqualTo(999999);
    }
}