package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

//TODO: Proper RecipeCategory assignment
public class MachineDecorationsRecipeDatagen extends GenerationsRecipeProvider.Proxied implements IConditionBuilder {
    public MachineDecorationsRecipeDatagen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.HEALER.dyeMap().get(DyeColor.BLUE).get(), 1) //TODO: Decide which color to be base or incorporate dye into recipe.
                .define('Q', GenerationsItems.COPPER_PLATE.get())
                .define('R', Items.IRON_INGOT)
                .define('P', Items.DIAMOND)
                    .pattern("QRQ")
                    .pattern("RPR")
                    .pattern("QRQ")
                .unlockedBy("has_copper_plate_has_diamond_has_iron_ingot",
                        inventoryTrigger(ItemPredicate.Builder.item().of(GenerationsItems.COPPER_PLATE.get(), Items.DIAMOND, Items.IRON_INGOT).build()))
                .save(consumer, GenerationsCore.id("healer"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.ROTOM_PC.get(), 1)
                .define('Q', GenerationsItems.COPPER_PLATE.get())
                .define('A', Blocks.GLASS_PANE)
                .define('R', Items.REDSTONE)
                .define('P', Blocks.REDSTONE_LAMP)
                .pattern("QAQ")
                .pattern("QPQ")
                .pattern("QRQ")
                .unlockedBy("has_copper_plate_has_redstone_lamp_has_glass_pane",
                        inventoryTrigger(ItemPredicate.Builder.item().of(GenerationsItems.COPPER_PLATE.get(), Blocks.REDSTONE_LAMP, Blocks.GLASS_PANE).build()))
                .save(consumer, GenerationsCore.id("pc"));

        for (DyeColor color : DyeColor.values()) {
            buildClockRecipes(consumer, color);
            buildVendingMachineRecipes(consumer, color);
            buildColoredHealerCraftingRecipes(consumer, color);
        }

        //FURNACES
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get())
                .define('X', GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()), has(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.get())
                .define('#', GenerationsBlocks.SMOOTH_CHARGE_STONE.get())
                .define('X', GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get())
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("IXI")
                .pattern("###")
                .unlockedBy(getHasName(GenerationsBlocks.SMOOTH_CHARGE_STONE.get()), has(GenerationsBlocks.SMOOTH_CHARGE_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.get())
                .define('#', ItemTags.LOGS)
                .define('X', GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get())
                .pattern(" # ").pattern("#X#").pattern(" # ")
                .unlockedBy(getHasName(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get()), has(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get())
                .define('X', GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock()), has(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.get())
                .define('#', GenerationsBlocks.SMOOTH_VOLCANIC_STONE.get())
                .define('X', GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get())
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("IXI")
                .pattern("###")
                .unlockedBy(getHasName(GenerationsBlocks.SMOOTH_VOLCANIC_STONE.get()), has(GenerationsBlocks.SMOOTH_VOLCANIC_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.get())
                .define('#', ItemTags.LOGS)
                .define('X', GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get())
                .pattern(" # ").pattern("#X#").pattern(" # ")
                .unlockedBy(getHasName(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get()), has(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get())
                .define('X', GenerationsBlocks.GRAY_MARBLE_SET.getStairs())
                .define('Y', GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock())
                .define('#', Items.BOWL)
                .pattern(" # ")
                .pattern("XYX")
                .unlockedBy(getHasName(GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock()))
                .save(consumer);

        buildPokeBallDisplayRecipes(consumer, CobblemonItems.POKE_BALL.asItem(), GenerationsDecorationBlocks.POKE_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.GREAT_BALL.asItem(), GenerationsDecorationBlocks.GREAT_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.ULTRA_BALL.asItem(), GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.MASTER_BALL.asItem(), GenerationsDecorationBlocks.MASTER_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.CHERISH_BALL.asItem(), GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.DIVE_BALL.asItem(), GenerationsDecorationBlocks.DIVE_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.DUSK_BALL.asItem(), GenerationsDecorationBlocks.DUSK_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.FAST_BALL.asItem(), GenerationsDecorationBlocks.FAST_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.FRIEND_BALL.asItem(), GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY.get());
        //buildPokeBallDisplayRecipes(consumer, CobblemonItems.GS_BALL.asItem(), GenerationsDecorationBlocks.GS_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.HEAL_BALL.asItem(), GenerationsDecorationBlocks.HEAL_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.HEAVY_BALL.asItem(), GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.LEVEL_BALL.asItem(), GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.LOVE_BALL.asItem(), GenerationsDecorationBlocks.LOVE_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.LURE_BALL.asItem(), GenerationsDecorationBlocks.LURE_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.LUXURY_BALL.asItem(), GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.MOON_BALL.asItem(), GenerationsDecorationBlocks.MOON_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.NEST_BALL.asItem(), GenerationsDecorationBlocks.NEST_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.NET_BALL.asItem(), GenerationsDecorationBlocks.NET_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.PARK_BALL.asItem(), GenerationsDecorationBlocks.PARK_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.PREMIER_BALL.asItem(), GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.QUICK_BALL.asItem(), GenerationsDecorationBlocks.QUICK_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.REPEAT_BALL.asItem(), GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.SAFARI_BALL.asItem(), GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.SPORT_BALL.asItem(), GenerationsDecorationBlocks.SPORT_BALL_DISPLAY.get());
        buildPokeBallDisplayRecipes(consumer, CobblemonItems.TIMER_BALL.asItem(), GenerationsDecorationBlocks.TIMER_BALL_DISPLAY.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GenerationsUtilityBlocks.SCARECROW.get())
                .pattern(" # ")
                .pattern("BAB")
                .pattern(" B ")
                .define('#', Items.MELON)
                .define('A', Items.HAY_BLOCK)
                .define('B', Items.STICK)
                .unlockedBy(getHasName(Items.HAY_BLOCK), has(Items.HAY_BLOCK))
                .save(consumer);
    }

    private void buildColoredHealerCraftingRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var healer = GenerationsUtilityBlocks.HEALER.dyeMap().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, healer)
                .define('E', dye)
                .define('H', healer)
                .pattern("EEE")
                .pattern("EHE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsUtilityBlocks.HEALER.dyeMap().get(DyeColor.BLUE).get()), has(GenerationsUtilityBlocks.HEALER.dyeMap().get(DyeColor.BLUE).get())) //TODO: Decide which color to be base or incorporate dye into recipe.
                .save(consumer);
    }

    //    private void buildPastelBeanBagRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) { //TODO: Figure out recipe
//        var dye = DyeItem.byColor(color);
//        var pastelBeanBag = PastelBeanBagBlock.getBlock(color);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
//                .define('E', dye)
//                .define('X', Items.WHITE_WOOL)
//                .pattern("XEX")
//                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
//                .save(consumer);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, pastelBeanBag)
//                .requires(dye)
//                .requires(GenerationsItemTags.PASTEL_BEAN_BAG)
//                .unlockedBy("has_" + GenerationsItemTags.PASTEL_BEAN_BAG.location().getPath(), has(GenerationsItemTags.PASTEL_BEAN_BAG))
//                .save(consumer, BuiltInRegistries.ITEM.getKey(pastelBeanBag).withPath(a -> a + "_dyed"));
//    }

    private void buildClockRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var clock = GenerationsUtilityBlocks.CLOCK.dyeMap().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, clock)
                .define('E', dye)
                .define('X', Items.IRON_INGOT)
                .define('C', Items.CLOCK)
                .pattern("XXX")
                .pattern("XEX")
                .pattern("XCX")
                .unlockedBy(getHasName(Items.CLOCK), has(Items.CLOCK))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, clock)
                .requires(dye)
                .requires(GenerationsItemTags.CLOCK)
                .unlockedBy("has_" + GenerationsItemTags.CLOCK.location().getPath(), has(GenerationsItemTags.CLOCK))
                .save(consumer, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(clock)).withPath(a -> a + "_dyed"));
    }

    private void buildVendingMachineRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var vendingMachine = GenerationsDecorationBlocks.VENDING_MACHINE.dyeMap().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, vendingMachine)
                .define('A', Items.IRON_INGOT)
                .define('B', Items.GLASS_PANE)
                .define('C', GenerationsItems.FRESH_WATER.get())
                .define('D', GenerationsItems.SODA_POP.get())
                .define('E', GenerationsItems.LEMONADE.get())
                .define('F', dye)
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .unlockedBy(getHasName(GenerationsItems.FRESH_WATER.get()), has(GenerationsItems.FRESH_WATER.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, vendingMachine)
                .requires(dye)
                .requires(GenerationsItemTags.VENDING_MACHINE)
                .unlockedBy("has_" + GenerationsItemTags.VENDING_MACHINE.location().getPath(), has(GenerationsItemTags.VENDING_MACHINE))
                .save(consumer, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(vendingMachine)).withPath(a -> a + "_dyed"));
    }

    private void buildPokeBallDisplayRecipes(Consumer<FinishedRecipe> consumer, Item pokeball, Block display) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, display)
                .requires(pokeball)
                .requires(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get())
                .unlockedBy(getHasName(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get()), has(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get()))
                .unlockedBy(getHasName(pokeball), has(pokeball))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, display)
                .define('X', GenerationsBlocks.GRAY_MARBLE_SET.getStairs())
                .define('Y', GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock())
                .define('#', Items.BOWL)
                .define('Z', pokeball)
                .pattern(" Z ")
                .pattern(" # ")
                .pattern("XYX")
                .unlockedBy(getHasName(GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock()))
                .save(consumer, "pokeball_display_with_" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(pokeball)).getPath());
    }
}
