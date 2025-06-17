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
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import net.minecraft.core.Holder
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
            GenerationsItems.CRYSTAL.value(),
            GenerationsArmor.CRYSTALLIZED,
            GenerationsTools.CRYSTAL
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            GenerationsItems.SAPPHIRE.value(),
            GenerationsArmor.AQUA,
            GenerationsTools.SAPPHIRE
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            GenerationsItems.RUBY.value(),
            GenerationsArmor.MAGMA,
            GenerationsTools.RUBY
        )
        buildArmorToolFullSetCrafting(
            recipeOutput,
            GenerationsItems.SILICON.value(),
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
        buildArmorSetCrafting(recipeOutput, GenerationsItems.Z_INGOT.value(), GenerationsArmor.ULTRA)
        //Modified Armor Recipes
        buildModifiedArmorSetCrafting(
            recipeOutput,
            Items.COPPER_INGOT,
            GenerationsItems.CRYSTAL.value(),
            GenerationsArmor.AETHER.helmet,
            GenerationsArmor.AETHER.chestplate,
            GenerationsArmor.AETHER.leggings,
            GenerationsArmor.AETHER.boots
        )
        buildModifiedArmorSetCrafting(
            recipeOutput,
            GenerationsItems.RUBY.value(),
            FIRE_STONE.asItem(),
            GenerationsArmor.FLARE.helmet,
            GenerationsArmor.FLARE.chestplate,
            GenerationsArmor.FLARE.leggings,
            GenerationsArmor.FLARE.boots
        )
        buildModifiedArmorSetCrafting(
            recipeOutput,
            GenerationsItems.CRYSTAL.value(),
            GenerationsItems.SILICON.value(),
            GenerationsArmor.NEO_PLASMA.helmet,
            GenerationsArmor.NEO_PLASMA.chestplate,
            GenerationsArmor.NEO_PLASMA.leggings,
            GenerationsArmor.NEO_PLASMA.boots
        )
        buildModifiedArmorSetCrafting(
            recipeOutput,
            GenerationsItems.SILICON.value(),
            GenerationsItems.CRYSTAL.value(),
            GenerationsArmor.PLASMA.helmet,
            GenerationsArmor.PLASMA.chestplate,
            GenerationsArmor.PLASMA.leggings,
            GenerationsArmor.PLASMA.boots
        ) //check above
        buildModifiedArmorSetCrafting(
            recipeOutput,
            Items.AMETHYST_SHARD,
            GenerationsItems.SILICON.value(),
            GenerationsArmor.SKULL.helmet,
            GenerationsArmor.SKULL.chestplate,
            GenerationsArmor.SKULL.leggings,
            GenerationsArmor.SKULL.boots
        )

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, GenerationsTools.WOODEN_HAMMER.value())
            .define('X', ItemTags.PLANKS)
            .define('#', Items.STICK)
            .pattern("XXX")
            .pattern("X#X")
            .pattern(" # ")
            .unlockedBy(
                getHasName(GenerationsTools.WOODEN_HAMMER.value()),
                RecipeProvider.has(GenerationsTools.WOODEN_HAMMER.value())
            )
            .save(recipeOutput)

        buildHammerRecipes(recipeOutput, GenerationsTools.STONE_HAMMER.value(), Items.COBBLESTONE)
        buildHammerRecipes(recipeOutput, GenerationsTools.IRON_HAMMER.value(), Items.IRON_INGOT)
        buildHammerRecipes(recipeOutput, GenerationsTools.GOLDEN_HAMMER.value(), Items.GOLD_INGOT)
        buildHammerRecipes(recipeOutput, GenerationsTools.DIAMOND_HAMMER.value(), Items.DIAMOND)
        netheriteSmithing(
            recipeOutput,
            GenerationsTools.DIAMOND_HAMMER.value(),
            RecipeCategory.TOOLS,
            GenerationsTools.NETHERITE_HAMMER.value()
        )

        //		ultriteSmithing(consumer, GenerationsTools.NETHERITE_HAMMER, RecipeCategory.TOOLS, GenerationsTools.ULTRITE_HAMMER);
        ultriteSmithing(recipeOutput, Items.NETHERITE_SWORD, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.sword.value())
        ultriteSmithing(recipeOutput, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.shovel.value())
        ultriteSmithing(recipeOutput, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.pickaxe.value())
        ultriteSmithing(recipeOutput, Items.NETHERITE_AXE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.axe.value())
        ultriteSmithing(recipeOutput, Items.NETHERITE_HOE, RecipeCategory.TOOLS, GenerationsTools.ULTRITE.hoe.value())

        ultriteSmithing(recipeOutput, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, GenerationsArmor.ULTRITE.helmet.value())
        ultriteSmithing(
            recipeOutput,
            Items.NETHERITE_CHESTPLATE,
            RecipeCategory.COMBAT,
            GenerationsArmor.ULTRITE.chestplate.value()
        )
        ultriteSmithing(
            recipeOutput,
            Items.NETHERITE_LEGGINGS,
            RecipeCategory.COMBAT,
            GenerationsArmor.ULTRITE.leggings.value()
        )
        ultriteSmithing(recipeOutput, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, GenerationsArmor.ULTRITE.boots.value())
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
        buildHelmetRecipes(consumer, baseItem, armorSet.helmet.value())
        buildChestplateRecipes(consumer, baseItem, armorSet.chestplate.value())
        buildLeggingsRecipes(consumer, baseItem, armorSet.leggings.value())
        buildBootsRecipes(consumer, baseItem, armorSet.boots.value())
    }

    private fun buildToolSetCrafting(consumer: RecipeOutput, baseItem: ItemLike, toolSet: GenerationsTools.ToolSet) {
        if (toolSet.pickaxe.value() != null) buildPickaxeRecipes(consumer, toolSet.pickaxe.value(), baseItem)
        if (toolSet.axe.value() != null) buildAxeRecipes(consumer, toolSet.axe.value(), baseItem)
        if (toolSet.sword.value() != null) buildSwordRecipes(consumer, toolSet.sword.value(), baseItem)
        if (toolSet.shovel.value() != null) buildShovelRecipes(consumer, toolSet.shovel.value(), baseItem)
        if (toolSet.hoe.value() != null) buildHoeRecipes(consumer, toolSet.hoe.value(), baseItem)
        if (toolSet.hammer.value() != null) buildHammerRecipes(consumer, toolSet.hammer.value(), baseItem)
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

    private fun buildHoeRecipes(consumer: RecipeOutput, hoe: Item, baseItem: ItemLike) {
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
        shovel: Item,
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

    private fun buildSwordRecipes(consumer: RecipeOutput, sword: Item, baseItem: ItemLike) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
            .define('X', baseItem)
            .define('#', Items.STICK)
            .pattern("X")
            .pattern("X")
            .pattern("#")
            .unlockedBy(getHasName(baseItem), has(baseItem))
            .save(consumer)
    }

    private fun buildAxeRecipes(consumer: RecipeOutput, axe: Item, baseItem: ItemLike) {
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
        pickaxe: Item,
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
        helmet: Holder<Item>?,
        chestplate: Holder<Item>?,
        leggings: Holder<Item>?,
        boots: Holder<Item>?
    ) {
        if (helmet != null) buildModifiedHelmetRecipes(consumer, itemTop, itemBottom, helmet.value())
        if (chestplate != null) buildModifiedChestplateRecipes(consumer, itemTop, itemBottom, chestplate.value())
        if (leggings != null) buildModifiedLeggingsRecipes(consumer, itemTop, itemBottom, leggings.value())
        if (boots != null) buildModifiedBootsRecipes(consumer, itemTop, itemBottom, boots.value())
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
                Ingredient.of(GenerationsItems.ULTRITE_UPGRADE_SMITHING_TEMPLATE.value()),
                Ingredient.of(ingredientItem),
                Ingredient.of(GenerationsItems.ULTRITE_INGOT.value()),
                category,
                resultItem
            ).unlocks("has_ultrite_ingot", RecipeProvider.has(GenerationsItems.ULTRITE_INGOT.value()))
                .save(finishedRecipeConsumer, getItemName(resultItem) + "_smithing")
        }
    }
}
