package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import com.cobblemon.mod.common.CobblemonItems.APRICORN_LOG
import com.cobblemon.mod.common.CobblemonItems.APRICORN_PLANKS
import com.cobblemon.mod.common.CobblemonItems.DAWN_STONE
import com.cobblemon.mod.common.CobblemonItems.DUSK_STONE
import com.cobblemon.mod.common.CobblemonItems.FIRE_STONE
import com.cobblemon.mod.common.CobblemonItems.GHOST_GEM
import com.cobblemon.mod.common.CobblemonItems.GREAT_BALL
import com.cobblemon.mod.common.CobblemonItems.ICE_STONE
import com.cobblemon.mod.common.CobblemonItems.LEAF_STONE
import com.cobblemon.mod.common.CobblemonItems.MASTER_BALL
import com.cobblemon.mod.common.CobblemonItems.MOON_STONE
import com.cobblemon.mod.common.CobblemonItems.POKE_BALL
import com.cobblemon.mod.common.CobblemonItems.SHINY_STONE
import com.cobblemon.mod.common.CobblemonItems.SUN_STONE
import com.cobblemon.mod.common.CobblemonItems.THUNDER_STONE
import com.cobblemon.mod.common.CobblemonItems.ULTRA_BALL
import com.cobblemon.mod.common.CobblemonItems.WATER_STONE
import generations.gg.generations.core.generationscore.common.util.extensions.id
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsFullBlockSet
import generations.gg.generations.core.generationscore.common.world.level.block.set.GenerationsUltraBlockSet
import generations.gg.generations.core.generationscore.forge.datagen.data.families.GenerationsBlockFamilies
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.BlockFamily
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.tags.ItemTags
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.neoforged.neoforge.common.conditions.IConditionBuilder
import java.util.*
import java.util.concurrent.CompletableFuture

/**
 * Creates recipes for building blocks using Forge Datagen
 * @see net.minecraft.data.recipes.packs.VanillaRecipeProvider
 *
 * @see net.minecraft.data.recipes.RecipeProvider
 *
 * @author Joseph T. McQuigg
 */
class BuildingBlockRecipeDatagen(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : GenerationsRecipeProvider.Proxied(output, registries), IConditionBuilder {
    override fun buildRecipes(recipeOutput: RecipeOutput) {
        generateForEnabledBlockFamilies(recipeOutput)

        //Colored Shingles
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.WHITE_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIGHT_GRAY_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.LIGHT_GRAY_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.GRAY_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BLACK_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.BLACK_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROWN_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.BROWN_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RED_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.RED_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ORANGE_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.ORANGE_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.YELLOW_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.YELLOW_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIME_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.LIME_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GREEN_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.GREEN_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CYAN_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.CYAN_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIGHT_BLUE_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.LIGHT_BLUE_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BLUE_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.BLUE_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.PURPLE_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.PURPLE_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MAGENTA_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.MAGENTA_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.PINK_SHINGLES_SET.baseBlock, count = 5) {
            define('X', Blocks.GRAVEL)
            define('Y', Items.INK_SAC)
            define('Z', Items.PINK_DYE)
            pattern("XYX")
            pattern("ZXZ")
            pattern("XYX")
            unlockedByItem(Blocks.GRAVEL)
            unlockedByItem(Items.INK_SAC)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKECENTER_SIGN) {
            define('X', Blocks.IRON_BLOCK)
            define('Y', Items.RED_DYE)
            pattern(" X ")
            pattern("Y Y")
            unlockedByItem(Blocks.IRON_BLOCK)
            unlockedByItem(Items.RED_DYE)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKEMART_SIGN) {
            define('X', Blocks.IRON_BLOCK)
            define('Y', Items.BLUE_DYE)
            pattern(" X ")
            pattern("Y Y")
            unlockedByItem(Blocks.IRON_BLOCK)
            unlockedByItem(Items.BLUE_DYE)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKECENTER_SCARLET_SIGN) {
            define('X', Blocks.IRON_BLOCK)
            define('Y', Items.RED_DYE)
            pattern("XXX")
            pattern("YYY")
            pattern("XXX")
            unlockedByItem(Blocks.IRON_BLOCK)
            unlockedByItem(Items.RED_DYE)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKECENTER_DOOR) {
            define('G', Blocks.GRAY_STAINED_GLASS)
            define('B', Blocks.BLUE_STAINED_GLASS)
            define('Y', Items.RED_DYE)
            pattern("YGB")
            pattern("YBG")
            pattern("YGB")
            unlockedByItem(Blocks.IRON_BLOCK)
            unlockedByItem(Items.RED_DYE)
        }

        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKECENTER_ROOF_SET.baseBlock) {
            requires(GenerationsBlocks.RED_SHINGLES_SET.baseBlock, 2)
            unlockedByItem(GenerationsBlocks.RED_SHINGLES_SET.baseBlock)
        }

        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKECENTER_ROOF_2_SET.baseBlock) {
            requires(GenerationsBlocks.RED_SHINGLES_SET.baseBlock, 1)
            requires(GenerationsBlocks.RED_POKE_BRICK_SET.baseBlock, 1)
            unlockedByItem(GenerationsBlocks.RED_SHINGLES_SET.baseBlock)
        }

        //temple block
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock, count = 4) {
            define('E', Blocks.STONE_BRICKS)
            define('Q', Blocks.SANDSTONE)
            pattern("EQ")
            pattern("QE")
            unlockedByItem(Blocks.STONE_BRICKS)
        }

