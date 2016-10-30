package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticatorIntegrationTest {
    private Authenticator authenticator;

    @Before public void beforeEach() { authenticator = new Authenticator(3, SECONDS); }

    @Test public void checkingPassword_withinTheTimeFrame_returnsTrue() throws InterruptedException {
        int password = authenticator.generatePassword("user-id");

        SECONDS.sleep(2);

        assertThat(authenticator.isPasswordValid("user-id", password)).isTrue();
    }
}
