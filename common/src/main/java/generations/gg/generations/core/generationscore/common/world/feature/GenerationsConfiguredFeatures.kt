package generations.gg.generations.core.generationscore.common.world.feature

import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsOres
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsOreSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest

object GenerationsConfiguredFeatures {
    fun init() {}

    @JvmField
    val POKE_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("poke_ball_loot")
    @JvmField
    val BEAST_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("beast_ball_loot")
    @JvmField
    val CHERISH_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("cherish_ball_loot")
    @JvmField
    val DIVE_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("dive_ball_loot")
    @JvmField
    val DREAM_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("dream_ball_loot")
    @JvmField
    val DUSK_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("dusk_ball_loot")
    @JvmField
    val FAST_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("fast_ball_loot")
    @JvmField
    val FRIEND_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("friend_ball_loot")
    @JvmField
    val GIGATON_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("gigaton_ball_loot")
    @JvmField
    val GREAT_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("great_ball_loot")
    @JvmField
    val HEAL_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("heal_ball_loot")
    @JvmField
    val HEAVY_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("heavy_ball_loot")
    @JvmField
    val JET_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("jet_ball_loot")
    @JvmField
    val LEADEN_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("leaden_ball_loot")
    @JvmField
    val LEVEL_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("level_ball_loot")
    @JvmField
    val LOVE_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("love_ball_loot")
    @JvmField
    val LURE_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("lure_ball_loot")
    @JvmField
    val LUXURY_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("luxury_ball_loot")
    @JvmField
    val MASTER_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("master_ball_loot")
    @JvmField
    val MOON_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("moon_ball_loot")
    @JvmField
    val NEST_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("nest_ball_loot")
    @JvmField
    val NET_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("net_ball_loot")
    @JvmField
    val ORIGIN_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("origin_ball_loot")
    @JvmField
    val PARK_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("park_ball_loot")
    @JvmField
    val PREMIER_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("premier_ball_loot")
    @JvmField
    val QUICK_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("quick_ball_loot")
    @JvmField
    val REPEAT_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("repeat_ball_loot")
    @JvmField
    val SAFARI_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("safari_ball_loot")
    @JvmField
    val SPORT_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("sport_ball_loot")
    @JvmField
    val STRANGE_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("strange_ball_loot")
    @JvmField
    val TIMER_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("timer_ball_loot")
    @JvmField
    val ULTRA_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ultra_ball_loot")
    @JvmField
    val WING_BALL_LOOT: ResourceKey<ConfiguredFeature<*, *>> = registerKey("wing_ball_loot")

    @JvmField
    val ORE_SILICON: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_silicon")
    @JvmField
    val ORE_SILICON_SMALL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_silicon_small")

    @JvmField
    val ORE_SAPPHIRE: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_sapphire")
    @JvmField
    val ORE_SAPPHIRE_SMALL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_sapphire_small")
    @JvmField
    val ORE_SAPPHIRE_OVERWORLD_SMALL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_sapphire_overworld_small")
    @JvmField
    val ORE_SAPPHIRE_OVERWORLD_LARGE: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_sapphire_overworld_large")
    val ORE_SAPPHIRE_OVERWORLD_BURIED: ResourceKey<ConfiguredFeature<*, *>> =
        registerKey("ore_sapphire_overworld_buried")

    @JvmField
    val ORE_RUBY: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_ruby")
    @JvmField
    val ORE_RUBY_SMALL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_ruby_small")
    @JvmField
    val ORE_RUBY_OVERWORLD_SMALL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_ruby_overworld_small")
    @JvmField
    val ORE_RUBY_OVERWORLD_LARGE: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_ruby_overworld_large")
    val ORE_RUBY_OVERWORLD_BURIED: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_ruby_overworld_buried")

    @JvmField
    val ORE_CRYSTAL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_crystal")
    @JvmField
    val ORE_CRYSTAL_SMALL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_crystal_small")
    @JvmField
    val ORE_CRYSTAL_OVERWORLD_SMALL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_crystal_overworld_small")
    @JvmField
    val ORE_CRYSTAL_OVERWORLD_LARGE: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_crystal_overworld_large")
    val ORE_CRYSTAL_OVERWORLD_BURIED: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_crystal_overworld_buried")

    @JvmField
    val ORE_MEGASTONE: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_megastone")
    @JvmField
    val ORE_Z_CRYSTAL: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_z_crystal")
    @JvmField
    val ORE_METEORITE: ResourceKey<ConfiguredFeature<*, *>> = registerKey("ore_meteorite")

