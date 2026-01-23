package generations.gg.generations.core.generationscore.fabric

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsFeatures
import generations.gg.generations.core.generationscore.common.world.feature.PlatformFeatureRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import java.util.function.Supplier

object FabricPlatformFeatureRegistry : PlatformFeatureRegistry {
    override fun <T> register(registry: Registry<in T>, name: String, value: Supplier<T>): Supplier<T> {
        val id = ResourceLocation.fromNamespaceAndPath(GenerationsCore.MOD_ID, name)
        val registered = Registry.register(registry, id, value.get())
        return Supplier { registered }
    }

    override fun <T> registerForHolder(registry: Registry<T>, name: String, value: Supplier<T>): Supplier<Holder.Reference<T>> {
        val id = ResourceLocation.fromNamespaceAndPath(GenerationsCore.MOD_ID, name)
        val registered = Registry.registerForHolder(registry, id, value.get())
        return Supplier { registered }
    }

    override fun registerParticle(name: String): Supplier<SimpleParticleType> {
        val particle = Registry.register(BuiltInRegistries.PARTICLE_TYPE, GenerationsCore.id(name), FabricParticleTypes.simple())
        return Supplier { particle }
    }
}
