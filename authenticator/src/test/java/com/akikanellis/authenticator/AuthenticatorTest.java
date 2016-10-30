package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticatorTest {
    @Mock private Generator generator;
    @Mock private Validator validator;
    private Authenticator authenticator;

    @Before public void beforeEach() { authenticator = new Authenticator(generator, validator); }

}