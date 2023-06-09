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

	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
		buildToolSetCrafting(consumer, GenerationsBlocks.CHARGE_COBBLESTONE.get(), GenerationsTools.CHARGE_STONE);
		buildToolSetCrafting(consumer, GenerationsBlocks.VOLCANIC_COBBLESTONE.get(), GenerationsTools.VOLCANIC_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ALUMINUM_INGOT.get(), GenerationsArmor.ALUMINUM_HELMET, GenerationsArmor.ALUMINUM_CHESTPLATE, GenerationsArmor.ALUMINUM_LEGGINGS, GenerationsArmor.ALUMINUM_BOOTS, GenerationsTools.ALUMINUM);
		buildArmorToolFullSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsArmor.ROCKET_HELMET, GenerationsArmor.ROCKET_CHESTPLATE, GenerationsArmor.ROCKET_LEGGINGS, GenerationsArmor.ROCKET_BOOTS, GenerationsTools.AMETHYST);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsArmor.CRYSTAL_HELMET, GenerationsArmor.CRYSTAL_CHESTPLATE, GenerationsArmor.CRYSTAL_LEGGINGS, GenerationsArmor.CRYSTAL_BOOTS, GenerationsTools.CRYSTAL);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SAPPHIRE.get(), GenerationsArmor.AQUA_HELMET, GenerationsArmor.AQUA_CHESTPLATE, GenerationsArmor.AQUA_LEGGINGS, GenerationsArmor.AQUA_BOOTS, GenerationsTools.SAPHIRE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsArmor.MAGMA_HELMET, GenerationsArmor.MAGMA_CHESTPLATE, GenerationsArmor.MAGMA_LEGGINGS, GenerationsArmor.MAGMA_BOOTS, GenerationsTools.RUBY);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsArmor.GALACTIC_HELMET, GenerationsArmor.GALACTIC_CHESTPLATE, GenerationsArmor.GALACTIC_LEGGINGS, GenerationsArmor.GALACTIC_BOOTS, GenerationsTools.SILICON);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DAWN_STONE_SHARD.get(), GenerationsArmor.DAWN_STONE_HELMET, GenerationsArmor.DAWN_STONE_CHESTPLATE, GenerationsArmor.DAWN_STONE_LEGGINGS, GenerationsArmor.DAWN_STONE_BOOTS, GenerationsTools.DAWN_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DUSK_STONE_SHARD.get(), GenerationsArmor.DUSK_STONE_HELMET, GenerationsArmor.DUSK_STONE_CHESTPLATE, GenerationsArmor.DUSK_STONE_LEGGINGS, GenerationsArmor.DUSK_STONE_BOOTS, GenerationsTools.DUSK_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.FIRE_STONE_SHARD.get(), GenerationsArmor.FIRE_STONE_HELMET, GenerationsArmor.FIRE_STONE_CHESTPLATE, GenerationsArmor.FIRE_STONE_LEGGINGS, GenerationsArmor.FIRE_STONE_BOOTS, GenerationsTools.FIRE_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ICE_STONE_SHARD.get(), GenerationsArmor.ICE_STONE_HELMET, GenerationsArmor.ICE_STONE_CHESTPLATE, GenerationsArmor.ICE_STONE_LEGGINGS, GenerationsArmor.ICE_STONE_BOOTS, GenerationsTools.ICE_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.LEAF_STONE_SHARD.get(), GenerationsArmor.LEAF_STONE_HELMET, GenerationsArmor.LEAF_STONE_CHESTPLATE, GenerationsArmor.LEAF_STONE_LEGGINGS, GenerationsArmor.LEAF_STONE_BOOTS, GenerationsTools.LEAF_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SUN_STONE_SHARD.get(), GenerationsArmor.SUN_STONE_HELMET, GenerationsArmor.SUN_STONE_CHESTPLATE, GenerationsArmor.SUN_STONE_LEGGINGS, GenerationsArmor.SUN_STONE_BOOTS, GenerationsTools.SUN_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.MOON_STONE_SHARD.get(), GenerationsArmor.MOON_STONE_HELMET, GenerationsArmor.MOON_STONE_CHESTPLATE, GenerationsArmor.MOON_STONE_LEGGINGS, GenerationsArmor.MOON_STONE_BOOTS, GenerationsTools.MOON_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.THUNDER_STONE_SHARD.get(), GenerationsArmor.THUNDER_STONE_HELMET, GenerationsArmor.THUNDER_STONE_CHESTPLATE, GenerationsArmor.THUNDER_STONE_LEGGINGS, GenerationsArmor.THUNDER_STONE_BOOTS, GenerationsTools.THUNDER_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.WATER_STONE_SHARD.get(), GenerationsArmor.WATER_STONE_HELMET, GenerationsArmor.WATER_STONE_CHESTPLATE, GenerationsArmor.WATER_STONE_LEGGINGS, GenerationsArmor.WATER_STONE_BOOTS, GenerationsTools.WATER_STONE);

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
				.pattern("XX ")
				.pattern(" # ")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildShovelRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<GenerationsShovelItem> shovel, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern(" X ")
				.pattern(" # ")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildSwordRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<GenerationsSwordItem> sword, ItemLike baseItem) {
	ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword.get())
			.define('X', baseItem)
			.define('#', Items.STICK)
			.pattern(" X ")
			.pattern(" X ")
			.pattern(" # ")
			.unlockedBy(getHasName(baseItem), has(baseItem))
			.save(consumer);
	}

	private void buildAxeRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<GenerationsAxeItem> axe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern(" XX")
				.pattern(" #X")
				.pattern(" # ")
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
}