    fun bootStrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val stoneReplaceables: RuleTest = TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)
        val deepslateReplaceables: RuleTest = TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
        val siliconOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.SILICON_ORE_SET)
        register(context, ORE_SILICON, Feature.ORE, OreConfiguration(siliconOres, 8, 0.1f))
        register(context, ORE_SILICON_SMALL, Feature.ORE, OreConfiguration(siliconOres, 4, 0.1f))

        val sapphireOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.SAPPHIRE_ORE_SET)
        register(context, ORE_SAPPHIRE, Feature.ORE, OreConfiguration(sapphireOres, 6))
        register(context, ORE_SAPPHIRE_SMALL, Feature.ORE, OreConfiguration(sapphireOres, 6, 1.0f))
        register(context, ORE_SAPPHIRE_OVERWORLD_SMALL, Feature.ORE, OreConfiguration(sapphireOres, 2, 0.5f))
        register(context, ORE_SAPPHIRE_OVERWORLD_LARGE, Feature.ORE, OreConfiguration(sapphireOres, 2, 0.7f))
        register(context, ORE_SAPPHIRE_OVERWORLD_BURIED, Feature.ORE, OreConfiguration(sapphireOres, 4, 1.0f))

        val rubyOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.RUBY_ORE_SET)
        register(context, ORE_RUBY, Feature.ORE, OreConfiguration(rubyOres, 6))
        register(context, ORE_RUBY_SMALL, Feature.ORE, OreConfiguration(rubyOres, 6, 1.0f))
        register(context, ORE_RUBY_OVERWORLD_SMALL, Feature.ORE, OreConfiguration(rubyOres, 2, 0.5f))
        register(context, ORE_RUBY_OVERWORLD_LARGE, Feature.ORE, OreConfiguration(rubyOres, 2, 0.7f))
        register(context, ORE_RUBY_OVERWORLD_BURIED, Feature.ORE, OreConfiguration(rubyOres, 4, 1.0f))

        val crystalOres = targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.CRYSTAL_ORE_SET)
        register(context, ORE_CRYSTAL, Feature.ORE, OreConfiguration(crystalOres, 6))
        register(context, ORE_CRYSTAL_SMALL, Feature.ORE, OreConfiguration(crystalOres, 6, 1.0f))
        register(context, ORE_CRYSTAL_OVERWORLD_SMALL, Feature.ORE, OreConfiguration(crystalOres, 2, 0.5f))
        register(context, ORE_CRYSTAL_OVERWORLD_LARGE, Feature.ORE, OreConfiguration(crystalOres, 2, 0.7f))
        register(context, ORE_CRYSTAL_OVERWORLD_BURIED, Feature.ORE, OreConfiguration(crystalOres, 4, 1.0f))

        val megastoneOres =
            targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.MEGASTONE_ORE_SET)
        register(context, ORE_MEGASTONE, Feature.ORE, OreConfiguration(megastoneOres, 3, 0.0f))

        val z_crystalOres =
            targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.Z_CRYSTAL_ORE_SET)
        register(context, ORE_Z_CRYSTAL, Feature.ORE, OreConfiguration(z_crystalOres, 3, 0.0f))

        val meteoriteOres =
            targetBlockState(stoneReplaceables, deepslateReplaceables, GenerationsOres.METEORITE_ORE_SET)
        register(context, ORE_METEORITE, Feature.ORE, OreConfiguration(meteoriteOres, 3, 0.0f))

        register(context, POKE_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.POKE_BALL_LOOT.value())))
        register(context, BEAST_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.BEAST_BALL_LOOT.value())))
        register(context, CHERISH_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.CHERISH_BALL_LOOT.value())))
        register(context, DIVE_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.DIVE_BALL_LOOT.value())))
        register(context, DREAM_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.DREAM_BALL_LOOT.value())))
        register(context, DUSK_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.DUSK_BALL_LOOT.value())))
        register(context, FAST_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.FAST_BALL_LOOT.value())))
        register(context, FRIEND_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.FRIEND_BALL_LOOT.value())))
        register(context, GIGATON_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.GIGATON_BALL_LOOT.value())))
        register(context, GREAT_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.GREAT_BALL_LOOT.value())))
        register(context, HEAL_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.HEAL_BALL_LOOT.value())))
        register(context, HEAVY_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.HEAVY_BALL_LOOT.value())))
        register(context, JET_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.JET_BALL_LOOT.value())))
        register(context, LEADEN_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LEADEN_BALL_LOOT.value())))
        register(context, LEVEL_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LEVEL_BALL_LOOT.value())))
        register(context, LOVE_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LOVE_BALL_LOOT.value())))
        register(context, LURE_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LURE_BALL_LOOT.value())))
        register(context, LUXURY_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.LUXURY_BALL_LOOT.value())))
        register(context, MASTER_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.MASTER_BALL_LOOT.value())))
        register(context, MOON_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.MOON_BALL_LOOT.value())))
        register(context, NEST_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.NEST_BALL_LOOT.value())))
        register(context, NET_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.NET_BALL_LOOT.value())))
        register(context, ORIGIN_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.ORIGIN_BALL_LOOT.value())))
        register(context, PARK_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.PARK_BALL_LOOT.value())))
        register(context, PREMIER_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.PREMIER_BALL_LOOT.value())))
        register(context, QUICK_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.QUICK_BALL_LOOT.value())))
        register(context, REPEAT_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.REPEAT_BALL_LOOT.value())))
        register(context, SAFARI_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.SAFARI_BALL_LOOT.value())))
        register(context, SPORT_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.SPORT_BALL_LOOT.value())))
        register(context, STRANGE_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.STRANGE_BALL_LOOT.value())))
        register(context, TIMER_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.TIMER_BALL_LOOT.value())))
        register(context, ULTRA_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.ULTRA_BALL_LOOT.value())))
        register(context, WING_BALL_LOOT, Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(BlockStateProvider.simple(GenerationsUtilityBlocks.WING_BALL_LOOT.value())))
    }

    private fun registerKey(name: String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, id(name))
    }

    private fun <FC : FeatureConfiguration?, F : Feature<FC>?> register(
        context: BootstrapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>,
        feature: F,
        configuration: FC
    ) {
        context.register(key, ConfiguredFeature(feature, configuration))
    }

    private fun targetBlockState(
        ruleTest: RuleTest,
        ruleTest2: RuleTest,
        oreSet: GenerationsOreSet
    ): List<OreConfiguration.TargetBlockState> {
        return java.util.List.of(
            OreConfiguration.target(ruleTest, oreSet.ore.defaultBlockState()),
            OreConfiguration.target(ruleTest2, oreSet.deepslateOre.defaultBlockState())
        )
    }
}
