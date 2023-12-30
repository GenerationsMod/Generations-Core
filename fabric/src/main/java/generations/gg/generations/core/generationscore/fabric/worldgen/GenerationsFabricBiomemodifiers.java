package generations.gg.generations.core.generationscore.fabric.worldgen;

import generations.gg.generations.core.generationscore.world.feature.GenerationsPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceKey;
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
        registerUnderGroundOres(GenerationsPlacedFeatures.ORE_ALUMINUM_LOWER);
        registerUnderGroundOres(GenerationsPlacedFeatures.ORE_ALUMINUM_MIDDLE);
        registerUnderGroundOres(GenerationsPlacedFeatures.ORE_ALUMINUM_UPPER);
    }

    /**
     * Registers PlacedFeatures with Biomemodificaions#addFeature
     * @param placedFeature The PlacedFeature to register
     */
    private static void registerUnderGroundOres(ResourceKey<PlacedFeature> placedFeature) {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, placedFeature);
    }

    /**
     * Registers PlacedFeatures with Biomemodificaions#addFeature
     * Allows for a biome specific predicate to be passed in
     * @param placedFeature The PlacedFeature to register
     * @param biomeSelector The BiomeSelector to use
     */
    private static void registerUnderGroundOres(ResourceKey<PlacedFeature> placedFeature, Predicate<BiomeSelectionContext> biomeSelector) {
        BiomeModifications.addFeature(biomeSelector, GenerationStep.Decoration.UNDERGROUND_ORES, placedFeature);
    }
}
