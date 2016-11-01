package com.akikanellis.authenticator;

import java.util.OptionalInt;

/**
 * A password generator which takes care of returning a generated or cached password to the user.
 */
class Generator {
    private final PasswordGenerationAlgorithm passwordGenerationAlgorithm;
    private final PasswordRepository passwordRepository;

    Generator(PasswordGenerationAlgorithm passwordGenerationAlgorithm, PasswordRepository passwordRepository) {
        this.passwordGenerationAlgorithm = passwordGenerationAlgorithm;
        this.passwordRepository = passwordRepository;
    }

    int generate(String userId) {
        OptionalInt currentPassword = passwordRepository.getPasswordOfUser(userId);

        if (currentPassword.isPresent()) {
            return currentPassword.getAsInt();
        } else {
            int generatedPassword = passwordGenerationAlgorithm.next();
            passwordRepository.addPassword(userId, generatedPassword);
            return generatedPassword;
        }
    }
}
