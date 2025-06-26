package generations.gg.generations.core.generationscore.common.world.feature

import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*

object GenerationsPlacedFeatures {
    fun init() {}
    @JvmField
    val ORE_SILICON_UPPER: ResourceKey<PlacedFeature> = registerKey("ore_silicon_upper")
    @JvmField
    val ORE_SILICON_MIDDLE: ResourceKey<PlacedFeature> = registerKey("ore_silicon_middle")
    @JvmField
    val ORE_SILICON_LOWER: ResourceKey<PlacedFeature> = registerKey("ore_silicon_lower")

    @JvmField
    val ORE_SAPPHIRE: ResourceKey<PlacedFeature> = registerKey("ore_sapphire")
    @JvmField
    val ORE_SAPPHIRE_BURIED: ResourceKey<PlacedFeature> = registerKey("ore_sapphire_buried")
    @JvmField
    val ORE_SAPPHIRE_OVERWORLD_SMALL: ResourceKey<PlacedFeature> = registerKey("ore_sapphire_overworld_small")
    @JvmField
    val ORE_SAPPHIRE_OVERWORLD_LARGE: ResourceKey<PlacedFeature> = registerKey("ore_sapphire_overworld_large")
    @JvmField
    val ORE_SAPPHIRE_OVERWORLD_BURIED: ResourceKey<PlacedFeature> = registerKey("ore_sapphire_overworld_buried")

    @JvmField
    val ORE_RUBY: ResourceKey<PlacedFeature> = registerKey("ore_ruby")
    @JvmField
    val ORE_RUBY_BURIED: ResourceKey<PlacedFeature> = registerKey("ore_ruby_buried")
    @JvmField
    val ORE_RUBY_OVERWORLD_SMALL: ResourceKey<PlacedFeature> = registerKey("ore_ruby_overworld_small")
    @JvmField
    val ORE_RUBY_OVERWORLD_LARGE: ResourceKey<PlacedFeature> = registerKey("ore_ruby_overworld_large")
    @JvmField
    val ORE_RUBY_OVERWORLD_BURIED: ResourceKey<PlacedFeature> = registerKey("ore_ruby_overworld_buried")

    @JvmField
    val ORE_CRYSTAL: ResourceKey<PlacedFeature> = registerKey("ore_crystal")
    @JvmField
    val ORE_CRYSTAL_BURIED: ResourceKey<PlacedFeature> = registerKey("ore_crystal_buried")
    @JvmField
    val ORE_CRYSTAL_OVERWORLD_SMALL: ResourceKey<PlacedFeature> = registerKey("ore_crystal_overworld_small")
    @JvmField
    val ORE_CRYSTAL_OVERWORLD_LARGE: ResourceKey<PlacedFeature> = registerKey("ore_crystal_overworld_large")
    @JvmField
    val ORE_CRYSTAL_OVERWORLD_BURIED: ResourceKey<PlacedFeature> = registerKey("ore_crystal_overworld_buried")

    @JvmField
    val ORE_MEGASTONE: ResourceKey<PlacedFeature> = registerKey("ore_megastone")
    @JvmField
    val ORE_Z_CRYSTAL: ResourceKey<PlacedFeature> = registerKey("ore_z_crystal")
    @JvmField
    val ORE_METEORITE: ResourceKey<PlacedFeature> = registerKey("ore_meteorite")

