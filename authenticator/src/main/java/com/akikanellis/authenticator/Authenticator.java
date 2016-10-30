package com.akikanellis.authenticator;

public class Authenticator {
    private final Generator generator;
    private final Validator validator;

    Authenticator(Generator generator, Validator validator) {
        this.generator = generator;
        this.validator = validator;
    }


}
