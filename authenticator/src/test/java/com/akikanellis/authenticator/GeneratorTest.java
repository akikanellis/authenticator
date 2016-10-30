package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeneratorTest {
    @Mock private Random random;
    @Mock private PasswordRepository passwordRepository;
    private Generator generator;

    @Before public void beforeEach() { generator = new Generator(random, passwordRepository); }

    @Test public void generatingPassword_withNoSetPasswordForUser_generatesNewPassword() {
        when(random.nextInt()).thenReturn(1);

        int password = generator.generate("user-id");

        assertThat(password).isEqualTo(1);
    }
}