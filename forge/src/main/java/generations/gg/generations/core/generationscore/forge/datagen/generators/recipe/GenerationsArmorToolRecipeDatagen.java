package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.item.tools.*;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GenerationsArmorToolRecipeDatagen extends GenerationsRecipeProvider.Proxied implements IConditionBuilder {

	public GenerationsArmorToolRecipeDatagen(PackOutput output) {
		super(output);
	}

	//AETHER, FLARE, NEO
	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
		buildToolSetCrafting(consumer, GenerationsBlocks.CHARGE_COBBLESTONE.get(), GenerationsTools.CHARGE_STONE);
		buildToolSetCrafting(consumer, GenerationsBlocks.VOLCANIC_COBBLESTONE.get(), GenerationsTools.VOLCANIC_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ALUMINUM_INGOT.get(), GenerationsArmor.ALUMINUM_HELMET.get(), GenerationsArmor.ALUMINUM_CHESTPLATE.get(), GenerationsArmor.ALUMINUM_LEGGINGS.get(), GenerationsArmor.ALUMINUM_BOOTS.get(), GenerationsTools.ALUMINUM);
		buildArmorToolFullSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsArmor.ROCKET_HELMET.get(), GenerationsArmor.ROCKET_CHESTPLATE.get(), GenerationsArmor.ROCKET_LEGGINGS.get(), GenerationsArmor.ROCKET_BOOTS.get(), GenerationsTools.AMETHYST);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsArmor.CRYSTAL_HELMET.get(), GenerationsArmor.CRYSTAL_CHESTPLATE.get(), GenerationsArmor.CRYSTAL_LEGGINGS.get(), GenerationsArmor.CRYSTAL_BOOTS.get(), GenerationsTools.CRYSTAL);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SAPPHIRE.get(), GenerationsArmor.AQUA_HELMET.get(), GenerationsArmor.AQUA_CHESTPLATE.get(), GenerationsArmor.AQUA_LEGGINGS.get(), GenerationsArmor.AQUA_BOOTS.get(), GenerationsTools.SAPPHIRE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsArmor.MAGMA_HELMET.get(), GenerationsArmor.MAGMA_CHESTPLATE.get(), GenerationsArmor.MAGMA_LEGGINGS.get(), GenerationsArmor.MAGMA_BOOTS.get(), GenerationsTools.RUBY);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsArmor.GALACTIC_HELMET.get(), GenerationsArmor.GALACTIC_CHESTPLATE.get(), GenerationsArmor.GALACTIC_LEGGINGS.get(), GenerationsArmor.GALACTIC_BOOTS.get(), GenerationsTools.SILICON);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DAWN_STONE.get(), GenerationsArmor.DAWN_STONE_HELMET.get(), GenerationsArmor.DAWN_STONE_CHESTPLATE.get(), GenerationsArmor.DAWN_STONE_LEGGINGS.get(), GenerationsArmor.DAWN_STONE_BOOTS.get(), GenerationsTools.DAWN_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DUSK_STONE.get(), GenerationsArmor.DUSK_STONE_HELMET.get(), GenerationsArmor.DUSK_STONE_CHESTPLATE.get(), GenerationsArmor.DUSK_STONE_LEGGINGS.get(), GenerationsArmor.DUSK_STONE_BOOTS.get(), GenerationsTools.DUSK_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.FIRE_STONE.get(), GenerationsArmor.FIRE_STONE_HELMET.get(), GenerationsArmor.FIRE_STONE_CHESTPLATE.get(), GenerationsArmor.FIRE_STONE_LEGGINGS.get(), GenerationsArmor.FIRE_STONE_BOOTS.get(), GenerationsTools.FIRE_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ICE_STONE.get(), GenerationsArmor.ICE_STONE_HELMET.get(), GenerationsArmor.ICE_STONE_CHESTPLATE.get(), GenerationsArmor.ICE_STONE_LEGGINGS.get(), GenerationsArmor.ICE_STONE_BOOTS.get(), GenerationsTools.ICE_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.LEAF_STONE.get(), GenerationsArmor.LEAF_STONE_HELMET.get(), GenerationsArmor.LEAF_STONE_CHESTPLATE.get(), GenerationsArmor.LEAF_STONE_LEGGINGS.get(), GenerationsArmor.LEAF_STONE_BOOTS.get(), GenerationsTools.LEAF_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SUN_STONE.get(), GenerationsArmor.SUN_STONE_HELMET.get(), GenerationsArmor.SUN_STONE_CHESTPLATE.get(), GenerationsArmor.SUN_STONE_LEGGINGS.get(), GenerationsArmor.SUN_STONE_BOOTS.get(), GenerationsTools.SUN_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.MOON_STONE.get(), GenerationsArmor.MOON_STONE_HELMET.get(), GenerationsArmor.MOON_STONE_CHESTPLATE.get(), GenerationsArmor.MOON_STONE_LEGGINGS.get(), GenerationsArmor.MOON_STONE_BOOTS.get(), GenerationsTools.MOON_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.THUNDER_STONE.get(), GenerationsArmor.THUNDER_STONE_HELMET.get(), GenerationsArmor.THUNDER_STONE_CHESTPLATE.get(), GenerationsArmor.THUNDER_STONE_LEGGINGS.get(), GenerationsArmor.THUNDER_STONE_BOOTS.get(), GenerationsTools.THUNDER_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.WATER_STONE.get(), GenerationsArmor.WATER_STONE_HELMET.get(), GenerationsArmor.WATER_STONE_CHESTPLATE.get(), GenerationsArmor.WATER_STONE_LEGGINGS.get(), GenerationsArmor.WATER_STONE_BOOTS.get(), GenerationsTools.WATER_STONE);
		buildArmorSetCrafting(consumer, GenerationsItems.Z_INGOT.get(), GenerationsArmor.ULTRA_HELMET.get(), GenerationsArmor.ULTRA_CHESTPLATE.get(), GenerationsArmor.ULTRA_LEGGINGS.get(), GenerationsArmor.ULTRA_BOOTS.get());
		//Modified Armor Recipes
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.ALUMINUM_INGOT.get(), GenerationsItems.CRYSTAL.get(), GenerationsArmor.AETHER_HELMET.get(), GenerationsArmor.AETHER_CHESTPLATE.get(), GenerationsArmor.AETHER_LEGGINGS.get(), GenerationsArmor.AETHER_BOOTS.get());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsItems.FIRE_STONE.get(), GenerationsArmor.FLARE_HELMET.get(), GenerationsArmor.FLARE_CHESTPLATE.get(), GenerationsArmor.FLARE_LEGGINGS.get(), GenerationsArmor.FLARE_BOOTS.get());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsItems.SILICON.get(), GenerationsArmor.NEO_PLASMA_HELMET.get(), GenerationsArmor.NEO_PLASMA_CHESTPLATE.get(), GenerationsArmor.NEO_PLASMA_LEGGINGS.get(), GenerationsArmor.NEO_PLASMA_BOOTS.get());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsItems.CRYSTAL.get(), GenerationsArmor.PLASMA_HELMET.get(), GenerationsArmor.PLASMA_CHESTPLATE.get(), GenerationsArmor.PLASMA_LEGGINGS.get(), GenerationsArmor.PLASMA_BOOTS.get());  //check above
		buildModifiedArmorSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsItems.SILICON.get(), GenerationsArmor.SKULL_HELMET.get(), GenerationsArmor.SKULL_CHESTPLATE.get(), GenerationsArmor.SKULL_LEGGINGS.get(), GenerationsArmor.SKULL_BOOTS.get());

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, GenerationsTools.WOODEN_HAMMER.get())
				.define('X', ItemTags.PLANKS)
				.define('#', Items.STICK)
				.pattern("XXX")
				.pattern("X#X")
				.pattern(" # ")
				.unlockedBy(getHasName(GenerationsTools.WOODEN_HAMMER.get()), has(GenerationsTools.WOODEN_HAMMER.get()))
				.save(consumer);

		buildHammerRecipes(consumer, GenerationsTools.STONE_HAMMER.get(), Items.COBBLESTONE);
		buildHammerRecipes(consumer, GenerationsTools.IRON_HAMMER.get(), Items.IRON_INGOT);
		buildHammerRecipes(consumer, GenerationsTools.GOLDEN_HAMMER.get(), Items.GOLD_INGOT);
		buildHammerRecipes(consumer, GenerationsTools.DIAMOND_HAMMER.get(), Items.DIAMOND);
		netheriteSmithing(consumer, GenerationsTools.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, GenerationsTools.NETHERITE_HAMMER.get());
	}

	private void buildArmorToolFullSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, Item helmet, Item chestplate, Item leggings, Item boots, GenerationsTools.ToolSet toolSet) {
		buildArmorSetCrafting(consumer, baseItem, helmet, chestplate, leggings, boots);
		buildToolSetCrafting(consumer, baseItem, toolSet);
	}

	private void buildArmorSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, Item helmet, Item chestplate, Item leggings, Item boots) {
		if (helmet != null) buildHelmetRecipes(consumer, baseItem, helmet);
		if (chestplate != null) buildChestplateRecipes(consumer, baseItem, chestplate);
		if (leggings != null) buildLeggingsRecipes(consumer, baseItem, leggings);
		if (boots != null) buildBootsRecipes(consumer, baseItem, boots);
	}

	private void buildToolSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ItemLike baseItem, GenerationsTools.ToolSet toolSet) {
		if (toolSet.pickaxe() != null) buildPickaxeRecipes(consumer, toolSet.pickaxe().get(), baseItem);
		if (toolSet.axe() != null) buildAxeRecipes(consumer, toolSet.axe().get(), baseItem);
		if (toolSet.sword() != null) buildSwordRecipes(consumer, toolSet.sword().get(), baseItem);
		if (toolSet.shovel() != null) buildShovelRecipes(consumer, toolSet.shovel().get(), baseItem);
		if (toolSet.hoe() != null) buildHoeRecipes(consumer, toolSet.hoe().get(), baseItem);
		if (toolSet.hammer() != null) buildHammerRecipes(consumer, toolSet.hammer().get(), baseItem);
	}

	private void buildHammerRecipes(@NotNull Consumer<FinishedRecipe> consumer, Item hammer, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hammer)
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XXX")
				.pattern("X#X")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildHoeRecipes(@NotNull Consumer<FinishedRecipe> consumer, GenerationsHoeItem hoe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XX")
				.pattern(" #")
				.pattern(" #")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildShovelRecipes(@NotNull Consumer<FinishedRecipe> consumer, GenerationsShovelItem shovel, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("X")
				.pattern("#")
				.pattern("#")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildSwordRecipes(@NotNull Consumer<FinishedRecipe> consumer, GenerationsSwordItem sword, ItemLike baseItem) {
	ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
			.define('X', baseItem)
			.define('#', Items.STICK)
			.pattern("X")
			.pattern("X")
			.pattern("#")
			.unlockedBy(getHasName(baseItem), has(baseItem))
			.save(consumer);
	}

	private void buildAxeRecipes(@NotNull Consumer<FinishedRecipe> consumer, GenerationsAxeItem axe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XX")
				.pattern("#X")
				.pattern("# ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildPickaxeRecipes(@NotNull Consumer<FinishedRecipe> consumer, GenerationsPickaxeItem pickaxe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XXX")
				.pattern(" # ")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildHelmetRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, Item helmet){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
				.define('X', baseItem)
				.pattern("XXX")
				.pattern("X X")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildChestplateRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, Item chestplate){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
				.define('X', baseItem)
				.pattern("X X")
				.pattern("XXX")
				.pattern("XXX")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildLeggingsRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, Item leggings){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
				.define('X', baseItem)
				.pattern("XXX")
				.pattern("X X")
				.pattern("X X")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildBootsRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, Item boots){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
				.define('X', baseItem)
				.pattern("X X")
				.pattern("X X")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}
	
	//Modified Armor Recipes
	private void buildModifiedArmorSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike itemTop, ItemLike itemBottom, Item helmet, Item chestplate, Item leggings, Item boots) {
		if (helmet != null) buildModifiedHelmetRecipes(consumer, itemTop, itemBottom, helmet);
		if (chestplate != null) buildModifiedChestplateRecipes(consumer, itemTop, itemBottom, chestplate);
		if (leggings != null) buildModifiedLeggingsRecipes(consumer, itemTop, itemBottom, leggings);
		if (boots != null) buildModifiedBootsRecipes(consumer, itemTop, itemBottom, boots);
	}

	private void buildModifiedHelmetRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike item1, ItemLike item2, Item helmet){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
				.define('X', item1)
				.define('Y', item2)
				.pattern("XXX")
				.pattern("Y Y")
				.unlockedBy(getHasName(item1), has(item1))
				.unlockedBy(getHasName(item2), has(item2))
				.save(consumer);
	}

	private void buildModifiedChestplateRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike item1, ItemLike item2, Item chestplate){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
				.define('X', item1)
				.define('Y', item2)
				.pattern("X X")
				.pattern("XXX")
				.pattern("YYY")
				.unlockedBy(getHasName(item1), has(item1))
				.unlockedBy(getHasName(item2), has(item2))
				.save(consumer);
	}

	private void buildModifiedLeggingsRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike item1, ItemLike item2, Item leggings){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
				.define('X', item1)
				.define('Y', item2)
				.pattern("XXX")
				.pattern("X X")
				.pattern("Y Y")
				.unlockedBy(getHasName(item1), has(item1))
				.unlockedBy(getHasName(item2), has(item2))
				.save(consumer);
	}

	private void buildModifiedBootsRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike item1, ItemLike item2, Item boots){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
				.define('X', item1)
				.define('Y', item2)
				.pattern("X X")
				.pattern("Y Y")
				.unlockedBy(getHasName(item1), has(item1))
				.unlockedBy(getHasName(item2), has(item2))
				.save(consumer);
	}
}
