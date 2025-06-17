package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import com.cobblemon.mod.common.CobblemonItems.CHERISH_BALL
import com.cobblemon.mod.common.CobblemonItems.DIVE_BALL
import com.cobblemon.mod.common.CobblemonItems.DUSK_BALL
import com.cobblemon.mod.common.CobblemonItems.FAST_BALL
import com.cobblemon.mod.common.CobblemonItems.FRIEND_BALL
import com.cobblemon.mod.common.CobblemonItems.GREAT_BALL
import com.cobblemon.mod.common.CobblemonItems.HEAL_BALL
import com.cobblemon.mod.common.CobblemonItems.HEAVY_BALL
import com.cobblemon.mod.common.CobblemonItems.LEVEL_BALL
import com.cobblemon.mod.common.CobblemonItems.LOVE_BALL
import com.cobblemon.mod.common.CobblemonItems.LURE_BALL
import com.cobblemon.mod.common.CobblemonItems.LUXURY_BALL
import com.cobblemon.mod.common.CobblemonItems.MASTER_BALL
import com.cobblemon.mod.common.CobblemonItems.MOON_BALL
import com.cobblemon.mod.common.CobblemonItems.NEST_BALL
import com.cobblemon.mod.common.CobblemonItems.NET_BALL
import com.cobblemon.mod.common.CobblemonItems.PARK_BALL
import com.cobblemon.mod.common.CobblemonItems.PC
import com.cobblemon.mod.common.CobblemonItems.POKE_BALL
import com.cobblemon.mod.common.CobblemonItems.POTION
import com.cobblemon.mod.common.CobblemonItems.PREMIER_BALL
import com.cobblemon.mod.common.CobblemonItems.QUICK_BALL
import com.cobblemon.mod.common.CobblemonItems.RARE_CANDY
import com.cobblemon.mod.common.CobblemonItems.REPEAT_BALL
import com.cobblemon.mod.common.CobblemonItems.SAFARI_BALL
import com.cobblemon.mod.common.CobblemonItems.SPORT_BALL
import com.cobblemon.mod.common.CobblemonItems.TIMER_BALL
import com.cobblemon.mod.common.CobblemonItems.ULTRA_BALL
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsDecorationBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import generations.gg.generations.core.generationscore.forge.datagen.generators.blocks.asValue
import net.minecraft.advancements.Criterion
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.data.recipes.ShapedRecipeBuilder.shaped
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.DyeItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.conditions.IConditionBuilder
import java.util.*
import java.util.concurrent.CompletableFuture

