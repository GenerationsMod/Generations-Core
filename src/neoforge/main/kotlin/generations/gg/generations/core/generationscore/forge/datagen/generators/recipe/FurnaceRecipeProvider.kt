package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import com.google.common.collect.ImmutableList
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsOres
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.common.conditions.IConditionBuilder
import java.util.concurrent.CompletableFuture

//TODO: Proper RecipeCategory assignment
class FurnaceRecipeProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : GenerationsRecipeProvider.Proxied(output, registries), IConditionBuilder {
    override fun buildRecipes(recipeOutput: RecipeOutput) {
        oreSmelting(
            recipeOutput,
            MEGASTONE_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.MEGASTONE_SHARD.value(),
            1.0f,
            200,
            "mega_stone_shard"
        )
        oreSmelting(
            recipeOutput,
            METEORITE_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.METEORITE_SHARD.value(),
            1.0f,
            200,
            "meteorite_shard"
        )
        oreSmelting(
            recipeOutput,
            Z_CRYSTAL_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.Z_INGOT.value(),
            1.0f,
            200,
            "z_ingot"
        )
        oreSmelting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, GenerationsItems.RUBY.value(), 1.0f, 200, "ruby_gem")
        oreSmelting(
            recipeOutput,
            SAPPHIRE_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.SAPPHIRE.value(),
            1.0f,
            200,
            "sapphire_gem"
        )
        oreSmelting(
            recipeOutput,
            CRYSTAL_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.CRYSTAL.value(),
            1.0f,
            200,
            "crystal_gem"
        )
        oreSmelting(
            recipeOutput,
            SILICON_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.SILICON.value(),
            1.0f,
            200,
            "silicon"
        )
        oreBlasting(
            recipeOutput,
            Z_CRYSTAL_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.Z_INGOT.value(),
            1.0f,
            100,
            "z_ingot"
        )
        oreBlasting(recipeOutput, RUBY_SMELTABLES, RecipeCategory.MISC, GenerationsItems.RUBY.value(), 1.0f, 100, "ruby_gem")
        oreBlasting(
            recipeOutput,
            SAPPHIRE_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.SAPPHIRE.value(),
            1.0f,
            100,
            "sapphire_gem"
        )
        oreBlasting(
            recipeOutput,
            CRYSTAL_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.CRYSTAL.value(),
            1.0f,
            100,
            "crystal_gem"
        )
        oreBlasting(
            recipeOutput,
            SILICON_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.SILICON.value(),
            1.0f,
            100,
            "silicon"
        )
        oreBlasting(
            recipeOutput,
            MEGASTONE_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.MEGASTONE_SHARD.value(),
            1.0f,
            100,
            "mega_stone_shard"
        )
        oreBlasting(
            recipeOutput,
            METEORITE_SMELTABLES,
            RecipeCategory.MISC,
            GenerationsItems.METEORITE_SHARD.value(),
            1.0f,
            100,
            "meteorite_shard"
        )

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock),
            RecipeCategory.MISC,
            GenerationsBlocks.CHARGE_STONE_SET.baseBlock,
            0.1f,
            200
        ).unlockedBy("has_charge_stone_cobble", has(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock))
            .save(recipeOutput)
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(GenerationsBlocks.CHARGE_STONE_SET.baseBlock),
            RecipeCategory.MISC,
            GenerationsBlocks.SMOOTH_CHARGE_STONE.value(),
            0.1f,
            200
        ).unlockedBy("has_charge_stone", has(GenerationsBlocks.CHARGE_STONE_SET.baseBlock)).save(recipeOutput)
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock),
            RecipeCategory.MISC,
            GenerationsBlocks.VOLCANIC_STONE.value(),
            0.1f,
            200
        ).unlockedBy("has_volcanic_stone", has(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock))
            .save(recipeOutput)
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(GenerationsBlocks.VOLCANIC_STONE.value()),
            RecipeCategory.MISC,
            GenerationsBlocks.SMOOTH_VOLCANIC_STONE.value(),
            0.1f,
            200
        ).unlockedBy("has_volcanic_stone", has(GenerationsBlocks.VOLCANIC_STONE.value())).save(recipeOutput)

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(GenerationsBlocks.ULTRA_SANDSTONE.value()),
            RecipeCategory.MISC,
            GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE.value(),
            0.1f,
            200
        ).unlockedBy(
            getHasName(GenerationsBlocks.ULTRA_SANDSTONE.value()), has(GenerationsBlocks.ULTRA_SANDSTONE.value())
        ).save(recipeOutput)
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.value()),
            RecipeCategory.MISC,
            GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE.value(),
            0.1f,
            200
        ).unlockedBy(
            getHasName(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.value()),
            has(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.value())
        ).save(recipeOutput)

        //ChargeStone Ores
        /*
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_COAL_ORE), RecipeCategory.MISC, Items.COAL, 0.1f, 200, "coal");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_IRON_ORE), RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_COPPER_ORE), RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_GOLD_ORE), RecipeCategory.MISC, Items.GOLD_INGOT, 1.0f, 200, "gold_ingot");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_DIAMOND_ORE), RecipeCategory.MISC, Items.DIAMOND, 1.0f, 200, "diamond");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE), RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2f, 200, "lapis_lazuli");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_REDSTONE_ORE), RecipeCategory.REDSTONE, Items.REDSTONE, 0.7f, 200, "redstone");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_EMERALD_ORE), RecipeCategory.MISC, Items.EMERALD, 1.0f, 200, "emerald");

        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_COAL_ORE), RecipeCategory.MISC, Items.COAL, 0.1f, 100, "coal");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_IRON_ORE), RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 100, "iron_ingot");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_COPPER_ORE), RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 100, "copper_ingot");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_GOLD_ORE), RecipeCategory.MISC, Items.GOLD_INGOT, 1.0f, 100, "gold_ingot");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_DIAMOND_ORE), RecipeCategory.MISC, Items.DIAMOND, 1.0f, 100, "diamond");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE), RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2f, 100, "lapis_lazuli");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_REDSTONE_ORE), RecipeCategory.REDSTONE, Items.REDSTONE, 0.7f, 100, "redstone");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_EMERALD_ORE), RecipeCategory.MISC, Items.EMERALD, 1.0f, 100, "emerald");
         */
    }

    companion object {
        private val Z_CRYSTAL_SMELTABLES: ImmutableList<ItemLike> = GenerationsOres.Z_CRYSTAL_ORE_SET.immutableList
        private val RUBY_SMELTABLES: ImmutableList<ItemLike> = GenerationsOres.RUBY_ORE_SET.immutableList
        private val SAPPHIRE_SMELTABLES: ImmutableList<ItemLike> = GenerationsOres.SAPPHIRE_ORE_SET.immutableList
        private val CRYSTAL_SMELTABLES: ImmutableList<ItemLike> = GenerationsOres.CRYSTAL_ORE_SET.immutableList
        private val SILICON_SMELTABLES: ImmutableList<ItemLike> = GenerationsOres.SILICON_ORE_SET.immutableList
        private val MEGASTONE_SMELTABLES: ImmutableList<ItemLike> = GenerationsOres.MEGASTONE_ORE_SET.immutableList
        private val METEORITE_SMELTABLES: ImmutableList<ItemLike> = GenerationsOres.METEORITE_ORE_SET.immutableList
    }
}
