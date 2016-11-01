package com.akikanellis.authenticator;

import org.junit.Test;

import static com.akikanellis.authenticator.Preconditions.checkNotNullOrEmpty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PreconditionsTest {

    @Test public void checkingString_withValidString_returnsSameString() {
        String expectedString = "string";

        String actualString = checkNotNullOrEmpty(expectedString);

        assertThat(actualString).isSameAs(expectedString);
    }

    @Test public void checkingString_withNullString_throwsExceptionWithMessageForNullability() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> checkNotNullOrEmpty(null))
                .withMessageContaining("null");
    }

    @Test public void checkingString_withEmptyString_throwsExceptionWithMessageForEmptiness() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> checkNotNullOrEmpty(""))
                .withMessageContaining("empty");
    }
}