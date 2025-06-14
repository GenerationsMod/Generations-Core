package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import com.cobblemon.mod.common.CobblemonItems.DAWN_STONE
import com.cobblemon.mod.common.CobblemonItems.DUSK_STONE
import com.cobblemon.mod.common.CobblemonItems.FIRE_STONE
import com.cobblemon.mod.common.CobblemonItems.ICE_STONE
import com.cobblemon.mod.common.CobblemonItems.LEAF_STONE
import com.cobblemon.mod.common.CobblemonItems.MOON_STONE
import com.cobblemon.mod.common.CobblemonItems.SUN_STONE
import com.cobblemon.mod.common.CobblemonItems.THUNDER_STONE
import com.cobblemon.mod.common.CobblemonItems.WATER_STONE
import generations.gg.generations.core.generationscore.common.world.item.GenerationsArmor
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.GenerationsTools
import generations.gg.generations.core.generationscore.common.world.item.tools.*
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.common.conditions.IConditionBuilder
import java.util.concurrent.CompletableFuture

class GenerationsArmorToolRecipeDatagen(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : GenerationsRecipeProvider.Proxied(output, registries), IConditionBuilder {
    //AETHER, FLARE, NEO



    override fun buildRecipes(recipeOutput: RecipeOutput) {
        buildToolSetCrafting(
            recipeOutput,
            GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock,
            GenerationsTools.CHARGE_STONE
        )
        buildToolSetCrafting(
            recipeOutput,
            GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock,
            GenerationsTools.VOLCANIC_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            Items.AMETHYST_SHARD,
            GenerationsArmor.ROCKET,
            GenerationsTools.AMETHYST
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            GenerationsItems.CRYSTAL.get(),
            GenerationsArmor.CRYSTALLIZED,
            GenerationsTools.CRYSTAL
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            GenerationsItems.SAPPHIRE.get(),
            GenerationsArmor.AQUA,
            GenerationsTools.SAPPHIRE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            GenerationsItems.RUBY.get(),
            GenerationsArmor.MAGMA,
            GenerationsTools.RUBY
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            GenerationsItems.SILICON.get(),
            GenerationsArmor.GALACTIC,
            GenerationsTools.SILICON
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            DAWN_STONE.asItem(),
            GenerationsArmor.DAWN_STONE,
            GenerationsTools.DAWN_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            DUSK_STONE.asItem(),
            GenerationsArmor.DUSK_STONE,
            GenerationsTools.DUSK_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            FIRE_STONE.asItem(),
            GenerationsArmor.FIRE_STONE,
            GenerationsTools.FIRE_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            ICE_STONE.asItem(),
            GenerationsArmor.ICE_STONE,
            GenerationsTools.ICE_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            LEAF_STONE.asItem(),
            GenerationsArmor.LEAF_STONE,
            GenerationsTools.LEAF_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            SUN_STONE.asItem(),
            GenerationsArmor.SUN_STONE,
            GenerationsTools.SUN_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            MOON_STONE.asItem(),
            GenerationsArmor.MOON_STONE,
            GenerationsTools.MOON_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            THUNDER_STONE.asItem(),
            GenerationsArmor.THUNDER_STONE,
            GenerationsTools.THUNDER_STONE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            WATER_STONE.asItem(),
            GenerationsArmor.WATER_STONE,
            GenerationsTools.WATER_STONE
        )
        buildArmorSetCrafting(recipeOutput, GenerationsItems.Z_INGOT.get(), GenerationsArmor.ULTRA)
        //Modified Armor Recipes
        buildModifiedArmorSetCrafting(
            recipeOutput,
            Items.COPPER_INGOT,
            GenerationsItems.CRYSTAL.get(),
            GenerationsArmor.AETHER.helmet.get(),
            GenerationsArmor.AETHER.chestplate.get(),
            GenerationsArmor.AETHER.leggings.get(),
            GenerationsArmor.AETHER.boots.get()
        )
        buildModifiedArmorSetCrafting(
            recipeOutput,
            GenerationsItems.RUBY.get(),
            FIRE_STONE.asItem(),
            GenerationsArmor.FLARE.helmet.get(),
            GenerationsArmor.FLARE.chestplate.get(),
            GenerationsArmor.FLARE.leggings.get(),
            GenerationsArmor.FLARE.boots.get()
        )
        buildModifiedArmorSetCrafting(
            recipeOutput,
            GenerationsItems.CRYSTAL.get(),
            GenerationsItems.SILICON.get(),
            GenerationsArmor.NEO_PLASMA.helmet.get(),
            GenerationsArmor.NEO_PLASMA.chestplate.get(),
            GenerationsArmor.NEO_PLASMA.leggings.get(),
            GenerationsArmor.NEO_PLASMA.boots.get()
        )
        buildModifiedArmorSetCrafting(
            recipeOutput,
            GenerationsItems.SILICON.get(),
            GenerationsItems.CRYSTAL.get(),
            GenerationsArmor.PLASMA.helmet.get(),
            GenerationsArmor.PLASMA.chestplate.get(),
            GenerationsArmor.PLASMA.leggings.get(),
            GenerationsArmor.PLASMA.boots.get()
        ) //check above
        buildModifiedArmorSetCrafting(
            recipeOutput,
            Items.AMETHYST_SHARD,
            GenerationsItems.SILICON.get(),
            GenerationsArmor.SKULL.helmet.get(),
            GenerationsArmor.SKULL.chestplate.get(),
            GenerationsArmor.SKULL.leggings.get(),
            GenerationsArmor.SKULL.boots.get()
        )

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, GenerationsTools.WOODEN_HAMMER.get())
            .define('X', ItemTags.PLANKS)
            .define('#', Items.STICK)
            .pattern("XXX")
            .pattern("X#X")
            .pattern(" # ")
            .unlockedBy(
                getHasName(GenerationsTools.WOODEN_HAMMER.get()),
                RecipeProvider.has(GenerationsTools.WOODEN_HAMMER.get())
            )
            .save(recipeOutput)

        buildHammerRecipes(recipeOutput, GenerationsTools.STONE_HAMMER.get(), Items.COBBLESTONE)
        buildHammerRecipes(recipeOutput, GenerationsTools.IRON_HAMMER.get(), Items.IRON_INGOT)
        buildHammerRecipes(recipeOutput, GenerationsTools.GOLDEN_HAMMER.get(), Items.GOLD_INGOT)
        buildHammerRecipes(recipeOutput, GenerationsTools.DIAMOND_HAMMER.get(), Items.DIAMOND)
        netheriteSmithing(
            recipeOutput,
            GenerationsTools.DIAMOND_HAMMER.get(),
            RecipeCategory.TOOLS,
            GenerationsTools.NETHERITE_HAMMER.get()
        )

        //		ultriteSmithing(consumer, GenerationsTools.NETHERITE_HAMMER.get(), RecipeCategory.TOOLS, GenerationsTools.ULTRITE_HAMMER.get());
        ultriteSmithing(recipeOutput, Items.NETHERITE_SWORD, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.sword.get())
        ultriteSmithing(recipeOutput, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.shovel.get())
        ultriteSmithing(recipeOutput, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.pickaxe.get())
        ultriteSmithing(recipeOutput, Items.NETHERITE_AXE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.axe.get())
        ultriteSmithing(recipeOutput, Items.NETHERITE_HOE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.hoe.get())

        ultriteSmithing(recipeOutput, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, GenerationsArmor.ULTRITE.helmet.get())
        ultriteSmithing(
            recipeOutput,
            Items.NETHERITE_CHESTPLATE,
            RecipeCategory.COMBAT,
            GenerationsArmor.ULTRITE.chestplate.get()
        )
        ultriteSmithing(
            recipeOutput,
            Items.NETHERITE_LEGGINGS,
            RecipeCategory.COMBAT,
            GenerationsArmor.ULTRITE.leggings.get()
        )
        ultriteSmithing(recipeOutput, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, GenerationsArmor.ULTRITE.boots.get())
    }

    private fun buildArmorToolFullSetCrafting(
        consumer: RecipeOutput,
        baseItem: ItemLike,
        armorSet: GenerationsArmor.ArmorSet,
        toolSet: GenerationsTools.ToolSet
    ) {
        buildArmorSetCrafting(consumer, baseItem, armorSet)
        buildToolSetCrafting(consumer, baseItem, toolSet)
    }

    private fun buildArmorSetCrafting(consumer: RecipeOutput, baseItem: ItemLike, armorSet: GenerationsArmor.ArmorSet) {
        buildHelmetRecipes(consumer, baseItem, armorSet.helmet.get())
        buildChestplateRecipes(consumer, baseItem, armorSet.chestplate.get())
        buildLeggingsRecipes(consumer, baseItem, armorSet.leggings.get())
        buildBootsRecipes(consumer, baseItem, armorSet.boots.get())
    }

    private fun buildToolSetCrafting(consumer: RecipeOutput, baseItem: ItemLike, toolSet: GenerationsTools.ToolSet) {
        if (toolSet.pickaxe != null) buildPickaxeRecipes(consumer, toolSet.pickaxe.get(), baseItem)
        if (toolSet.axe != null) buildAxeRecipes(consumer, toolSet.axe.get(), baseItem)
        if (toolSet.sword != null) buildSwordRecipes(consumer, toolSet.sword.get(), baseItem)
        if (toolSet.shovel != null) buildShovelRecipes(consumer, toolSet.shovel.get(), baseItem)
        if (toolSet.hoe != null) buildHoeRecipes(consumer, toolSet.hoe.get(), baseItem)
        if (toolSet.hammer != null) buildHammerRecipes(consumer, toolSet.hammer.get(), baseItem)
    }

    private fun buildHammerRecipes(consumer: RecipeOutput, hammer: Item, baseItem: ItemLike) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hammer)
            .define('X', baseItem)
            .define('#', Items.STICK)
            .pattern("XXX")
            .pattern("X#X")
            .pattern(" # ")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildHoeRecipes(consumer: RecipeOutput, hoe: GenerationsHoeItem, baseItem: ItemLike) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
            .define('X', baseItem)
            .define('#', Items.STICK)
            .pattern("XX")
            .pattern(" #")
            .pattern(" #")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildShovelRecipes(
        consumer: RecipeOutput,
        shovel: GenerationsShovelItem,
        baseItem: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
            .define('X', baseItem)
            .define('#', Items.STICK)
            .pattern("X")
            .pattern("#")
            .pattern("#")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildSwordRecipes(consumer: RecipeOutput, sword: GenerationsSwordItem, baseItem: ItemLike) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
            .define('X', baseItem)
            .define('#', Items.STICK)
            .pattern("X")
            .pattern("X")
            .pattern("#")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildAxeRecipes(consumer: RecipeOutput, axe: GenerationsAxeItem, baseItem: ItemLike) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
            .define('X', baseItem)
            .define('#', Items.STICK)
            .pattern("XX")
            .pattern("#X")
            .pattern("# ")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildPickaxeRecipes(
        consumer: RecipeOutput,
        pickaxe: GenerationsPickaxeItem,
        baseItem: ItemLike
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
            .define('X', baseItem)
            .define('#', Items.STICK)
            .pattern("XXX")
            .pattern(" # ")
            .pattern(" # ")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildHelmetRecipes(consumer: RecipeOutput, baseItem: ItemLike, helmet: Item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
            .define('X', baseItem)
            .pattern("XXX")
            .pattern("X X")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildChestplateRecipes(consumer: RecipeOutput, baseItem: ItemLike, chestplate: Item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
            .define('X', baseItem)
            .pattern("X X")
            .pattern("XXX")
            .pattern("XXX")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildLeggingsRecipes(consumer: RecipeOutput, baseItem: ItemLike, leggings: Item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
            .define('X', baseItem)
            .pattern("XXX")
            .pattern("X X")
            .pattern("X X")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildBootsRecipes(consumer: RecipeOutput, baseItem: ItemLike, boots: Item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
            .define('X', baseItem)
            .pattern("X X")
            .pattern("X X")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    //Modified Armor Recipes
    private fun buildModifiedArmorSetCrafting(
        consumer: RecipeOutput,
        itemTop: ItemLike,
        itemBottom: ItemLike,
        helmet: Item?,
        chestplate: Item?,
        leggings: Item?,
        boots: Item?
    ) {
        if (helmet != null) buildModifiedHelmetRecipes(consumer, itemTop, itemBottom, helmet)
        if (chestplate != null) buildModifiedChestplateRecipes(consumer, itemTop, itemBottom, chestplate)
        if (leggings != null) buildModifiedLeggingsRecipes(consumer, itemTop, itemBottom, leggings)
        if (boots != null) buildModifiedBootsRecipes(consumer, itemTop, itemBottom, boots)
    }

    private fun buildModifiedHelmetRecipes(
        consumer: RecipeOutput,
        item1: ItemLike,
        item2: ItemLike,
        helmet: Item
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
            .define('X', item1)
            .define('Y', item2)
            .pattern("XXX")
            .pattern("Y Y")
            .unlockedBy(getHasName(item1), has(item1))
            .unlockedBy(getHasName(item2), has(item2))
            .save(consumer)
    }

    private fun buildModifiedChestplateRecipes(
        consumer: RecipeOutput,
        item1: ItemLike,
        item2: ItemLike,
        chestplate: Item
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
            .define('X', item1)
            .define('Y', item2)
            .pattern("X X")
            .pattern("XXX")
            .pattern("YYY")
            .unlockedBy(getHasName(item1), has(item1))
            .unlockedBy(getHasName(item2), has(item2))
            .save(consumer)
    }

    private fun buildModifiedLeggingsRecipes(
        consumer: RecipeOutput,
        item1: ItemLike,
        item2: ItemLike,
        leggings: Item
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
            .define('X', item1)
            .define('Y', item2)
            .pattern("XXX")
            .pattern("X X")
            .pattern("Y Y")
            .unlockedBy(getHasName(item1), has(item1))
            .unlockedBy(getHasName(item2), has(item2))
            .save(consumer)
    }

    private fun buildModifiedBootsRecipes(
        consumer: RecipeOutput,
        item1: ItemLike,
        item2: ItemLike,
        boots: Item
    ) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
            .define('X', item1)
            .define('Y', item2)
            .pattern("X X")
            .pattern("Y Y")
            .unlockedBy(getHasName(item1), has(item1))
            .unlockedBy(getHasName(item2), has(item2))
            .save(consumer)
    }

    companion object {
        protected fun ultriteSmithing(
            finishedRecipeConsumer: RecipeOutput,
            ingredientItem: Item,
            category: RecipeCategory,
            resultItem: Item
        ) {
            SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(GenerationsItems.ULTRITE_UPGRADE_SMITHING_TEMPLATE.get()),
                Ingredient.of(ingredientItem),
                Ingredient.of(GenerationsItems.ULTRITE_INGOT.get()),
                category,
                resultItem
            ).unlocks("has_ultrite_ingot", RecipeProvider.has(GenerationsItems.ULTRITE_INGOT.get()))
                .save(finishedRecipeConsumer, getItemName(resultItem) + "_smithing")
        }
    }
}
