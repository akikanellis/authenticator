package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;
import java.util.OptionalInt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PasswordRepositoryTest {
    @Mock private Map<String, Integer> expiringMap;
    private PasswordRepository passwordRepository;

    @Before public void beforeEach() { passwordRepository = new PasswordRepository(expiringMap); }

    @Test public void gettingPasswordOfUser_withExistingUser_returnsPassword() {
        when(expiringMap.get("user-id")).thenReturn(1);

        OptionalInt password = passwordRepository.getPasswordOfUser("user-id");

        //noinspection OptionalGetWithoutIsPresent
        assertThat(password.getAsInt()).isEqualTo(1);
    }

    @Test public void gettingPasswordOfUser_withMissingUser_returnsEmpty() {
        when(expiringMap.get("user-id")).thenReturn(null);

        OptionalInt password = passwordRepository.getPasswordOfUser("user-id");

        //noinspection OptionalGetWithoutIsPresent
        assertThat(password).isEmpty();
    }
}