package com.akikanellis.authenticator;

final class Preconditions {

    private Preconditions() { throw new AssertionError("No instances."); }

    static String checkNotNullOrEmpty(String s) {
        if (s == null) throw new IllegalArgumentException("String is null.");
        if (s.isEmpty()) throw new IllegalArgumentException("String is empty.");

        return s;
    }
}
