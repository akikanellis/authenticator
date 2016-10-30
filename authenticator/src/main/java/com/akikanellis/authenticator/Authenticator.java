package com.akikanellis.authenticator;

import static com.akikanellis.authenticator.Preconditions.checkNotNullOrEmpty;

public class Authenticator {
    private final Generator generator;
    private final Validator validator;

    Authenticator(Generator generator, Validator validator) {
        this.generator = generator;
        this.validator = validator;
    }


    public int generatePassword(String userId) {
        checkNotNullOrEmpty(userId);

        return generator.generate(userId);
    }
}
