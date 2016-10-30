package com.akikanellis.authenticator;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class ExpiringMap<K, V> implements Map<K, V> {
    private final Map<K, V> map;

    ExpiringMap() { this.map = new ConcurrentHashMap<>(); }

    @Override public int size() { return map.size(); }

    @Override public boolean isEmpty() { return map.isEmpty(); }

    @Override public boolean containsKey(Object key) { return map.containsKey(key); }

    @Override public boolean containsValue(Object value) { return map.containsValue(value); }

    @Override public V get(Object key) { return map.get(key); }

    @Override public V put(K key, V value) { return map.put(key, value); }

    @Override public V remove(Object key) { return map.remove(key); }

    @Override public void putAll(Map<? extends K, ? extends V> otherMap) { map.putAll(otherMap); }

    @Override public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
