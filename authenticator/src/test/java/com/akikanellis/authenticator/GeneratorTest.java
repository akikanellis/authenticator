package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.OptionalInt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeneratorTest {
    @Mock private PasswordGenerationAlgorithm passwordGenerationAlgorithm;
    @Mock private PasswordRepository passwordRepository;
    private Generator generator;

    @Before public void beforeEach() { generator = new Generator(passwordGenerationAlgorithm, passwordRepository); }

    @Test public void generatingPassword_withNoSetPasswordForUser_generatesNewPassword() {
        when(passwordRepository.getPasswordOfUser("user-id")).thenReturn(OptionalInt.empty());
        when(passwordGenerationAlgorithm.next()).thenReturn(1);

        int password = generator.generate("user-id");

        assertThat(password).isEqualTo(1);
    }

    @Test public void generatingPassword_withNoSetPasswordForUser_addsNewPasswordToRepository() {
        when(passwordRepository.getPasswordOfUser("user-id")).thenReturn(OptionalInt.empty());
        when(passwordGenerationAlgorithm.next()).thenReturn(1);

        int password = generator.generate("user-id");

        verify(passwordRepository).addPassword("user-id", password);
    }

    @Test public void generatingPassword_withPasswordAlreadySetForUser_returnsPreviousPassword() {
        when(passwordRepository.getPasswordOfUser("user-id")).thenReturn(OptionalInt.of(10));

        int password = generator.generate("user-id");

        verifyZeroInteractions(passwordGenerationAlgorithm);
        assertThat(password).isEqualTo(10);
    }
}