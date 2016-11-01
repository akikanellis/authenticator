package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorTest {
    @Mock private Generator generator;
    @Mock private Validator validator;
    private Authenticator authenticator;

    @Before public void beforeEach() { authenticator = new Authenticator(generator, validator); }

    @Test public void generatingAPassword_withValidUserID_generatesAPassword() {
        when(generator.generate("user-id")).thenReturn(1);

        int password = authenticator.generatePassword("user-id");

        assertThat(password).isEqualTo(1);
    }

    @Test public void generatingAPassword_withNullUserID_throwsException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> authenticator.generatePassword(null));
    }

    @Test public void generatingAPassword_withEmptyUserID_throwsException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> authenticator.generatePassword(""));
    }

    @Test public void validatingUserWithPassword_withValidUserIDAndSamePassword_returnsTrue() {
        when(validator.validate("user-id", 1)).thenReturn(true);

        boolean isValid = authenticator.isPasswordValid("user-id", 1);

        assertThat(isValid).isTrue();
    }

    @Test public void validatingUserWithPassword_withValidUserIDAndDifferentPassword_returnsFalse() {
        when(validator.validate(eq("user-id"), anyInt())).thenReturn(false);

        boolean isValid = authenticator.isPasswordValid("user-id", 15);

        assertThat(isValid).isFalse();
    }

    @Test public void validatingUserWithPassword_withNullUserID_throwsException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> authenticator.isPasswordValid(null, 1));
    }

    @Test public void validatingUserWithPassword_withEmptyUserID_throwsException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> authenticator.isPasswordValid("", 1));
    }
}