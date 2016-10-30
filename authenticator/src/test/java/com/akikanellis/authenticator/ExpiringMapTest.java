package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiringMapTest {
    private ExpiringMap<String, Integer> expiringMap;

    @Before public void beforeEach() { expiringMap = new ExpiringMap<>(); }

    @Test public void interfaceOfExpiringMap_isMap() { assertThat(expiringMap).isInstanceOf(Map.class); }

    @Test public void addingElements_addsToMap() {
        expiringMap.put("key", 1);

        assertThat(expiringMap.get("key")).isEqualTo(1);
    }

    @Test public void gettingElement_withElementMissing_returnsNull() {
        assertThat(expiringMap.get("key")).isNull();
    }

    @Test public void size_returnsCorrectSize() {
        expiringMap.put("key1", 1);
        expiringMap.put("key2", 2);
        expiringMap.put("key3", 3);

        assertThat(expiringMap.size()).isEqualTo(3);
    }

    @Test public void isEmpty_whenEmpty_returnsTrue() { assertThat(expiringMap.isEmpty()).isTrue(); }

    @Test public void isEmpty_whenNotEmpty_returnsFalse() {
        expiringMap.put("key", 1);

        assertThat(expiringMap.isEmpty()).isFalse();
    }

    @Test public void containsKey_withKeyPresent_returnsTrue() {
        expiringMap.put("key", 1);

        assertThat(expiringMap.containsKey("key")).isTrue();
    }

    @Test public void containsKey_withKeyMissing_returnsFalse() {
        assertThat(expiringMap.containsKey("key")).isFalse();
    }

    @Test public void containsValue_withValuePresent_returnsTrue() {
        expiringMap.put("key", 1);

        assertThat(expiringMap.containsValue(1)).isTrue();
    }

    @Test public void containsKey_withValueMissing_returnsFalse() {
        assertThat(expiringMap.containsValue(1)).isFalse();
    }
}