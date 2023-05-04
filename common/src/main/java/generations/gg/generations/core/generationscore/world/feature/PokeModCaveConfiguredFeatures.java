package generations.gg.generations.core.generationscore.world.feature;

import com.google.common.collect.ImmutableList;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class PokeModCaveConfiguredFeatures {
    /* All cave biome ConfiguredFeatures */
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHARGESTONE_PATCH = register("chargestone_patch");

    private static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, GenerationsCore.id(name));
    }

    public static void onInitialize(BootstapContext<ConfiguredFeature<?,?>> context) {
        context.register(CHARGESTONE_PATCH, new ConfiguredFeature<>(
                PokeModCaveFeatures.NOISY_SPHERE_REPLACE,
                new NoisySphereReplaceConfig(
                        ImmutableList.of(Blocks.STONE, Blocks.DEEPSLATE),
                        GenerationsBlocks.CHARGE_STONE.get().defaultBlockState(),
                        6, 10)));
    }
}
