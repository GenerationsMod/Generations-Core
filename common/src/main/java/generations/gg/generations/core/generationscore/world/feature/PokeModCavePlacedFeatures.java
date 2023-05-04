package generations.gg.generations.core.generationscore.world.feature;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PokeModCavePlacedFeatures {
    public static final ResourceKey<PlacedFeature> CHARGESTONE_PATCH = register("chargestone_patch");

    public static final ResourceKey<PlacedFeature> CHARGESTONE_PATCH_2 = register("chargestone_patch_2");

    private static ResourceKey<PlacedFeature> register(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, GenerationsCore.id(name));
    }

    public static void onInitialize(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> lookup = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(CHARGESTONE_PATCH, new PlacedFeature(
                lookup.getOrThrow(PokeModCaveConfiguredFeatures.CHARGESTONE_PATCH),
                List.of(
                        CountPlacement.of(250),
                        InSquarePlacement.spread(),
                        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                        EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(),
                                BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                        RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                        BiomeFilter.biome())));
        context.register(CHARGESTONE_PATCH_2,
                new PlacedFeature(
                        lookup.getOrThrow(PokeModCaveConfiguredFeatures.CHARGESTONE_PATCH),
                        List.of(
                                CountPlacement.of(250),
                                InSquarePlacement.spread(),
                                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(),
                                        BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
                                RandomOffsetPlacement.vertical(ConstantInt.of(1)),
                                BiomeFilter.biome())));
    }
}
