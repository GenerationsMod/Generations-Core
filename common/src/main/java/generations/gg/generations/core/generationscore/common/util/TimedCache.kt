package generations.gg.generations.core.generationscore.common.util

import java.time.Duration
import java.util.function.BiConsumer
import java.util.function.Function

class TimedCache<K, V>(expiryDuration: Duration, private val onExpire: BiConsumer<K, V>, supplier: Function<K, V>) {
    private val cache: MutableMap<K, CacheEntry<V>> = HashMap()
    private val expiryTicks = expiryDuration.toMillis() / 50 // time in Minecraft ticks
    private var currentTick: Long = 0 // track current tick
    private val supplier: Function<K, CacheEntry<V>>

    init {
        this.supplier = supplier.andThen { v: V ->
            CacheEntry(
                v,
                currentTick
            )
        }
    }

    fun get(key: K): V? {
        val entry = cache.computeIfAbsent(key, supplier)
            ?: return null

        entry.accessTick = currentTick
        return entry.value
    }

    fun clear() {
        val iterator: MutableIterator<Map.Entry<K, CacheEntry<V>>> = cache.entries.iterator()

        while (iterator.hasNext()) {
            val entry = iterator.next()
            onExpire.accept(entry.key, entry.value.value)
            iterator.remove()
        }
    }

    fun tick() {
        currentTick++

        if (currentTick % expiryTicks == 0L) {
            val iterator: MutableIterator<Map.Entry<K, CacheEntry<V>>> = cache.entries.iterator()

            while (iterator.hasNext()) {
                val entry = iterator.next()

                if (currentTick - entry.value.accessTick >= expiryTicks) {
                    // Call onExpire before removing the entry

                    onExpire.accept(entry.key, entry.value.value)
                    iterator.remove()
                }
            }
        }
    }

    private class CacheEntry<V>(var value: V, var accessTick: Long)
}