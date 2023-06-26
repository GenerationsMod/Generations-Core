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
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ALUMINUM_INGOT.get(), GenerationsArmor.ALUMINUM.helmet(), GenerationsArmor.ALUMINUM.chestplate(), GenerationsArmor.ALUMINUM.leggings(), GenerationsArmor.ALUMINUM.boots(), GenerationsTools.ALUMINUM);
		buildArmorToolFullSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsArmor.ROCKET.helmet(), GenerationsArmor.ROCKET.chestplate(), GenerationsArmor.ROCKET.leggings(), GenerationsArmor.ROCKET.boots(), GenerationsTools.AMETHYST);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsArmor.CRYSTALLIZED.helmet(), GenerationsArmor.CRYSTALLIZED.chestplate(), GenerationsArmor.CRYSTALLIZED.leggings(), GenerationsArmor.CRYSTALLIZED.boots(), GenerationsTools.CRYSTAL);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SAPPHIRE.get(), GenerationsArmor.AQUA.helmet(), GenerationsArmor.AQUA.chestplate(), GenerationsArmor.AQUA.leggings(), GenerationsArmor.AQUA.boots(), GenerationsTools.SAPPHIRE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsArmor.MAGMA.helmet(), GenerationsArmor.MAGMA.chestplate(), GenerationsArmor.MAGMA.leggings(), GenerationsArmor.MAGMA.boots(), GenerationsTools.RUBY);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsArmor.GALACTIC.helmet(), GenerationsArmor.GALACTIC.chestplate(), GenerationsArmor.GALACTIC.leggings(), GenerationsArmor.GALACTIC.boots(), GenerationsTools.SILICON);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DAWN_STONE.get(), GenerationsArmor.DAWN_STONE.helmet(), GenerationsArmor.DAWN_STONE.chestplate(), GenerationsArmor.DAWN_STONE.leggings(), GenerationsArmor.DAWN_STONE.boots(), GenerationsTools.DAWN_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DUSK_STONE.get(), GenerationsArmor.DUSK_STONE.helmet(), GenerationsArmor.DUSK_STONE.chestplate(), GenerationsArmor.DUSK_STONE.leggings(), GenerationsArmor.DUSK_STONE.boots(), GenerationsTools.DUSK_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.FIRE_STONE.get(), GenerationsArmor.FIRE_STONE.helmet(), GenerationsArmor.FIRE_STONE.chestplate(), GenerationsArmor.FIRE_STONE.leggings(), GenerationsArmor.FIRE_STONE.boots(), GenerationsTools.FIRE_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ICE_STONE.get(), GenerationsArmor.ICE_STONE.helmet(), GenerationsArmor.ICE_STONE.chestplate(), GenerationsArmor.ICE_STONE.leggings(), GenerationsArmor.ICE_STONE.boots(), GenerationsTools.ICE_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.LEAF_STONE.get(), GenerationsArmor.LEAF_STONE.helmet(), GenerationsArmor.LEAF_STONE.chestplate(), GenerationsArmor.LEAF_STONE.leggings(), GenerationsArmor.LEAF_STONE.boots(), GenerationsTools.LEAF_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SUN_STONE.get(), GenerationsArmor.SUN_STONE.helmet(), GenerationsArmor.SUN_STONE.chestplate(), GenerationsArmor.SUN_STONE.leggings(), GenerationsArmor.SUN_STONE.boots(), GenerationsTools.SUN_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.MOON_STONE.get(), GenerationsArmor.MOON_STONE.helmet(), GenerationsArmor.MOON_STONE.chestplate(), GenerationsArmor.MOON_STONE.leggings(), GenerationsArmor.MOON_STONE.boots(), GenerationsTools.MOON_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.THUNDER_STONE.get(), GenerationsArmor.THUNDER_STONE.helmet(), GenerationsArmor.THUNDER_STONE.chestplate(), GenerationsArmor.THUNDER_STONE.leggings(), GenerationsArmor.THUNDER_STONE.boots(), GenerationsTools.THUNDER_STONE);
		buildArmorToolFullSetCrafting(consumer, GenerationsItems.WATER_STONE.get(), GenerationsArmor.WATER_STONE.helmet(), GenerationsArmor.WATER_STONE.chestplate(), GenerationsArmor.WATER_STONE.leggings(), GenerationsArmor.WATER_STONE.boots(), GenerationsTools.WATER_STONE);
		buildArmorSetCrafting(consumer, GenerationsItems.Z_INGOT.get(), GenerationsArmor.ULTRA.helmet(), GenerationsArmor.ULTRA.chestplate(), GenerationsArmor.ULTRA.leggings(), GenerationsArmor.ULTRA.boots());
		//Modified Armor Recipes
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.ALUMINUM_INGOT.get(), GenerationsItems.CRYSTAL.get(), GenerationsArmor.AETHER.helmet(), GenerationsArmor.AETHER.chestplate(), GenerationsArmor.AETHER.leggings(), GenerationsArmor.AETHER.boots());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsItems.FIRE_STONE.get(), GenerationsArmor.FLARE.helmet(), GenerationsArmor.FLARE.chestplate(), GenerationsArmor.FLARE.leggings(), GenerationsArmor.FLARE.boots());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsItems.SILICON.get(), GenerationsArmor.NEO_PLASMA.helmet(), GenerationsArmor.NEO_PLASMA.chestplate(), GenerationsArmor.NEO_PLASMA.leggings(), GenerationsArmor.NEO_PLASMA.boots());
		buildModifiedArmorSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsItems.CRYSTAL.get(), GenerationsArmor.PLASMA.helmet(), GenerationsArmor.PLASMA.chestplate(), GenerationsArmor.PLASMA.leggings(), GenerationsArmor.PLASMA.boots());  //check above
		buildModifiedArmorSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsItems.SILICON.get(), GenerationsArmor.SKULL.helmet(), GenerationsArmor.SKULL.chestplate(), GenerationsArmor.SKULL.leggings(), GenerationsArmor.SKULL.boots());

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
