package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;


import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.decorations.RugBlock;
import generations.gg.generations.core.generationscore.world.level.block.decorations.UmbrellaBlock;
import generations.gg.generations.core.generationscore.world.level.block.entities.VendingMachineBlock;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.ClockBlock;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.HealerBlock;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.PCBlock;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.animal.Sheep;
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
public class MachineRecipeDatagen extends GenerationsRecipeProvider.Proxied implements IConditionBuilder {
    public MachineRecipeDatagen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BLUE_HEALER.get(), 1) //TODO: Decide which color to be base or incorporate dye into recipe.
                .define('Q', GenerationsItems.ALUMINUM_PLATE.get())
                .define('R', Items.IRON_INGOT)
                .define('P', Items.DIAMOND)
                    .pattern("QRQ")
                    .pattern("RPR")
                    .pattern("QRQ")
                .unlockedBy("has_aluminum_plate_has_diamond_has_iron_ingot",
                        inventoryTrigger(ItemPredicate.Builder.item().of(GenerationsItems.ALUMINUM_PLATE.get(), Items.DIAMOND, Items.IRON_INGOT).build()))
                .save(consumer, GenerationsCore.id("healer"));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.RED_PC.get(), 1)
                .define('Q', GenerationsItems.ALUMINUM_PLATE.get())
                .define('A', Blocks.GLASS_PANE)
                .define('R', Items.REDSTONE)
                .define('P', Blocks.REDSTONE_LAMP)
                .pattern("QAQ")
                .pattern("QPQ")
                .pattern("QRQ")
                .unlockedBy("has_aluminum_plate_has_redstone_lamp_has_glass_pane",
                        inventoryTrigger(ItemPredicate.Builder.item().of(GenerationsItems.ALUMINUM_PLATE.get(), Blocks.REDSTONE_LAMP, Blocks.GLASS_PANE).build()))
                .save(consumer, GenerationsCore.id("pc"));

        for (DyeColor color : DyeColor.values()) {
            buildColoredPcCraftingRecipes(consumer, color);
            buildClockRecipes(consumer, color);
            buildVendingMachineRecipes(consumer, color);
            buildUmbrellaRecipes(consumer, color);
            buildPokeballRugRecipes(consumer, color);
            buildColoredHealerCraftingRecipes(consumer, color);
        }

        //Trading Machine
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsItems.TRADE_HOLDER_LEFT.get())
                .define('Q', GenerationsItems.ALUMINUM_PLATE.get())
                .define('A', GenerationsItems.POKE_BALL.get())
                .pattern("QQA")
                .pattern(" QQ")
                .unlockedBy(getHasName(GenerationsItems.POKE_BALL.get()), has(GenerationsItems.POKE_BALL.get()))
                .unlockedBy(getHasName(GenerationsItems.ALUMINUM_PLATE.get()), has(GenerationsItems.ALUMINUM_PLATE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsItems.TRADE_HOLDER_RIGHT.get())
                .define('Q', GenerationsItems.ALUMINUM_PLATE.get())
                .define('A', GenerationsItems.POKE_BALL.get())
                .pattern("QQA")
                .pattern(" QQ")
                .unlockedBy(getHasName(GenerationsItems.POKE_BALL.get()), has(GenerationsItems.POKE_BALL.get()))
                .unlockedBy(getHasName(GenerationsItems.ALUMINUM_PLATE.get()), has(GenerationsItems.ALUMINUM_PLATE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsItems.TRADE_PANEL.get())
                .define('Q', GenerationsItems.ALUMINUM_PLATE.get())
                .define('A', Items.REDSTONE)
                .pattern("QQQ")
                .pattern("AAA")
                .pattern("QAQ")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .unlockedBy(getHasName(GenerationsItems.ALUMINUM_PLATE.get()), has(GenerationsItems.ALUMINUM_PLATE.get()))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsItems.TRADE_MONITOR.get())
                .define('Q', GenerationsItems.ALUMINUM_PLATE.get())
                .define('A', Items.GLASS_PANE)
                .pattern("QQQ")
                .pattern("QAQ")
                .pattern("QQQ")
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Items.GLASS_PANE))
                .unlockedBy(getHasName(GenerationsItems.ALUMINUM_PLATE.get()), has(GenerationsItems.ALUMINUM_PLATE.get()))
                .save(consumer);

        //FURNACES
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get())
                .define('X', GenerationsBlocks.CHARGE_COBBLESTONE.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsBlocks.CHARGE_COBBLESTONE.get()), has(GenerationsBlocks.CHARGE_COBBLESTONE.get()))
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
                .define('X', GenerationsBlocks.VOLCANIC_COBBLESTONE.get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsBlocks.VOLCANIC_COBBLESTONE.get()), has(GenerationsBlocks.VOLCANIC_COBBLESTONE.get()))
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
    }

    private void buildColoredHealerCraftingRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var healer = HealerBlock.getBlock(color);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, healer)
                .define('E', dye)
                .define('H', healer)
                .pattern("EEE")
                .pattern("EHE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsUtilityBlocks.BLUE_HEALER.get()), has(GenerationsUtilityBlocks.BLUE_HEALER.get())) //TODO: Decide which color to be base or incorporate dye into recipe.
                .save(consumer);
    }

    private void buildColoredPcCraftingRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var finishedPc = PCBlock.getBlock(color);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, finishedPc)
                .define('E', dye)
                .define('H', GenerationsItemTags.PC)
                .pattern("EEE")
                .pattern("EHE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsUtilityBlocks.RED_PC.get()) , has(GenerationsUtilityBlocks.RED_PC.get())) //TODO: Decide if to incorporate dye into recipe.
                .save(consumer);
    }

    private void buildPokeballRugRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var rug = RugBlock.getBlock(color);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, rug)
                .define('E', dye)
                .define('X', Items.WHITE_WOOL)
                .pattern("XEX")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, rug)
                .requires(dye)
                .requires(GenerationsItemTags.POKEBALL_RUG)
                .unlockedBy(GenerationsItemTags.POKEBALL_RUG.location().getPath(), has(GenerationsItemTags.POKEBALL_RUG))
                .save(consumer, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(rug)).withPath(a -> a + "_dyed"));
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

    private void buildUmbrellaRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var umbrella = UmbrellaBlock.getBlock(color);

        var builder = ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, umbrella)
                .define('X', Items.WHITE_WOOL);
                if (color != DyeColor.WHITE) builder.define('E', Sheep.ITEM_BY_DYE.get(color));
                builder.define('A', Items.STICK);
                if(color != DyeColor.WHITE) builder.pattern("XEX");
                else builder.pattern("XXX");
                builder.pattern(" A ")
                .pattern(" A ")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, umbrella)
                .requires(dye)
                .requires(GenerationsItemTags.UMBRELLA)
                .unlockedBy("has_" + GenerationsItemTags.UMBRELLA.location().getPath(), has(GenerationsItemTags.UMBRELLA))
                .save(consumer, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(umbrella)).withPath(a -> a + "_dyed"));
    }

    private void buildClockRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var clock = ClockBlock.getBlock(color);

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
        var vendingMachine = VendingMachineBlock.getBlock(color);

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
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, display)
                .define('X', pokeball)
                .define('#', GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get())
                .pattern("X")
                .pattern("#")
                .unlockedBy(getHasName(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get()), has(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get()))
                .save(consumer);
    }
}
