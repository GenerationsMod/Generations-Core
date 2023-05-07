package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
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

		buildToolSetCrafting(consumer, GenerationsBlocks.CHARGE_COBBLESTONE.get(), GenerationsTools.CHARGE_STONE_PICKAXE, GenerationsTools.CHARGE_STONE_AXE, GenerationsTools.CHARGE_STONE_SHOVEL, GenerationsTools.CHARGE_STONE_SWORD, GenerationsTools.CHARGE_STONE_HOE, GenerationsTools.CHARGE_STONE_HAMMER);

		buildToolSetCrafting(consumer, GenerationsBlocks.VOLCANIC_COBBLESTONE.get(), GenerationsTools.VOLCANIC_STONE_PICKAXE, GenerationsTools.VOLCANIC_STONE_AXE, GenerationsTools.VOLCANIC_STONE_SHOVEL, GenerationsTools.VOLCANIC_STONE_SWORD, GenerationsTools.VOLCANIC_STONE_HOE, GenerationsTools.VOLCANIC_STONE_HAMMER);

		buildArmorToolFullSetCrafting(consumer, GenerationsItems.ALUMINUM_INGOT.get(), GenerationsArmor.ALUMINUM_HELMET, GenerationsArmor.ALUMINUM_CHESTPLATE, GenerationsArmor.ALUMINUM_LEGGINGS, GenerationsArmor.ALUMINUM_BOOTS, GenerationsTools.ALUMINUM_PICKAXE, GenerationsTools.ALUMINUM_AXE, GenerationsTools.ALUMINUM_SWORD, GenerationsTools.ALUMINUM_SHOVEL, GenerationsTools.ALUMINUM_HOE, GenerationsTools.ALUMINUM_HAMMER);

		buildArmorToolFullSetCrafting(consumer, Items.AMETHYST_SHARD, GenerationsArmor.ROCKET_HELMET, GenerationsArmor.ROCKET_CHESTPLATE, GenerationsArmor.ROCKET_LEGGINGS, GenerationsArmor.ROCKET_BOOTS, GenerationsTools.AMETHYST_PICKAXE, GenerationsTools.AMETHYST_AXE, GenerationsTools.AMETHYST_SWORD, GenerationsTools.AMETHYST_SHOVEL, GenerationsTools.AMETHYST_HOE, GenerationsTools.AMETHYST_HAMMER);

		buildArmorToolFullSetCrafting(consumer, GenerationsItems.CRYSTAL.get(), GenerationsArmor.CRYSTAL_HELMET, GenerationsArmor.CRYSTAL_CHESTPLATE, GenerationsArmor.CRYSTAL_LEGGINGS, GenerationsArmor.CRYSTAL_BOOTS, GenerationsTools.CRYSTAL_PICKAXE, GenerationsTools.CRYSTAL_AXE, GenerationsTools.CRYSTAL_SWORD, GenerationsTools.CRYSTAL_SHOVEL, GenerationsTools.CRYSTAL_HOE, GenerationsTools.CRYSTAL_HAMMER);

		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SAPPHIRE.get(), GenerationsArmor.AQUA_HELMET, GenerationsArmor.AQUA_CHESTPLATE, GenerationsArmor.AQUA_LEGGINGS, GenerationsArmor.AQUA_BOOTS, GenerationsTools.SAPPHIRE_PICKAXE, GenerationsTools.SAPPHIRE_AXE, GenerationsTools.SAPPHIRE_SWORD, GenerationsTools.SAPPHIRE_SHOVEL, GenerationsTools.SAPPHIRE_HOE, GenerationsTools.SAPPHIRE_HAMMER);

		buildArmorToolFullSetCrafting(consumer, GenerationsItems.RUBY.get(), GenerationsArmor.MAGMA_HELMET, GenerationsArmor.MAGMA_CHESTPLATE, GenerationsArmor.MAGMA_LEGGINGS, GenerationsArmor.MAGMA_BOOTS, GenerationsTools.RUBY_PICKAXE, GenerationsTools.RUBY_AXE, GenerationsTools.RUBY_SWORD, GenerationsTools.RUBY_SHOVEL, GenerationsTools.RUBY_HOE, GenerationsTools.RUBY_HAMMER);

		buildArmorToolFullSetCrafting(consumer, GenerationsItems.SILICON.get(), GenerationsArmor.GALACTIC_HELMET, GenerationsArmor.GALACTIC_CHESTPLATE, GenerationsArmor.GALACTIC_LEGGINGS, GenerationsArmor.GALACTIC_BOOTS, GenerationsTools.SILICON_PICKAXE, GenerationsTools.SILICON_AXE, GenerationsTools.SILICON_SWORD, GenerationsTools.SILICON_SHOVEL, GenerationsTools.SILICON_HOE, GenerationsTools.SILICON_HAMMER);

		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DAWN_STONE_SHARD.get(), GenerationsArmor.DAWN_STONE_HELMET, GenerationsArmor.DAWN_STONE_CHESTPLATE, GenerationsArmor.DAWN_STONE_LEGGINGS, GenerationsArmor.DAWN_STONE_BOOTS, GenerationsTools.DAWN_STONE_PICKAXE, GenerationsTools.DAWN_STONE_AXE, GenerationsTools.DAWN_STONE_SWORD, GenerationsTools.DAWN_STONE_SHOVEL, GenerationsTools.DAWN_STONE_HOE, GenerationsTools.DAWN_STONE_HAMMER);

		buildArmorToolFullSetCrafting(consumer, GenerationsItems.DUSK_STONE_SHARD.get(), GenerationsArmor.DUSK_STONE_HELMET, GenerationsArmor.DUSK_STONE_CHESTPLATE, GenerationsArmor.DUSK_STONE_LEGGINGS, GenerationsArmor.DUSK_STONE_BOOTS, GenerationsTools.DUSK_STONE_PICKAXE, GenerationsTools.DUSK_STONE_AXE, GenerationsTools.DUSK_STONE_SWORD, GenerationsTools.DUSK_STONE_SHOVEL, GenerationsTools.DUSK_STONE_HOE, GenerationsTools.DUSK_STONE_HAMMER);
	}

	private void buildArmorToolFullSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, RegistrySupplier<Item> helmet, RegistrySupplier<Item> chestplate, RegistrySupplier<Item> leggings, RegistrySupplier<Item> boots, RegistrySupplier<Item> pickaxe, RegistrySupplier<Item> axe, RegistrySupplier<Item> sword, RegistrySupplier<Item> shovel, RegistrySupplier<Item> hoe, RegistrySupplier<Item> hammer) {
		buildArmorSetCrafting(consumer, baseItem, helmet, chestplate, leggings, boots);
		buildToolSetCrafting(consumer, baseItem, pickaxe, axe, sword, shovel, hoe, hammer);
	}

	private void buildArmorSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike baseItem, RegistrySupplier<Item> helmet, RegistrySupplier<Item> chestplate, RegistrySupplier<Item> leggings, RegistrySupplier<Item> boots) {
		if (helmet != null) buildHelmetRecipes(consumer, baseItem, helmet);
		if (chestplate != null) buildChestplateRecipes(consumer, baseItem, chestplate);
		if (leggings != null) buildLeggingsRecipes(consumer, baseItem, leggings);
		if (boots != null) buildBootsRecipes(consumer, baseItem, boots);
	}

	private void buildToolSetCrafting(@NotNull Consumer<FinishedRecipe> consumer, @NotNull ItemLike baseItem, RegistrySupplier<Item> pickaxe, RegistrySupplier<Item> axe, RegistrySupplier<Item> sword, RegistrySupplier<Item> shovel, RegistrySupplier<Item> hoe, RegistrySupplier<Item> hammer) {
		if (pickaxe != null) buildPickaxeRecipes(consumer, pickaxe, baseItem);
		if (axe != null) buildAxeRecipes(consumer, axe, baseItem);
		if (sword != null) buildSwordRecipes(consumer, sword, baseItem);
		if (shovel != null) buildShovelRecipes(consumer, shovel, baseItem);
		if (hoe != null) buildHoeRecipes(consumer, hoe, baseItem);
		if (hammer != null) buildHammerRecipes(consumer, hammer, baseItem);
	}



	private void buildHammerRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<Item> hammer, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hammer.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XXX")
				.pattern("X#X")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildHoeRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<Item> hoe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern("XX ")
				.pattern(" # ")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildShovelRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<Item> shovel, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern(" X ")
				.pattern(" # ")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildSwordRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<Item> sword, ItemLike baseItem) {
	ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword.get())
			.define('X', baseItem)
			.define('#', Items.STICK)
			.pattern(" X ")
			.pattern(" X ")
			.pattern(" # ")
			.unlockedBy(getHasName(baseItem), has(baseItem))
			.save(consumer);
	}

	private void buildAxeRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<Item> axe, ItemLike baseItem) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe.get())
				.define('X', baseItem)
				.define('#', Items.STICK)
				.pattern(" XX")
				.pattern(" #X")
				.pattern(" # ")
				.unlockedBy(getHasName(baseItem), has(baseItem))
				.save(consumer);
	}

	private void buildPickaxeRecipes(@NotNull Consumer<FinishedRecipe> consumer, RegistrySupplier<Item> pickaxe, ItemLike baseItem) {
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
