package com.akikanellis.authenticator;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.akikanellis.authenticator.Preconditions.checkNotNullOrEmpty;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * A one-time password generator and authenticator. Each password is valid for 30 seconds from the time it was created.
 */
public class Authenticator {
    private final Generator generator;
    private final Validator validator;

    public Authenticator() { this(30, SECONDS); }

    /* Visible for testing */
    Authenticator(long expiringDelay, TimeUnit timeUnit) {
        ScheduledExecutorService expirer = Executors.newSingleThreadScheduledExecutor();
        Map<String, Integer> expiringMap = new ExpiringMap<>(expirer, expiringDelay, timeUnit);
        PasswordRepository passwordRepository = new PasswordRepository(expiringMap);

        Random random = new Random();
        PasswordGenerationAlgorithm passwordGenerationAlgorithm = new PasswordGenerationAlgorithm(random);

        this.generator = new Generator(passwordGenerationAlgorithm, passwordRepository);
        this.validator = new Validator(passwordRepository);
    }

    /* Visible for testing */
    Authenticator(Generator generator, Validator validator) {
        this.generator = generator;
        this.validator = validator;
    }

    /**
     * Generates a 6-digit password for the given user ID.
     *
     * @param userId the user ID to generate the password for
     * @return a 6-digit password that is valid for 30 seconds
     * @throws java.lang.IllegalArgumentException if the user ID is null or empty
     */
    public int generatePassword(String userId) {
        checkNotNullOrEmpty(userId);

        return generator.generate(userId);
    }

    /**
     * Validates a user ID against a password.
     *
     * @param userId             the user ID to validate
     * @param passwordToValidate the password to validate with the user ID
     * @return true if the user ID exists and matches with the password, false if not
     * @throws java.lang.IllegalArgumentException if the user ID is null or empty
     */
    public boolean isPasswordValid(String userId, int passwordToValidate) {
        checkNotNullOrEmpty(userId);

        return validator.validate(userId, passwordToValidate);
    }
}