//temple brick
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.TEMPLE_BRICK_SET.baseBlock, count = 4) {
            define('E', GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
            pattern("EE")
            pattern("EE")
            unlockedByItem(GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
        }

//castle block
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BLOCK_SET.baseBlock, count = 4) {
            define('E', GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
            define('Q', Blocks.BRICKS)
            pattern("EQ")
            pattern("QE")
            unlockedByItem(GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
        }

//Cracked Castle Block
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRACKED_CASTLE_BLOCK_SET.baseBlock, count = 4) {
            define('E', Blocks.BRICKS)
            define('Q', GenerationsBlocks.CASTLE_BLOCK_SET.baseBlock)
            pattern("EQ")
            pattern("QE")
            unlockedByItem(GenerationsBlocks.CASTLE_BLOCK_SET.baseBlock)
        }

//Castle Pillar
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_PILLAR) {
            define('X', GenerationsBlocks.CASTLE_BLOCK_SET.baseBlock)
            pattern("X")
            pattern("X")
            pattern("X")
            unlockedByItem(GenerationsBlocks.ICE_BRICK_SET.baseBlock)
        }

//Broken Castle Pillar
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROKEN_CASTLE_PILLAR) {
            define('X', GenerationsBlocks.CASTLE_BLOCK_SET.baseBlock)
            pattern("X")
            pattern("X")
            unlockedByItem(GenerationsBlocks.ICE_BRICK_SET.baseBlock)
        }

