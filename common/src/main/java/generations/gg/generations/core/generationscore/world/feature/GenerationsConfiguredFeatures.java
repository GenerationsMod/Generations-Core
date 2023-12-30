package generations.gg.generations.core.generationscore.world.feature;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsOreSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class GenerationsConfiguredFeatures {

    public static void init() {}

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM = registerKey("ore_aluminum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM_SMALL = registerKey("ore_aluminum_small");

    public static void bootStrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        var aluminiumOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.ALUMINUM_ORE_SET);
        register(context, ORE_ALUMINUM, Feature.ORE, new OreConfiguration(aluminiumOres, 9, 0.0F));
        register(context, ORE_ALUMINUM_SMALL, Feature.ORE, new OreConfiguration(aluminiumOres, 4, 0.0F));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, GenerationsCore.id(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static List<OreConfiguration.TargetBlockState> targetBlockState(RuleTest ruleTest, RuleTest ruleTest2, GenerationsOreSet oreSet) {
        return List.of(
                OreConfiguration.target(ruleTest, oreSet.getOre().defaultBlockState()),
                OreConfiguration.target(ruleTest2, oreSet.getDeepslateOre().defaultBlockState())
        );
    }
}
