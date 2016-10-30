package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GeneratorTest {
    @Mock private PasswordRepository passwordRepository;
    private Generator generator;

    @Before public void beforeEach() { generator = new Generator(passwordRepository);}
}