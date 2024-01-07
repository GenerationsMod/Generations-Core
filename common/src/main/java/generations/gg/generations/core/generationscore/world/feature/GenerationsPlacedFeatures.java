package generations.gg.generations.core.generationscore.world.feature;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class GenerationsPlacedFeatures {
    public static void init(){}
    public static final ResourceKey<PlacedFeature> ORE_SILICON_UPPER = registerKey("ore_silicon_upper");
    public static final ResourceKey<PlacedFeature> ORE_SILICON_MIDDLE = registerKey("ore_silicon_middle");
    public static final ResourceKey<PlacedFeature> ORE_SILICON_LOWER = registerKey("ore_silicon_lower");

    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE = registerKey("ore_sapphire");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_BURIED = registerKey("ore_sapphire_buried");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_OVERWORLD_SMALL = registerKey("ore_sapphire_overworld_small");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_OVERWORLD_LARGE = registerKey("ore_sapphire_overworld_large");
    public static final ResourceKey<PlacedFeature> ORE_SAPPHIRE_OVERWORLD_BURIED = registerKey("ore_sapphire_overworld_buried");

    public static final ResourceKey<PlacedFeature> ORE_RUBY = registerKey("ore_ruby");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_BURIED = registerKey("ore_ruby_buried");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_OVERWORLD_SMALL = registerKey("ore_ruby_overworld_small");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_OVERWORLD_LARGE = registerKey("ore_ruby_overworld_large");
    public static final ResourceKey<PlacedFeature> ORE_RUBY_OVERWORLD_BURIED = registerKey("ore_ruby_overworld_buried");

    public static final ResourceKey<PlacedFeature> ORE_CRYSTAL = registerKey("ore_crystal");
    public static final ResourceKey<PlacedFeature> ORE_CRYSTAL_BURIED = registerKey("ore_crystal_buried");
    public static final ResourceKey<PlacedFeature> ORE_CRYSTAL_OVERWORLD_SMALL = registerKey("ore_crystal_overworld_small");
    public static final ResourceKey<PlacedFeature> ORE_CRYSTAL_OVERWORLD_LARGE = registerKey("ore_crystal_overworld_large");
    public static final ResourceKey<PlacedFeature> ORE_CRYSTAL_OVERWORLD_BURIED = registerKey("ore_crystal_overworld_buried");

    public static final ResourceKey<PlacedFeature> POKE_BALL_LOOT = registerKey("poke_ball_loot");
    public static final ResourceKey<PlacedFeature> BEAST_BALL_LOOT = registerKey("beast_ball_loot");
    public static final ResourceKey<PlacedFeature> CHERISH_BALL_LOOT = registerKey("cherish_ball_loot");
    public static final ResourceKey<PlacedFeature> DIVE_BALL_LOOT = registerKey("dive_ball_loot");
    public static final ResourceKey<PlacedFeature> DREAM_BALL_LOOT = registerKey("dream_ball_loot");
    public static final ResourceKey<PlacedFeature> DUSK_BALL_LOOT = registerKey("dusk_ball_loot");
    public static final ResourceKey<PlacedFeature> FAST_BALL_LOOT = registerKey("fast_ball_loot");
    public static final ResourceKey<PlacedFeature> FRIEND_BALL_LOOT = registerKey("friend_ball_loot");
    public static final ResourceKey<PlacedFeature> GIGATON_BALL_LOOT = registerKey("gigaton_ball_loot");
    public static final ResourceKey<PlacedFeature> GREAT_BALL_LOOT = registerKey("great_ball_loot");
    public static final ResourceKey<PlacedFeature> HEAL_BALL_LOOT = registerKey("heal_ball_loot");
    public static final ResourceKey<PlacedFeature> HEAVY_BALL_LOOT = registerKey("heavy_ball_loot");
    public static final ResourceKey<PlacedFeature> JET_BALL_LOOT = registerKey("jet_ball_loot");
    public static final ResourceKey<PlacedFeature> LEADEN_BALL_LOOT = registerKey("leaden_ball_loot");
    public static final ResourceKey<PlacedFeature> LEVEL_BALL_LOOT = registerKey("level_ball_loot");
    public static final ResourceKey<PlacedFeature> LOVE_BALL_LOOT = registerKey("love_ball_loot");
    public static final ResourceKey<PlacedFeature> LURE_BALL_LOOT = registerKey("lure_ball_loot");
    public static final ResourceKey<PlacedFeature> LUXURY_BALL_LOOT = registerKey("luxury_ball_loot");
    public static final ResourceKey<PlacedFeature> MASTER_BALL_LOOT = registerKey("master_ball_loot");
    public static final ResourceKey<PlacedFeature> MOON_BALL_LOOT = registerKey("moon_ball_loot");
    public static final ResourceKey<PlacedFeature> NEST_BALL_LOOT = registerKey("nest_ball_loot");
    public static final ResourceKey<PlacedFeature> NET_BALL_LOOT = registerKey("net_ball_loot");
    public static final ResourceKey<PlacedFeature> ORIGIN_BALL_LOOT = registerKey("origin_ball_loot");
    public static final ResourceKey<PlacedFeature> PARK_BALL_LOOT = registerKey("park_ball_loot");
    public static final ResourceKey<PlacedFeature> PREMIER_BALL_LOOT = registerKey("premier_ball_loot");
    public static final ResourceKey<PlacedFeature> QUICK_BALL_LOOT = registerKey("quick_ball_loot");
    public static final ResourceKey<PlacedFeature> REPEAT_BALL_LOOT = registerKey("repeat_ball_loot");
    public static final ResourceKey<PlacedFeature> SAFARI_BALL_LOOT = registerKey("safari_ball_loot");
    public static final ResourceKey<PlacedFeature> SPORT_BALL_LOOT = registerKey("sport_ball_loot");
    public static final ResourceKey<PlacedFeature> STRANGE_BALL_LOOT = registerKey("strange_ball_loot");
    public static final ResourceKey<PlacedFeature> TIMER_BALL_LOOT = registerKey("timer_ball_loot");
    public static final ResourceKey<PlacedFeature> ULTRA_BALL_LOOT = registerKey("ultra_ball_loot");
    public static final ResourceKey<PlacedFeature> WING_BALL_LOOT = registerKey("wing_ball_loot");

    public static void bootStrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ORE_SILICON_UPPER, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SILICON), GenerationsOrePlacements.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        register(context, ORE_SILICON_MIDDLE, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SILICON), GenerationsOrePlacements.commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        register(context, ORE_SILICON_LOWER, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SILICON_SMALL), GenerationsOrePlacements.commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));

        register(context, ORE_SAPPHIRE, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE), GenerationsOrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
        register(context, ORE_SAPPHIRE_BURIED, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE_SMALL), GenerationsOrePlacements.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));
        register(context, ORE_SAPPHIRE_OVERWORLD_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE_OVERWORLD_SMALL), GenerationsOrePlacements.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_SAPPHIRE_OVERWORLD_LARGE, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE_OVERWORLD_LARGE), GenerationsOrePlacements.rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_SAPPHIRE_OVERWORLD_BURIED, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE_OVERWORLD_SMALL), GenerationsOrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, ORE_RUBY, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY), GenerationsOrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
        register(context, ORE_RUBY_BURIED, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY_SMALL), GenerationsOrePlacements.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));
        register(context, ORE_RUBY_OVERWORLD_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY_OVERWORLD_SMALL), GenerationsOrePlacements.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_RUBY_OVERWORLD_LARGE, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY_OVERWORLD_LARGE), GenerationsOrePlacements.rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_RUBY_OVERWORLD_BURIED, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY_OVERWORLD_SMALL), GenerationsOrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        
        register(context, ORE_CRYSTAL, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL), GenerationsOrePlacements.commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
        register(context, ORE_CRYSTAL_BURIED, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL_SMALL), GenerationsOrePlacements.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64))));
        register(context, ORE_CRYSTAL_OVERWORLD_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL_OVERWORLD_SMALL), GenerationsOrePlacements.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_CRYSTAL_OVERWORLD_LARGE, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL_OVERWORLD_LARGE), GenerationsOrePlacements.rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_CRYSTAL_OVERWORLD_BURIED, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL_OVERWORLD_SMALL), GenerationsOrePlacements.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        
        register(context, POKE_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.POKE_BALL_LOOT), worldSurfaceSquaredWithChance(250));
        register(context, BEAST_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.BEAST_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, CHERISH_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.CHERISH_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, DIVE_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.DIVE_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, DREAM_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.DREAM_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, DUSK_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.DUSK_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, FAST_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.FAST_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, FRIEND_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.FRIEND_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, GIGATON_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.GIGATON_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, GREAT_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.GREAT_BALL_LOOT), worldSurfaceSquaredWithChance(350));
        register(context, HEAL_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.HEAL_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, HEAVY_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.HEAVY_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, JET_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.JET_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, LEADEN_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LEADEN_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, LEVEL_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LEVEL_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, LOVE_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LOVE_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, LURE_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LURE_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, LUXURY_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LUXURY_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, MASTER_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.MASTER_BALL_LOOT), worldSurfaceSquaredWithChance(1000));
        register(context, MOON_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.MOON_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, NEST_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.NEST_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, NET_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.NET_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, ORIGIN_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORIGIN_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, PARK_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.PARK_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, PREMIER_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.PREMIER_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, QUICK_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.QUICK_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, REPEAT_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.REPEAT_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, SAFARI_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.SAFARI_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, SPORT_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.SPORT_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, STRANGE_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.STRANGE_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, TIMER_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.TIMER_BALL_LOOT), worldSurfaceSquaredWithChance(200));
        register(context, ULTRA_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ULTRA_BALL_LOOT), worldSurfaceSquaredWithChance(500));
        register(context, WING_BALL_LOOT, configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.WING_BALL_LOOT), worldSurfaceSquaredWithChance(200));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, GenerationsCore.id(name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static List<PlacementModifier> worldSurfaceSquaredWithChance(int chance) {
        return List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }
}
