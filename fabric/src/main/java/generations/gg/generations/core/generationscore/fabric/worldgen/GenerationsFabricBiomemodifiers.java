package generations.gg.generations.core.generationscore.fabric.worldgen;

import com.cobblemon.mod.common.api.tags.CobblemonBiomeTags;
import generations.gg.generations.core.generationscore.world.feature.GenerationsPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Predicate;

/**
 * Registers PlacedFeatures with Biomemodificaions#addFeature
 * @see BiomeModifications#addFeature
 * @author Joseph T. McQuigg
 */
public class GenerationsFabricBiomemodifiers {

    /**
     * Registers all PlacedFeatures with Biomemodificaions#addFeature
     */
    public static void generateOres() {
        registerUnderGroundOres(GenerationsPlacedFeatures.ORE_SILICON_LOWER, GenerationsPlacedFeatures.ORE_SILICON_MIDDLE, GenerationsPlacedFeatures.ORE_SILICON_UPPER);

        registerUnderGroundOres(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_WET), GenerationsPlacedFeatures.ORE_SAPPHIRE, GenerationsPlacedFeatures.ORE_SAPPHIRE_BURIED);
        registerUnderGroundOres(GenerationsPlacedFeatures.ORE_SAPPHIRE_OVERWORLD_SMALL, GenerationsPlacedFeatures.ORE_SAPPHIRE_OVERWORLD_LARGE, GenerationsPlacedFeatures.ORE_SAPPHIRE_OVERWORLD_BURIED);

