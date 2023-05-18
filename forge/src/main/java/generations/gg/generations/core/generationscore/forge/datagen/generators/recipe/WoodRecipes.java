package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class WoodRecipes extends GenerationsRecipeProvider.Proxied implements IConditionBuilder {
	public WoodRecipes(PackOutput output) {super(output);}

	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
		buildAllWoodCraftingRecipes(consumer, GenerationsItemTags.ULTRA_DARK_LOGS, GenerationsWood.ULTRA_DARK_PLANKS.get(), GenerationsWood.ULTRA_DARK_WOOD.get(), GenerationsWood.STRIPPED_GHOST_WOOD.get(), GenerationsWood.STRIPPED_GHOST_LOG.get(), GenerationsWood.ULTRA_DARK_LOG.get(), GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM.get(), GenerationsItems.ULTRA_DARK_BOAT_ITEM.get(), GenerationsWood.ULTRA_DARK_CRAFTING_TABLE.get(), GenerationsItems.ULTRA_DARK_HANGING_SIGN.get());
		buildAllWoodCraftingRecipes(consumer, GenerationsItemTags.ULTRA_JUNGLE_LOGS, GenerationsWood.ULTRA_JUNGLE_PLANKS.get(), GenerationsWood.ULTRA_JUNGLE_WOOD.get() , GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG.get(), GenerationsWood.ULTRA_JUNGLE_LOG.get(), GenerationsItems.ULTRA_JUNGLE_CHEST_BOAT_ITEM.get(), GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.get(), GenerationsWood.ULTRA_JUNGLE_CRAFTING_TABLE.get(), GenerationsItems.ULTRA_JUNGLE_HANGING_SIGN.get());
		buildAllWoodCraftingRecipes(consumer, GenerationsItemTags.GHOST_LOGS, GenerationsWood.GHOST_PLANKS.get(), GenerationsWood.GHOST_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_DARK_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_DARK_LOG.get(), GenerationsWood.GHOST_LOG.get(), GenerationsItems.GHOST_CHEST_BOAT_ITEM.get(), GenerationsItems.GHOST_BOAT_ITEM.get(), GenerationsWood.GHOST_CRAFTING_TABLE.get(), GenerationsItems.GHOST_HANGING_SIGN.get());
	}

	private void buildAllWoodCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer, @NotNull TagKey<Item> logTag, @NotNull Block planks, Block wood, Block strippedWood, Block strippedLog, RotatedPillarBlock LogBlock, Item chestBoatItem, Item boat, Block craftingTable, ItemLike hangingSign){
		planksFromLog(consumer, planks, logTag,4);  //planks from log
		woodFromLogs(consumer, wood, LogBlock);
		woodFromLogs(consumer, strippedWood, strippedLog);
		twoByTwoPacker(consumer, RecipeCategory.DECORATIONS, craftingTable, planks);
		woodenBoat(consumer, boat, planks);
		chestBoat(consumer, chestBoatItem, boat);
		hangingSign(consumer, hangingSign, strippedLog);
	}
}