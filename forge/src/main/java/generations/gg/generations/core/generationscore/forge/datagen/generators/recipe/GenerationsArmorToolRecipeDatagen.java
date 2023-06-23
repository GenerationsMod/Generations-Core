package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import dev.architectury.registry.registries.RegistrySupplier;
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
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ALUMINUM_INGOT.get(), GenerationsArmor.ALUMINUM_HELMET, GenerationsArmor.ALUMINUM_CHESTPLATE, GenerationsArmor.ALUMINUM_LEGGINGS, GenerationsArmor.ALUMINUM_BOOTS, GenerationsTools.ALUMINUM);
		buildArmorToolFullSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsArmor.ROCKET_HELMET, GenerationsArmor.ROCKET_CHESTPLATE, GenerationsArmor.ROCKET_LEGGINGS, GenerationsArmor.ROCKET_BOOTS, GenerationsTools.AMETHYST);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsArmor.CRYSTAL_HELMET, GenerationsArmor.CRYSTAL_CHESTPLATE, GenerationsArmor.CRYSTAL_LEGGINGS, GenerationsArmor.CRYSTAL_BOOTS, GenerationsTools.CRYSTAL);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SAPPHIRE.get(), GenerationsArmor.AQUA_HELMET, GenerationsArmor.AQUA_CHESTPLATE, GenerationsArmor.AQUA_LEGGINGS, GenerationsArmor.AQUA_BOOTS, GenerationsTools.SAPPHIRE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsArmor.MAGMA_HELMET, GenerationsArmor.MAGMA_CHESTPLATE, GenerationsArmor.MAGMA_LEGGINGS, GenerationsArmor.MAGMA_BOOTS, GenerationsTools.RUBY);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsArmor.GALACTIC_HELMET, GenerationsArmor.GALACTIC_CHESTPLATE, GenerationsArmor.GALACTIC_LEGGINGS, GenerationsArmor.GALACTIC_BOOTS, GenerationsTools.SILICON);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DAWN_STONE.get(), GenerationsArmor.DAWN_STONE_HELMET, GenerationsArmor.DAWN_STONE_CHESTPLATE, GenerationsArmor.DAWN_STONE_LEGGINGS, GenerationsArmor.DAWN_STONE_BOOTS, GenerationsTools.DAWN_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DUSK_STONE.get(), GenerationsArmor.DUSK_STONE_HELMET, GenerationsArmor.DUSK_STONE_CHESTPLATE, GenerationsArmor.DUSK_STONE_LEGGINGS, GenerationsArmor.DUSK_STONE_BOOTS, GenerationsTools.DUSK_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.FIRE_STONE.get(), GenerationsArmor.FIRE_STONE_HELMET, GenerationsArmor.FIRE_STONE_CHESTPLATE, GenerationsArmor.FIRE_STONE_LEGGINGS, GenerationsArmor.FIRE_STONE_BOOTS, GenerationsTools.FIRE_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ICE_STONE.get(), GenerationsArmor.ICE_STONE_HELMET, GenerationsArmor.ICE_STONE_CHESTPLATE, GenerationsArmor.ICE_STONE_LEGGINGS, GenerationsArmor.ICE_STONE_BOOTS, GenerationsTools.ICE_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.LEAF_STONE.get(), GenerationsArmor.LEAF_STONE_HELMET, GenerationsArmor.LEAF_STONE_CHESTPLATE, GenerationsArmor.LEAF_STONE_LEGGINGS, GenerationsArmor.LEAF_STONE_BOOTS, GenerationsTools.LEAF_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SUN_STONE.get(), GenerationsArmor.SUN_STONE_HELMET, GenerationsArmor.SUN_STONE_CHESTPLATE, GenerationsArmor.SUN_STONE_LEGGINGS, GenerationsArmor.SUN_STONE_BOOTS, GenerationsTools.SUN_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.MOON_STONE.get(), GenerationsArmor.MOON_STONE_HELMET, GenerationsArmor.MOON_STONE_CHESTPLATE, GenerationsArmor.MOON_STONE_LEGGINGS, GenerationsArmor.MOON_STONE_BOOTS, GenerationsTools.MOON_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.THUNDER_STONE.get(), GenerationsArmor.THUNDER_STONE_HELMET, GenerationsArmor.THUNDER_STONE_CHESTPLATE, GenerationsArmor.THUNDER_STONE_LEGGINGS, GenerationsArmor.THUNDER_STONE_BOOTS, GenerationsTools.THUNDER_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.WATER_STONE.get(), GenerationsArmor.WATER_STONE_HELMET, GenerationsArmor.WATER_STONE_CHESTPLATE, GenerationsArmor.WATER_STONE_LEGGINGS, GenerationsArmor.WATER_STONE_BOOTS, GenerationsTools.WATER_STONE);
		buildArmorSetCrafting(consumer, GenerationsItems.Z_INGOT.get(), GenerationsArmor.ULTRA_HELMET, GenerationsArmor.ULTRA_CHESTPLATE, GenerationsArmor.ULTRA_LEGGINGS, GenerationsArmor.ULTRA_BOOTS);
		//Modified Armor Recipes
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.ALUMINUM_INGOT.get(), GenerationsItems.CRYSTAL.get(), GenerationsArmor.AETHER_HELMET, GenerationsArmor.AETHER_CHESTPLATE, GenerationsArmor.AETHER_LEGGINGS, GenerationsArmor.AETHER_BOOTS);
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsItems.FIRE_STONE.get(), GenerationsArmor.FLARE_HELMET, GenerationsArmor.FLARE_CHESTPLATE, GenerationsArmor.FLARE_LEGGINGS, GenerationsArmor.FLARE_BOOTS);
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsItems.SILICON.get(), GenerationsArmor.NEO_PLASMA_HELMET, GenerationsArmor.NEO_PLASMA_CHESTPLATE, GenerationsArmor.NEO_PLASMA_LEGGINGS, GenerationsArmor.NEO_PLASMA_BOOTS);
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsItems.CRYSTAL.get(), GenerationsArmor.PLASMA_HELMET, GenerationsArmor.PLASMA_CHESTPLATE, GenerationsArmor.PLASMA_LEGGINGS, GenerationsArmor.PLASMA_BOOTS);  //check above
		buildModifiedArmorSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsItems.SILICON.get(), GenerationsArmor.SKULL_HELMET, GenerationsArmor.SKULL_CHESTPLATE, GenerationsArmor.SKULL_LEGGINGS, GenerationsArmor.SKULL_BOOTS);

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

	private void buildArmorToolFullSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, RegistrySupplier<Item> helmet, RegistrySupplier<Item> chestplate, RegistrySupplier<Item> leggings, RegistrySupplier<Item> boots, GenerationsTools.ToolSet toolSet) {
		buildArmorSetCrafting(consumer, baseItem, helmet, chestplate, leggings, boots);
		buildToolSetCrafting(consumer, baseItem, toolSet);
	}

	private void buildArmorSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, RegistrySupplier<Item> helmet, RegistrySupplier<Item> chestplate, RegistrySupplier<Item> leggings, RegistrySupplier<Item> boots) {
		if (helmet != null) buildHelmetRecipes(consumer, baseItem, helmet);
		if (chestplate != null) buildChestplateRecipes(consumer, baseItem, chestplate);
		if (leggings != null) buildLeggingsRecipes(consumer, baseItem, leggings);
		if (boots != null) buildBootsRecipes(consumer, baseItem, boots);
	}

	private void buildToolSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ItemLike baseItem, GenerationsTools.ToolSet toolSet) {
		if (toolSet.pickaxe() != null) buildPickaxeRecipes(consumer, toolSet.pickaxe(), baseItem);
		if (toolSet.axe() != null) buildAxeRecipes(consumer, toolSet.axe(), baseItem);
		if (toolSet.sword() != null) buildSwordRecipes(consumer, toolSet.sword(), baseItem);
		if (toolSet.shovel() != null) buildShovelRecipes(consumer, toolSet.shovel(), baseItem);
		if (toolSet.hoe() != null) buildHoeRecipes(consumer, toolSet.hoe(), baseItem);
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

	private void buildHoeRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<GenerationsHoeItem> hoe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XX")
				.pattern(" #")
				.pattern(" #")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildShovelRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<GenerationsShovelItem> shovel, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("X")
				.pattern("#")
				.pattern("#")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildSwordRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<GenerationsSwordItem> sword, ItemLike baseItem) {
	ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword.get())
			.define('X', baseItem)
			.define('#', Items.STICK)
			.pattern("X")
			.pattern("X")
			.pattern("#")
			.unlockedBy(getHasName(baseItem), has(baseItem))
			.save(consumer);
	}

	private void buildAxeRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<GenerationsAxeItem> axe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XX")
				.pattern("#X")
				.pattern("# ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildPickaxeRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<GenerationsPickaxeItem> pickaxe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XXX")
				.pattern(" # ")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildHelmetRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, RegistrySupplier<Item> helmet){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet.get())
				.define('X', baseItem)
				.pattern("XXX")
				.pattern("X X")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildChestplateRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, RegistrySupplier<Item> chestplate){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate.get())
				.define('X', baseItem)
				.pattern("X X")
				.pattern("XXX")
				.pattern("XXX")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildLeggingsRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, RegistrySupplier<Item> leggings){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings.get())
				.define('X', baseItem)
				.pattern("XXX")
				.pattern("X X")
				.pattern("X X")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildBootsRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, RegistrySupplier<Item> boots){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots.get())
				.define('X', baseItem)
				.pattern("X X")
				.pattern("X X")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}
	
	//Modified Armor Recipes
	private void buildModifiedArmorSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike itemTop, ItemLike itemBottom, RegistrySupplier<Item> helmet, RegistrySupplier<Item> chestplate, RegistrySupplier<Item> leggings, RegistrySupplier<Item> boots) {
		if (helmet != null) buildModifiedHelmetRecipes(consumer, itemTop, itemBottom, helmet);
		if (chestplate != null) buildModifiedChestplateRecipes(consumer, itemTop, itemBottom, chestplate);
		if (leggings != null) buildModifiedLeggingsRecipes(consumer, itemTop, itemBottom, leggings);
		if (boots != null) buildModifiedBootsRecipes(consumer, itemTop, itemBottom, boots);
	}

	private void buildModifiedHelmetRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike item1, ItemLike item2, RegistrySupplier<Item> helmet){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet.get())
				.define('X', item1)
				.define('Y', item2)
				.pattern("XXX")
				.pattern("Y Y")
				.unlockedBy(getHasName(item1), has(item1))
				.unlockedBy(getHasName(item2), has(item2))
				.save(consumer);
	}

	private void buildModifiedChestplateRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike item1, ItemLike item2, RegistrySupplier<Item> chestplate){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate.get())
				.define('X', item1)
				.define('Y', item2)
				.pattern("X X")
				.pattern("XXX")
				.pattern("YYY")
				.unlockedBy(getHasName(item1), has(item1))
				.unlockedBy(getHasName(item2), has(item2))
				.save(consumer);
	}

	private void buildModifiedLeggingsRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike item1, ItemLike item2, RegistrySupplier<Item> leggings){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings.get())
				.define('X', item1)
				.define('Y', item2)
				.pattern("XXX")
				.pattern("X X")
				.pattern("Y Y")
				.unlockedBy(getHasName(item1), has(item1))
				.unlockedBy(getHasName(item2), has(item2))
				.save(consumer);
	}

	private void buildModifiedBootsRecipes(@NotNull Consumer<FinishedRecipe> consumer, ItemLike item1, ItemLike item2, RegistrySupplier<Item> boots){
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots.get())
				.define('X', item1)
				.define('Y', item2)
				.pattern("X X")
				.pattern("Y Y")
				.unlockedBy(getHasName(item1), has(item1))
				.unlockedBy(getHasName(item2), has(item2))
				.save(consumer);
	}
}
