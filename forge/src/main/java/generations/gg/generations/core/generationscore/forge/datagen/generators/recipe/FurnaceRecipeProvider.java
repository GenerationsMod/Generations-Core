package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.google.common.collect.ImmutableList;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

//TODO: Proper RecipeCategory assignment
public class FurnaceRecipeProvider extends GenerationsRecipeProvider.Proxied implements IConditionBuilder {

    private static final ImmutableList<ItemLike> ALUMINUM_SMELTABLES;
    private static final ImmutableList<ItemLike> Z_CRYSTAL_SMELTABLES;
    private static final ImmutableList<ItemLike> RUBY_SMELTABLES;
    private static final ImmutableList<ItemLike> SAPPHIRE_SMELTABLES;
    private static final ImmutableList<ItemLike> CRYSTAL_SMELTABLES;
    private static final ImmutableList<ItemLike> SILICON_SMELTABLES;
    private static final ImmutableList<ItemLike> MEGASTONE_SMELTABLES;
    private static final ImmutableList<ItemLike> METEORITE_SMELTABLES;
    private static final ImmutableList<ItemLike> TUMBLESTONE_SMELTABLES;
    private static final ImmutableList<ItemLike> BLACK_TUMPLESTONE_SMELTABLES;
    private static final ImmutableList<ItemLike> SKY_TUMBLESTONE_SMELTABLES;
    private static final ImmutableList<ItemLike> RARE_TUMBLESTONE_SMELTABLES;