    @JvmField
    val POKE_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("poke_ball_loot")
    @JvmField
    val BEAST_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("beast_ball_loot")
    @JvmField
    val CHERISH_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("cherish_ball_loot")
    @JvmField
    val DIVE_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("dive_ball_loot")
    @JvmField
    val DREAM_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("dream_ball_loot")
    @JvmField
    val DUSK_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("dusk_ball_loot")
    @JvmField
    val FAST_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("fast_ball_loot")
    @JvmField
    val FRIEND_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("friend_ball_loot")
    @JvmField
    val GIGATON_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("gigaton_ball_loot")
    @JvmField
    val GREAT_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("great_ball_loot")
    @JvmField
    val HEAL_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("heal_ball_loot")
    @JvmField
    val HEAVY_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("heavy_ball_loot")
    @JvmField
    val JET_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("jet_ball_loot")
    @JvmField
    val LEADEN_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("leaden_ball_loot")
    @JvmField
    val LEVEL_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("level_ball_loot")
    @JvmField
    val LOVE_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("love_ball_loot")
    @JvmField
    val LURE_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("lure_ball_loot")
    @JvmField
    val LUXURY_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("luxury_ball_loot")
    @JvmField
    val MASTER_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("master_ball_loot")
    @JvmField
    val MOON_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("moon_ball_loot")
    @JvmField
    val NEST_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("nest_ball_loot")
    @JvmField
    val NET_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("net_ball_loot")
    @JvmField
    val ORIGIN_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("origin_ball_loot")
    @JvmField
    val PARK_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("park_ball_loot")
    @JvmField
    val PREMIER_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("premier_ball_loot")
    @JvmField
    val QUICK_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("quick_ball_loot")
    @JvmField
    val REPEAT_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("repeat_ball_loot")
    @JvmField
    val SAFARI_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("safari_ball_loot")
    @JvmField
    val SPORT_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("sport_ball_loot")
    @JvmField
    val STRANGE_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("strange_ball_loot")
    @JvmField
    val TIMER_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("timer_ball_loot")
    @JvmField
    val ULTRA_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("ultra_ball_loot")
    @JvmField
    val WING_BALL_LOOT: ResourceKey<PlacedFeature> = registerKey("wing_ball_loot")

    fun bootStrap(context: BootstrapContext<PlacedFeature>) {
        val configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE)

