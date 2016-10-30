package com.akikanellis.authenticator;

import java.util.OptionalInt;

class Validator {
    private final PasswordRepository passwordRepository;

    Validator(PasswordRepository passwordRepository) { this.passwordRepository = passwordRepository; }

    boolean validate(String userId, int passwordToValidate) {
        OptionalInt currentPassword = passwordRepository.getPasswordOfUser(userId);

        return currentPassword.isPresent() && currentPassword.getAsInt() == passwordToValidate;
    }
}
