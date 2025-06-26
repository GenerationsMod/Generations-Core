package generations.gg.generations.core.generationscore.common.util

import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import java.util.HashMap

abstract class PlatformRegistry<T>(registryKey: ResourceKey<Registry<T>>, registry: Registry<T>) {
    val register: EntryRegister<T> = GenerationsCore.implementation.entryRegister(registry, registryKey)

        protected val queue = mutableListOf<Holder<T>>()

        /**
         * Creates a new entry in this registry.
         *
         * @param E The type of the entry.
         * @param name The name of the entry, this will be an [ResourceLocation.path].
         * @param entry The entry being added.
         * @return The entry created.
         */
        open fun <V: T> create(name: String, entry: () -> V): Holder<V> = register.holder(name, entry).also { queue.add(it as Holder<T>) }

        /**
         * Handles the registration of this registry into the platform specific one.
         *
         * @param consumer The consumer that will handle the logic for registering every entry in this registry into the platform specific one.
         */
        open fun register() {
            this.queue
        }

    open fun allHolders(): Collection<Holder<T>> = this.queue.toList()


    /**
     * Returns a collection of every entry in this registry.
     *
     * @return The entries of this registry.
    */
    open fun all(): Collection<T> = allHolders().map { it.value() }.toList()

    open fun init() {}
}