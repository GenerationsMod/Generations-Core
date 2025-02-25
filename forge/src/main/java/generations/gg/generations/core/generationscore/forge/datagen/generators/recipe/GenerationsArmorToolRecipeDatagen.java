package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.common.world.item.tools.*;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
		buildToolSetCrafting(consumer, GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock(), GenerationsTools.CHARGE_STONE);
		buildToolSetCrafting(consumer, GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock(), GenerationsTools.VOLCANIC_STONE);
		buildArmorToolFullSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsArmor.ROCKET, GenerationsTools.AMETHYST);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsArmor.CRYSTALLIZED, GenerationsTools.CRYSTAL);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SAPPHIRE.get(), GenerationsArmor.AQUA, GenerationsTools.SAPPHIRE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsArmor.MAGMA, GenerationsTools.RUBY);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsArmor.GALACTIC, GenerationsTools.SILICON);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.DAWN_STONE.asItem(), GenerationsArmor.DAWN_STONE, GenerationsTools.DAWN_STONE);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.DUSK_STONE.asItem(), GenerationsArmor.DUSK_STONE, GenerationsTools.DUSK_STONE);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.FIRE_STONE.asItem(), GenerationsArmor.FIRE_STONE, GenerationsTools.FIRE_STONE);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.ICE_STONE.asItem(), GenerationsArmor.ICE_STONE, GenerationsTools.ICE_STONE);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.LEAF_STONE.asItem(), GenerationsArmor.LEAF_STONE, GenerationsTools.LEAF_STONE);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.SUN_STONE.asItem(), GenerationsArmor.SUN_STONE, GenerationsTools.SUN_STONE);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.MOON_STONE.asItem(), GenerationsArmor.MOON_STONE, GenerationsTools.MOON_STONE);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.THUNDER_STONE.asItem(), GenerationsArmor.THUNDER_STONE, GenerationsTools.THUNDER_STONE);
		buildArmorToolFullSetCrafting(consumer, CobblemonItems.WATER_STONE.asItem(), GenerationsArmor.WATER_STONE, GenerationsTools.WATER_STONE);
		buildArmorSetCrafting(consumer, GenerationsItems.Z_INGOT.get(), GenerationsArmor.ULTRA);
		//Modified Armor Recipes
		buildModifiedArmorSetCrafting(consumer, Items.COPPER_INGOT, GenerationsItems.CRYSTAL.get(), GenerationsArmor.AETHER.helmet().get(), GenerationsArmor.AETHER.chestplate().get(), GenerationsArmor.AETHER.leggings().get(), GenerationsArmor.AETHER.boots().get());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.RUBY.get(), CobblemonItems.FIRE_STONE.asItem(), GenerationsArmor.FLARE.helmet().get(), GenerationsArmor.FLARE.chestplate().get(), GenerationsArmor.FLARE.leggings().get(), GenerationsArmor.FLARE.boots().get());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsItems.SILICON.get(), GenerationsArmor.NEO_PLASMA.helmet().get(), GenerationsArmor.NEO_PLASMA.chestplate().get(), GenerationsArmor.NEO_PLASMA.leggings().get(), GenerationsArmor.NEO_PLASMA.boots().get());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsItems.CRYSTAL.get(), GenerationsArmor.PLASMA.helmet().get(), GenerationsArmor.PLASMA.chestplate().get(), GenerationsArmor.PLASMA.leggings().get(), GenerationsArmor.PLASMA.boots().get());  //check above
		buildModifiedArmorSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsItems.SILICON.get(), GenerationsArmor.SKULL.helmet().get(), GenerationsArmor.SKULL.chestplate().get(), GenerationsArmor.SKULL.leggings().get(), GenerationsArmor.SKULL.boots().get());

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
//		ultriteSmithing(consumer, GenerationsTools.NETHERITE_HAMMER.get(), RecipeCategory.TOOLS, GenerationsTools.ULTRITE_HAMMER.get());

		ultriteSmithing(consumer, Items.NETHERITE_SWORD, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.sword().get());
		ultriteSmithing(consumer, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.shovel().get());
		ultriteSmithing(consumer, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.pickaxe().get());
		ultriteSmithing(consumer, Items.NETHERITE_AXE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.axe().get());
		ultriteSmithing(consumer, Items.NETHERITE_HOE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.hoe().get());

		ultriteSmithing(consumer, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, GenerationsArmor.ULTRITE.helmet().get());
		ultriteSmithing(consumer, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, GenerationsArmor.ULTRITE.chestplate().get());
		ultriteSmithing(consumer, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, GenerationsArmor.ULTRITE.leggings().get());
		ultriteSmithing(consumer, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, GenerationsArmor.ULTRITE.boots().get());

	}

	protected static void ultriteSmithing(Consumer<FinishedRecipe> finishedRecipeConsumer, Item ingredientItem, RecipeCategory category, Item resultItem) {
		SmithingTransformRecipeBuilder.smithing(Ingredient.of(GenerationsItems.ULTRITE_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(ingredientItem), Ingredient.of(GenerationsItems.ULTRITE_INGOT.get()), category, resultItem).unlocks("has_ultrite_ingot", has(GenerationsItems.ULTRITE_INGOT.get())).save(finishedRecipeConsumer, getItemName(resultItem) + "_smithing");
	}

	private void buildArmorToolFullSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, GenerationsArmor.ArmorSet armorSet, GenerationsTools.ToolSet toolSet) {
		buildArmorSetCrafting(consumer, baseItem, armorSet);
		buildToolSetCrafting(consumer, baseItem, toolSet);
	}

	private void buildArmorSetCrafting(@NotNull Consumer<FinishedRecipe> consumer,  @NotNull ItemLike baseItem, GenerationsArmor.ArmorSet armorSet) {
		if (armorSet.helmet() != null) buildHelmetRecipes(consumer, baseItem, armorSet.helmet().get());
		if (armorSet.chestplate() != null) buildChestplateRecipes(consumer, baseItem, armorSet.chestplate().get());
		if (armorSet.leggings() != null) buildLeggingsRecipes(consumer, baseItem, armorSet.leggings().get());
		if (armorSet.boots() != null) buildBootsRecipes(consumer, baseItem, armorSet.boots().get());
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
