package generations.gg.generations.core.generationscore.common.util;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TimedCache<K, V> {
    private final Map<K, CacheEntry<V>> cache = new HashMap<>();
    private final long expiryTicks; // time in Minecraft ticks
    private long currentTick = 0; // track current tick
    private final BiConsumer<K, V> onExpire;
    private final Function<K, CacheEntry<V>> supplier;

    public TimedCache(Duration expiryDuration, BiConsumer<K, V> onExpire, Function<K, V> supplier) {
        this.expiryTicks = expiryDuration.toMillis() / 50;
        this.onExpire = onExpire;
        this.supplier = supplier.andThen(v -> new CacheEntry<>(v, currentTick));
    }

    public V get(K key) {
        CacheEntry<V> entry = cache.computeIfAbsent(key, supplier);

        if(entry == null) return null;

        entry.accessTick = currentTick;
        return entry.value;
    }

    public void clear() {
        Iterator<Map.Entry<K, CacheEntry<V>>> iterator = cache.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<K, CacheEntry<V>> entry = iterator.next();
            onExpire.accept(entry.getKey(), entry.getValue().value);
            iterator.remove();
        }
    }

    public void tick() {
        currentTick++;

        if(currentTick % expiryTicks == 0) {

            Iterator<Map.Entry<K, CacheEntry<V>>> iterator = cache.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<K, CacheEntry<V>> entry = iterator.next();

                if (currentTick - entry.getValue().accessTick >= expiryTicks) {

                    // Call onExpire before removing the entry
                    onExpire.accept(entry.getKey(), entry.getValue().value);
                    iterator.remove();
                }
            }
        }
    }

    private static class CacheEntry<V> {
        V value;
        long accessTick;

        CacheEntry(V value, long accessTick) {
            this.value = value;
            this.accessTick = accessTick;
        }
    }
}