//Castle Brick
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BRICK_SET.baseBlock, count = 4) {
            define('E', GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
            define('Q', Blocks.BRICKS)
            pattern("EQ")
            pattern("QE")
            unlockedByItem(GenerationsBlocks.TEMPLE_BRICK_SET.baseBlock)
        }

//Castle Brick 2
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BRICK_2_SET.baseBlock, count = 4) {
            define('E', GenerationsBlocks.CASTLE_BRICK_SET.baseBlock)
            define('Q', Blocks.BRICKS)
            pattern("EQ")
            pattern("QE")
            unlockedByItem(GenerationsBlocks.CASTLE_BRICK_SET.baseBlock)
        }

        //Gray Castle Brick
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CASTLE_BRICK_SET.baseBlock, count = 4) {
            define('Q', Blocks.STONE_BRICKS)
            define('E', Blocks.STONE)
            pattern("EQ")
            pattern("EQ")
            unlockedByItem(Blocks.STONE_BRICKS)
        }

        //Gray Castle Brick 2
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CASTLE_BRICK_2_SET.baseBlock, count = 4) {
            define('Q', Blocks.STONE_BRICKS)
            define('E', Blocks.QUARTZ_BLOCK)
            pattern("EQ")
            pattern("EQ")
            unlockedByItem(Blocks.QUARTZ_BLOCK)
            unlockedByItem(Blocks.STONE_BRICKS)
        }

        //White Castle Brick
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_CASTLE_BRICK_SET.baseBlock, count = 4) {
            define('E', Blocks.QUARTZ_BLOCK)
            define('Q', Blocks.BRICKS)
            pattern("EQ")
            pattern("EQ")
            unlockedByItem(Blocks.QUARTZ_BLOCK)
            unlockedByItem(Blocks.BRICKS)
        }

        //White Castle Brick 2
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_CASTLE_BRICK_2_SET.baseBlock, count = 4) {
            define('E', Blocks.QUARTZ_BLOCK)
            define('Q', GenerationsBlocks.WHITE_CASTLE_BRICK_SET.baseBlock)
            pattern("EQ")
            pattern("EQ")
            unlockedByItem(GenerationsBlocks.WHITE_CASTLE_BRICK_SET.baseBlock)
        }

        //Castle Wall
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_SET.baseBlock, count = 4) {
            define('E', Blocks.STONE_BRICKS)
            define('Q', ItemTags.DIRT)
            define('P', ItemTags.SAND)
            pattern("PQ")
            pattern("EE")
            unlockedByItem(Blocks.STONE_BRICKS)
            unlockedBy("has_sand", has(ItemTags.SAND))
            unlockedBy("has_dirt", has(ItemTags.DIRT))
        }

        //Castle Wall 2
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_2_SET.baseBlock, count = 4) {
            define('E', GenerationsBlocks.CASTLE_WALL_SET.baseBlock)
            define('Q', ItemTags.DIRT)
            pattern("QQ")
            pattern("EE")
            unlockedByItem(GenerationsBlocks.CASTLE_WALL_SET.baseBlock)
        }

        //Castle Wall 3
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_3_SET.baseBlock, count = 4) {
            define('E', Blocks.STONE_BRICKS)
            define('Q', GenerationsBlocks.CASTLE_WALL_SET.baseBlock)
            pattern("EE")
            pattern("QQ")
            unlockedByItem(GenerationsBlocks.CASTLE_WALL_SET.baseBlock)
        }

        //Castle Wall 4
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_4_SET.baseBlock, count = 4) {
            define('E', GenerationsBlocks.CASTLE_WALL_SET.baseBlock)
            define('P', ItemTags.SAND)
            pattern("PP")
            pattern("EE")
            unlockedByItem(GenerationsBlocks.CASTLE_WALL_SET.baseBlock)
        }

        //Castle Floor
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_FLOOR_SET.baseBlock, count = 6) {
            define('E', ItemTags.DIRT)
            define('P', ItemTags.SAND)
            pattern("EEE")
            pattern("PPP")
            unlockedBy("has_dirt", has(ItemTags.DIRT))
            unlockedBy("has_sand", has(ItemTags.SAND))
        }

        /*
         * Ice Blocks
         */
        //Ice Brick
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_BRICK_SET.baseBlock, count = 4) {
            define('E', Blocks.ICE)
            define('Q', Blocks.PACKED_ICE)
            pattern("EQ")
            pattern("QE")
            unlockedByItem(Blocks.PACKED_ICE)
        }

        //Ice Pillar
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR) {
            define('X', GenerationsBlocks.ICE_BRICK_SET.baseBlock)
            pattern("X")
            pattern("X")
            pattern("X")
            unlockedByItem(GenerationsBlocks.ICE_BRICK_SET.baseBlock)
        }

        //Broken Ice Pillar
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROKEN_ICE_PILLAR) {
            define('X', GenerationsBlocks.ICE_BRICK_SET.baseBlock)
            pattern("X")
            pattern("X")
            unlockedByItem(GenerationsBlocks.ICE_BRICK_SET.baseBlock)
        }

        //Ice Pillar Side
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR_SIDE_SET.baseBlock, count = 4) {
            define('X', GenerationsBlocks.ICE_BRICK_SET.baseBlock)
            define('#', Blocks.ICE)
            pattern("#X")
            pattern("X#")
            unlockedByItem(GenerationsBlocks.ICE_BRICK_SET.baseBlock)
        }

        //Ice Pillar Top
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR_TOP_SET.baseBlock, count = 4) {
            define('X', GenerationsBlocks.ICE_BRICK_SET.baseBlock)
            define('#', Blocks.PACKED_ICE)
            pattern("#X")
            pattern("X#")
            unlockedByItem(GenerationsBlocks.ICE_BRICK_SET.baseBlock)
        }

        /*
         * Rock Pallets
         */
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ROCK_SET.baseBlock, count = 4) {
            define('E', Blocks.GRANITE)
            define('Q', Blocks.STONE)
            pattern("EQ")
            pattern("QE")
            unlockedByItem(Blocks.GRANITE)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CAVE_ROCK_SET.baseBlock, count = 4) {
            define('E', Blocks.CLAY)
            define('Q', Blocks.STONE)
            pattern("EQ")
            pattern("QE")
            unlockedByItem(Blocks.CLAY)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CAVE_ROCK_FLOOR_SET.baseBlock) {
            define('X', Blocks.GRAVEL)
            define('C', GenerationsBlocks.CAVE_ROCK_SET.baseBlock)
            pattern("XC")
            pattern("XC")
            unlockedByItem(Blocks.GRAVEL)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CAVE_ROCK_FLOOR_SET.baseBlock) {
            define('X', Blocks.GRAVEL)
            pattern("XX")
            pattern("XX")
            unlockedByItem(Blocks.GRAVEL)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_CAVE_ROCK_FLOOR_SET.baseBlock) {
            define('X', Blocks.GRAVEL)
            define('Y', Blocks.ICE)
            pattern("XY")
            pattern("XY")
            unlockedByItem(Blocks.ICE)
        }

        //Bridge Block
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRIDGE_BLOCK_SET.baseBlock, count = 3) {
            define('E', Blocks.OAK_LOG)
            pattern("EEE")
            unlockedByItem(Blocks.OAK_LOG)
        }

        //Compressed Polished Blocks
        //Reason for changing to 3x3 is feature parity and deepslate conflicts with deepslate tiles.
        threeByThreePacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.COMPRESSED_POLISHED_ANDESITE_SET.baseBlock,
            Blocks.POLISHED_ANDESITE
        )
        threeByThreePacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.COMPRESSED_POLISHED_DIORITE_SET.baseBlock,
            Blocks.POLISHED_DIORITE
        )
        threeByThreePacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.COMPRESSED_POLISHED_GRANITE_SET.baseBlock,
            Blocks.POLISHED_GRANITE
        )
        threeByThreePacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.COMPRESSED_POLISHED_DEEPSLATE_SET.baseBlock,
            Blocks.POLISHED_DEEPSLATE
        )

        //Cobble Ruins Pallet
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_1_SET.baseBlock, count = 4) {
            define('E', Blocks.COBBLESTONE)
            define('Q', GenerationsBlocks.ROCK_SET.baseBlock)
            pattern("EQ")
            pattern("EQ")
            unlockedByItem(GenerationsBlocks.ROCK_SET.baseBlock)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_2_SET.baseBlock, count = 4) {
            define('E', GenerationsBlocks.ROCK_SET.baseBlock)
            define('Q', Blocks.COBBLESTONE)
            pattern("EQ")
            pattern("EQ")
            unlockedByItem(GenerationsBlocks.ROCK_SET.baseBlock)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_3_SET.baseBlock, count = 4) {
            define('E', GenerationsBlocks.ROCK_SET.baseBlock)
            define('Q', Blocks.COBBLESTONE)
            pattern("EE")
            pattern("QQ")
            unlockedByItem(GenerationsBlocks.ROCK_SET.baseBlock)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_4_SET.baseBlock, count = 4) {
            define('E', Blocks.COBBLESTONE)
            define('Q', GenerationsBlocks.ROCK_SET.baseBlock)
            pattern("EE")
            pattern("QQ")
            unlockedByItem(GenerationsBlocks.ROCK_SET.baseBlock)
        }

        //House Floors
        //Missing Blocks for 1+2 recipes
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.HOUSE_FLOOR_1.value(),
            GenerationsBlocks.BURST_TURF.value()
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.HOUSE_FLOOR_2.value(),
            GenerationsBlocks.MIRRORED_FLOOR_1_SET.baseBlock
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.HOUSE_FLOOR_3.value(),
            GenerationsBlocks.RUINS_SAND.value()
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.HOUSE_FLOOR_4.value(),
            GenerationsBlocks.OCEAN_BLOCK_SET.baseBlock
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.HOUSE_FLOOR_5.value(),
            GenerationsBlocks.MIRROR_GLASS_SET.baseBlock
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.HOUSE_FLOOR_6.value(),
            GenerationsBlocks.NORMAL_SANDSTONE_SET.baseBlock
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.HOUSE_FLOOR_7.value(),
            GenerationsBlocks.ICE_PILLAR_TOP_SET.baseBlock
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.HOUSE_FLOOR_8.value(),
            Items.WAXED_EXPOSED_CUT_COPPER
        )

        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_8)
                .define('X', GenerationsBlocks.COPPER_JUNK)
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(GenerationsBlocks.COPPER_JUNK), has(GenerationsBlocks.COPPER_JUNK))
                .save(consumer);
         */

        //Mirrored Floors
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.MIRRORED_FLOOR_1_SET.baseBlock,
            Items.BLUE_STAINED_GLASS
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.MIRRORED_FLOOR_2_SET.baseBlock,
            Items.GRAY_STAINED_GLASS
        )
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.MIRRORED_FLOOR_3_SET.baseBlock,
            Items.WHITE_STAINED_GLASS
        )

        //Floor 1-4
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.FLOOR_1_SET.baseBlock) {
            define('X', Blocks.ACACIA_LOG)
            define('Y', APRICORN_LOG)
            pattern("XY")
            pattern("YX")
            unlockedByItem(Blocks.ACACIA_LOG)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.FLOOR_2_SET.baseBlock) {
            define('X', GenerationsBlocks.VOLCANIC_STONE)
            define('Y', GenerationsBlocks.COOL_STONE_SET.baseBlock)
            pattern("XY")
            pattern("YX")
            unlockedByHolder(GenerationsBlocks.VOLCANIC_STONE)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.FLOOR_3_SET.baseBlock) {
            define('X', Blocks.BIRCH_LOG)
            define('Y', Blocks.RED_SANDSTONE)
            pattern("XY")
            pattern("YX")
            unlockedByItem(Blocks.BIRCH_LOG)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.FLOOR_4_SET.baseBlock) {
            define('X', Blocks.SANDSTONE)
            define('Y', Blocks.END_STONE)
            pattern("XY")
            pattern("YX")
            unlockedByItem(Blocks.END_STONE)
        }

        // Ocean Block
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.OCEAN_BLOCK_SET.baseBlock, count = 4) {
            requires(Items.PRISMARINE, 1)
            requires(WATER_STONE, 1)
            unlockedByItem(WATER_STONE)
        }

        // Water Quartz Block
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WATER_QUARTZ_SET.baseBlock, count = 4) {
            requires(Items.QUARTZ, 1)
            requires(GenerationsBlocks.OCEAN_BLOCK_SET.baseBlock, 1)
            unlockedByItem(Items.QUARTZ)
        }

        // Cool Stone
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COOL_STONE_SET.baseBlock, count = 1) {
            requires(Items.STONE, 1)
            requires(Items.WHITE_DYE, 1)
            unlockedByItem(Items.WHITE_DYE)
        }

        // Bleach Stone
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BLEACH_STONE_SET.baseBlock, count = 1) {
            requires(Items.COBBLESTONE, 1)
            requires(Items.WHITE_DYE, 1)
            unlockedByItem(Items.WHITE_DYE)
        }

        // Machine Block
