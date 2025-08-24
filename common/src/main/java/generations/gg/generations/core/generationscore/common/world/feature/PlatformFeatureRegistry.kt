package generations.gg.generations.core.generationscore.common.world.feature

import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.particles.SimpleParticleType
import java.util.function.Supplier

interface PlatformFeatureRegistry {
    fun <T> register(registry: Registry<in T>, name: String, value: Supplier<T>): Supplier<T>
    fun <T> registerForHolder(registry: Registry<T>, name: String, value: Supplier<T>): Supplier<Holder.Reference<T>>
    fun registerParticle(name: String): Supplier<SimpleParticleType>

    companion object {
        lateinit var INSTANCE: PlatformFeatureRegistry
    }
}