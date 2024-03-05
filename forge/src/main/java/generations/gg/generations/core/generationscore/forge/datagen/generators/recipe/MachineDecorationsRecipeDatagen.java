package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsDecorationBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
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

        for (DyeColor color : DyeColor.values()) {
            buildVendingMachineRecipes(consumer, color);
            buildPastelBeanBagRecipes(consumer, color);
            buildSwivelChairRecipes(consumer, color);
            buildCouchOttomanRecipes(consumer, color);
            buildCouchArmLeftRecipes(consumer, color);
            buildCouchArmRightRecipes(consumer, color);
            buildCouchMiddleRecipes(consumer, color);
            buildStreetLampRecipes(consumer, color);
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

        //Cooking Pot
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.COOKING_POT.get())
                .define('I', Items.IRON_INGOT)
                .define('R', Items.CAULDRON)
                .pattern("IRI")
                .pattern("I I")
                .unlockedBy(getHasName(Items.CAULDRON), has(Items.CAULDRON))
                .save(consumer);

        //Fridge
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.FRIDGE.get())
                .define('I', Items.IRON_INGOT)
                .define('C', Items.ICE)
                .define('R', Items.REDSTONE)
                .pattern("RII")
                .pattern("RCI")
                .pattern("RII")
                .unlockedBy(getHasName(Items.ICE), has(Items.ICE))
                .save(consumer);

        //Box
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BOX.get())
                .define('P', Items.PAPER)
                .define('C', Items.CHEST)
                .pattern("PPP")
                .pattern("PCP")
                .pattern("PPP")
                .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                .save(consumer);

        //Meloetta Music Box
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsShrines.MELOETTA_MUSIC_BOX.get())
                .define('J', Items.JUKEBOX)
                .define('O', GenerationsItems.ORB.get())
                .pattern("JJJ")
                .pattern("JOJ")
                .pattern("JJJ")
                .unlockedBy(getHasName(Items.JUKEBOX), has(Items.JUKEBOX))
                .save(consumer);

        //Prison Bottle Stem
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsItems.PRISON_BOTTLE_STEM.get())
                .define('R', GenerationsItems.RUBY.get())
                .define('O', GenerationsItems.ORB.get())
                .define('G', Items.GOLD_INGOT)
                .pattern("RRR")
                .pattern("GOG")
                .pattern("GGG")
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        //PC
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.ROTOM_PC.get(), 1)
                .define('P', CobblemonItems.PC)
                .define('R', Items.REDSTONE)
                .define('D', Items.DIAMOND)
                .pattern("RDR")
                .pattern("DPD")
                .pattern("RDR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.TABLE_PC.get(), 1)
                .define('P', CobblemonItems.PC)
                .define('R', Items.REDSTONE)
                .define('D', Items.IRON_INGOT)
                .pattern("RDR")
                .pattern("DPD")
                .pattern("RDR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);

        //Elevators
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BLACK_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.BLACK_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BLUE_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.BLUE_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BROWN_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.BROWN_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.CYAN_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.CYAN_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.GRAY_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.GRAY_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.GREEN_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.GREEN_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.LIGHT_BLUE_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.LIGHT_GRAY_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.LIGHT_GRAY_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.LIME_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.LIME_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.MAGENTA_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.MAGENTA_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.ORANGE_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.ORANGE_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.PINK_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.PINK_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.PURPLE_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.PURPLE_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.RED_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.RED_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.WHITE_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.WHITE_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.YELLOW_ELEVATOR.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('T', Items.YELLOW_GLAZED_TERRACOTTA)
                .pattern("RIR")
                .pattern("TTT")
                .pattern("RIR")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        //Misc Furniture
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SNORLAX_BEAN_BAG.get(), 1)
                .define('B', Items.BLUE_WOOL)
                .define('Y', Items.YELLOW_WOOL)
                .define('W', Items.WHITE_WOOL)
                .pattern("BYB")
                .pattern("BWB")
                .unlockedBy(getHasName(Items.BLUE_WOOL), has(Items.BLUE_WOOL))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.COUCH.get(), 1)
                .define('G', Items.GREEN_WOOL)
                .define('Y', Items.YELLOW_WOOL)
                .define('O', Items.OAK_PLANKS)
                .pattern("GGG")
                .pattern("YGG")
                .pattern("OOO")
                .unlockedBy(getHasName(Items.GREEN_WOOL), has(Items.GREEN_WOOL))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.HDTV.get(), 1)
                .define('G', Items.GLASS)
                .define('R', Items.IRON_INGOT)
                .define('I', Items.REDSTONE)
                .pattern("GGG")
                .pattern("IRI")
                .pattern("III")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SWITCH.get(), 1)
                .define('C', Items.COPPER_INGOT)
                .define('R', Items.REDSTONE)
                .define('B', Items.BLUE_DYE)
                .define('D', Items.RED_DYE)
                .define('S', GenerationsItems.SILICON.get())
                .pattern("SRS")
                .pattern("SRS")
                .pattern("BCD")
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.FOONGUS_CUSHION.get(), 1)
                .define('W', Items.WHITE_WOOL)
                .define('R', Items.RED_WOOL)
                .define('M', Items.BROWN_MUSHROOM)
                .define('B', Items.BLACK_WOOL)
                .pattern("RRR")
                .pattern("BMB")
                .pattern("WWW")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.POKEBALL_CUSHION.get(), 1)
                .define('W', Items.WHITE_WOOL)
                .define('R', Items.RED_WOOL)
                .define('B', Items.BLACK_WOOL)
                .pattern("RRR")
                .pattern("BWB")
                .pattern("WWW")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.GREATBALL_CUSHION.get(), 1)
                .define('W', Items.WHITE_WOOL)
                .define('R', Items.BLUE_WOOL)
                .define('B', Items.BLACK_WOOL)
                .pattern("RRR")
                .pattern("BWB")
                .pattern("WWW")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.MASTERBALL_CUSHION.get(), 1)
                .define('W', Items.WHITE_WOOL)
                .define('R', Items.PURPLE_WOOL)
                .define('B', Items.BLACK_WOOL)
                .pattern("RRR")
                .pattern("BWB")
                .pattern("WWW")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.POKEBALL_PILLAR.get(), 1)
                .define('S', Items.STONE)
                .define('P', CobblemonItems.POKE_BALL)
                .define('I', Items.COPPER_INGOT)
                .pattern("SPS")
                .pattern("SIS")
                .pattern("SSS")
                .unlockedBy(getHasName(CobblemonItems.POKE_BALL), has(CobblemonItems.POKE_BALL))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.BUSH.get(), 1)
                .define('B', Items.BONE_MEAL)
                .define('O', Items.OAK_SAPLING)
                .define('I', Items.IRON_INGOT)
                .pattern("BBB")
                .pattern("IOI")
                .pattern("III")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.BENCH.get(), 1)
                .define('O', Items.OAK_PLANKS)
                .define('I', Items.IRON_INGOT)
                .pattern("OOO")
                .pattern("I I")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.DOUBLE_STREET_LAMP.get(), 1)
                .define('C', Items.COPPER_INGOT)
                .define('L', Items.REDSTONE_LAMP)
                .define('G', Items.GLASS)
                .pattern("LCL")
                .pattern("GCG")
                .pattern(" C ")
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.HOUSE_LAMP.get(), 1)
                .define('W', Items.WHITE_WOOL)
                .define('R', Items.REDSTONE_LAMP)
                .define('I', Items.IRON_INGOT)
                .pattern("WRW")
                .pattern("WIW")
                .pattern("III")
                .unlockedBy(getHasName(Items.REDSTONE_LAMP), has(Items.REDSTONE_LAMP))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.LITWICK_CANDLE.get(), 1)
                .define('C', Items.WHITE_CANDLE)
                .define('Y', Items.YELLOW_CANDLE)
                .pattern("CYC")
                .pattern("CCC")
                .unlockedBy(getHasName(Items.WHITE_CANDLE), has(Items.WHITE_CANDLE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.LITWICK_CANDLES.get(), 1)
                .requires(GenerationsDecorationBlocks.LITWICK_CANDLE.get(), 3)
                .unlockedBy(getHasName(GenerationsDecorationBlocks.LITWICK_CANDLE.get()), has(GenerationsDecorationBlocks.LITWICK_CANDLE.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_1.get(), 1)
                .define('P', CobblemonItems.POTION)
                .define('R', CobblemonItems.RARE_CANDY)
                .define('G', Items.GLASS)
                .define('W', GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock())
                .pattern("PRP")
                .pattern("GGG")
                .pattern("WWW")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_2.get(), 1)
                .define('R', CobblemonItems.RARE_CANDY)
                .define('L', Items.LIME_WOOL)
                .define('G', Items.GLASS)
                .define('W', GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock())
                .pattern("RLR")
                .pattern("GGG")
                .pattern("WWW")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_1.get(), 1)
                .define('P', CobblemonItems.POTION)
                .define('R', CobblemonItems.RARE_CANDY)
                .define('W', GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock())
                .pattern("PRP")
                .pattern("WWW")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_2.get(), 1)
                .define('R', CobblemonItems.RARE_CANDY)
                .define('L', Items.LIME_WOOL)
                .define('W', GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock())
                .pattern("RLR")
                .pattern("WWW")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_1.get(), 1)
                .define('G', Items.GLASS)
                .define('W', GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock())
                .pattern("GGG")
                .pattern("WWW")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_2.get(), 1)
                .define('G', Items.GLASS)
                .define('W', GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock())
                .pattern("WWW")
                .pattern("GGG")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.WORK_DESK.get(), 1)
                .define('G', GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock())
                .define('B', GenerationsBlocks.BLUE_MARBLE_SET.getBaseBlock())
                .pattern("GGG")
                .pattern("GBG")
                .unlockedBy(getHasName(GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.DESK.get(), 1)
                .define('W', GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock())
                .pattern("WWW")
                .pattern("W W")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()), has(GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock()))
                .save(consumer);
    }


        private void buildPastelBeanBagRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var pastelBeanBag = GenerationsDecorationBlocks.PASTEL_BEAN_BAG.block().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
                .define('E', dye)
                .define('X', Items.WHITE_WOOL)
                .pattern("XEX")
                .pattern("XXX")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, pastelBeanBag)
                .requires(dye)
                .requires(GenerationsItemTags.PASTEL_BEAN_BAG)
                .unlockedBy("has_" + GenerationsItemTags.PASTEL_BEAN_BAG.location().getPath(), has(GenerationsItemTags.PASTEL_BEAN_BAG))
                .save(consumer, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(pastelBeanBag)).withPath(a -> a + "_dyed"));
    }

    private void buildSwivelChairRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var pastelBeanBag = GenerationsDecorationBlocks.SWIVEL_CHAIR.block().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
                .define('E', dye)
                .define('X', Items.WHITE_WOOL)
                .define('S', GenerationsItems.SILICON.get())
                .define('I', Items.IRON_INGOT)
                .pattern("EXX")
                .pattern("XXX")
                .pattern("SIS")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
    }

    private void buildCouchArmLeftRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var pastelBeanBag = GenerationsDecorationBlocks.COUCH_ARM_LEFT.block().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
                .define('E', dye)
                .define('W', Items.WHITE_WOOL)
                .define('P', Items.DARK_OAK_PLANKS)
                .pattern("WEE")
                .pattern("WWW")
                .pattern("P P")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
    }
    private void buildCouchArmRightRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var pastelBeanBag = GenerationsDecorationBlocks.COUCH_ARM_RIGHT.block().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
                .define('E', dye)
                .define('W', Items.WHITE_WOOL)
                .define('P', Items.DARK_OAK_PLANKS)
                .pattern("EEW")
                .pattern("WWW")
                .pattern("P P")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
    }
    private void buildCouchMiddleRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var pastelBeanBag = GenerationsDecorationBlocks.COUCH_MIDDLE.block().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
                .define('E', dye)
                .define('W', Items.WHITE_WOOL)
                .define('P', Items.DARK_OAK_PLANKS)
                .pattern("EWE")
                .pattern("WWW")
                .pattern("P P")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
    }
    private void buildCouchOttomanRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var pastelBeanBag = GenerationsDecorationBlocks.COUCH_OTTOMAN.block().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
                .define('E', dye)
                .define('W', Items.WHITE_WOOL)
                .define('P', Items.DARK_OAK_PLANKS)
                .pattern("EEE")
                .pattern("WWW")
                .pattern("P P")
                .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
                .save(consumer);
    }
    private void buildStreetLampRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var pastelBeanBag = GenerationsDecorationBlocks.STREET_LAMP.block().get(color).get();

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
                .define('E', dye)
                .define('L', Items.REDSTONE_LAMP)
                .define('I', Items.IRON_INGOT)
                .pattern("ELE")
                .pattern(" I ")
                .pattern(" I ")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);
    }

    private void buildVendingMachineRecipes(Consumer<FinishedRecipe> consumer, DyeColor color) {
        var dye = DyeItem.byColor(color);
        var vendingMachine = GenerationsDecorationBlocks.VENDING_MACHINE.block().get(color).get();

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
                .save(consumer, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(vendingMachine)).withPath(a -> a + "_dyed"));
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
