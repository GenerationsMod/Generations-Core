package generations.gg.generations.core.generationscore.world.feature;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GenerationsPlacedFeatures {
    public static void init(){}
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_UPPER = registerKey("ore_aluminum_upper");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_MIDDLE = registerKey("ore_aluminum_middle");
    public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_LOWER = registerKey("ore_aluminum_lower");

    public static void bootStrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, ORE_ALUMINUM_UPPER, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_ALUMINUM), GenerationsOrePlacements.modifiersWithCount(90, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        register(context, ORE_ALUMINUM_MIDDLE, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_ALUMINUM), GenerationsOrePlacements.modifiersWithCount(90, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        register(context, ORE_ALUMINUM_LOWER, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_ALUMINUM_SMALL), GenerationsOrePlacements.modifiersWithCount(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, GenerationsCore.id(name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
