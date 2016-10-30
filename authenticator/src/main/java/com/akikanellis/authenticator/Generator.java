package com.akikanellis.authenticator;

import java.util.Random;

class Generator {
    private final Random random;
    private final PasswordRepository passwordRepository;

    Generator(Random random, PasswordRepository passwordRepository) {
        this.random = random;
        this.passwordRepository = passwordRepository;
    }

    int generate(String userId) {
        return random.nextInt();
    }
}