        registerUnderGroundOres(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_HOT), GenerationsPlacedFeatures.ORE_RUBY, GenerationsPlacedFeatures.ORE_RUBY_BURIED);
        registerUnderGroundOres(GenerationsPlacedFeatures.ORE_RUBY_OVERWORLD_SMALL, GenerationsPlacedFeatures.ORE_RUBY_OVERWORLD_LARGE, GenerationsPlacedFeatures.ORE_RUBY_OVERWORLD_BURIED);

        registerUnderGroundOres(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_COLD), GenerationsPlacedFeatures.ORE_CRYSTAL, GenerationsPlacedFeatures.ORE_CRYSTAL_BURIED);
        registerUnderGroundOres(GenerationsPlacedFeatures.ORE_CRYSTAL_OVERWORLD_SMALL, GenerationsPlacedFeatures.ORE_CRYSTAL_OVERWORLD_LARGE, GenerationsPlacedFeatures.ORE_CRYSTAL_OVERWORLD_BURIED);

        registerSurfaceFeatures(GenerationsPlacedFeatures.POKE_BALL_LOOT);
        registerSurfaceFeatures(GenerationsPlacedFeatures.BEAST_BALL_LOOT, BiomeSelectors.foundInTheEnd());
        registerSurfaceFeatures(GenerationsPlacedFeatures.CHERISH_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_FLORAL));
        registerSurfaceFeatures(GenerationsPlacedFeatures.DIVE_BALL_LOOT, BiomeSelectors.tag(BiomeTags.IS_OCEAN));
        registerSurfaceFeatures(GenerationsPlacedFeatures.DUSK_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_CAVE));
        registerSurfaceFeatures(GenerationsPlacedFeatures.DREAM_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_MAGICAL));
        registerSurfaceFeatures(GenerationsPlacedFeatures.FAST_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_SNOWY_FOREST));
        registerSurfaceFeatures(GenerationsPlacedFeatures.FRIEND_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_TAIGA));
        registerSurfaceFeatures(GenerationsPlacedFeatures.GIGATON_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_DESERT));
        registerSurfaceFeatures(GenerationsPlacedFeatures.GREAT_BALL_LOOT);
        registerSurfaceFeatures(GenerationsPlacedFeatures.HEAL_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_RIVER));
        registerSurfaceFeatures(GenerationsPlacedFeatures.HEAVY_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_TUNDRA));
        registerSurfaceFeatures(GenerationsPlacedFeatures.JET_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_PLATEAU));
        registerSurfaceFeatures(GenerationsPlacedFeatures.LEADEN_BALL_LOOT, BiomeSelectors.foundInTheNether());
        registerSurfaceFeatures(GenerationsPlacedFeatures.LEVEL_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_SAVANNA));
        registerSurfaceFeatures(GenerationsPlacedFeatures.LOVE_BALL_LOOT, BiomeSelectors.tag(BiomeTags.IS_BEACH));
        registerSurfaceFeatures(GenerationsPlacedFeatures.LURE_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_JUNGLE));
        registerSurfaceFeatures(GenerationsPlacedFeatures.LUXURY_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_ISLAND));
        registerSurfaceFeatures(GenerationsPlacedFeatures.MASTER_BALL_LOOT);
        registerSurfaceFeatures(GenerationsPlacedFeatures.MOON_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_SPOOKY));
        registerSurfaceFeatures(GenerationsPlacedFeatures.NEST_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_FOREST));
        registerSurfaceFeatures(GenerationsPlacedFeatures.NET_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_COAST));
        registerSurfaceFeatures(GenerationsPlacedFeatures.ORIGIN_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_PLAINS));
        registerSurfaceFeatures(GenerationsPlacedFeatures.PARK_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_SWAMP));
        registerSurfaceFeatures(GenerationsPlacedFeatures.PREMIER_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_GLACIAL));
        registerSurfaceFeatures(GenerationsPlacedFeatures.QUICK_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_HILLS));
        registerSurfaceFeatures(GenerationsPlacedFeatures.REPEAT_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_BAMBOO));
        registerSurfaceFeatures(GenerationsPlacedFeatures.SAFARI_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_GRASSLAND));
        registerSurfaceFeatures(GenerationsPlacedFeatures.SPORT_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_MOUNTAIN));
        registerSurfaceFeatures(GenerationsPlacedFeatures.STRANGE_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_DRIPSTONE));
        registerSurfaceFeatures(GenerationsPlacedFeatures.TIMER_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_BADLANDS));
        registerSurfaceFeatures(GenerationsPlacedFeatures.ULTRA_BALL_LOOT);
        registerSurfaceFeatures(GenerationsPlacedFeatures.WING_BALL_LOOT, BiomeSelectors.tag(CobblemonBiomeTags.IS_PEAK));

    }

    /**
     * Registers PlacedFeatures with Biomemodificaions#addFeature
     * @param placedFeature The PlacedFeature(s) to register
     */
    @SafeVarargs
    private static void registerUnderGroundOres(ResourceKey<PlacedFeature>... placedFeature) {
        for (ResourceKey<PlacedFeature> feature : placedFeature)
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, feature);
    }


    /**
     * Registers PlacedFeatures with Biomemodificaions#addFeature
     * Allows for a biome specific predicate to be passed in
     *
     * @param biomeSelector The BiomeSelector to use
     * @param placedFeature The PlacedFeature(s) to register
     */
    @SafeVarargs
    private static void registerUnderGroundOres(Predicate<BiomeSelectionContext> biomeSelector, ResourceKey<PlacedFeature>... placedFeature) {
        for (ResourceKey<PlacedFeature> feature : placedFeature)
            BiomeModifications.addFeature(biomeSelector, GenerationStep.Decoration.UNDERGROUND_ORES, feature);
    }


    /**
     * Registers PlacedFeatures with Biomemodificaions#addFeature
     * @param placedFeature The PlacedFeature to register
     * @param biomeSelector The BiomeSelector to use
     */
    private static void registerSurfaceFeatures(ResourceKey<PlacedFeature> placedFeature, Predicate<BiomeSelectionContext> biomeSelector) {
        BiomeModifications.addFeature(biomeSelector, GenerationStep.Decoration.RAW_GENERATION, placedFeature);
    }

    /**
     * Registers PlacedFeatures with Biomemodificaions#addFeature
     * @param placedFeature The PlacedFeature to register
     */
    private static void registerSurfaceFeatures(ResourceKey<PlacedFeature> placedFeature) {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.RAW_GENERATION, placedFeature);
    }
}
