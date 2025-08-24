package generations.gg.generations.core.generationscore.common.world.biome

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsPlacedFeatures
import generations.gg.generations.core.generationscore.common.client.particle.GenerationsParticles
import net.minecraft.core.HolderGetter
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Carvers
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.AmbientMoodSettings
import net.minecraft.world.level.biome.AmbientParticleSettings
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep

object GenerationsBiomes {
    val TERASTAL_CAVES = ResourceKey.create(Registries.BIOME,
        ResourceLocation.fromNamespaceAndPath(GenerationsCore.MOD_ID, "terastal_caves"))

    fun bootstrap(context: BootstrapContext<Biome>) {
        context.register(TERASTAL_CAVES, terastalCaves(context))
    }

    fun terastalCaves(context: BootstrapContext<Biome>): Biome {
        val spawnBuilder = MobSpawnSettings.Builder()

        val placedFeatures = context.lookup(Registries.PLACED_FEATURE)
        val configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER)

        val particleGetter: HolderGetter<ParticleType<*>> = context.lookup(Registries.PARTICLE_TYPE)

        val teraParticleKey: ResourceKey<ParticleType<*>> = ResourceKey.create(
            Registries.PARTICLE_TYPE,
            ResourceLocation.fromNamespaceAndPath(GenerationsCore.MOD_ID, "terastal_caves_particle")
        )
        val teraSimple: SimpleParticleType =
            particleGetter.getOrThrow(teraParticleKey).value() as SimpleParticleType

        val biomeBuilder = BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers)

        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE)
        biomeBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND)

        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GenerationsPlacedFeatures.LARGE_TERA_CRYSTAL)
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GenerationsPlacedFeatures.TERA_CRYSTAL_CLUSTER)

        return Biome.BiomeBuilder()
            .hasPrecipitation(false)
            .temperature(0.7f)
            .downfall(0.0f)
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biomeBuilder.build())
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(0xa9f2ff)
                    .waterFogColor(0xbdfbdb)
                    .skyColor(0x254089)
                    .grassColorOverride(0x90e48c)
                    .foliageColorOverride(0x90e48c)
                    .fogColor(0xa0dcf6)
                    .ambientMoodSound(AmbientMoodSettings(
                        net.minecraft.sounds.SoundEvents.AMBIENT_CAVE, 6000, 8, 2.0
                    ))
                    .ambientParticle(AmbientParticleSettings(GenerationsParticles.TERASTAL_CAVES_PARTICLE.get(), 0.002F))
                    .build()
            )
            .build()
    }
}