        register(
            context,
            ORE_SILICON_UPPER,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SILICON),
            GenerationsOrePlacements.commonOrePlacement(
                20, HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(80), VerticalAnchor.absolute(384)
                )
            )
        )
        register(
            context,
            ORE_SILICON_MIDDLE,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SILICON),
            GenerationsOrePlacements.commonOrePlacement(
                20, HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)
                )
            )
        )
        register(
            context,
            ORE_SILICON_LOWER,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SILICON_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                4, HeightRangePlacement.uniform(
                    VerticalAnchor.bottom(), VerticalAnchor.absolute(72)
                )
            )
        )

        register(
            context,
            ORE_SAPPHIRE,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE),
            GenerationsOrePlacements.commonOrePlacement(
                4, HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)
                )
            )
        )
        register(
            context,
            ORE_SAPPHIRE_BURIED,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                8, HeightRangePlacement.uniform(
                    VerticalAnchor.bottom(), VerticalAnchor.absolute(64)
                )
            )
        )
        register(
            context,
            ORE_SAPPHIRE_OVERWORLD_SMALL,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE_OVERWORLD_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                7, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )
        register(
            context,
            ORE_SAPPHIRE_OVERWORLD_LARGE,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE_OVERWORLD_LARGE),
            GenerationsOrePlacements.rareOrePlacement(
                9, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )
        register(
            context,
            ORE_SAPPHIRE_OVERWORLD_BURIED,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_SAPPHIRE_OVERWORLD_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                4, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )

        register(
            context,
            ORE_RUBY,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY),
            GenerationsOrePlacements.commonOrePlacement(
                4, HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)
                )
            )
        )
        register(
            context,
            ORE_RUBY_BURIED,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                8, HeightRangePlacement.uniform(
                    VerticalAnchor.bottom(), VerticalAnchor.absolute(64)
                )
            )
        )
        register(
            context,
            ORE_RUBY_OVERWORLD_SMALL,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY_OVERWORLD_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                7, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )
        register(
            context,
            ORE_RUBY_OVERWORLD_LARGE,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY_OVERWORLD_LARGE),
            GenerationsOrePlacements.rareOrePlacement(
                9, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )
        register(
            context,
            ORE_RUBY_OVERWORLD_BURIED,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_RUBY_OVERWORLD_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                4, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )

        register(
            context,
            ORE_CRYSTAL,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL),
            GenerationsOrePlacements.commonOrePlacement(
                5, HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32)
                )
            )
        )
        register(
            context,
            ORE_CRYSTAL_BURIED,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                9, HeightRangePlacement.uniform(
                    VerticalAnchor.bottom(), VerticalAnchor.absolute(64)
                )
            )
        )
        register(
            context,
            ORE_CRYSTAL_OVERWORLD_SMALL,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL_OVERWORLD_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                7, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )
        register(
            context,
            ORE_CRYSTAL_OVERWORLD_LARGE,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL_OVERWORLD_LARGE),
            GenerationsOrePlacements.rareOrePlacement(
                9, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )
        register(
            context,
            ORE_CRYSTAL_OVERWORLD_BURIED,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_CRYSTAL_OVERWORLD_SMALL),
            GenerationsOrePlacements.commonOrePlacement(
                4, HeightRangePlacement.triangle(
                    VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)
                )
            )
        )

        register(
            context,
            ORE_MEGASTONE,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_MEGASTONE),
            GenerationsOrePlacements.rareOrePlacement(
                20, HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0)
                )
            )
        )
        register(
            context,
            ORE_Z_CRYSTAL,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_Z_CRYSTAL),
            GenerationsOrePlacements.rareOrePlacement(
                20, HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0)
                )
            )
        )
        register(
            context,
            ORE_METEORITE,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORE_METEORITE),
            GenerationsOrePlacements.rareOrePlacement(
                20, HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0)
                )
            )
        )

        register(
            context,
            POKE_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.POKE_BALL_LOOT),
            oceanFloorSquaredWithChance(250)
        )
        register(
            context,
            BEAST_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.BEAST_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            CHERISH_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.CHERISH_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            DIVE_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.DIVE_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            DREAM_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.DREAM_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            DUSK_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.DUSK_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            FAST_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.FAST_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            FRIEND_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.FRIEND_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            GIGATON_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.GIGATON_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            GREAT_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.GREAT_BALL_LOOT),
            oceanFloorSquaredWithChance(350)
        )
        register(
            context,
            HEAL_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.HEAL_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            HEAVY_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.HEAVY_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            JET_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.JET_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            LEADEN_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LEADEN_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            LEVEL_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LEVEL_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            LOVE_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LOVE_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            LURE_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LURE_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            LUXURY_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.LUXURY_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            MASTER_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.MASTER_BALL_LOOT),
            oceanFloorSquaredWithChance(1000)
        )
        register(
            context,
            MOON_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.MOON_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            NEST_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.NEST_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            NET_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.NET_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            ORIGIN_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ORIGIN_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            PARK_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.PARK_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            PREMIER_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.PREMIER_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            QUICK_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.QUICK_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            REPEAT_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.REPEAT_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            SAFARI_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.SAFARI_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            SPORT_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.SPORT_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            STRANGE_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.STRANGE_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            TIMER_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.TIMER_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
        register(
            context,
            ULTRA_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.ULTRA_BALL_LOOT),
            oceanFloorSquaredWithChance(500)
        )
        register(
            context,
            WING_BALL_LOOT,
            configuredFeatureRegistryEntryLookup.getOrThrow(GenerationsConfiguredFeatures.WING_BALL_LOOT),
            oceanFloorSquaredWithChance(200)
        )
    }

    private fun registerKey(name: String): ResourceKey<PlacedFeature> {
        return ResourceKey.create(Registries.PLACED_FEATURE, id(name))
    }

    private fun register(
        context: BootstrapContext<PlacedFeature>,
        key: ResourceKey<PlacedFeature>,
        configuration: Holder<ConfiguredFeature<*, *>>,
        modifiers: List<PlacementModifier>
    ) {
        context.register(key, PlacedFeature(configuration, java.util.List.copyOf(modifiers)))
    }

    private fun worldSurfaceSquaredWithChance(chance: Int): List<PlacementModifier> {
        return java.util.List.of(
            RarityFilter.onAverageOnceEvery(chance),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()
        )
    }

    private fun oceanFloorSquaredWithChance(chance: Int): List<PlacementModifier> {
        return java.util.List.of(
            RarityFilter.onAverageOnceEvery(chance),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BiomeFilter.biome()
        )
    }
}
