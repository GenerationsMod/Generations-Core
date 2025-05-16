package generations.gg.generations.core.generationscore.common.world.level.levelgen

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsMushroomBlock
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.features.TreeFeatures
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

object GenerationsFeatures {
    val BALLONLEA_BLUE_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("ballonlea_blue_mushroom")
    val BALLONLEA_GREEN_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("ballonlea_green_mushroom")
    val BALLONLEA_PINK_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("ballonlea_pink_mushroom")
    val BALLONLEA_YELLOW_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("ballonlea_yellow_mushroom")

    val GROUP_BALLONLEA_BLUE_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("group_ballonlea_blue_mushroom")
    val GROUP_BALLONLEA_GREEN_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> =
        register("group_ballonlea_green_mushroom")
    val GROUP_BALLONLEA_PINK_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("group_ballonlea_pink_mushroom")
    val GROUP_BALLONLEA_YELLOW_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> =
        register("group_ballonlea_yellow_mushroom")

    val TALL_BALLONLEA_BLUE_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("tall_ballonlea_blue_mushroom")
    val TALL_BALLONLEA_GREEN_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("tall_ballonlea_green_mushroom")
    val TALL_BALLONLEA_PINK_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> = register("tall_ballonlea_pink_mushroom")
    val TALL_BALLONLEA_YELLOW_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> =
        register("tall_ballonlea_yellow_mushroom")

    val DOUBLE_BALLONLEA_BLUE_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> =
        register("double_ballonlea_blue_mushroom")
    val DOUBLE_BALLONLEA_GREEN_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> =
        register("double_ballonlea_green_mushroom")
    val DOUBLE_BALLONLEA_PINK_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> =
        register("double_ballonlea_pink_mushroom")
    val DOUBLE_BALLONLEA_YELLOW_MUSHROOM: ResourceKey<ConfiguredFeature<*, *>> =
        register("double_ballonlea_yellow_mushroom")

    fun onInitialize(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        registerMushroom(context, BALLONLEA_BLUE_MUSHROOM, GenerationsBlocks.BALLONLEA_BLUE_MUSHROOM)
        registerMushroom(context, BALLONLEA_GREEN_MUSHROOM, GenerationsBlocks.BALLONLEA_GREEN_MUSHROOM)
        registerMushroom(context, BALLONLEA_PINK_MUSHROOM, GenerationsBlocks.BALLONLEA_PINK_MUSHROOM)
        registerMushroom(context, BALLONLEA_YELLOW_MUSHROOM, GenerationsBlocks.BALLONLEA_YELLOW_MUSHROOM)
        registerMushroom(context, GROUP_BALLONLEA_BLUE_MUSHROOM, GenerationsBlocks.GROUP_BALLONLEA_BLUE_MUSHROOM)
        registerMushroom(context, GROUP_BALLONLEA_GREEN_MUSHROOM, GenerationsBlocks.GROUP_BALLONLEA_GREEN_MUSHROOM)
        registerMushroom(context, GROUP_BALLONLEA_PINK_MUSHROOM, GenerationsBlocks.GROUP_BALLONLEA_PINK_MUSHROOM)
        registerMushroom(context, GROUP_BALLONLEA_YELLOW_MUSHROOM, GenerationsBlocks.GROUP_BALLONLEA_YELLOW_MUSHROOM)
        registerMushroom(context, TALL_BALLONLEA_BLUE_MUSHROOM, GenerationsBlocks.TALL_BALLONLEA_BLUE_MUSHROOM)
        registerMushroom(context, TALL_BALLONLEA_GREEN_MUSHROOM, GenerationsBlocks.TALL_BALLONLEA_GREEN_MUSHROOM)
        registerMushroom(context, TALL_BALLONLEA_PINK_MUSHROOM, GenerationsBlocks.TALL_BALLONLEA_PINK_MUSHROOM)
        registerMushroom(context, TALL_BALLONLEA_YELLOW_MUSHROOM, GenerationsBlocks.TALL_BALLONLEA_YELLOW_MUSHROOM)
        registerMushroom(context, DOUBLE_BALLONLEA_BLUE_MUSHROOM, GenerationsBlocks.DOUBLE_BALLONLEA_BLUE_MUSHROOM)
        registerMushroom(context, DOUBLE_BALLONLEA_GREEN_MUSHROOM, GenerationsBlocks.DOUBLE_BALLONLEA_GREEN_MUSHROOM)
        registerMushroom(context, DOUBLE_BALLONLEA_PINK_MUSHROOM, GenerationsBlocks.DOUBLE_BALLONLEA_PINK_MUSHROOM)
        registerMushroom(context, DOUBLE_BALLONLEA_YELLOW_MUSHROOM, GenerationsBlocks.DOUBLE_BALLONLEA_YELLOW_MUSHROOM)
    }

    private fun register(name: String): ResourceKey<ConfiguredFeature<*, *>> {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, id(name))
    }

    private fun registerMushroom(
        context: BootstrapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>,
        mushroom: RegistrySupplier<GenerationsMushroomBlock>
    ) {
        FeatureUtils.register(
            context, key, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(
                    BlockStateProvider.simple(mushroom.get())
                )
            )
        )
    }

    private fun registerTree(
        context: BootstrapContext<ConfiguredFeature<*, *>>,
        key: ResourceKey<ConfiguredFeature<*, *>>,
        name: String,
        leaves: RegistrySupplier<Block>
    ) {
        val configuration =
            TreeFeatures.createStraightBlobTree(Blocks.OAK_LOG, leaves.get(), 4, 2, 0, 2).ignoreVines().build()
        FeatureUtils.register(context, key, Feature.TREE, configuration)
    }
}
