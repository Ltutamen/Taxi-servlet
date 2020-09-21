package ua.axiom.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class MapBuilder<K, V>  {
    private static class Pair<K, V> {
        public K key;
        public V val;

        public Pair(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private final List<Pair<K, V>> pairs;

    private final Supplier<? extends Map<K, V>> supplier;

    public MapBuilder() {
        this(HashMap::new);
    }

    public MapBuilder(Supplier<? extends Map<K, V>> mapProvider) {
        this.supplier = mapProvider;
        pairs = new ArrayList<>();
    }

    public  MapBuilder<K, V> addPair(K key, V value) {
        pairs.add(new Pair<>(key, value));
        return this;
    }

    public Map<K, V> build() {
        Map<K, V> result = supplier.get();

        for (Pair<K, V> pair : pairs) {
            result.put(pair.key, pair.val);
        }

        return result;
    }
}
