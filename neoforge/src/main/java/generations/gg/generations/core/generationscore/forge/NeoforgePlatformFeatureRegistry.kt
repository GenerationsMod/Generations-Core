// NeoForgePlatformFeatureRegistry.kt (NeoForge source set)
package generations.gg.generations.core.generationscore.forge

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsFeatures
import generations.gg.generations.core.generationscore.common.world.feature.PlatformFeatureRegistry
import generations.gg.generations.core.generationscore.common.client.particle.GenerationsParticles
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries

object NeoForgePlatformFeatureRegistry : PlatformFeatureRegistry {
    private val cached = Reference2ObjectOpenHashMap<ResourceKey<*>, DeferredRegister<*>>()

    override fun <T> register(registry: Registry<in T>, name: String, value: Supplier<T>): Supplier<T> {
        @Suppress("UNCHECKED_CAST")
        val register = cached.computeIfAbsent(registry.key()) {
            DeferredRegister.create(registry.key(), GenerationsCore.MOD_ID)
        } as DeferredRegister<T>
        return register.register(name, value)
    }

    override fun <T> registerForHolder(registry: Registry<T>, name: String, value: Supplier<T>): Supplier<Holder.Reference<T>> {
        @Suppress("UNCHECKED_CAST")
        val register = cached.computeIfAbsent(registry.key()) {
            DeferredRegister.create(registry.key(), GenerationsCore.MOD_ID)
        } as DeferredRegister<T>

        val holder: DeferredHolder<T, T> = register.register(name, value)
        return Supplier { holder.delegate as Holder.Reference<T> }
    }

    override fun registerParticle(name: String): Supplier<SimpleParticleType> {
        return register(BuiltInRegistries.PARTICLE_TYPE, name, Supplier { SimpleParticleType(false) })
    }

    fun registerAll(bus: net.neoforged.bus.api.IEventBus) {
        cached.values.forEach { it.register(bus) }
    }

    init {
        PlatformFeatureRegistry.INSTANCE = NeoForgePlatformFeatureRegistry
        GenerationsFeatures.init()
        GenerationsParticles.init()
    }
}
