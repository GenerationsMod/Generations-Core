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
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Joseph T. McQuigg
 */
public class GenerationsForgeBiomemodifiers {

    private static final ResourceKey<BiomeModifier> ADD_ORE_SILICON = registerKey("add_ore_silicon");
    private static final ResourceKey<BiomeModifier> ADD_POKE_BALL_LOOT = registerKey("add_poke_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_BEAST_BALL_LOOT = registerKey("add_beast_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_CHERISH_BALL_LOOT = registerKey("add_cherish_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_DIVE_BALL_LOOT = registerKey("add_dive_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_DREAM_BALL_LOOT = registerKey("add_dream_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_DUSK_BALL_LOOT = registerKey("add_dusk_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_FAST_BALL_LOOT = registerKey("add_fast_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_FRIEND_BALL_LOOT = registerKey("add_friend_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_GIGATON_BALL_LOOT = registerKey("add_gigaton_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_GREAT_BALL_LOOT = registerKey("add_great_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_HEAL_BALL_LOOT = registerKey("add_heal_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_HEAVY_BALL_LOOT = registerKey("add_heavy_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_JET_BALL_LOOT = registerKey("add_jet_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_LEADEN_BALL_LOOT = registerKey("add_leaden_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_LEVEL_BALL_LOOT = registerKey("add_level_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_LOVE_BALL_LOOT = registerKey("add_love_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_LURE_BALL_LOOT = registerKey("add_lure_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_LUXURY_BALL_LOOT = registerKey("add_luxury_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_MASTER_BALL_LOOT = registerKey("add_master_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_MOON_BALL_LOOT = registerKey("add_moon_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_NEST_BALL_LOOT = registerKey("add_nest_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_NET_BALL_LOOT = registerKey("add_net_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_ORIGIN_BALL_LOOT = registerKey("add_origin_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_PARK_BALL_LOOT = registerKey("add_park_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_PREMIER_BALL_LOOT = registerKey("add_premier_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_QUICK_BALL_LOOT = registerKey("add_quick_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_REPEAT_BALL_LOOT = registerKey("add_repeat_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_SAFARI_BALL_LOOT = registerKey("add_safari_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_SPORT_BALL_LOOT = registerKey("add_sport_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_STRANGE_BALL_LOOT = registerKey("add_strange_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_TIMER_BALL_LOOT = registerKey("add_timer_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_ULTRA_BALL_LOOT = registerKey("add_ultra_ball_loot");
    private static final ResourceKey<BiomeModifier> ADD_WING_BALL_LOOT = registerKey("add_wing_ball_loot");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeaturesLookup = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomesLookup = context.lookup(Registries.BIOME);

        context.register(ADD_ORE_SILICON, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(
                        placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SILICON_UPPER),
                        placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SILICON_MIDDLE),
                        placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_SILICON_LOWER)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_POKE_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.POKE_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_BEAST_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.BEAST_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_CHERISH_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_FLORAL),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.CHERISH_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_DIVE_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_OCEAN),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.DIVE_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_DREAM_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_MAGICAL),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.DREAM_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_DUSK_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_CAVE),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.DUSK_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_FAST_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_SNOWY_FOREST),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.FAST_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_FRIEND_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_TAIGA),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.FRIEND_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_GIGATON_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_DESERT),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.GIGATON_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_GREAT_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.GREAT_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_HEAL_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_RIVER),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.HEAL_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_HEAVY_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_TUNDRA),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.HEAVY_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_JET_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_PLATEAU),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.JET_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_LEADEN_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LEADEN_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_LEVEL_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_SAVANNA),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LEVEL_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_LOVE_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_BEACH),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LOVE_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_LURE_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_JUNGLE),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LURE_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_LUXURY_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_ISLAND),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.LUXURY_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_MASTER_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.MASTER_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_MOON_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_SPOOKY),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.MOON_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_NEST_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_FOREST),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.NEST_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_NET_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_COAST),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.NET_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_ORIGIN_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_PLAINS),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORIGIN_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_PARK_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_SWAMP),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.PARK_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_PREMIER_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_GLACIAL),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.PREMIER_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_QUICK_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_HILLS),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.QUICK_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_REPEAT_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_BAMBOO),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.REPEAT_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_SAFARI_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_GRASSLAND),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.SAFARI_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_SPORT_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_MOUNTAIN),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.SPORT_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_STRANGE_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_DRIPSTONE),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.STRANGE_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_TIMER_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_BADLANDS),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.TIMER_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_ULTRA_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ULTRA_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

        context.register(ADD_WING_BALL_LOOT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(CobblemonBiomeTags.IS_PEAK),
                HolderSet.direct(placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.WING_BALL_LOOT)),
                GenerationStep.Decoration.RAW_GENERATION
        ));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, GenerationsCore.id(name));
    }
}