//TODO: Proper RecipeCategory assignment
class MachineDecorationsRecipeDatagen(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    GenerationsRecipeProvider.Proxied(output, registries),
    IConditionBuilder {
    override fun buildRecipes(recipeOutput: RecipeOutput) {
        for (color in DyeColor.entries) {
            buildVendingMachineRecipes(recipeOutput, color)
            buildPastelBeanBagRecipes(recipeOutput, color)
            buildSwivelChairRecipes(recipeOutput, color)
            buildCouchOttomanRecipes(recipeOutput, color)
            buildCouchArmLeftRecipes(recipeOutput, color)
            buildCouchArmRightRecipes(recipeOutput, color)
            buildCouchMiddleRecipes(recipeOutput, color)
            buildStreetLampRecipes(recipeOutput, color)
        }

        //FURNACES
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.CHARGE_STONE_FURNACE)
            .define('X', GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock)
            .pattern("XXX")
            .pattern("X X")
            .pattern("XXX")
            .unlockedBy(
                getHasName(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock),
                has(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(
            RecipeCategory.DECORATIONS,
            GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE
        )
            .define('#', GenerationsBlocks.SMOOTH_CHARGE_STONE)
            .define('X', GenerationsUtilityBlocks.CHARGE_STONE_FURNACE)
            .define('I', Items.IRON_INGOT)
            .pattern("III")
            .pattern("IXI")
            .pattern("###")
            .unlockedBy(
                getHasName(GenerationsBlocks.SMOOTH_CHARGE_STONE),
                has(GenerationsBlocks.SMOOTH_CHARGE_STONE.value())
            )
            .save(recipeOutput)

        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.CHARGE_STONE_SMOKER)
            .define('#', ItemTags.LOGS)
            .define('X', GenerationsUtilityBlocks.CHARGE_STONE_FURNACE)
            .pattern(" # ").pattern("#X#").pattern(" # ")
            .unlockedBy(
                getHasName(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE),
                has(GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.value())
            )
            .save(recipeOutput)

        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE)
            .define('X', GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock)
            .pattern("XXX")
            .pattern("X X")
            .pattern("XXX")
            .unlockedBy(
                getHasName(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock),
                has(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(
            RecipeCategory.DECORATIONS,
            GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE
        )
            .define('#', GenerationsBlocks.SMOOTH_VOLCANIC_STONE)
            .define('X', GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE)
            .define('I', Items.IRON_INGOT)
            .pattern("III")
            .pattern("IXI")
            .pattern("###")
            .unlockedBy(
                getHasName(GenerationsBlocks.SMOOTH_VOLCANIC_STONE),
                has(GenerationsBlocks.SMOOTH_VOLCANIC_STONE.value())
            )
            .save(recipeOutput)

        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER)
            .define('#', ItemTags.LOGS)
            .define('X', GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE)
            .pattern(" # ").pattern("#X#").pattern(" # ")
            .unlockedBy(
                getHasName(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE),
                has(GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.asValue())
            )
            .save(recipeOutput)

        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY)
            .define('X', GenerationsBlocks.GRAY_MARBLE_SET.stairs)
            .define('Y', GenerationsBlocks.GRAY_MARBLE_SET.baseBlock)
            .define('#', Items.BOWL)
            .pattern(" # ")
            .pattern("XYX")
            .unlockedBy(
                getHasName(GenerationsBlocks.GRAY_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.GRAY_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)

        buildPokeBallDisplayRecipes(recipeOutput, POKE_BALL.asItem(), GenerationsDecorationBlocks.POKE_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, GREAT_BALL.asItem(), GenerationsDecorationBlocks.GREAT_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, ULTRA_BALL.asItem(), GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, MASTER_BALL.asItem(), GenerationsDecorationBlocks.MASTER_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, CHERISH_BALL.asItem(), GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, DIVE_BALL.asItem(), GenerationsDecorationBlocks.DIVE_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, DUSK_BALL.asItem(), GenerationsDecorationBlocks.DUSK_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, FAST_BALL.asItem(), GenerationsDecorationBlocks.FAST_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, FRIEND_BALL.asItem(), GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY)
        //buildPokeBallDisplayRecipes(consumer, CobblemonItems.GS_BALL.asItem(), GenerationsDecorationBlocks.GS_BALL_DISPLAY);
        buildPokeBallDisplayRecipes(recipeOutput, HEAL_BALL.asItem(), GenerationsDecorationBlocks.HEAL_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, HEAVY_BALL.asItem(), GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, LEVEL_BALL.asItem(), GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, LOVE_BALL.asItem(), GenerationsDecorationBlocks.LOVE_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, LURE_BALL.asItem(), GenerationsDecorationBlocks.LURE_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, LUXURY_BALL.asItem(), GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, MOON_BALL.asItem(), GenerationsDecorationBlocks.MOON_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, NEST_BALL.asItem(), GenerationsDecorationBlocks.NEST_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, NET_BALL.asItem(), GenerationsDecorationBlocks.NET_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, PARK_BALL.asItem(), GenerationsDecorationBlocks.PARK_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, PREMIER_BALL.asItem(), GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, QUICK_BALL.asItem(), GenerationsDecorationBlocks.QUICK_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, REPEAT_BALL.asItem(), GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, SAFARI_BALL.asItem(), GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, SPORT_BALL.asItem(), GenerationsDecorationBlocks.SPORT_BALL_DISPLAY)
        buildPokeBallDisplayRecipes(recipeOutput, TIMER_BALL.asItem(), GenerationsDecorationBlocks.TIMER_BALL_DISPLAY)

        shaped(RecipeCategory.MISC, GenerationsUtilityBlocks.SCARECROW)
            .pattern(" # ")
            .pattern("BAB")
            .pattern(" B ")
            .define('#', Items.MELON)
            .define('A', Items.HAY_BLOCK)
            .define('B', Items.STICK)
            .unlockedBy(getHasName(Items.HAY_BLOCK), has(Items.HAY_BLOCK))
            .save(recipeOutput)

        //Cooking Pot
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.COOKING_POT)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.CAULDRON)
            .pattern("IRI")
            .pattern("I I")
            .unlockedBy(getHasName(Items.CAULDRON), has(Items.CAULDRON))
            .save(recipeOutput)

        //Fridge
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.FRIDGE)
            .define('I', Items.IRON_INGOT)
            .define('C', Items.ICE)
            .define('R', Items.REDSTONE)
            .pattern("RII")
            .pattern("RCI")
            .pattern("RII")
            .unlockedBy(getHasName(Items.ICE), has(Items.ICE))
            .save(recipeOutput)

        //RKS Machine
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.RKS_MACHINE)
            .define('I', Items.IRON_INGOT)
            .define('G', Items.GLASS)
            .define('R', GenerationsItems.RUBY)
            .define('N', Items.NETHERITE_INGOT)
            .define('S', GenerationsItems.SILICON)
            .pattern("IRI")
            .pattern("GGG")
            .pattern("NSN")
            .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
            .save(recipeOutput)

        //Box
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BOX)
            .define('P', Items.PAPER)
            .define('C', Items.CHEST)
            .pattern("PPP")
            .pattern("PCP")
            .pattern("PPP")
            .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
            .save(recipeOutput)

        //Meloetta Music Box
        shaped(RecipeCategory.DECORATIONS, GenerationsShrines.MELOETTA_MUSIC_BOX)
            .define('J', Items.JUKEBOX)
            .define('O', GenerationsItems.ORB)
            .pattern("JJJ")
            .pattern("JOJ")
            .pattern("JJJ")
            .unlockedBy(getHasName(Items.JUKEBOX), has(Items.JUKEBOX))
            .save(recipeOutput)

        //Prison Bottle Stem
        shaped(RecipeCategory.DECORATIONS, GenerationsShrines.PRISON_BOTTLE_STEM)
            .define('R', GenerationsItems.RUBY)
            .define('O', GenerationsItems.ORB)
            .define('G', Items.GOLD_INGOT)
            .pattern("RRR")
            .pattern("GOG")
            .pattern("GGG")
            .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
            .save(recipeOutput)

        //PC
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.ROTOM_PC, 1)
            .define('P', PC)
            .define('R', Items.REDSTONE)
            .define('D', Items.DIAMOND)
            .pattern("RDR")
            .pattern("DPD")
            .pattern("RDR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.TABLE_PC, 1)
            .define('P', PC)
            .define('R', Items.REDSTONE)
            .define('D', Items.IRON_INGOT)
            .pattern("RDR")
            .pattern("DPD")
            .pattern("RDR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)

        //Elevators
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BLACK_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.BLACK_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BLUE_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.BLUE_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.BROWN_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.BROWN_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.CYAN_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.CYAN_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.GRAY_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.GRAY_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.GREEN_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.GREEN_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.LIGHT_BLUE_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.LIGHT_BLUE_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.LIGHT_GRAY_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.LIGHT_GRAY_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.LIME_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.LIME_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.MAGENTA_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.MAGENTA_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.ORANGE_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.ORANGE_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.PINK_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.PINK_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.PURPLE_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.PURPLE_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.RED_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.RED_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.WHITE_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.WHITE_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.YELLOW_ELEVATOR, 1)
            .define('I', Items.IRON_INGOT)
            .define('R', Items.REDSTONE)
            .define('T', Items.YELLOW_GLAZED_TERRACOTTA)
            .pattern("RIR")
            .pattern("TTT")
            .pattern("RIR")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        //Misc Furniture
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SNORLAX_BEAN_BAG, 1)
            .define('B', Items.BLUE_WOOL)
            .define('Y', Items.YELLOW_WOOL)
            .define('W', Items.WHITE_WOOL)
            .pattern("BYB")
            .pattern("BWB")
            .unlockedBy(getHasName(Items.BLUE_WOOL), has(Items.BLUE_WOOL))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.COUCH, 1)
            .define('G', Items.GREEN_WOOL)
            .define('Y', Items.YELLOW_WOOL)
            .define('O', Items.OAK_PLANKS)
            .pattern("GGG")
            .pattern("YGG")
            .pattern("OOO")
            .unlockedBy(getHasName(Items.GREEN_WOOL), has(Items.GREEN_WOOL))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.HDTV, 1)
            .define('G', Items.GLASS)
            .define('R', Items.IRON_INGOT)
            .define('I', Items.REDSTONE)
            .pattern("GGG")
            .pattern("IRI")
            .pattern("III")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SWITCH, 1)
            .define('C', Items.COPPER_INGOT)
            .define('R', Items.REDSTONE)
            .define('B', Items.BLUE_DYE)
            .define('D', Items.RED_DYE)
            .define('S', GenerationsItems.SILICON)
            .pattern("SRS")
            .pattern("SRS")
            .pattern("BCD")
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.FOONGUS_CUSHION, 1)
            .define('W', Items.WHITE_WOOL)
            .define('R', Items.RED_WOOL)
            .define('M', Items.BROWN_MUSHROOM)
            .define('B', Items.BLACK_WOOL)
            .pattern("RRR")
            .pattern("BMB")
            .pattern("WWW")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.POKEBALL_CUSHION, 1)
            .define('W', Items.WHITE_WOOL)
            .define('R', Items.RED_WOOL)
            .define('B', Items.BLACK_WOOL)
            .pattern("RRR")
            .pattern("BWB")
            .pattern("WWW")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.GREATBALL_CUSHION, 1)
            .define('W', Items.WHITE_WOOL)
            .define('R', Items.BLUE_WOOL)
            .define('B', Items.BLACK_WOOL)
            .pattern("RRR")
            .pattern("BWB")
            .pattern("WWW")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.MASTERBALL_CUSHION, 1)
            .define('W', Items.WHITE_WOOL)
            .define('R', Items.PURPLE_WOOL)
            .define('B', Items.BLACK_WOOL)
            .pattern("RRR")
            .pattern("BWB")
            .pattern("WWW")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.POKEBALL_PILLAR, 1)
            .define('S', Items.STONE)
            .define('P', POKE_BALL)
            .define('I', Items.COPPER_INGOT)
            .pattern("SPS")
            .pattern("SIS")
            .pattern("SSS")
            .unlockedBy(getHasName(POKE_BALL), has(POKE_BALL))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.BUSH, 1)
            .define('B', Items.BONE_MEAL)
            .define('O', Items.OAK_SAPLING)
            .define('I', Items.IRON_INGOT)
            .pattern("BBB")
            .pattern("IOI")
            .pattern("III")
            .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.BENCH, 1)
            .define('O', Items.OAK_PLANKS)
            .define('I', Items.IRON_INGOT)
            .pattern("OOO")
            .pattern("I I")
            .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.DOUBLE_STREET_LAMP, 1)
            .define('C', Items.COPPER_INGOT)
            .define('L', Items.REDSTONE_LAMP)
            .define('G', Items.GLASS)
            .pattern("LCL")
            .pattern("GCG")
            .pattern(" C ")
            .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.HOUSE_LAMP, 1)
            .define('W', Items.WHITE_WOOL)
            .define('R', Items.REDSTONE_LAMP)
            .define('I', Items.IRON_INGOT)
            .pattern("WRW")
            .pattern("WIW")
            .pattern("III")
            .unlockedBy(getHasName(Items.REDSTONE_LAMP), has(Items.REDSTONE_LAMP))
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.LITWICK_CANDLE, 1)
            .define('C', Items.WHITE_CANDLE)
            .define('Y', Items.YELLOW_CANDLE)
            .pattern("CYC")
            .pattern("CCC")
            .unlockedBy(getHasName(Items.WHITE_CANDLE), has(Items.WHITE_CANDLE))
            .save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(
            RecipeCategory.DECORATIONS,
            GenerationsDecorationBlocks.LITWICK_CANDLES.value(),
            1
        )
            .requires(GenerationsDecorationBlocks.LITWICK_CANDLE, 3)
            .unlockedBy(
                getHasName(GenerationsDecorationBlocks.LITWICK_CANDLE),
                has(GenerationsDecorationBlocks.LITWICK_CANDLE.asValue())
            )
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHELF, 1)
            .define('B', GenerationsBlocks.BLUE_MARBLE_SET.baseBlock)
            .define('W', Items.WHITE_DYE)
            .define('O', Items.OAK_PLANKS)
            .pattern("BBB")
            .pattern("OOO")
            .pattern("WWW")
            .unlockedBy(
                getHasName(GenerationsBlocks.BLUE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.BLUE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.BOOK_SHELF, 1)
            .define('B', Items.BOOK)
            .define('W', GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            .pattern("WWW")
            .pattern("BBB")
            .pattern("WWW")
            .unlockedBy(
                getHasName(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(
            RecipeCategory.DECORATIONS,
            GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_1,
            1
        )
            .define('P', POTION)
            .define('R', RARE_CANDY)
            .define('G', Items.GLASS)
            .define('W', GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            .pattern("PRP")
            .pattern("GGG")
            .pattern("WWW")
            .unlockedBy(
                getHasName(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(
            RecipeCategory.DECORATIONS,
            GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_2,
            1
        )
            .define('R', RARE_CANDY)
            .define('L', Items.LIME_WOOL)
            .define('G', Items.GLASS)
            .define('W', GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            .pattern("RLR")
            .pattern("GGG")
            .pattern("WWW")
            .unlockedBy(
                getHasName(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(
            RecipeCategory.DECORATIONS,
            GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_1,
            1
        )
            .define('P', POTION)
            .define('R', RARE_CANDY)
            .define('W', GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            .pattern("PRP")
            .pattern("WWW")
            .unlockedBy(
                getHasName(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(
            RecipeCategory.DECORATIONS,
            GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_2,
            1
        )
            .define('R', RARE_CANDY)
            .define('L', Items.LIME_WOOL)
            .define('W', GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            .pattern("RLR")
            .pattern("WWW")
            .unlockedBy(
                getHasName(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_1, 1)
            .define('G', Items.GLASS)
            .define('W', GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            .pattern("GGG")
            .pattern("WWW")
            .unlockedBy(
                getHasName(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_2, 1)
            .define('G', Items.GLASS)
            .define('W', GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            .pattern("WWW")
            .pattern("GGG")
            .unlockedBy(
                getHasName(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.WORK_DESK, 1)
            .define('G', GenerationsBlocks.GRAY_MARBLE_SET.baseBlock)
            .define('B', GenerationsBlocks.BLUE_MARBLE_SET.baseBlock)
            .pattern("GGG")
            .pattern("GBG")
            .unlockedBy(
                getHasName(GenerationsBlocks.GRAY_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.GRAY_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsDecorationBlocks.DESK, 1)
            .define('W', GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            .pattern("WWW")
            .pattern("W W")
            .unlockedBy(
                getHasName(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.WHITE_MARBLE_SET.baseBlock)
            )
            .save(recipeOutput)
        shaped(RecipeCategory.DECORATIONS, GenerationsUtilityBlocks.TRASH_CAN, 1)
            .define('W', Items.IRON_INGOT)
            .define('C', Items.COMPOSTER)
            .pattern("WWW")
            .pattern("WCW")
            .pattern("WWW")
            .unlockedBy(getHasName(Items.COMPOSTER), has(Items.COMPOSTER))
            .save(recipeOutput)
    }


    private fun buildPastelBeanBagRecipes(consumer: RecipeOutput, color: DyeColor) {
        val dye = DyeItem.byColor(color)
        val pastelBeanBag = GenerationsDecorationBlocks.PASTEL_BEAN_BAG.block[color]!!

        shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
            .define('E', dye)
            .define('X', Items.WHITE_WOOL)
            .pattern("XEX")
            .pattern("XXX")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(consumer)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, pastelBeanBag.value())
            .requires(dye)
            .requires(GenerationsItemTags.PASTEL_BEAN_BAG)
            .unlockedBy(
                "has_" + GenerationsItemTags.PASTEL_BEAN_BAG.location().path,
                has(GenerationsItemTags.PASTEL_BEAN_BAG)
            )
            .save(
                consumer,
                Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(pastelBeanBag.value()))
                    .withPath { a: String -> a + "_dyed" })
    }

    private fun buildSwivelChairRecipes(consumer: RecipeOutput, color: DyeColor) {
        val dye = DyeItem.byColor(color)
        val pastelBeanBag = GenerationsDecorationBlocks.SWIVEL_CHAIR.block[color]!!

        shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
            .define('E', dye)
            .define('X', Items.WHITE_WOOL)
            .define('S', GenerationsItems.SILICON)
            .define('I', Items.IRON_INGOT)
            .pattern("EXX")
            .pattern("XXX")
            .pattern("SIS")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(consumer)
    }

    private fun buildCouchArmLeftRecipes(consumer: RecipeOutput, color: DyeColor) {
        val dye = DyeItem.byColor(color)
        val pastelBeanBag = GenerationsDecorationBlocks.COUCH_ARM_LEFT.block[color]!!

        shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
            .define('E', dye)
            .define('W', Items.WHITE_WOOL)
            .define('P', Items.DARK_OAK_PLANKS)
            .pattern("WEE")
            .pattern("WWW")
            .pattern("P P")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(consumer)
    }

    private fun buildCouchArmRightRecipes(consumer: RecipeOutput, color: DyeColor) {
        val dye = DyeItem.byColor(color)
        val pastelBeanBag = GenerationsDecorationBlocks.COUCH_ARM_RIGHT.block[color]!!

        shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
            .define('E', dye)
            .define('W', Items.WHITE_WOOL)
            .define('P', Items.DARK_OAK_PLANKS)
            .pattern("EEW")
            .pattern("WWW")
            .pattern("P P")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(consumer)
    }

    private fun buildCouchMiddleRecipes(consumer: RecipeOutput, color: DyeColor) {
        val dye = DyeItem.byColor(color)
        val pastelBeanBag = GenerationsDecorationBlocks.COUCH_MIDDLE.block[color]!!

        shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
            .define('E', dye)
            .define('W', Items.WHITE_WOOL)
            .define('P', Items.DARK_OAK_PLANKS)
            .pattern("EWE")
            .pattern("WWW")
            .pattern("P P")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(consumer)
    }

    private fun buildCouchOttomanRecipes(consumer: RecipeOutput, color: DyeColor) {
        val dye = DyeItem.byColor(color)
        val pastelBeanBag = GenerationsDecorationBlocks.COUCH_OTTOMAN.block[color]!!

        shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
            .define('E', dye)
            .define('W', Items.WHITE_WOOL)
            .define('P', Items.DARK_OAK_PLANKS)
            .pattern("EEE")
            .pattern("WWW")
            .pattern("P P")
            .unlockedBy(getHasName(Items.WHITE_WOOL), has(Items.WHITE_WOOL))
            .save(consumer)
    }

    private fun buildStreetLampRecipes(consumer: RecipeOutput, color: DyeColor) {
        val dye = DyeItem.byColor(color)
        val pastelBeanBag = GenerationsDecorationBlocks.STREET_LAMP.block[color]!!

        shaped(RecipeCategory.DECORATIONS, pastelBeanBag)
            .define('E', dye)
            .define('L', Items.REDSTONE_LAMP)
            .define('I', Items.IRON_INGOT)
            .pattern("ELE")
            .pattern(" I ")
            .pattern(" I ")
            .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
            .save(consumer)
    }

    private fun buildVendingMachineRecipes(consumer: RecipeOutput, color: DyeColor) {
        val dye = DyeItem.byColor(color)
        val vendingMachine = GenerationsDecorationBlocks.VENDING_MACHINE.block[color]!!

        shaped(RecipeCategory.DECORATIONS, vendingMachine)
            .define('A', Items.IRON_INGOT)
            .define('B', Items.GLASS_PANE)
            .define('C', GenerationsItems.FRESH_WATER)
            .define('D', GenerationsItems.SODA_POP)
            .define('E', GenerationsItems.LEMONADE)
            .define('F', dye)
            .pattern("ABA")
            .pattern("CDE")
            .pattern("AFA")
            .unlockedBy(getHasName(GenerationsItems.FRESH_WATER), has(GenerationsItems.FRESH_WATER.value()))
            .save(consumer)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, vendingMachine.value())
            .requires(dye)
            .requires(GenerationsItemTags.VENDING_MACHINE)
            .unlockedBy(
                "has_" + GenerationsItemTags.VENDING_MACHINE.location().path,
                has(GenerationsItemTags.VENDING_MACHINE)
            )
            .save(
                consumer,
                Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(vendingMachine.value()))
                    .withPath { a: String -> a + "_dyed" })
    }

    private fun buildPokeBallDisplayRecipes(consumer: RecipeOutput, pokeball: Item, display: Holder<Block>) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, display.value())
            .requires(pokeball)
            .requires(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY)
            .unlockedBy(
                getHasName(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY),
                has(GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.asValue())
            )
            .unlockedBy(getHasName(pokeball), has(pokeball))
            .save(consumer)

        shaped(RecipeCategory.DECORATIONS, display)
            .define('X', GenerationsBlocks.GRAY_MARBLE_SET.stairs)
            .define('Y', GenerationsBlocks.GRAY_MARBLE_SET.baseBlock)
            .define('#', Items.BOWL)
            .define('Z', pokeball)
            .pattern(" Z ")
            .pattern(" # ")
            .pattern("XYX")
            .unlockedBy(
                getHasName(GenerationsBlocks.GRAY_MARBLE_SET.baseBlock),
                has(GenerationsBlocks.GRAY_MARBLE_SET.baseBlock)
            )
            .save(
                consumer,
                "pokeball_display_with_" + Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(pokeball)).path
            )
    }
    fun getHasName(itemLike: Holder<out ItemLike>): String {
        return RecipeProvider.getHasName(itemLike.value())
    }

    fun shaped(category: RecipeCategory, result: Holder<out ItemLike>, count: Int): ShapedRecipeBuilder {
        return shaped(category, result.value(), count)
    }

    fun shaped(arg: RecipeCategory, result: Holder<out ItemLike>): ShapedRecipeBuilder {
        return shaped(arg, result.value())
    }

    fun has(itemLike: Holder<ItemLike>): Criterion<InventoryChangeTrigger.TriggerInstance> {
        return RecipeProvider.has(itemLike.value())
    }
}
