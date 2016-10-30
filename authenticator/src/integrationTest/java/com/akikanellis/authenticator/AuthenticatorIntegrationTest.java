package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticatorIntegrationTest {
    private Authenticator authenticator;

    @Before public void beforeEach() { authenticator = new Authenticator(3, SECONDS); }

    @Test public void generatingPassword_forSameUserWithinTheTimeFrame_returnsSamePassword()
            throws InterruptedException {
        int firstPassword = authenticator.generatePassword("user-id");
        SECONDS.sleep(2);
        int secondPassword = authenticator.generatePassword("user-id");

        assertThat(firstPassword).isEqualTo(secondPassword);
    }

    @Test public void generatingPassword_forSameUserOutsideOfTheTimeFrame_returnsDifferentPassword()
            throws InterruptedException {
        int firstPassword = authenticator.generatePassword("user-id");
        SECONDS.sleep(4);
        int secondPassword = authenticator.generatePassword("user-id");

        assertThat(firstPassword).isNotEqualTo(secondPassword);
    }

    @Test public void checkingPassword_withinTheTimeFrame_returnsTrue() throws InterruptedException {
        int password = authenticator.generatePassword("user-id");
        SECONDS.sleep(2);

        assertThat(authenticator.isPasswordValid("user-id", password)).isTrue();
    }

    @Test public void checkingPassword_outsideOfTheTimeFrame_returnsFalse() throws InterruptedException {
        int password = authenticator.generatePassword("user-id");
        SECONDS.sleep(4);

        assertThat(authenticator.isPasswordValid("user-id", password)).isFalse();
    }
}
