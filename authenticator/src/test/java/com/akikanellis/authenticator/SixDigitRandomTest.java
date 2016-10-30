package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SixDigitRandomTest {
    @Mock private Random random;
    private SixDigitRandom sixDigitRandom;

    @Before public void beforeEach() { sixDigitRandom = new SixDigitRandom(random); }

    @Test public void gettingNext_usesRandom() {
        sixDigitRandom.next();

        verify(random).nextInt(anyInt());
        verifyNoMoreInteractions(random);
    }

    @Test public void gettingNext_withLowestPossibleValue_generatesANumberWithSixDigits() {
        when(random.nextInt(anyInt())).thenReturn(0);

        int number = sixDigitRandom.next();

        assertThatNumberIsSixDigits(number);
    }

    @Test public void gettingNext_withHighestPossibleValue_generatesANumberWithSixDigits() {
        when(random.nextInt()).thenReturn(899999);

        int number = sixDigitRandom.next();

        assertThatNumberIsSixDigits(number);
    }

    @Test public void gettingNext_withAMediumPossibleValue_generatesANumberWithSixDigits() {
        when(random.nextInt()).thenReturn(50000);

        int number = sixDigitRandom.next();

        assertThatNumberIsSixDigits(number);
    }

    private void assertThatNumberIsSixDigits(int number) {
        assertThat(number)
                .isGreaterThanOrEqualTo(100000)
                .isLessThanOrEqualTo(999999);
    }
}