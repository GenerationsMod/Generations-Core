package generations.gg.generations.core.generationscore.forge.datagen.generators.worldgen;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.forge.worldgen.GenerationsForgeBiomemodifiers;
import generations.gg.generations.core.generationscore.world.feature.GenerationsConfiguredFeatures;
import generations.gg.generations.core.generationscore.world.feature.GenerationsPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, GenerationsConfiguredFeatures::bootStrap)
            .add(Registries.PLACED_FEATURE, GenerationsPlacedFeatures::bootStrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, GenerationsForgeBiomemodifiers::bootstrap);

    public WorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(GenerationsCore.MOD_ID));
    }
}
