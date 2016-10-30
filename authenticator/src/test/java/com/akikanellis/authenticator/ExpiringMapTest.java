package com.akikanellis.authenticator;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiringMapTest {
    private ExpiringMap<String, Integer> expiringMap;

    @Before public void beforeEach() { expiringMap = new ExpiringMap<>(); }

    @Test public void interfaceOfExpiringMap_isMap() { assertThat(expiringMap).isInstanceOf(Map.class); }

    @Test public void addingElements_addsToMap() {
        putSingleEntry();

        assertThat(expiringMap.get("key")).isEqualTo(1);
    }

    @Test public void gettingElement_withElementMissing_returnsNull() { assertThat(expiringMap.get("key")).isNull(); }

    @Test public void size_withElements_returnsCorrectSize() {
        putThreeEntries();

        assertThat(expiringMap.size()).isEqualTo(3);
    }

    @Test public void size_withNoElements_returnsZero() { assertThat(expiringMap.size()).isZero(); }

    @Test public void isEmpty_whenEmpty_returnsTrue() { assertThat(expiringMap.isEmpty()).isTrue(); }

    @Test public void isEmpty_whenNotEmpty_returnsFalse() {
        putSingleEntry();

        assertThat(expiringMap.isEmpty()).isFalse();
    }

    @Test public void containsKey_withKeyPresent_returnsTrue() {
        putSingleEntry();

        assertThat(expiringMap.containsKey("key")).isTrue();
    }

    @Test public void containsKey_withKeyMissing_returnsFalse() {
        assertThat(expiringMap.containsKey("key")).isFalse();
    }

    @Test public void containsValue_withValuePresent_returnsTrue() {
        putSingleEntry();

        assertThat(expiringMap.containsValue(1)).isTrue();
    }

    @Test public void containsKey_withValueMissing_returnsFalse() {
        assertThat(expiringMap.containsValue(1)).isFalse();
    }

    @Test public void remove_withKeyPresent_returnsValue() {
        putSingleEntry();

        assertThat(expiringMap.remove("key")).isEqualTo(1);
    }

    private void putSingleEntry() {expiringMap.put("key", 1);}

    @Test public void remove_withKeyMissing_returnsNull() { assertThat(expiringMap.remove("key")).isNull(); }

    @Test public void addingAllElements_withElementsPresent_addsThemAll() {
        Map<String, Integer> otherMap = new HashMap<>();
        otherMap.put("key1", 1);
        otherMap.put("key2", 2);
        otherMap.put("key3", 3);

        expiringMap.putAll(otherMap);

        assertThat(expiringMap).containsAllEntriesOf(otherMap);
    }

    @Test public void addingAllElements_withNoElements_doesNothing() {
        Map<String, Integer> otherMap = new HashMap<>();

        expiringMap.putAll(otherMap);

        assertThat(expiringMap).isEmpty();
    }

    @Test public void clear_removesAllElements(){
        putThreeEntries();

        expiringMap.clear();

        assertThat(expiringMap).isEmpty();
    }

    @Test public void gettingKeySet_returnsASetOfAllKeys(){
        putThreeEntries();

        assertThat(expiringMap.keySet()).containsExactly("key1", "key2", "key3");
    }

    @Test public void gettingValues_returnsACollectionOfAllValues(){
        putThreeEntries();

        assertThat(expiringMap.values()).containsExactly(1, 2, 3);
    }

    @Test public void gettingEntrySet_returnsASetOfAllEntries(){
        putThreeEntries();

        //noinspection unchecked
        assertThat(expiringMap.entrySet()).containsExactly(
                new SimpleEntry<>("key1", 1),
                new SimpleEntry<>("key2", 2),
                new SimpleEntry<>("key3", 3)
        );
    }

    private void putThreeEntries() {
        expiringMap.put("key1", 1);
        expiringMap.put("key2", 2);
        expiringMap.put("key3", 3);
    }
}