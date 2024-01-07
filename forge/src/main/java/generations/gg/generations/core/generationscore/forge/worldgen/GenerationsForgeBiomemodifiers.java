package generations.gg.generations.core.generationscore.forge.worldgen;

import com.cobblemon.mod.common.api.tags.CobblemonBiomeTags;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.feature.GenerationsPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Joseph T. McQuigg
 */
public class GenerationsForgeBiomemodifiers {

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeaturesLookup = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomesLookup = context.lookup(Registries.BIOME);

        registerUnderGroundOres(context, "add_ore_silicon", HolderSet.direct(
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SILICON_UPPER),
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SILICON_MIDDLE),
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SILICON_LOWER))
        );

        registerUnderGroundOres(context, "add_ore_sapphire", biomesLookup.getOrThrow(Tags.Biomes.IS_WET),
                HolderSet.direct(
                    placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SAPPHIRE),
                    placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SAPPHIRE_BURIED))
        );

        registerUnderGroundOres(context, "add_ore_sapphire_overworld", HolderSet.direct(
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SAPPHIRE_OVERWORLD_SMALL),
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SAPPHIRE_OVERWORLD_LARGE),
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SAPPHIRE_OVERWORLD_BURIED))
        );

        registerUnderGroundOres(context, "add_ore_ruby", biomesLookup.getOrThrow(Tags.Biomes.IS_HOT),
                HolderSet.direct(
                    placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_RUBY),
                    placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_RUBY_BURIED))
        );

        registerUnderGroundOres(context, "add_ore_ruby_overworld", HolderSet.direct(
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_RUBY_OVERWORLD_SMALL),
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_RUBY_OVERWORLD_LARGE),
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_RUBY_OVERWORLD_BURIED))
        );

        registerUnderGroundOres(context, "add_ore_crystal", biomesLookup.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD),
                HolderSet.direct(
                    placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_CRYSTAL),
                    placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_CRYSTAL_BURIED))
        );

        registerUnderGroundOres(context, "add_ore_crystal_overworld", HolderSet.direct(
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_CRYSTAL_OVERWORLD_SMALL),
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_CRYSTAL_OVERWORLD_LARGE),
                placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_CRYSTAL_OVERWORLD_BURIED))
        );

        registerSurfaceFeatures(context, "add_poke_ball_loot",
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.POKE_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_beast_ball_loot",
                biomesLookup.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.BEAST_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_cherish_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_FLORAL),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.CHERISH_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_dive_ball_loot",
                biomesLookup.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.DIVE_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_dream_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_MAGICAL),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.DREAM_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_dusk_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_CAVE),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.DUSK_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_fast_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_SNOWY_FOREST),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.FAST_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_friend_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_TAIGA),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.FRIEND_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_gigaton_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_DESERT),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.GIGATON_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_great_ball_loot",
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.GREAT_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_heal_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_RIVER),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.HEAL_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_heavy_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_TUNDRA),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.HEAVY_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_jet_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_PLATEAU),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.JET_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_leaden_ball_loot",
                biomesLookup.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LEADEN_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_level_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_SAVANNA),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LEVEL_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_love_ball_loot",
                biomesLookup.getOrThrow(BiomeTags.IS_BEACH),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LOVE_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_lure_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_JUNGLE),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LURE_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_luxury_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_ISLAND),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LUXURY_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_master_ball_loot",
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.MASTER_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_moon_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_SPOOKY),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.MOON_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_nest_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_FOREST),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.NEST_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_net_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_COAST),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.NET_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_origin_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_PLAINS),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORIGIN_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_park_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_SWAMP),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.PARK_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_premier_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_GLACIAL),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.PREMIER_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_quick_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_HILLS),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.QUICK_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_repeat_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_BAMBOO),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.REPEAT_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_safari_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_GRASSLAND),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.SAFARI_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_sport_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_MOUNTAIN),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.SPORT_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_strange_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_DRIPSTONE),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.STRANGE_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_timer_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_BADLANDS),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.TIMER_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_ultra_ball_loot",
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ULTRA_BALL_LOOT)));

        registerSurfaceFeatures(context, "add_wing_ball_loot",
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_PEAK),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.WING_BALL_LOOT)));
    }

    private static void registerUnderGroundOres(BootstapContext<BiomeModifier> context, String biomeModifier, HolderSet<Biome> biomes, HolderSet<PlacedFeature> features) {
        context.register(registerKey(biomeModifier), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes,
                features,
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static void registerUnderGroundOres(BootstapContext<BiomeModifier> context, String biomeModifier, HolderSet<PlacedFeature> features) {
        context.register(registerKey(biomeModifier), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD),
                features,
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static void registerSurfaceFeatures(BootstapContext<BiomeModifier> context, String biomeModifier, HolderSet<PlacedFeature> features) {
        context.register(registerKey(biomeModifier), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD),
                features,
                GenerationStep.Decoration.RAW_GENERATION));
    }

    private static void registerSurfaceFeatures(BootstapContext<BiomeModifier> context, String biomeModifier, HolderSet<Biome> biomes, HolderSet<PlacedFeature> features) {
        context.register(registerKey(biomeModifier), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes,
                features,
                GenerationStep.Decoration.RAW_GENERATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, GenerationsCore.id(name));
    }
}
