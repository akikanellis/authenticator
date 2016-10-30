package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiringMapTest {
    private ExpiringMap<String, Integer> expiringMap;

    @Before public void beforeEach() { expiringMap = new ExpiringMap<>(); }

    @Test public void interfaceOfExpiringMap_isMap() {
        assertThat(expiringMap).isInstanceOf(Map.class);
    }

    @Test public void addingElements_addsToInternalMap() {
        expiringMap.put("key", 1);

        assertThat(expiringMap.get("key")).isEqualTo(1);
    }
}