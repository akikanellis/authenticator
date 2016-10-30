package com.akikanellis.authenticator;

import java.util.OptionalInt;
import java.util.Random;

class Generator {
    private final Random random;
    private final PasswordRepository passwordRepository;

    Generator(Random random, PasswordRepository passwordRepository) {
        this.random = random;
        this.passwordRepository = passwordRepository;
    }

    int generate(String userId) {
        OptionalInt currentPassword = passwordRepository.getPasswordOfUser(userId);

        return currentPassword.orElseGet(random::nextInt);
    }
}
