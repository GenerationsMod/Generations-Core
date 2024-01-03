package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import com.cobblemon.mod.common.CobblemonItems;
import generations.gg.generations.core.generationscore.forge.datagen.data.families.GenerationsBlockFamilies;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsBlockSet;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsFullBlockSet;
import generations.gg.generations.core.generationscore.world.level.block.set.GenerationsUltraBlockSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Creates recipes for building blocks using Forge Datagen
 * @see net.minecraft.data.recipes.packs.VanillaRecipeProvider
 * @see net.minecraft.data.recipes.RecipeProvider
 * @author J.T. McQuigg
 */
public class BuildingBlockRecipeDatagen extends GenerationsRecipeProvider.Proxied implements IConditionBuilder {

    public BuildingBlockRecipeDatagen(PackOutput output) {super(output);}

    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        generateForEnabledBlockFamilies(consumer);

        //Colored Shingles
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.WHITE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIGHT_GRAY_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.LIGHT_GRAY_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.GRAY_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BLACK_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.BLACK_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROWN_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.BROWN_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RED_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.RED_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ORANGE_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.ORANGE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.YELLOW_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.YELLOW_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIME_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.LIME_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GREEN_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.GREEN_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CYAN_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.CYAN_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIGHT_BLUE_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.LIGHT_BLUE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BLUE_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.BLUE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.PURPLE_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.PURPLE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MAGENTA_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.MAGENTA_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.PINK_SHINGLES_SET.getBaseBlock(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.PINK_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKECENTER_SIGN.get())
                .define('X', Blocks.IRON_BLOCK)
                .define('Y', Items.RED_DYE)
                .pattern(" X ")
                .pattern("Y Y")
                .unlockedBy(getHasName(Blocks.IRON_BLOCK), has(Blocks.IRON_BLOCK))
                .unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKEMART_SIGN.get())
                .define('X', Blocks.IRON_BLOCK)
                .define('Y', Items.BLUE_DYE)
                .pattern(" X ")
                .pattern("Y Y")
                .unlockedBy(getHasName(Blocks.IRON_BLOCK), has(Blocks.IRON_BLOCK))
                .unlockedBy(getHasName(Items.BLUE_DYE), has(Items.BLUE_DYE))
                .save(consumer);

        //temple block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock(), 4)
                .define('E', Blocks.STONE_BRICKS)
                .define('Q', Blocks.SANDSTONE)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(consumer);

        //temple brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.TEMPLE_BRICK_SET.getBaseBlock(), 4)
                .define('E', GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock())
                .pattern("EE")
                .pattern("EE")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock()), has(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock()))
                .save(consumer);

        //castle block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BLOCK_SET.getBaseBlock(), 4)
                .define('E', GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock())
                .define('Q', Blocks.BRICKS)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock()), has(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock()))
                .save(consumer);

        //Cracked Castle Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRACKED_CASTLE_BLOCK_SET.getBaseBlock(), 4)
                .define('E', Blocks.BRICKS)
                .define('Q', GenerationsBlocks.CASTLE_BLOCK_SET.getBaseBlock())
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_BLOCK_SET.getBaseBlock()), has(GenerationsBlocks.CASTLE_BLOCK_SET.getBaseBlock()))
                .save(consumer);


        //Castle Brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BRICK_SET.getBaseBlock(), 4)
                .define('E', GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock())
                .define('Q', Blocks.BRICKS)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BRICK_SET.getBaseBlock()), has(GenerationsBlocks.TEMPLE_BRICK_SET.getBaseBlock()))
                .save(consumer);

        //Castle Brick 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BRICK_2_SET.getBaseBlock(), 4)
                .define('E', GenerationsBlocks.CASTLE_BRICK_SET.getBaseBlock())
                .define('Q', Blocks.BRICKS)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_BRICK_SET.getBaseBlock()), has(GenerationsBlocks.CASTLE_BRICK_SET.getBaseBlock()))
                .save(consumer);

        //Gray Castle Brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CASTLE_BRICK_SET.getBaseBlock(), 4)
                .define('Q', Blocks.STONE_BRICKS)
                .define('E', Blocks.STONE)
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(consumer);

        //Gray Castle Brick 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CASTLE_BRICK_2_SET.getBaseBlock(), 4)
                .define('Q', Blocks.STONE_BRICKS)
                .define('E', Blocks.QUARTZ_BLOCK)
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(consumer);

        //White Castle Brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_CASTLE_BRICK_SET.getBaseBlock(), 4)
                .define('E', Blocks.QUARTZ_BLOCK)
                .define('Q', Blocks.BRICKS)
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                .save(consumer);

        //White Castle Brick 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_CASTLE_BRICK_2_SET.getBaseBlock(), 4)
                .define('E', Blocks.QUARTZ_BLOCK)
                .define('Q', GenerationsBlocks.WHITE_CASTLE_BRICK_SET.getBaseBlock())
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_CASTLE_BRICK_SET.getBaseBlock()), has(GenerationsBlocks.WHITE_CASTLE_BRICK_SET.getBaseBlock()))
                .save(consumer);

        //Castle Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock(), 4)
                .define('E', Blocks.STONE_BRICKS)
                .define('Q', ItemTags.DIRT)
                .define('P', ItemTags.SAND)
                .pattern("PQ")
                .pattern("EE")
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .unlockedBy("has_sand", has(ItemTags.SAND))
                .unlockedBy("has_dirt", has(ItemTags.DIRT))
                .save(consumer);

        //Castle Wall 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_2_SET.getBaseBlock(), 4)
                .define('E', GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock())
                .define('Q', ItemTags.DIRT)
                .pattern("QQ")
                .pattern("EE")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock()), has(GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock()))
                .save(consumer);

        //Castle Wall 3
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_3_SET.getBaseBlock(), 4)
                .define('E', Blocks.STONE_BRICKS)
                .define('Q', GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock())
                .pattern("EE")
                .pattern("QQ")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock()), has(GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock()))
                .save(consumer);

        //Castle Wall 4
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_4_SET.getBaseBlock(), 4)
                .define('E', GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock())
                .define('P', ItemTags.SAND)
                .pattern("PP")
                .pattern("EE")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock()), has(GenerationsBlocks.CASTLE_WALL_SET.getBaseBlock()))
                .save(consumer);

        //Castle Floor
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_FLOOR_SET.getBaseBlock(), 6)
                .define('E', ItemTags.DIRT)
                .define('P', ItemTags.SAND)
                .pattern("EEE")
                .pattern("PPP")
                .unlockedBy("has_dirt", has(ItemTags.DIRT))
                .unlockedBy("has_sand", has(ItemTags.SAND))
                .save(consumer);

        /*
         * Ice Blocks
         */
        //Ice Brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_BRICK_SET.getBaseBlock(), 4)
                .define('E', Blocks.ICE)
                .define('Q', Blocks.PACKED_ICE)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(Blocks.PACKED_ICE), has(Blocks.PACKED_ICE))
                .save(consumer);

        //Ice Pillar
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR.get())
                .define('X', GenerationsBlocks.ICE_BRICK_SET.getBaseBlock())
                .pattern("X")
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(GenerationsBlocks.ICE_BRICK_SET.getBaseBlock()), has(GenerationsBlocks.ICE_BRICK_SET.getBaseBlock()))
                .save(consumer);

        //Broken Ice Pillar
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROKEN_ICE_PILLAR.get())
                .define('X', GenerationsBlocks.ICE_BRICK_SET.getBaseBlock())
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(GenerationsBlocks.ICE_BRICK_SET.getBaseBlock()), has(GenerationsBlocks.ICE_BRICK_SET.getBaseBlock()))
                .save(consumer);

        //Ice Pillar Side
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR_SIDE_SET.getBaseBlock(), 4)
                .define('X', GenerationsBlocks.ICE_BRICK_SET.getBaseBlock())
                .define('#', Blocks.ICE)
                .pattern("#X")
                .pattern("X#")
                .unlockedBy(getHasName(GenerationsBlocks.ICE_BRICK_SET.getBaseBlock()), has(GenerationsBlocks.ICE_BRICK_SET.getBaseBlock()))
                .save(consumer);

        //Ice Pillar Top
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR_TOP_SET.getBaseBlock(), 4)
                .define('X', GenerationsBlocks.ICE_BRICK_SET.getBaseBlock())
                .define('#', Blocks.PACKED_ICE)
                .pattern("#X")
                .pattern("X#")
                .unlockedBy(getHasName(GenerationsBlocks.ICE_BRICK_SET.getBaseBlock()), has(GenerationsBlocks.ICE_BRICK_SET.getBaseBlock()))
                .save(consumer);

        /*
         * Rock Pallets
         */
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ROCK_SET.getBaseBlock(), 4)
                .define('E', Blocks.GRANITE)
                .define('Q', Blocks.STONE)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(Blocks.GRANITE), has(Blocks.GRANITE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CAVE_ROCK_SET.getBaseBlock(), 4)
                .define('E', Blocks.CLAY)
                .define('Q', Blocks.STONE)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(Blocks.CLAY), has(Blocks.CLAY))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CAVE_ROCK_FLOOR_SET.getBaseBlock())
                .define('X', Blocks.GRAVEL)
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_CAVE_ROCK_FLOOR_SET.getBaseBlock())
                .define('X', Blocks.GRAVEL)
                .define('Y', Blocks.ICE)
                .pattern("XY")
                .pattern("XY")
                .unlockedBy(getHasName(Blocks.ICE), has(Blocks.ICE))
                .save(consumer);

        //Bridge Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRIDGE_BLOCK_SET.getBaseBlock(), 3)
                .define('E', Blocks.OAK_LOG)
                .pattern("EEE")
                .unlockedBy(getHasName(Blocks.OAK_LOG), has(Blocks.OAK_LOG))
                .save(consumer);


        //Compressed Polished Blocks
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS,GenerationsBlocks.COMPRESSED_POLISHED_ANDESITE_SET.getBaseBlock(), Blocks.POLISHED_ANDESITE);
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS,GenerationsBlocks.COMPRESSED_POLISHED_DIORITE_SET.getBaseBlock(), Blocks.POLISHED_DIORITE);
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS,GenerationsBlocks.COMPRESSED_POLISHED_GRANITE_SET.getBaseBlock(), Blocks.POLISHED_GRANITE);
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS,GenerationsBlocks.COMPRESSED_POLISHED_DEEPSLATE_SET.getBaseBlock(), Blocks.POLISHED_DEEPSLATE);

        //Cobble Ruins Pallet
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_1_SET.getBaseBlock(), 4)
                .define('E', Blocks.COBBLESTONE)
                .define('Q', GenerationsBlocks.ROCK_SET.getBaseBlock())
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(GenerationsBlocks.ROCK_SET.getBaseBlock()), has(GenerationsBlocks.ROCK_SET.getBaseBlock()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_2_SET.getBaseBlock(), 4)
                .define('E', GenerationsBlocks.ROCK_SET.getBaseBlock())
                .define('Q', Blocks.COBBLESTONE)
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(GenerationsBlocks.ROCK_SET.getBaseBlock()), has(GenerationsBlocks.ROCK_SET.getBaseBlock()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_3_SET.getBaseBlock(), 4)
                .define('E', GenerationsBlocks.ROCK_SET.getBaseBlock())
                .define('Q', Blocks.COBBLESTONE)
                .pattern("EE")
                .pattern("QQ")
                .unlockedBy(getHasName(GenerationsBlocks.ROCK_SET.getBaseBlock()), has(GenerationsBlocks.ROCK_SET.getBaseBlock()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_4_SET.getBaseBlock(), 4)
                .define('E', Blocks.COBBLESTONE)
                .define('Q', GenerationsBlocks.ROCK_SET.getBaseBlock())
                .pattern("EE")
                .pattern("QQ")
                .unlockedBy(getHasName(GenerationsBlocks.ROCK_SET.getBaseBlock()), has(GenerationsBlocks.ROCK_SET.getBaseBlock()))
                .save(consumer);

        //House Floors
        //Missing Blocks for 1+2 recipes
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_1.get(), GenerationsBlocks.BURST_TURF.get());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_3.get(), GenerationsBlocks.RUINS_SAND.get());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_4.get(), GenerationsBlocks.OCEAN_BLOCK_SET.getBaseBlock());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_5.get(), GenerationsBlocks.MIRROR_GLASS_SET.getBaseBlock());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_6.get(), GenerationsBlocks.NORMAL_SANDSTONE_SET.getBaseBlock());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_7.get(), GenerationsBlocks.ICE_PILLAR_TOP_SET.getBaseBlock());

        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_8.get())
                .define('X', GenerationsBlocks.COPPER_JUNK.get())
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(GenerationsBlocks.COPPER_JUNK.get()), has(GenerationsBlocks.COPPER_JUNK.get()))
                .save(consumer);
         */

        //Ruins Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RUINS_WALL.get(), 4)
                .define('X', ItemTags.SAND)
                .define('Y', GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock())
                .pattern("XY")
                .pattern("XY")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock()), has(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock()))
                .save(consumer);

        //Dusty Ruins Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.DUSTY_RUINS_WALL.get(), 4)
                .define('X', ItemTags.SAND)
                .define('Y', GenerationsBlocks.RUINS_WALL.get())
                .pattern("XY")
                .pattern("XY")
                .unlockedBy(getHasName(GenerationsBlocks.RUINS_WALL.get()), has(GenerationsBlocks.RUINS_WALL.get()))
                .save(consumer);

        //ChargeStone Recipes  (Follow Stone Palette)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CHARGE_STONE_BRICKS.get(), 4)
                .define('#', GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock()), has(GenerationsBlocks.CHARGE_STONE_SET.getBaseBlock()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE_SET.getBaseBlock()).requires(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()).requires(Blocks.VINE).group("mossy_charge_cobblestone").unlockedBy(getHasName(Items.VINE), VanillaRecipeProvider.has(Blocks.VINE)).save(consumer, VanillaRecipeProvider.getConversionRecipeName(GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE_SET.getBaseBlock(), Blocks.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.getBaseBlock()).requires(GenerationsBlocks.CHARGE_STONE_BRICKS.get()).requires(Blocks.VINE).group("mossy_charge_stone_bricks").unlockedBy(getHasName(Items.VINE), VanillaRecipeProvider.has(Blocks.VINE)).save(consumer, VanillaRecipeProvider.getConversionRecipeName(GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.getBaseBlock(), Blocks.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE_SET.getBaseBlock()).requires(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()).requires(Blocks.MOSS_BLOCK).group("mossy_charge_cobblestone").unlockedBy(getHasName(Blocks.MOSS_BLOCK), VanillaRecipeProvider.has(Blocks.MOSS_BLOCK)).save(consumer, VanillaRecipeProvider.getConversionRecipeName(GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE_SET.getBaseBlock(), Blocks.MOSS_BLOCK));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.getBaseBlock()).requires(GenerationsBlocks.CHARGE_STONE_BRICKS.get()).requires(Blocks.MOSS_BLOCK).group("mossy_charge_stone_bricks").unlockedBy(getHasName(Blocks.MOSS_BLOCK), VanillaRecipeProvider.has(Blocks.MOSS_BLOCK)).save(consumer, VanillaRecipeProvider.getConversionRecipeName(GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS_SET.getBaseBlock(), Blocks.MOSS_BLOCK));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRIGHT_CHARGE_COBBLESTONE_SET.getBaseBlock())
                .define('Q', GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()).define('E', Items.GLOWSTONE_DUST)
                .pattern("EEE")
                .pattern("EQE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()), has(GenerationsBlocks.CHARGE_COBBLESTONE_SET.getBaseBlock()))
                .save(consumer);

        //Volcanic Stone Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.VOLCANIC_STONE_BRICKS.get(), 4)
                .define('#', GenerationsBlocks.VOLCANIC_STONE.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(GenerationsBlocks.VOLCANIC_STONE.get()), has(GenerationsBlocks.VOLCANIC_STONE.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_VOLCANIC_COBBLESTONE_SET.getBaseBlock()).requires(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock()).requires(Blocks.VINE).group("mossy_volcanic_cobblestone").unlockedBy(getHasName(Items.VINE), VanillaRecipeProvider.has(Blocks.VINE)).save(consumer, VanillaRecipeProvider.getConversionRecipeName(GenerationsBlocks.MOSSY_VOLCANIC_COBBLESTONE_SET.getBaseBlock(), Blocks.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.getBaseBlock()).requires(GenerationsBlocks.VOLCANIC_STONE_BRICKS.get()).requires(Blocks.VINE).group("mossy_volcanic_stone_bricks").unlockedBy(getHasName(Items.VINE), VanillaRecipeProvider.has(Blocks.VINE)).save(consumer, VanillaRecipeProvider.getConversionRecipeName(GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.getBaseBlock(), Blocks.VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_VOLCANIC_COBBLESTONE_SET.getBaseBlock()).requires(GenerationsBlocks.VOLCANIC_COBBLESTONE_SET.getBaseBlock()).requires(Blocks.MOSS_BLOCK).group("mossy_volcanic_cobblestone").unlockedBy(getHasName(Blocks.MOSS_BLOCK), VanillaRecipeProvider.has(Blocks.MOSS_BLOCK)).save(consumer, VanillaRecipeProvider.getConversionRecipeName(GenerationsBlocks.MOSSY_VOLCANIC_COBBLESTONE_SET.getBaseBlock(), Blocks.MOSS_BLOCK));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.getBaseBlock()).requires(GenerationsBlocks.VOLCANIC_STONE_BRICKS.get()).requires(Blocks.MOSS_BLOCK).group("mossy_volcanic_stone_bricks").unlockedBy(getHasName(Blocks.MOSS_BLOCK), VanillaRecipeProvider.has(Blocks.MOSS_BLOCK)).save(consumer, VanillaRecipeProvider.getConversionRecipeName(GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS_SET.getBaseBlock(), Blocks.MOSS_BLOCK));


        //Golden Temple Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get()).define('#', GenerationsBlocks.GOLDEN_TEMPLE_SAND.get()).pattern("##").pattern("##").unlockedBy(getHasName(GenerationsBlocks.GOLDEN_TEMPLE_SAND.get()), has(GenerationsBlocks.GOLDEN_TEMPLE_SAND.get())).save(consumer);

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.CRYSTAL.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRYSTAL_BLOCK.get());

        //buildStairsCraftingRecipes(consumer, PixelmonBlocks.CRYSTAL_STAIRS, PixelmonBlocks.CRYSTAL_BLOCK, true);

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SAPPHIRE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SAPPHIRE_BLOCK.get());

        //buildStairsCraftingRecipes(consumer, PixelmonBlocks.SAPPHIRE_STAIRS, PixelmonBlocks.SAPPHIRE_BLOCK, true);

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.RUBY.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RUBY_BLOCK.get());

        //buildStairsCraftingRecipes(consumer, PixelmonBlocks.RUBY_STAIRS, PixelmonBlocks.RUBY_BLOCK, true);

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SILICON.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SILICON_BLOCK.get());
        /*
         * PokeChests
         */
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKEBALL_CHEST.get())
                .define('E', CobblemonItems.POKE_BALL.asItem())
                .define('X', Blocks.CHEST)
                .pattern("EEE")
                .pattern("EXE")
                .pattern("EEE")
                .unlockedBy(getHasName(CobblemonItems.POKE_BALL.asItem()), has(CobblemonItems.POKE_BALL.asItem()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GREATBALL_CHEST.get())
                .define('E', CobblemonItems.GREAT_BALL.asItem())
                .define('X', GenerationsBlocks.POKEBALL_CHEST.get())
                .pattern("EEE")
                .pattern("EXE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsBlocks.POKEBALL_CHEST.get()), has(GenerationsBlocks.POKEBALL_CHEST.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ULTRABALL_CHEST.get())
                .define('E', CobblemonItems.ULTRA_BALL.asItem())
                .define('X', GenerationsBlocks.GREATBALL_CHEST.get())
                .pattern("EEE")
                .pattern("EXE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsBlocks.GREATBALL_CHEST.get()), has(GenerationsBlocks.GREATBALL_CHEST.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MASTERBALL_CHEST.get())
                .define('E', CobblemonItems.MASTER_BALL.asItem())
                .define('X', GenerationsBlocks.ULTRABALL_CHEST.get())
                .pattern("EEE")
                .pattern("EXE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsBlocks.ULTRABALL_CHEST.get()), has(GenerationsBlocks.ULTRABALL_CHEST.get()))
                .save(consumer);



        //pokebrick Recipes
        buildBuildingBlockRecipes(consumer, Items.BLACK_DYE, GenerationsBlocks.BLACK_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.BLUE_DYE, GenerationsBlocks.BLUE_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.BROWN_DYE, GenerationsBlocks.BROWN_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.CYAN_DYE, GenerationsBlocks.CYAN_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.GRAY_DYE, GenerationsBlocks.GRAY_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.GREEN_DYE, GenerationsBlocks.GREEN_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.LIGHT_BLUE_DYE, GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.LIGHT_GRAY_DYE, GenerationsBlocks.LIGHT_GRAY_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.LIME_DYE, GenerationsBlocks.LIME_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.MAGENTA_DYE, GenerationsBlocks.MAGENTA_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.ORANGE_DYE, GenerationsBlocks.ORANGE_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.PINK_DYE, GenerationsBlocks.PINK_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.PURPLE_DYE, GenerationsBlocks.PURPLE_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.RED_DYE, GenerationsBlocks.RED_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.WHITE_DYE, GenerationsBlocks.WHITE_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.YELLOW_DYE, GenerationsBlocks.YELLOW_POKE_BRICK_SET.getBaseBlock(), Blocks.BRICKS);

        buildBuildingBlockRecipes(consumer, Items.BLACK_DYE, GenerationsBlocks.BLACK_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.BLUE_DYE, GenerationsBlocks.BLUE_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.BROWN_DYE, GenerationsBlocks.BROWN_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.CYAN_DYE, GenerationsBlocks.CYAN_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.GRAY_DYE, GenerationsBlocks.GRAY_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.GREEN_DYE, GenerationsBlocks.GREEN_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.LIGHT_BLUE_DYE, GenerationsBlocks.LIGHT_BLUE_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.LIGHT_GRAY_DYE, GenerationsBlocks.LIGHT_GRAY_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.LIME_DYE, GenerationsBlocks.LIME_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.MAGENTA_DYE, GenerationsBlocks.MAGENTA_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.ORANGE_DYE, GenerationsBlocks.ORANGE_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.PINK_DYE, GenerationsBlocks.PINK_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.PURPLE_DYE, GenerationsBlocks.PURPLE_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.RED_DYE, GenerationsBlocks.RED_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.WHITE_DYE, GenerationsBlocks.WHITE_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.YELLOW_DYE, GenerationsBlocks.YELLOW_MARBLE_SET.getBaseBlock(), Blocks.QUARTZ_BLOCK);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POWDER_BLUE_MARBLE_SET.getBaseBlock(), 4)
                .requires(Blocks.QUARTZ_BLOCK, 4)
                .requires(Items.LIGHT_BLUE_DYE)
                .requires(Items.WHITE_DYE)
                .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                .save(consumer);

        //Cursed Pumpkin Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CURSED_JACK_O_LANTERN.get())
                .define('E', GenerationsBlocks.CURSED_CARVED_PUMPKIN.get())
                .define('J', Blocks.TORCH)
                .pattern("E")
                .pattern("J")
                .unlockedBy(getHasName(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get()), has(GenerationsBlocks.CURSED_CARVED_PUMPKIN.get()))
                .save(consumer);

        /*
         * Unown Block Recipes
         */
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.UNOWN_BLOCK_BLANK.get())
                .define('X', GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock()), has(GenerationsBlocks.TEMPLE_BLOCK_SET.getBaseBlock()))
                .save(consumer);

        //unownBlock(consumer, GenerationsBlocks.UNOWN_BLOCK_A, GenerationsItems.UNOWN);
        //TODO: Finish these.

        /*
         * Ghost Block Recipes
         */

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_BRICKS_SET.getBaseBlock(), 16)
                .requires(Blocks.BRICKS)
                .requires(GenerationsItems.GHOST_GEM.get())
                .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                .unlockedBy(getHasName(GenerationsItems.GHOST_GEM.get()), has(GenerationsItems.GHOST_GEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_OBELISK_SET.getBaseBlock())
                .define('X', GenerationsBlocks.GHOST_BRICKS_SET.getBaseBlock())
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(GenerationsBlocks.GHOST_BRICKS_SET.getBaseBlock()), has(GenerationsBlocks.GHOST_BRICKS_SET.getBaseBlock()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_PILLAR.get())
                .define('X', GenerationsBlocks.GHOST_BRICKS_SET.getBaseBlock())
                .define('Y', GenerationsWood.GHOST_PLANKS.get())
                .pattern("YX")
                .pattern("XY")
                .unlockedBy(getHasName(GenerationsBlocks.GHOST_BRICKS_SET.getBaseBlock()), has(GenerationsBlocks.GHOST_BRICKS_SET.getBaseBlock()))
                .unlockedBy(getHasName(GenerationsWood.GHOST_PLANKS.get()), has(GenerationsWood.GHOST_PLANKS.get()))
                .save(consumer);

        //Ghost Log from Log
        //1 Gem 1 Log = 8 Logs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsWood.GHOST_LOG.get(), 8)
                .requires(GenerationsItems.GHOST_GEM.get())
                .requires(ItemTags.LOGS)
                .unlockedBy(getHasName(GenerationsItems.GHOST_GEM.get()), has(GenerationsItems.GHOST_GEM.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsWood.GHOST_PLANKS.get(), 32)
                .requires(GenerationsItems.GHOST_GEM.get())
                .requires(ItemTags.PLANKS)
                .unlockedBy(getHasName(GenerationsItems.GHOST_GEM.get()), has(GenerationsItems.GHOST_GEM.get()))
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(consumer, "ghost_planks_from_other_planks");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_LANTERN.get(), 4)
                .define('X', GenerationsWood.GHOST_PLANKS.get())
                .define('Y', Items.GLOWSTONE_DUST)
                .define('Z', Blocks.REDSTONE_LAMP)
                .pattern("XXX")
                .pattern("ZYZ")
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsWood.GHOST_PLANKS.get()), has(GenerationsWood.GHOST_PLANKS.get()))
                .unlockedBy(getHasName(Items.GLOWSTONE_DUST), has(Items.GLOWSTONE_DUST))
                .unlockedBy(getHasName(Blocks.REDSTONE_LAMP), has(Blocks.REDSTONE_LAMP))
                .save(consumer);

        //Ultra Sand/SandStone
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ULTRA_SANDSTONE.get()).define('#', GenerationsBlocks.ULTRA_SAND.get()).pattern("##").pattern("##").unlockedBy(getHasName(GenerationsBlocks.ULTRA_SAND.get()), has(GenerationsBlocks.ULTRA_SAND.get())).save(consumer);
        chiseled(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ULTRA_CHISELED_SANDSTONE.get(), GenerationsBlocks.ULTRA_SANDSTONE.get());
        cut(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ULTRA_CUT_SANDSTONE.get(), GenerationsBlocks.ULTRA_SANDSTONE.get());

        //Charge Dripstone
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CHARGE_DRIPSTONE_BLOCK.get(), GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get());
    }

    private void buildBuildingBlockRecipes(@NotNull Consumer<FinishedRecipe> consumer, Item dye, Block pokebrick, Block block) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pokebrick, 4)
                .requires(block, 4)
                .requires(dye)
                .unlockedBy(getHasName(block), has(block))
                .save(consumer);
    }

    /*
    private void unownBlock(@NotNull Consumer<FinishedRecipe> consumer, @NotNull Block createdBlock, @NotNull ItemLike photo){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, createdBlock)
                .define('X', GenerationsBlocks.TEMPLE_BLOCK.get())
                .define('Y', photo)
                .pattern("XY")
                .unlockedBy(getHasName(GenerationsBlocks.UNOWN_BLOCK_BLANK.get()), has(GenerationsBlocks.UNOWN_BLOCK_BLANK.get()))
                .save(consumer);
    }*/

    protected void generateForEnabledBlockFamilies(@NotNull Consumer<FinishedRecipe> consumer) {
        GenerationsBlockFamilies.getAllFamilies().forEach(arg -> {
            generateRecipes(consumer, arg);
            generateStoneCutterRecipesForFamily(consumer, arg);
        });
        GenerationsUltraBlockSet.ultraBlockSets.forEach(arg -> {
            BlockFamily family = arg.getBlockFamily();
            generateRecipes(consumer, family);
            generateStoneCutterRecipesForFamily(consumer, family);
        });
        GenerationsBlockSet.getBlockSets().forEach(arg -> {
            BlockFamily family = arg.getBlockFamily();
            generateRecipes(consumer, family);
            generateStoneCutterRecipesForFamily(consumer, family);
        });
        GenerationsFullBlockSet.getFullBlockSets().forEach(arg -> {
            BlockFamily family = arg.getBlockFamily();
            generateRecipes(consumer, family);
            generateStoneCutterRecipesForFamily(consumer, family);
        });
    }

    private void generateStoneCutterRecipesForFamily(@NotNull Consumer<FinishedRecipe> consumer, @NotNull BlockFamily family) {
        if (Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(family.getBaseBlock())).toString().contains("planks")) return;
        if (family.getVariants().containsKey(BlockFamily.Variant.SLAB)) stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.SLAB), family.getBaseBlock(), 2);
        if (family.getVariants().containsKey(BlockFamily.Variant.STAIRS)) stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.STAIRS), family.getBaseBlock());
        if (family.getVariants().containsKey(BlockFamily.Variant.WALL)) stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.WALL), family.getBaseBlock());
        if (family.getVariants().containsKey(BlockFamily.Variant.CHISELED)) stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.CHISELED), family.getBaseBlock());
    }
}
