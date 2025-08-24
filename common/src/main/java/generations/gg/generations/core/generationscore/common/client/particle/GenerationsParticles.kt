package generations.gg.generations.core.generationscore.common.client.particle

import generations.gg.generations.core.generationscore.common.world.feature.PlatformFeatureRegistry
import net.minecraft.core.particles.SimpleParticleType
import java.util.function.Supplier

object GenerationsParticles {
    fun init() {}

    var TERASTAL_CAVES_PARTICLE = register("terastal_caves_particle")

    private fun register(id: String): Supplier<SimpleParticleType> {
        return PlatformFeatureRegistry.INSTANCE.registerParticle(id)
    }
}