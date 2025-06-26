package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import net.minecraft.core.Holder
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
        planks: Holder<out Block>,
        wood: Holder<out Block>,
        strippedWood: Holder<out Block>,
        strippedLog: Holder<out Block>,
        logBlock: Holder<out Block>,
        chestBoatItem: Holder<out Item>,
        boat: Holder<out Item>,
        craftingTable: Holder<out Block>,
        hangingSign: Holder<out ItemLike>,
        bookshelf: Holder<out Block>
    ) {
        planksFromLog(consumer, planks.value(), logTag, 4) //planks from log
        woodFromLogs(consumer, wood.value(), logBlock.value())
        woodFromLogs(consumer, strippedWood.value(), strippedLog.value())
        twoByTwoPacker(consumer, RecipeCategory.DECORATIONS, craftingTable.value(), planks.value())
        woodenBoat(consumer, boat.value(), planks.value())
        chestBoat(consumer, chestBoatItem.value(), boat.value())
        hangingSign(consumer, hangingSign.value(), strippedLog.value())
        bookshelf(consumer, bookshelf.value(), planks.value())
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