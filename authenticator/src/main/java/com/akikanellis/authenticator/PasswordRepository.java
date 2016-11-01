package com.akikanellis.authenticator;

import java.util.Map;
import java.util.OptionalInt;

/**
 * The password repository holds all the currently valid passwords and provides a slim API for accessing them.
 */
class PasswordRepository {
    private final Map<String, Integer> expiringMap;

    PasswordRepository(Map<String, Integer> expiringMap) { this.expiringMap = expiringMap; }

    OptionalInt getPasswordOfUser(String userId) {
        Integer password = expiringMap.get(userId);

        return password == null ? OptionalInt.empty() : OptionalInt.of(password);
    }

    void addPassword(String userId, int password) { expiringMap.put(userId, password); }
}