//        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MACHINE_BLOCK) {
//            define('X', Blocks.SMOOTH_STONE)
//            define('Y', Items.REDSTONE)
//            pattern("XXX")
//            pattern("XYX")
//            pattern("XXX")
//            unlockedByItem(Blocks.SMOOTH_STONE)
//        }

        // Ruins Sand
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RUINS_SAND, count = 4) {
            requires(Items.SAND, 1)
            requires(GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock, 1)
            unlockedByItem(GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
        }

        // Ruins Wall
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RUINS_WALL, count = 4) {
            define('X', ItemTags.SAND)
            define('Y', GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
            pattern("XY")
            pattern("XY")
            unlockedByItem(GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
        }

        // Dusty Ruins Wall
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.DUSTY_RUINS_WALL, count = 4) {
            define('X', ItemTags.SAND)
            define('Y', GenerationsBlocks.RUINS_WALL)
            pattern("XY")
            pattern("XY")
            unlockedByHolder(GenerationsBlocks.RUINS_WALL)
        }

        // ChargeStone Recipes
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CHARGE_STONE_BRICKS, count = 4) {
            define('#', GenerationsBlocks.CHARGE_STONE_SET.baseBlock)
            pattern("##")
            pattern("##")
            unlockedByItem(GenerationsBlocks.CHARGE_STONE_SET.baseBlock)
        }

        // Mossy Charge Variants (VINE)
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE_SET.baseBlock, getConversionRecipeName(GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE_SET.baseBlock, Blocks.VINE)) {
            requires(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock)
            requires(Blocks.VINE)
            group("mossy_charge_cobblestone")
            unlockedByItem(Blocks.VINE)
        }

        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.baseBlock, getConversionRecipeName(GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.baseBlock, Blocks.VINE)) {
            requires(GenerationsBlocks.CHARGE_STONE_BRICKS)
            requires(Blocks.VINE)
            group("mossy_charge_stone_bricks")
            unlockedByItem(Blocks.VINE)
        }

        // Mossy Charge Variants (MOSS_BLOCK)
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE_SET.baseBlock, getConversionRecipeName(GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE_SET.baseBlock, Blocks.MOSS_BLOCK)) {
            requires(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock)
            requires(Blocks.MOSS_BLOCK)
            group("mossy_charge_cobblestone")
            unlockedByItem(Blocks.MOSS_BLOCK)
        }

        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.baseBlock, getConversionRecipeName(GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.baseBlock, Blocks.MOSS_BLOCK)) {
            requires(GenerationsBlocks.CHARGE_STONE_BRICKS)
            requires(Blocks.MOSS_BLOCK)
            group("mossy_charge_stone_bricks")
            unlockedByItem(Blocks.MOSS_BLOCK)
        }

        // Bright Charge Cobblestone
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRIGHT_CHARGE_COBBLESTONE_SET.baseBlock) {
            define('Q', GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock)
            define('E', Items.GLOWSTONE_DUST)
            pattern("EEE")
            pattern("EQE")
            pattern("EEE")
            unlockedByItem(GenerationsBlocks.CHARGE_COBBLESTONE_SET.baseBlock)
        }

        // Volcanic Stone Recipes
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.VOLCANIC_STONE_BRICKS, count = 4) {
            define('#', GenerationsBlocks.VOLCANIC_STONE)
            pattern("##")
            pattern("##")
            unlockedByHolder(GenerationsBlocks.VOLCANIC_STONE)
        }

        // Mossy Volcanic Variants (VINE)
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_VOLCANIC_COBBLESTONE_SET.baseBlock, getConversionRecipeName(GenerationsBlocks.MOSSY_VOLCANIC_COBBLESTONE_SET.baseBlock, Blocks.VINE)) {
            requires(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock)
            requires(Blocks.VINE)
            group("mossy_volcanic_cobblestone")
            unlockedByItem(Blocks.VINE)
        }

        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.baseBlock, getConversionRecipeName(GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.baseBlock, Blocks.VINE)) {
            requires(GenerationsBlocks.VOLCANIC_STONE_BRICKS)
            requires(Blocks.VINE)
            group("mossy_volcanic_stone_bricks")
            unlockedByItem(Blocks.VINE)
        }

        // Mossy Volcanic Variants (MOSS_BLOCK)
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_VOLCANIC_COBBLESTONE_SET.baseBlock, getConversionRecipeName(GenerationsBlocks.MOSSY_VOLCANIC_COBBLESTONE_SET.baseBlock, Blocks.MOSS_BLOCK)) {
            requires(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.baseBlock)
            requires(Blocks.MOSS_BLOCK)
            group("mossy_volcanic_cobblestone")
            unlockedByItem(Blocks.MOSS_BLOCK)
        }

        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.baseBlock, getConversionRecipeName(GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.baseBlock, Blocks.MOSS_BLOCK)) {
            requires(GenerationsBlocks.VOLCANIC_STONE_BRICKS)
            requires(Blocks.MOSS_BLOCK)
            group("mossy_volcanic_stone_bricks")
            unlockedByItem(Blocks.MOSS_BLOCK)
        }

        // Dark Prismarine Pillar
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.DARK_PRISMARINE_PILLAR) {
            define('X', Items.DARK_PRISMARINE)
            pattern("X")
            pattern("X")
            pattern("X")
            unlockedByItem(Items.DARK_PRISMARINE)
        }

        // Dark Prismarine Pillar Broken
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROKEN_DARK_PRISMARINE_PILLAR) {
            define('X', Items.DARK_PRISMARINE)
            pattern("X")
            pattern("X")
            unlockedByItem(Items.DARK_PRISMARINE)
        }

        // Prismarine Pillar
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.PRISMARINE_PILLAR) {
            define('X', Items.PRISMARINE)
            pattern("X")
            pattern("X")
            pattern("X")
            unlockedByItem(Items.PRISMARINE)
        }

        // Prismarine Pillar Broken
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROKEN_PRISMARINE_PILLAR) {
            define('X', Items.PRISMARINE)
            pattern("X")
            pattern("X")
            unlockedByItem(Items.PRISMARINE)
        }


        //Haunted Pillar
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HAUNTED_PILLAR) {
            define('X', GenerationsBlocks.GHOST_BRICKS_SET.baseBlock)
            pattern("X")
            pattern("X")
            pattern("X")
            unlockedByItem(GenerationsBlocks.GHOST_BRICKS_SET.baseBlock)
        }

        //Haunted Pillar Broken
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROKEN_HAUNTED_PILLAR) {
            define('X', GenerationsBlocks.GHOST_BRICKS_SET.baseBlock)
            pattern("X")
            pattern("X")
            unlockedByItem(GenerationsBlocks.GHOST_BRICKS_SET.baseBlock)
        }

        //Warning Block
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WARNING_BLOCK) {
            requires(Items.YELLOW_CONCRETE, 1)
            requires(Items.BLACK_CONCRETE, 1)
            unlockedByItem(Items.YELLOW_CONCRETE)
        }

        //Crate
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRATE) {
            requires(APRICORN_PLANKS, 9)
            unlockedByItem(APRICORN_PLANKS)
        }

        //Golden Temple Recipes
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE) {
            define('#', GenerationsBlocks.GOLDEN_TEMPLE_SAND)
            pattern("##")
            pattern("##")
            unlockedByHolder(GenerationsBlocks.GOLDEN_TEMPLE_SAND)
        }

        nineBlockStorageRecipes(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsItems.CRYSTAL.value(),
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.CRYSTAL_BLOCK.value()
        )

        //Crystal Slab
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRYSTAL_SLAB) {
            define('X', GenerationsItems.CRYSTAL)
            pattern("XXX")
            unlockedByHolder(GenerationsItems.CRYSTAL)
        }

        //Crystal Stairs
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRYSTAL_STAIRS) {
            define('X', GenerationsItems.CRYSTAL)
            pattern("X  ")
            pattern("XX ")
            pattern("XXX")
            unlockedByHolder(GenerationsItems.CRYSTAL)
        }

        //Crystal Wall
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRYSTAL_WALL) {
            define('X', GenerationsItems.CRYSTAL)
            pattern("X  ")
            pattern("XX ")
            pattern("XXX")
            unlockedByHolder(GenerationsItems.CRYSTAL)
        }

        //Crystal Light
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRYSTAL_LIGHT) {
            define('X', GenerationsItems.CRYSTAL)
            define('Y', Blocks.SEA_LANTERN)
            pattern("XXX")
            pattern("XYX")
            pattern("XXX")
            unlockedByHolder(GenerationsItems.CRYSTAL)
        }

        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.SAPPHIRE_BLOCK.value(), GenerationsItems.SAPPHIRE.value())
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.RUBY_BLOCK.value(), GenerationsItems.RUBY.value())

        /*
         * PokeChests
         */
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKEBALL_CHEST) {
            define('E', POKE_BALL.asItem())
            define('X', Blocks.CHEST)
            pattern("EEE")
            pattern("EXE")
            pattern("EEE")
            unlockedByItem(POKE_BALL.asItem())
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GREATBALL_CHEST) {
            define('E', GREAT_BALL.asItem())
            define('X', GenerationsBlocks.POKEBALL_CHEST)
            pattern("EEE")
            pattern("EXE")
            pattern("EEE")
            unlockedByHolder(GenerationsBlocks.POKEBALL_CHEST)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ULTRABALL_CHEST) {
            define('E', ULTRA_BALL.asItem())
            define('X', GenerationsBlocks.GREATBALL_CHEST)
            pattern("EEE")
            pattern("EXE")
            pattern("EEE")
            unlockedByHolder(GenerationsBlocks.GREATBALL_CHEST)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MASTERBALL_CHEST) {
            define('E', MASTER_BALL.asItem())
            define('X', GenerationsBlocks.ULTRABALL_CHEST)
            pattern("EEE")
            pattern("EXE")
            pattern("EEE")
            unlockedByHolder(GenerationsBlocks.ULTRABALL_CHEST)
        }

        //Evolution Stone Block Recipes
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.DAWN_STONE_BLOCK.value(), DAWN_STONE.asItem())
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.DUSK_STONE_BLOCK.value(), DUSK_STONE.asItem())
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.FIRE_STONE_BLOCK.value(), FIRE_STONE.asItem())

        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.ICE_STONE_BLOCK.value(), ICE_STONE.asItem())

        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.LEAF_STONE_BLOCK.value(), LEAF_STONE.asItem())
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.MOON_STONE_BLOCK.value(), MOON_STONE.asItem())
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.SHINY_STONE_BLOCK.value(), SHINY_STONE.asItem())
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.SUN_STONE_BLOCK.value(), SUN_STONE.asItem())

        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.THUNDER_STONE_BLOCK.value(), THUNDER_STONE.asItem())
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.WATER_STONE_BLOCK.value(), WATER_STONE.asItem())

        //Silicon Block
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.SILICON_BLOCK.value(), GenerationsItems.SILICON.value())

        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.Z_BLOCK.value(), GenerationsItems.Z_INGOT.value())
        nineStorageBlockRecipe(recipeOutput, GenerationsBlocks.ULTRITE_BLOCK.value(), GenerationsItems.ULTRITE_INGOT.value())

        //pokebrick Recipes
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.BLACK_DYE,
            GenerationsBlocks.BLACK_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.BLUE_DYE,
            GenerationsBlocks.BLUE_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.BROWN_DYE,
            GenerationsBlocks.BROWN_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.CYAN_DYE,
            GenerationsBlocks.CYAN_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.GRAY_DYE,
            GenerationsBlocks.GRAY_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.GREEN_DYE,
            GenerationsBlocks.GREEN_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.LIGHT_BLUE_DYE,
            GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.LIGHT_GRAY_DYE,
            GenerationsBlocks.LIGHT_GRAY_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.LIME_DYE,
            GenerationsBlocks.LIME_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.MAGENTA_DYE,
            GenerationsBlocks.MAGENTA_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.ORANGE_DYE,
            GenerationsBlocks.ORANGE_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.PINK_DYE,
            GenerationsBlocks.PINK_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.PURPLE_DYE,
            GenerationsBlocks.PURPLE_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.RED_DYE,
            GenerationsBlocks.RED_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.WHITE_DYE,
            GenerationsBlocks.WHITE_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.YELLOW_DYE,
            GenerationsBlocks.YELLOW_POKE_BRICK_SET.baseBlock,
            Blocks.BRICKS
        )

        buildBuildingBlockRecipes(
            recipeOutput,
            Items.BLACK_DYE,
            GenerationsBlocks.BLACK_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.BLUE_DYE,
            GenerationsBlocks.BLUE_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.BROWN_DYE,
            GenerationsBlocks.BROWN_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.CYAN_DYE,
            GenerationsBlocks.CYAN_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.GRAY_DYE,
            GenerationsBlocks.GRAY_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.GREEN_DYE,
            GenerationsBlocks.GREEN_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.LIGHT_BLUE_DYE,
            GenerationsBlocks.LIGHT_BLUE_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.LIGHT_GRAY_DYE,
            GenerationsBlocks.LIGHT_GRAY_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.LIME_DYE,
            GenerationsBlocks.LIME_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.MAGENTA_DYE,
            GenerationsBlocks.MAGENTA_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.ORANGE_DYE,
            GenerationsBlocks.ORANGE_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.PINK_DYE,
            GenerationsBlocks.PINK_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.PURPLE_DYE,
            GenerationsBlocks.PURPLE_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.RED_DYE,
            GenerationsBlocks.RED_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.WHITE_DYE,
            GenerationsBlocks.WHITE_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )
        buildBuildingBlockRecipes(
            recipeOutput,
            Items.YELLOW_DYE,
            GenerationsBlocks.YELLOW_MARBLE_SET.baseBlock,
            Blocks.QUARTZ_BLOCK
        )

        //Powder Blue Marble
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POWDER_BLUE_MARBLE_SET.baseBlock, count = 4) {
            requires(Blocks.QUARTZ_BLOCK, 4)
            requires(Items.LIGHT_BLUE_DYE)
            requires(Items.WHITE_DYE)
            unlockedByItem(Blocks.QUARTZ_BLOCK)
        }

        //Cursed Pumpkin Recipes
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CURSED_JACK_O_LANTERN) {
            define('E', GenerationsBlocks.CURSED_CARVED_PUMPKIN)
            define('J', Blocks.TORCH)
            pattern("E")
            pattern("J")
            unlockedByHolder(GenerationsBlocks.CURSED_CARVED_PUMPKIN)
        }

        /*
         * Unown Block Recipes
         */
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.UNOWN_BLOCK_BLANK) {
            define('X', GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
            pattern("XXX")
            pattern("XXX")
            pattern("XXX")
            unlockedByItem(GenerationsBlocks.TEMPLE_BLOCK_SET.baseBlock)
        }

        /*
         * Ghost Block Recipes
         */
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_BRICKS_SET.baseBlock, count = 16) {
            requires(Blocks.BRICKS)
            requires(GHOST_GEM)
            unlockedByItem(Blocks.BRICKS)
            unlockedByItem(GHOST_GEM)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_OBELISK_SET.baseBlock) {
            define('X', GenerationsBlocks.GHOST_BRICKS_SET.baseBlock)
            pattern("XX")
            pattern("XX")
            unlockedByItem(GenerationsBlocks.GHOST_BRICKS_SET.baseBlock)
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_PILLAR) {
            define('X', GenerationsBlocks.GHOST_BRICKS_SET.baseBlock)
            define('Y', GenerationsWood.GHOST_PLANKS)
            pattern("YX")
            pattern("XY")
            unlockedByItem(GenerationsBlocks.GHOST_BRICKS_SET.baseBlock)
            unlockedByHolder(GenerationsWood.GHOST_PLANKS)
        }

        //Ghost Log from Log
        //1 Gem 1 Log = 8 Logs
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsWood.GHOST_LOG, count = 8) {
            requires(GHOST_GEM)
            requires(ItemTags.LOGS)
            unlockedByItem(GHOST_GEM)
        }

        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsWood.GHOST_PLANKS, "ghost_planks_from_other_planks", count = 32) {
            requires(GHOST_GEM)
            requires(ItemTags.PLANKS)
            unlockedByItem(GHOST_GEM)
            unlockedBy("has_planks", has(ItemTags.PLANKS))
        }

        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_LANTERN, count = 4) {
            define('X', GenerationsWood.GHOST_PLANKS)
            define('Y', Items.GLOWSTONE_DUST)
            define('Z', Blocks.REDSTONE_LAMP)
            pattern("XXX")
            pattern("ZYZ")
            pattern("XXX")
            unlockedByHolder(GenerationsWood.GHOST_PLANKS)
            unlockedByItem(Items.GLOWSTONE_DUST)
            unlockedByItem(Blocks.REDSTONE_LAMP)
        }

        //Ultra Sand/SandStone
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ULTRA_SANDSTONE) {
            define('#', GenerationsBlocks.ULTRA_SAND)
            pattern("##")
            pattern("##")
            unlockedByHolder(GenerationsBlocks.ULTRA_SAND)
        }

        chiseled(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.ULTRA_CHISELED_SANDSTONE.value(),
            GenerationsBlocks.ULTRA_SANDSTONE.value()
        )

        cut(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.ULTRA_CUT_SANDSTONE.value(),
            GenerationsBlocks.ULTRA_SANDSTONE.value()
        )

        //Charge Dripstone
        twoByTwoPacker(
            recipeOutput,
            RecipeCategory.BUILDING_BLOCKS,
            GenerationsBlocks.CHARGE_DRIPSTONE_BLOCK.value(),
            GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.value()
        )

        ultriteUpgradeDuplication(recipeOutput)
    }

    private fun nineStorageBlockRecipe(consumer: RecipeOutput, block: Block, item: Item) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
            .define('E', item)
            .pattern("EEE")
            .pattern("EEE")
            .pattern("EEE")
            .unlockedBy(getHasName(item), has(item))
            .save(consumer)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item, 9)
            .requires(block)
            .unlockedBy(getHasName(item), has(item))
            .save(consumer, block.id.withSuffix("_decompress"))
    }

    private fun ultriteUpgradeDuplication(recipeOutput: RecipeOutput) {
        recipeOutput.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsItems.ULTRITE_UPGRADE_SMITHING_TEMPLATE, count = 2) {
            define('E', GenerationsItems.ULTRITE_UPGRADE_SMITHING_TEMPLATE)
            define('T', Blocks.END_STONE)
            define('X', Items.NETHERITE_INGOT)
            pattern("XEX")
            pattern("XTX")
            pattern("XXX")
            unlockedByHolder(GenerationsItems.ULTRITE_UPGRADE_SMITHING_TEMPLATE)
        }
    }

    private fun buildBuildingBlockRecipes(
        recipeOutput: RecipeOutput,
        dye: Item,
        pokebrick: Block,
        block: Block
    ) {
        recipeOutput.shapeless(RecipeCategory.BUILDING_BLOCKS, pokebrick, count = 4) {
            requires(block, 4)
            requires(dye)
            unlockedByItem(block)
        }
    }


    private fun generateForEnabledBlockFamilies(consumer: RecipeOutput) {
//        GenerationsBlocks.PINK_MARBLE_SET.blockFamily?.run {
//            generateRecipes(consumer, this, FeatureFlagSet.of())
//            generateStoneCutterRecipesForFamily(consumer, this)
//        }

        GenerationsBlockFamilies.allGenerationsFamilies.forEach { arg ->
            generateRecipes(consumer, arg, FeatureFlags.VANILLA_SET)
            generateStoneCutterRecipesForFamily(consumer, arg)
        }
        GenerationsUltraBlockSet.ultraBlockSets.forEach { arg ->
            val family: BlockFamily = arg.blockFamily ?: return
            generateRecipes(consumer, family, FeatureFlags.VANILLA_SET)
            generateStoneCutterRecipesForFamily(consumer, family)
        }
        GenerationsBlockSet.blockSets.forEach { arg ->
            val family: BlockFamily = arg.blockFamily ?: return
            generateRecipes(consumer, family, FeatureFlags.VANILLA_SET)
            generateStoneCutterRecipesForFamily(consumer, family)
        }
        GenerationsFullBlockSet.fullBlockSets.forEach { arg ->
            val family: BlockFamily = arg.blockFamily ?: return
            generateRecipes(consumer, family, FeatureFlags.VANILLA_SET)
            generateStoneCutterRecipesForFamily(consumer, family)
        }
    }

    private fun generateStoneCutterRecipesForFamily(consumer: RecipeOutput, family: BlockFamily) {
        if (Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(family.baseBlock)).toString().contains("planks")) return
        if (family.variants.containsKey(BlockFamily.Variant.SLAB)) stonecutterResultFromBase(
            consumer, RecipeCategory.BUILDING_BLOCKS,
            family[BlockFamily.Variant.SLAB], family.baseBlock, 2
        )
        if (family.variants.containsKey(BlockFamily.Variant.STAIRS)) stonecutterResultFromBase(
            consumer, RecipeCategory.BUILDING_BLOCKS,
            family[BlockFamily.Variant.STAIRS], family.baseBlock
        )
        if (family.variants.containsKey(BlockFamily.Variant.WALL)) stonecutterResultFromBase(
            consumer, RecipeCategory.BUILDING_BLOCKS,
            family[BlockFamily.Variant.WALL], family.baseBlock
        )
        if (family.variants.containsKey(BlockFamily.Variant.CHISELED)) stonecutterResultFromBase(
            consumer, RecipeCategory.BUILDING_BLOCKS,
            family[BlockFamily.Variant.CHISELED], family.baseBlock
        )
    }
}
