package generations.gg.generations.core.generationscore.forge.worldgen;

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

    public static final ResourceKey<BiomeModifier> ADD_ORE_ALUMINUM = registerKey("add_ore_aluminum");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeaturesLookup = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomesLookup = context.lookup(Registries.BIOME);

        context.register(ADD_ORE_ALUMINUM, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomesLookup.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(
                        placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_ALUMINUM_UPPER),
                        placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_ALUMINUM_MIDDLE),
                        placedFeaturesLookup.getOrThrow(GenerationsPlacedFeatures.ORE_ALUMINUM_LOWER)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, GenerationsCore.id(name));
    }
}
