package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.OptionalInt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
    @Mock private PasswordRepository passwordRepository;
    private Validator validator;

    @Before public void beforeEach() { validator = new Validator(passwordRepository); }

    @Test public void validating_withExistingUserAndSamePassword_returnsTrue() {
        when(passwordRepository.getPasswordOfUser("user-id")).thenReturn(OptionalInt.of(1));

        boolean isValid = validator.validate("user-id", 1);

        assertThat(isValid).isTrue();
    }

    @Test public void validating_withExistingUserAndDifferentPassword_returnsFalse() {
        when(passwordRepository.getPasswordOfUser("user-id")).thenReturn(OptionalInt.of(1));

        boolean isValid = validator.validate("user-id", 15);

        assertThat(isValid).isFalse();
    }

    @Test public void validating_withMissingUser_returnsFalse() {
        when(passwordRepository.getPasswordOfUser("user-id")).thenReturn(OptionalInt.empty());

        boolean isValid = validator.validate("user-id", 1);

        assertThat(isValid).isFalse();
    }
}