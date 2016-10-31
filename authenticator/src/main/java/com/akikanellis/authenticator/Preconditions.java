package com.akikanellis.authenticator;

final class Preconditions {

    private Preconditions() { throw new AssertionError("No instances."); }

    static String checkNotNullOrEmpty(String s) {
        checkNotNull(s);

        if (s.isEmpty()) throw new IllegalArgumentException("String can't be empty.");

        return s;
    }

    static <T> T checkNotNull(T reference) {
        if (reference == null) throw new NullPointerException();

        return reference;
    }
}
