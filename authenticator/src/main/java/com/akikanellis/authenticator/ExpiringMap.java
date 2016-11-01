package com.akikanellis.authenticator;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A Map that expires its entries after a give amount of time by using a
 * {@link java.util.concurrent.ScheduledExecutorService}.
 * <p>
 * This map is thread-safe.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
class ExpiringMap<K, V> implements Map<K, V> {
    private final Map<K, V> map;
    private final ScheduledExecutorService expirer;
    private final long expiringDelay;
    private final TimeUnit timeUnit;

    ExpiringMap(ScheduledExecutorService expirer, long expiringDelay, TimeUnit timeUnit) {
        this.map = new ConcurrentHashMap<>();
        this.expirer = expirer;
        this.expiringDelay = expiringDelay;
        this.timeUnit = timeUnit;
    }

    @Override public int size() { return map.size(); }

    @Override public boolean isEmpty() { return map.isEmpty(); }

    @Override public boolean containsKey(Object key) { return map.containsKey(key); }

    @Override public boolean containsValue(Object value) { return map.containsValue(value); }

    @Override public V get(Object key) { return map.get(key); }

    /**
     * Associates the specified value with the specified key in this map. If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.
     * <p>
     * After putting it, it schedules the entry to be removed after the specified amount of time.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for the key
     * @throws ClassCastException            if the class of the specified key or value prevents it from being stored in
     *                                       this map
     */
    @Override public V put(K key, V value) {
        V previousValue = map.put(key, value);
        expirer.schedule((Runnable) () -> map.remove(key), expiringDelay, timeUnit);

        return previousValue;
    }

    @Override public V remove(Object key) { return map.remove(key); }

    @Override public void putAll(Map<? extends K, ? extends V> otherMap) { map.putAll(otherMap); }

    @Override public void clear() { map.clear(); }

    @Override public Set<K> keySet() { return map.keySet(); }

    @Override public Collection<V> values() { return map.values(); }

    @Override public Set<Entry<K, V>> entrySet() { return map.entrySet(); }
}
