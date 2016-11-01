package com.akikanellis.authenticator;

import java.util.OptionalInt;

/**
 * A password validator which verifies if the user ID and the password given are a match.
 */
class Validator {
    private final PasswordRepository passwordRepository;

    Validator(PasswordRepository passwordRepository) { this.passwordRepository = passwordRepository; }

    boolean validate(String userId, int passwordToValidate) {
        OptionalInt currentPassword = passwordRepository.getPasswordOfUser(userId);

        return currentPassword.isPresent() && currentPassword.getAsInt() == passwordToValidate;
    }
}
