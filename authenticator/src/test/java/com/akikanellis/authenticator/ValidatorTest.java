package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
    @Mock private PasswordRepository passwordRepository;
    private Validator validator;

    @Before public void beforeEach() { validator = new Validator(passwordRepository); }


}