    static {
        ALUMINUM_SMELTABLES = ImmutableList.of(GenerationsItems.RAW_ALUMINUM.get(), GenerationsOres.ALUMINUM_ORE.get(), GenerationsOres.DEEPSLATE_ALUMINUM_ORE.get(), GenerationsOres.CHARGE_STONE_ALUMINUM_ORE.get());
        Z_CRYSTAL_SMELTABLES = ImmutableList.of(GenerationsOres.Z_CRYSTAL_ORE.get(), GenerationsOres.DEEPSLATE_Z_CRYSTAL_ORE.get(), GenerationsOres.CHARGE_STONE_Z_CRYSTAL_ORE.get());
        RUBY_SMELTABLES = ImmutableList.of(GenerationsOres.RUBY_ORE.get(), GenerationsOres.DEEPSLATE_RUBY_ORE.get(), GenerationsOres.CHARGE_STONE_RUBY_ORE.get());
        SAPPHIRE_SMELTABLES = ImmutableList.of(GenerationsOres.SAPPHIRE_ORE.get(), GenerationsOres.DEEPSLATE_SAPPHIRE_ORE.get(), GenerationsOres.CHARGE_STONE_SAPPHIRE_ORE.get());
        CRYSTAL_SMELTABLES = ImmutableList.of(GenerationsOres.CRYSTAL_ORE.get(), GenerationsOres.DEEPSLATE_CRYSTAL_ORE.get(), GenerationsOres.CHARGE_STONE_CRYSTAL_ORE.get());
        SILICON_SMELTABLES = ImmutableList.of(GenerationsOres.SILICON_ORE.get(), GenerationsOres.DEEPSLATE_SILICON_ORE.get(), GenerationsOres.CHARGE_STONE_SILICON_ORE.get());
        MEGASTONE_SMELTABLES = ImmutableList.of(GenerationsOres.MEGASTONE_ORE.get(), GenerationsOres.DEEPSLATE_MEGASTONE_ORE.get(), GenerationsOres.CHARGE_STONE_MEGASTONE_ORE.get());
        METEORITE_SMELTABLES = ImmutableList.of(GenerationsOres.METEORITE_ORE.get(), GenerationsOres.DEEPSLATE_METEORITE_ORE.get(), GenerationsOres.CHARGE_STONE_METEORITE_ORE.get());
        TUMBLESTONE_SMELTABLES = ImmutableList.of(GenerationsOres.TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_TUMBLESTONE_ORE.get());
        BLACK_TUMPLESTONE_SMELTABLES = ImmutableList.of(GenerationsOres.BLACK_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_BLACK_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_BLACK_TUMBLESTONE_ORE.get());
        SKY_TUMBLESTONE_SMELTABLES = ImmutableList.of(GenerationsOres.SKY_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_SKY_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_SKY_TUMBLESTONE_ORE.get());
        RARE_TUMBLESTONE_SMELTABLES = ImmutableList.of(GenerationsOres.RARE_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_RARE_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_RARE_TUMBLESTONE_ORE.get());
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
        oreSmelting(consumer, TUMBLESTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.TUMBLESTONE.get(), 1.0F, 200, "tumblestone");
        oreSmelting(consumer, BLACK_TUMPLESTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.BLACK_TUMBLESTONE.get(), 1.0F, 200, "black_tumblestone");
        oreSmelting(consumer, SKY_TUMBLESTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.SKY_TUMBLESTONE.get(), 1.0F, 200, "sky_tumblestone");
        oreSmelting(consumer, RARE_TUMBLESTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.RARE_TUMBLESTONE.get(), 1.0F, 200, "rare_tumblestone");
        oreBlasting(consumer, ALUMINUM_SMELTABLES, RecipeCategory.MISC, GenerationsItems.ALUMINUM_INGOT.get(), 1.0F, 100, "aluminum_ingot");
        oreBlasting(consumer, Z_CRYSTAL_SMELTABLES, RecipeCategory.MISC, GenerationsItems.Z_INGOT.get(), 1.0F, 100, "z_ingot");
        oreBlasting(consumer, RUBY_SMELTABLES, RecipeCategory.MISC, GenerationsItems.RUBY.get(), 1.0F, 100, "ruby_gem");
        oreBlasting(consumer, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.SAPPHIRE.get(), 1.0F, 100, "sapphire_gem");
        oreBlasting(consumer, CRYSTAL_SMELTABLES, RecipeCategory.MISC, GenerationsItems.CRYSTAL.get(), 1.0F, 100, "crystal_gem");
        oreBlasting(consumer, SILICON_SMELTABLES, RecipeCategory.MISC, GenerationsItems.SILICON.get(), 1.0F, 100, "silicon");
        oreBlasting(consumer, MEGASTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.MEGASTONE_SHARD.get(), 1.0F, 100, "mega_stone_shard");
        oreBlasting(consumer, METEORITE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.METEORITE_SHARD.get(), 1.0F, 100, "meteorite_shard");
        oreBlasting(consumer, TUMBLESTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.TUMBLESTONE.get(), 1.0F, 100, "tumblestone");
        oreBlasting(consumer, BLACK_TUMPLESTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.BLACK_TUMBLESTONE.get(), 1.0F, 100, "black_tumblestone");
        oreBlasting(consumer, SKY_TUMBLESTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.SKY_TUMBLESTONE.get(), 1.0F, 100, "sky_tumblestone");
        oreBlasting(consumer, RARE_TUMBLESTONE_SMELTABLES, RecipeCategory.MISC, GenerationsItems.RARE_TUMBLESTONE.get(), 1.0F, 100, "rare_tumblestone");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.CHARGE_COBBLESTONE.get()), RecipeCategory.MISC, GenerationsBlocks.CHARGE_STONE.get(), 0.1F, 200).unlockedBy("has_charge_stone_cobble", has(GenerationsBlocks.CHARGE_COBBLESTONE.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.CHARGE_STONE.get()), RecipeCategory.MISC, GenerationsBlocks.SMOOTH_CHARGE_STONE.get(), 0.1F, 200).unlockedBy("has_charge_stone", has(GenerationsBlocks.CHARGE_STONE.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.VOLCANIC_COBBLESTONE.get()), RecipeCategory.MISC, GenerationsBlocks.VOLCANIC_STONE.get(), 0.1F, 200).unlockedBy("has_volcanic_stone", has(GenerationsBlocks.VOLCANIC_COBBLESTONE.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.VOLCANIC_STONE.get()), RecipeCategory.MISC, GenerationsBlocks.SMOOTH_VOLCANIC_STONE.get(), 0.1F, 200).unlockedBy("has_volcanic_stone", has(GenerationsBlocks.VOLCANIC_STONE.get())).save(consumer);

        var aluminum_ingredient = Ingredient.of(GenerationsTools.ALUMINUM.pickaxe().get(), GenerationsTools.ALUMINUM.shovel().get(), GenerationsTools.ALUMINUM.axe().get(), GenerationsTools.ALUMINUM.hoe().get(), GenerationsTools.ALUMINUM.sword().get(), GenerationsArmor.ALUMINUM_HELMET.get(), GenerationsArmor.ALUMINUM_CHESTPLATE.get(), GenerationsArmor.ALUMINUM_LEGGINGS.get(), GenerationsArmor.ALUMINUM_BOOTS.get());

        SimpleCookingRecipeBuilder.smelting(aluminum_ingredient, RecipeCategory.MISC, GenerationsItems.ALUMINUM_NUGGET.get(), 0.1F, 200).unlockedBy("has_aluminum_pickaxe", has(GenerationsTools.ALUMINUM.pickaxe().get())).unlockedBy("has_aluminum_shovel", has(GenerationsTools.ALUMINUM.shovel().get())).unlockedBy("has_aluminum_axe", has(GenerationsTools.ALUMINUM.axe().get())).unlockedBy("has_aluminum_hoe", has(GenerationsTools.ALUMINUM.hoe().get())).unlockedBy("has_aluminum_sword", has(GenerationsTools.ALUMINUM.sword().get())).unlockedBy("has_aluminum_helmet", has(GenerationsArmor.ALUMINUM_HELMET.get())).unlockedBy("has_aluminum_chestplate", has(GenerationsArmor.ALUMINUM_CHESTPLATE.get())).unlockedBy("has_aluminum_leggings", has(GenerationsArmor.ALUMINUM_LEGGINGS.get())).unlockedBy("has_aluminum_boots", has(GenerationsArmor.ALUMINUM_BOOTS.get())).save(consumer, getSmeltingRecipeName(GenerationsItems.ALUMINUM_NUGGET.get()));
        SimpleCookingRecipeBuilder.blasting(aluminum_ingredient, RecipeCategory.MISC, GenerationsItems.ALUMINUM_NUGGET.get(), 0.1F, 200).unlockedBy("has_aluminum_pickaxe", has(GenerationsTools.ALUMINUM.pickaxe().get())).unlockedBy("has_aluminum_shovel", has(GenerationsTools.ALUMINUM.shovel().get())).unlockedBy("has_aluminum_axe", has(GenerationsTools.ALUMINUM.axe().get())).unlockedBy("has_aluminum_hoe", has(GenerationsTools.ALUMINUM.hoe().get())).unlockedBy("has_aluminum_sword", has(GenerationsTools.ALUMINUM.sword().get())).unlockedBy("has_aluminum_helmet", has(GenerationsArmor.ALUMINUM_HELMET.get())).unlockedBy("has_aluminum_chestplate", has(GenerationsArmor.ALUMINUM_CHESTPLATE.get())).unlockedBy("has_aluminum_leggings", has(GenerationsArmor.ALUMINUM_LEGGINGS.get())).unlockedBy("has_aluminum_boots", has(GenerationsArmor.ALUMINUM_BOOTS.get())).save(consumer, getBlastingRecipeName(GenerationsItems.ALUMINUM_NUGGET.get()));

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.ULTRA_SANDSTONE.get()), RecipeCategory.MISC, GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE.get(), 0.1F, 200).unlockedBy(getHasName(GenerationsBlocks.ULTRA_SANDSTONE.get()), has(GenerationsBlocks.ULTRA_SANDSTONE.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get()), RecipeCategory.MISC, GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get(), 0.1F, 200).unlockedBy(getHasName(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get()), has(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get())).save(consumer);

        //ChargeStone Ores
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
    }
}
