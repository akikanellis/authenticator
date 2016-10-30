package com.akikanellis.authenticator;

class Validator {
    private final PasswordRepository passwordRepository;

    Validator(PasswordRepository passwordRepository) { this.passwordRepository = passwordRepository; }

    boolean validate(String userId, int password) {
        return true;
    }
}
