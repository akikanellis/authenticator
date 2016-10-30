package com.akikanellis.authenticator;

import java.util.OptionalInt;

class Generator {
    private final SixDigitRandom random;
    private final PasswordRepository passwordRepository;

    Generator(SixDigitRandom random, PasswordRepository passwordRepository) {
        this.random = random;
        this.passwordRepository = passwordRepository;
    }

    int generate(String userId) {
        OptionalInt currentPassword = passwordRepository.getPasswordOfUser(userId);

        return currentPassword.orElseGet(random::next);
    }
}
