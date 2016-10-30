package com.akikanellis.authenticator;

import java.util.Map;
import java.util.OptionalInt;

class PasswordRepository {
    private final Map<String, Integer> expiringMap;

    PasswordRepository(Map<String, Integer> expiringMap) { this.expiringMap = expiringMap; }

    OptionalInt getPasswordOfUser(String userId) {
        Integer password = expiringMap.get(userId);

        return OptionalInt.of(password);
    }
}
