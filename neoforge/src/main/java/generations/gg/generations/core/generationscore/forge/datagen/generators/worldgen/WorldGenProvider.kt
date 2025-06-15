package generations.gg.generations.core.generationscore.forge.datagen.generators.worldgen

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsConfiguredFeatures
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsPlacedFeatures
import generations.gg.generations.core.generationscore.forge.worldgen.GenerationsForgeBiomemodifiers
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.common.world.BiomeModifier
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.Set
import java.util.concurrent.CompletableFuture

class WorldGenProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    DatapackBuiltinEntriesProvider(output, registries, BUILDER, Set.of<String>(GenerationsCore.MOD_ID)) {
    companion object {
        private val BUILDER: RegistrySetBuilder = RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, GenerationsConfiguredFeatures::bootStrap)
            .add(Registries.PLACED_FEATURE, GenerationsPlacedFeatures::bootStrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, GenerationsForgeBiomemodifiers::bootstrap)
    }
}
