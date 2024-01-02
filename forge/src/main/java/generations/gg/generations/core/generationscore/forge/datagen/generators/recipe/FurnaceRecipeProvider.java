package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.google.common.collect.ImmutableList;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

//TODO: Proper RecipeCategory assignment
public class FurnaceRecipeProvider extends GenerationsRecipeProvider.Proxied implements IConditionBuilder {

    private static final ImmutableList<ItemLike> Z_CRYSTAL_SMELTABLES;
    private static final ImmutableList<ItemLike> RUBY_SMELTABLES;
    private static final ImmutableList<ItemLike> SAPPHIRE_SMELTABLES;
    private static final ImmutableList<ItemLike> CRYSTAL_SMELTABLES;
    private static final ImmutableList<ItemLike> SILICON_SMELTABLES;
    private static final ImmutableList<ItemLike> MEGASTONE_SMELTABLES;
    private static final ImmutableList<ItemLike> METEORITE_SMELTABLES;

    static {
        Z_CRYSTAL_SMELTABLES = GenerationsOres.Z_CRYSTAL_ORE_SET.getImmutableList();
        RUBY_SMELTABLES = GenerationsOres.RUBY_ORE_SET.getImmutableList();
        SAPPHIRE_SMELTABLES = GenerationsOres.SAPPHIRE_ORE_SET.getImmutableList();
        CRYSTAL_SMELTABLES = GenerationsOres.CRYSTAL_ORE_SET.getImmutableList();
        SILICON_SMELTABLES = GenerationsOres.SILICON_ORE_SET.getImmutableList();
        MEGASTONE_SMELTABLES = GenerationsOres.MEGASTONE_ORE_SET.getImmutableList();
        METEORITE_SMELTABLES = GenerationsOres.METEORITE_ORE_SET.getImmutableList();
    }

    public FurnaceRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, MEGASTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.MEGASTONE_SHARD.get(), 1.0F, 200, "mega_stone_shard");
        oreSmelting(consumer, METEORITE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.METEORITE_SHARD.get(), 1.0F, 200, "meteorite_shard");
        oreSmelting(consumer, Z_CRYSTAL_SMELTABLES, RecipeCategory.MISC, GenerationsItems.Z_INGOT.get(), 1.0F, 200, "z_ingot");
        oreSmelting(consumer, RUBY_SMELTABLES, RecipeCategory.MISC, GenerationsItems.RUBY.get(), 1.0F, 200, "ruby_gem");
        oreSmelting(consumer, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.SAPPHIRE.get(), 1.0F, 200, "sapphire_gem");
        oreSmelting(consumer, CRYSTAL_SMELTABLES, RecipeCategory.MISC, GenerationsItems.CRYSTAL.get(), 1.0F, 200, "crystal_gem");
        oreSmelting(consumer, SILICON_SMELTABLES, RecipeCategory.MISC, GenerationsItems.SILICON.get(), 1.0F, 200, "silicon");
        oreBlasting(consumer, Z_CRYSTAL_SMELTABLES, RecipeCategory.MISC, GenerationsItems.Z_INGOT.get(), 1.0F, 100, "z_ingot");
        oreBlasting(consumer, RUBY_SMELTABLES, RecipeCategory.MISC, GenerationsItems.RUBY.get(), 1.0F, 100, "ruby_gem");
        oreBlasting(consumer, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.SAPPHIRE.get(), 1.0F, 100, "sapphire_gem");
        oreBlasting(consumer, CRYSTAL_SMELTABLES, RecipeCategory.MISC, GenerationsItems.CRYSTAL.get(), 1.0F, 100, "crystal_gem");
        oreBlasting(consumer, SILICON_SMELTABLES, RecipeCategory.MISC, GenerationsItems.SILICON.get(), 1.0F, 100, "silicon");
        oreBlasting(consumer, MEGASTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.MEGASTONE_SHARD.get(), 1.0F, 100, "mega_stone_shard");
        oreBlasting(consumer, METEORITE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.METEORITE_SHARD.get(), 1.0F, 100, "meteorite_shard");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()), RecipeCategory.MISC, GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock(), 0.1F, 200).unlockedBy("has_charge_stone_cobble", has(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock()), RecipeCategory.MISC, GenerationsBlocks.SMOOTH_CHARGE_STONE.get(), 0.1F, 200).unlockedBy("has_charge_stone", has(GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock()), RecipeCategory.MISC, GenerationsBlocks.VOLCANIC_STONE.get(), 0.1F, 200).unlockedBy("has_volcanic_stone", has(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.VOLCANIC_STONE.get()), RecipeCategory.MISC, GenerationsBlocks.SMOOTH_VOLCANIC_STONE.get(), 0.1F, 200).unlockedBy("has_volcanic_stone", has(GenerationsBlocks.VOLCANIC_STONE.get())).save(consumer);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.ULTRA_SANDSTONE.get()), RecipeCategory.MISC, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE.get(), 0.1F, 200).unlockedBy(getHasName(GenerationsBlocks.ULTRA_SANDSTONE.get()), has(GenerationsBlocks.ULTRA_SANDSTONE.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get()), RecipeCategory.MISC, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get(), 0.1F, 200).unlockedBy(getHasName(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get()), has(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get())).save(consumer);

        //ChargeStone Ores
        /*
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_COAL_ORE.get()), RecipeCategory.MISC, Items.COAL, 0.1f, 200, "coal");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_IRON_ORE.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 200, "iron_ingot");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_COPPER_ORE.get()), RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 200, "copper_ingot");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_GOLD_ORE.get()), RecipeCategory.MISC, Items.GOLD_INGOT, 1.0f, 200, "gold_ingot");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get()), RecipeCategory.MISC, Items.DIAMOND, 1.0f, 200, "diamond");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get()), RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2f, 200, "lapis_lazuli");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get()), RecipeCategory.REDSTONE, Items.REDSTONE, 0.7f, 200, "redstone");
        oreSmelting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get()), RecipeCategory.MISC, Items.EMERALD, 1.0f, 200, "emerald");

        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_COAL_ORE.get()), RecipeCategory.MISC, Items.COAL, 0.1f, 100, "coal");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_IRON_ORE.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.7f, 100, "iron_ingot");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_COPPER_ORE.get()), RecipeCategory.MISC, Items.COPPER_INGOT, 0.7f, 100, "copper_ingot");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_GOLD_ORE.get()), RecipeCategory.MISC, Items.GOLD_INGOT, 1.0f, 100, "gold_ingot");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get()), RecipeCategory.MISC, Items.DIAMOND, 1.0f, 100, "diamond");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get()), RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2f, 100, "lapis_lazuli");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get()), RecipeCategory.REDSTONE, Items.REDSTONE, 0.7f, 100, "redstone");
        oreBlasting(consumer, ImmutableList.of(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get()), RecipeCategory.MISC, Items.EMERALD, 1.0f, 100, "emerald");
         */
    }
}
