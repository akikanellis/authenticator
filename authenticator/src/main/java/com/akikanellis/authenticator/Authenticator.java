package com.akikanellis.authenticator;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.akikanellis.authenticator.Preconditions.checkNotNullOrEmpty;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Authenticator {
    private final Generator generator;
    private final Validator validator;

    public Authenticator() {
        ScheduledExecutorService expirer = Executors.newSingleThreadScheduledExecutor();
        Map<String, Integer> expiringMap = new ExpiringMap<>(expirer, 30, SECONDS);
        PasswordRepository passwordRepository = new PasswordRepository(expiringMap);

        Random random = new Random();
        SixDigitRandom sixDigitRandom = new SixDigitRandom(random);

        this.generator = new Generator(sixDigitRandom, passwordRepository);
        this.validator = new Validator(passwordRepository);
    }

    Authenticator(Generator generator, Validator validator) {
        this.generator = generator;
        this.validator = validator;
    }

    public int generatePassword(String userId) {
        checkNotNullOrEmpty(userId);

        return generator.generate(userId);
    }

    public boolean isPasswordValid(String userId, int passwordToValidate) {
        checkNotNullOrEmpty(userId);

        return validator.validate(userId, passwordToValidate);
    }
}
