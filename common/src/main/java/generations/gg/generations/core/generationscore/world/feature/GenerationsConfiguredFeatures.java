package generations.gg.generations.core.generationscore.world.feature;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsOreSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class GenerationsConfiguredFeatures {

    public static void init() {}

    public static final ResourceKey<ConfiguredFeature<?, ?>> POKE_BALL_LOOT = registerKey("poke_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BEAST_BALL_LOOT = registerKey("beast_ball_loot");
    public static final ResourceKey<ConfiguredFeature<? ,?>> CHERISH_BALL_LOOT = registerKey("cherish_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIVE_BALL_LOOT = registerKey("dive_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DREAM_BALL_LOOT = registerKey("dream_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DUSK_BALL_LOOT = registerKey("dusk_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAST_BALL_LOOT = registerKey("fast_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIEND_BALL_LOOT = registerKey("friend_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GIGATON_BALL_LOOT = registerKey("gigaton_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREAT_BALL_LOOT = registerKey("great_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HEAL_BALL_LOOT = registerKey("heal_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HEAVY_BALL_LOOT = registerKey("heavy_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JET_BALL_LOOT = registerKey("jet_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEADEN_BALL_LOOT = registerKey("leaden_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEVEL_BALL_LOOT = registerKey("level_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOVE_BALL_LOOT = registerKey("love_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LURE_BALL_LOOT = registerKey("lure_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LUXURY_BALL_LOOT = registerKey("luxury_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MASTER_BALL_LOOT = registerKey("master_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOON_BALL_LOOT = registerKey("moon_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NEST_BALL_LOOT = registerKey("nest_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NET_BALL_LOOT = registerKey("net_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORIGIN_BALL_LOOT = registerKey("origin_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PARK_BALL_LOOT = registerKey("park_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PREMIER_BALL_LOOT = registerKey("premier_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> QUICK_BALL_LOOT = registerKey("quick_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REPEAT_BALL_LOOT = registerKey("repeat_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAFARI_BALL_LOOT = registerKey("safari_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPORT_BALL_LOOT = registerKey("sport_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRANGE_BALL_LOOT = registerKey("strange_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIMER_BALL_LOOT = registerKey("timer_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ULTRA_BALL_LOOT = registerKey("ultra_ball_loot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WING_BALL_LOOT = registerKey("wing_ball_loot");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILICON = registerKey("ore_silicon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SILICON_SMALL = registerKey("ore_silicon_small");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE = registerKey("ore_sapphire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_SMALL = registerKey("ore_sapphire_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_OVERWORLD_SMALL = registerKey("ore_sapphire_overworld_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_OVERWORLD_LARGE = registerKey("ore_sapphire_overworld_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SAPPHIRE_OVERWORLD_BURIED = registerKey("ore_sapphire_overworld_buried");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY = registerKey("ore_ruby");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_SMALL = registerKey("ore_ruby_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_OVERWORLD_SMALL = registerKey("ore_ruby_overworld_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_OVERWORLD_LARGE = registerKey("ore_ruby_overworld_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_RUBY_OVERWORLD_BURIED = registerKey("ore_ruby_overworld_buried");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CRYSTAL = registerKey("ore_crystal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CRYSTAL_SMALL = registerKey("ore_crystal_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CRYSTAL_OVERWORLD_SMALL = registerKey("ore_crystal_overworld_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CRYSTAL_OVERWORLD_LARGE = registerKey("ore_crystal_overworld_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_CRYSTAL_OVERWORLD_BURIED = registerKey("ore_crystal_overworld_buried");


    public static void bootStrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> siliconOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.SILICON_ORE_SET);
        register(context, ORE_SILICON, Feature.ORE, new OreConfiguration(siliconOres, 8, 0.1F));
        register(context, ORE_SILICON_SMALL, Feature.ORE, new OreConfiguration(siliconOres, 4, 0.1F));

        List<OreConfiguration.TargetBlockState> sapphireOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.SAPPHIRE_ORE_SET);
        register(context, ORE_SAPPHIRE, Feature.ORE, new OreConfiguration(sapphireOres, 6));
        register(context, ORE_SAPPHIRE_SMALL, Feature.ORE, new OreConfiguration(sapphireOres, 6, 1.0F));
        register(context, ORE_SAPPHIRE_OVERWORLD_SMALL, Feature.ORE, new OreConfiguration(sapphireOres, 2, 0.5F));
        register(context, ORE_SAPPHIRE_OVERWORLD_LARGE, Feature.ORE, new OreConfiguration(sapphireOres, 2, 0.7F));
        register(context, ORE_SAPPHIRE_OVERWORLD_BURIED, Feature.ORE, new OreConfiguration(sapphireOres, 4, 1.0F));

        List<OreConfiguration.TargetBlockState> rubyOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.RUBY_ORE_SET);
        register(context, ORE_RUBY, Feature.ORE, new OreConfiguration(rubyOres, 6));
        register(context, ORE_RUBY_SMALL, Feature.ORE, new OreConfiguration(rubyOres, 6, 1.0F));
        register(context, ORE_RUBY_OVERWORLD_SMALL, Feature.ORE, new OreConfiguration(rubyOres, 2, 0.5F));
        register(context, ORE_RUBY_OVERWORLD_LARGE, Feature.ORE, new OreConfiguration(rubyOres, 2, 0.7F));
        register(context, ORE_RUBY_OVERWORLD_BURIED, Feature.ORE, new OreConfiguration(rubyOres, 4, 1.0F));

        List<OreConfiguration.TargetBlockState> crystalOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.CRYSTAL_ORE_SET);
        register(context, ORE_CRYSTAL, Feature.ORE, new OreConfiguration(crystalOres, 6));
        register(context, ORE_CRYSTAL_SMALL, Feature.ORE, new OreConfiguration(crystalOres, 6, 1.0F));
        register(context, ORE_CRYSTAL_OVERWORLD_SMALL, Feature.ORE, new OreConfiguration(crystalOres, 2, 0.5F));
        register(context, ORE_CRYSTAL_OVERWORLD_LARGE, Feature.ORE, new OreConfiguration(crystalOres, 2, 0.7F));
        register(context, ORE_CRYSTAL_OVERWORLD_BURIED, Feature.ORE, new OreConfiguration(crystalOres, 4, 1.0F));

        register(context, POKE_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.POKE_BALL_LOOT.get())));
        register(context, BEAST_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.BEAST_BALL_LOOT.get())));
        register(context, CHERISH_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.CHERISH_BALL_LOOT.get())));
        register(context, DIVE_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.DIVE_BALL_LOOT.get())));
        register(context, DREAM_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.DREAM_BALL_LOOT.get())));
        register(context, DUSK_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.DUSK_BALL_LOOT.get())));
        register(context, FAST_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.FAST_BALL_LOOT.get())));
        register(context, FRIEND_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.FRIEND_BALL_LOOT.get())));
        register(context, GIGATON_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.GIGATON_BALL_LOOT.get())));
        register(context, GREAT_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.GREAT_BALL_LOOT.get())));
        register(context, HEAL_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.HEAL_BALL_LOOT.get())));
        register(context, HEAVY_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.HEAVY_BALL_LOOT.get())));
        register(context, JET_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.JET_BALL_LOOT.get())));
        register(context, LEADEN_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LEADEN_BALL_LOOT.get())));
        register(context, LEVEL_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LEVEL_BALL_LOOT.get())));
        register(context, LOVE_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LOVE_BALL_LOOT.get())));
        register(context, LURE_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LURE_BALL_LOOT.get())));
        register(context, LUXURY_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LUXURY_BALL_LOOT.get())));
        register(context, MASTER_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.MASTER_BALL_LOOT.get())));
        register(context, MOON_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.MOON_BALL_LOOT.get())));
        register(context, NEST_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.NEST_BALL_LOOT.get())));
        register(context, NET_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.NET_BALL_LOOT.get())));
        register(context, ORIGIN_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.ORIGIN_BALL_LOOT.get())));
        register(context, PARK_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.PARK_BALL_LOOT.get())));
        register(context, PREMIER_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.PREMIER_BALL_LOOT.get())));
        register(context, QUICK_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.QUICK_BALL_LOOT.get())));
        register(context, REPEAT_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.REPEAT_BALL_LOOT.get())));
        register(context, SAFARI_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.SAFARI_BALL_LOOT.get())));
        register(context, SPORT_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.SPORT_BALL_LOOT.get())));
        register(context, STRANGE_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.STRANGE_BALL_LOOT.get())));
        register(context, TIMER_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.TIMER_BALL_LOOT.get())));
        register(context, ULTRA_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.ULTRA_BALL_LOOT.get())));
        register(context, WING_BALL_LOOT, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.WING_BALL_LOOT.get())));
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
