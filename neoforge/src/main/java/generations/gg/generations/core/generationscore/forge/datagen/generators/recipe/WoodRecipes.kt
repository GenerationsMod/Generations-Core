package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RotatedPillarBlock
import net.neoforged.neoforge.common.conditions.IConditionBuilder
import java.util.concurrent.CompletableFuture

class WoodRecipes(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    GenerationsRecipeProvider.Proxied(output, registries),
    IConditionBuilder {
    override fun buildRecipes(consumer: RecipeOutput) {
        buildAllWoodCraftingRecipes(
            consumer,
            GenerationsItemTags.ULTRA_DARK_LOGS,
            GenerationsWood.ULTRA_DARK_PLANKS,
            GenerationsWood.ULTRA_DARK_WOOD,
            GenerationsWood.STRIPPED_GHOST_WOOD,
            GenerationsWood.STRIPPED_GHOST_LOG,
            GenerationsWood.ULTRA_DARK_LOG,
            GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM,
            GenerationsItems.ULTRA_DARK_BOAT_ITEM,
            GenerationsWood.ULTRA_DARK_CRAFTING_TABLE,
            GenerationsItems.ULTRA_DARK_HANGING_SIGN,
            GenerationsWood.ULTRA_DARK_BOOKSHELF
        )
        buildAllWoodCraftingRecipes(
            consumer,
            GenerationsItemTags.ULTRA_JUNGLE_LOGS,
            GenerationsWood.ULTRA_JUNGLE_PLANKS,
            GenerationsWood.ULTRA_JUNGLE_WOOD,
            GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD,
            GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG,
            GenerationsWood.ULTRA_JUNGLE_LOG,
            GenerationsItems.ULTRA_JUNGLE_CHEST_BOAT_ITEM,
            GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM,
            GenerationsWood.ULTRA_JUNGLE_CRAFTING_TABLE,
            GenerationsItems.ULTRA_JUNGLE_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_BOOKSHELF
        )
        buildAllWoodCraftingRecipes(
            consumer,
            GenerationsItemTags.GHOST_LOGS,
            GenerationsWood.GHOST_PLANKS,
            GenerationsWood.GHOST_WOOD,
            GenerationsWood.STRIPPED_ULTRA_DARK_WOOD,
            GenerationsWood.STRIPPED_ULTRA_DARK_LOG,
            GenerationsWood.GHOST_LOG,
            GenerationsItems.GHOST_CHEST_BOAT_ITEM,
            GenerationsItems.GHOST_BOAT_ITEM,
            GenerationsWood.GHOST_CRAFTING_TABLE,
            GenerationsItems.GHOST_HANGING_SIGN,
            GenerationsWood.GHOST_BOOKSHELF
        )
    }

    private fun buildAllWoodCraftingRecipes(
        consumer: RecipeOutput,
        logTag: TagKey<Item>,
        planks: Block,
        wood: Block,
        strippedWood: Block,
        strippedLog: Block,
        LogBlock: RotatedPillarBlock,
        chestBoatItem: Item,
        boat: Item,
        craftingTable: Block,
        hangingSign: ItemLike,
        bookshelf: Block
    ) {
        planksFromLog(consumer, planks, logTag, 4) //planks from log
        woodFromLogs(consumer, wood, LogBlock)
        woodFromLogs(consumer, strippedWood, strippedLog)
        twoByTwoPacker(consumer, RecipeCategory.DECORATIONS, craftingTable, planks)
        woodenBoat(consumer, boat, planks)
        chestBoat(consumer, chestBoatItem, boat)
        hangingSign(consumer, hangingSign, strippedLog)
        bookshelf(consumer, bookshelf, planks)
    }

    private fun bookshelf(consumer: RecipeOutput, bookshelf: Block, planks: Block) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf)
            .define('#', planks)
            .define('X', ItemTags.BOOKSHELF_BOOKS)
            .pattern("###")
            .pattern("XXX")
            .pattern("###")
            .unlockedBy(getHasName(planks), has(planks))
            .save(consumer)
    }
}