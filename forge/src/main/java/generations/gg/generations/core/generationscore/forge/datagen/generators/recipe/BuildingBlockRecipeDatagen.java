package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.forge.datagen.data.families.GenerationsBlockFamilies;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
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

        //PokeGrass
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_GRASS.get(), 4)
                .define('X', Blocks.GRASS)
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(Blocks.GRASS), has(Blocks.GRASS))
                .save(consumer);

        //PokeDirt
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_DIRT.get(), 4)
                .define('X', Blocks.DIRT)
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(Blocks.DIRT), has(Blocks.DIRT))
                .save(consumer);

        //PokeSand
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND.get(), 4)
                .define('X', Blocks.SAND)
                .define('Y', Blocks.SANDSTONE)
                .pattern("XX")
                .pattern("YY")
                .unlockedBy(getHasName(Blocks.SANDSTONE), has(Blocks.SANDSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND_CORNER_1.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern(" Y")
                .pattern("X ")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND_CORNER_2.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern("Y ")
                .pattern(" X")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND_CORNER_3.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern(" X")
                .pattern("Y ")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND_CORNER_4.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern("X ")
                .pattern(" Y")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND_SIDE_1.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern("Y ")
                .pattern("YX")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND_SIDE_2.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern(" X")
                .pattern("YY")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND_SIDE_3.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern("YY")
                .pattern("X ")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKE_SAND_SIDE_4.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern("XY")
                .pattern(" Y")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SANDY_GRASS.get())
                .define('X', GenerationsBlocks.POKE_SAND.get())
                .define('Y', Blocks.GRASS)
                .pattern("YYY")
                .pattern("YXY")
                .pattern("YYY")
                .unlockedBy(getHasName(GenerationsBlocks.POKE_SAND.get()), has(GenerationsBlocks.POKE_SAND.get()))
                .save(consumer);

        //Shingles
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .pattern("XYX")
                .pattern("YXY")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        //Colored Shingles
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.WHITE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIGHT_GRAY_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.LIGHT_GRAY_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.GRAY_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BLACK_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.BLACK_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROWN_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.BROWN_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RED_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.RED_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ORANGE_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.ORANGE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.YELLOW_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.YELLOW_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIME_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.LIME_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GREEN_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.GREEN_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CYAN_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.CYAN_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LIGHT_BLUE_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.LIGHT_BLUE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BLUE_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.BLUE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.PURPLE_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.PURPLE_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MAGENTA_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.MAGENTA_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.PINK_SHINGLES.get(), 5)
                .define('X', Blocks.GRAVEL)
                .define('Y', Items.INK_SAC)
                .define('Z', Items.PINK_DYE)
                .pattern("XYX")
                .pattern("ZXZ")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
                .save(consumer);

        //shingles corner 1
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SHINGLES_CORNER_1.get())
                .define('X', GenerationsBlocks.SHINGLES.get())
                .define('Y', Items.BONE_MEAL)
                .define('Z', Items.INK_SAC)
                .pattern("  Y")
                .pattern(" X ")
                .pattern("ZZZ")
                .unlockedBy(getHasName(GenerationsBlocks.SHINGLES.get()), has(GenerationsBlocks.SHINGLES.get()))
                .save(consumer);

        //shingles corner 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SHINGLES_CORNER_2.get())
                .define('X', GenerationsBlocks.SHINGLES.get())
                .define('Y', Items.BONE_MEAL)
                .pattern(" Y")
                .pattern("X ")
                .unlockedBy(getHasName(GenerationsBlocks.SHINGLES.get()), has(GenerationsBlocks.SHINGLES.get()))
                .save(consumer);

        //Outside Wall
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.OUTSIDE_WALL.get())
                .requires(Blocks.STONE)
                .requires(Items.YELLOW_DYE)
                .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                .unlockedBy(getHasName(Items.YELLOW_DYE), has(Items.YELLOW_DYE))
                .save(consumer);

        //Inside Wall
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.INSIDE_WALL.get())
                .requires(Blocks.STONE)
                .requires(Items.WHITE_DYE)
                .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                .unlockedBy(getHasName(Items.WHITE_DYE), has(Items.WHITE_DYE))
                .save(consumer);

        //Inside Wall Molding
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.INSIDE_WALL_MOLDING.get(), 2)
                .requires(GenerationsBlocks.INSIDE_WALL.get(), 2)
                .unlockedBy(getHasName(GenerationsBlocks.INSIDE_WALL.get()), has(GenerationsBlocks.INSIDE_WALL.get()))
                .save(consumer);

        //Tree Bottom
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.TREE_BOTTOM.get())
                .define('X', Blocks.OAK_LEAVES)
                .define('Y', Blocks.OAK_LOG)
                .pattern("XXX")
                .pattern(" Y ")
                .unlockedBy(getHasName(Blocks.OAK_LEAVES), has(Blocks.OAK_LEAVES))
                .unlockedBy(getHasName(Blocks.OAK_LOG), has(Blocks.OAK_LOG))
                .save(consumer);

        //Tree Top
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.TREE_TOP.get())
                .define('X', Blocks.OAK_LEAVES)
                .define('Y', Items.STICK)
                .pattern("XYX")
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.OAK_LEAVES), has(Blocks.OAK_LEAVES))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(consumer);

        //Wooden Flooring
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WOODEN_FLOORING.get())
                .define('X', Blocks.OAK_SLAB)
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(Blocks.OAK_SLAB), has(Blocks.OAK_SLAB))
                .save(consumer);

        //Window 1
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WINDOW_1.get())
                .define('X', Blocks.IRON_BARS)
                .define('Y', Blocks.GLASS)
                .pattern("XYX")
                .unlockedBy(getHasName(Blocks.IRON_BARS), has(Blocks.IRON_BARS))
                .unlockedBy(getHasName(Blocks.GLASS), has(Blocks.GLASS))
                .save(consumer);

        //Window 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WINDOW_2.get())
                .define('X', Blocks.IRON_BARS)
                .define('Y', Blocks.GLASS)
                .define('Z', Items.PURPLE_DYE)
                .pattern(" Z ")
                .pattern("XYX")
                .pattern(" Z ")
                .unlockedBy(getHasName(Blocks.IRON_BARS), has(Blocks.IRON_BARS))
                .unlockedBy(getHasName(Blocks.GLASS), has(Blocks.GLASS))
                .unlockedBy(getHasName(Items.PURPLE_DYE), has(Items.PURPLE_DYE))
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.TEMPLE_BLOCK.get(), 4)
                .define('E', Blocks.STONE_BRICKS)
                .define('Q', Blocks.SANDSTONE)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(consumer);

        //temple brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.TEMPLE_BRICK.get(), 4)
                .define('E', GenerationsBlocks.TEMPLE_BLOCK.get())
                .pattern("EE")
                .pattern("EE")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK.get()), has(GenerationsBlocks.TEMPLE_BLOCK.get()))
                .save(consumer);

        //castle block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BLOCK.get(), 4)
                .define('E', GenerationsBlocks.TEMPLE_BLOCK.get())
                .define('Q', Blocks.BRICKS)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK.get()), has(GenerationsBlocks.TEMPLE_BLOCK.get()))
                .save(consumer);

        //Cracked Castle Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRACKED_CASTLE_BLOCK.get(), 4)
                .define('E', Blocks.BRICKS)
                .define('Q', GenerationsBlocks.CASTLE_BLOCK.get())
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_BLOCK.get()), has(GenerationsBlocks.CASTLE_BLOCK.get()))
                .save(consumer);


        //Castle Brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BRICK.get(), 4)
                .define('E', GenerationsBlocks.TEMPLE_BRICK.get())
                .define('Q', Blocks.BRICKS)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BRICK.get()), has(GenerationsBlocks.TEMPLE_BRICK.get()))
                .save(consumer);

        //Castle Brick 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_BRICK_2.get(), 4)
                .define('E', GenerationsBlocks.CASTLE_BRICK.get())
                .define('Q', Blocks.BRICKS)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_BRICK.get()), has(GenerationsBlocks.CASTLE_BRICK.get()))
                .save(consumer);

        //Gray Castle Brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CASTLE_BRICK.get(), 4)
                .define('Q', Blocks.STONE_BRICKS)
                .define('E', Blocks.STONE)
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(consumer);

        //Gray Castle Brick 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CASTLE_BRICK_2.get(), 4)
                .define('Q', Blocks.STONE_BRICKS)
                .define('E', Blocks.QUARTZ_BLOCK)
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(consumer);

        //White Castle Brick
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_CASTLE_BRICK.get(), 4)
                .define('E', Blocks.QUARTZ_BLOCK)
                .define('Q', Blocks.BRICKS)
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(Blocks.QUARTZ_BLOCK), has(Blocks.QUARTZ_BLOCK))
                .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                .save(consumer);

        //White Castle Brick 2
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WHITE_CASTLE_BRICK_2.get(), 4)
                .define('E', Blocks.QUARTZ_BLOCK)
                .define('Q', GenerationsBlocks.WHITE_CASTLE_BRICK.get())
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(GenerationsBlocks.WHITE_CASTLE_BRICK.get()), has(GenerationsBlocks.WHITE_CASTLE_BRICK.get()))
                .save(consumer);

        //Castle Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL.get(), 4)
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_2.get(), 4)
                .define('E', GenerationsBlocks.CASTLE_WALL.get())
                .define('Q', ItemTags.DIRT)
                .pattern("QQ")
                .pattern("EE")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_WALL.get()), has(GenerationsBlocks.CASTLE_WALL.get()))
                .save(consumer);

        //Castle Wall 3
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_3.get(), 4)
                .define('E', Blocks.STONE_BRICKS)
                .define('Q', GenerationsBlocks.CASTLE_WALL.get())
                .pattern("EE")
                .pattern("QQ")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_WALL.get()), has(GenerationsBlocks.CASTLE_WALL.get()))
                .save(consumer);

        //Castle Wall 4
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_WALL_4.get(), 4)
                .define('E', GenerationsBlocks.CASTLE_WALL.get())
                .define('P', ItemTags.SAND)
                .pattern("PP")
                .pattern("EE")
                .unlockedBy(getHasName(GenerationsBlocks.CASTLE_WALL.get()), has(GenerationsBlocks.CASTLE_WALL.get()))
                .save(consumer);

        //Castle Floor
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CASTLE_FLOOR.get(), 6)
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_BRICK.get(), 4)
                .define('E', Blocks.ICE)
                .define('Q', Blocks.PACKED_ICE)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(Blocks.PACKED_ICE), has(Blocks.PACKED_ICE))
                .save(consumer);

        //Ice Pillar
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR.get())
                .define('X', GenerationsBlocks.ICE_BRICK.get())
                .pattern("X")
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(GenerationsBlocks.ICE_BRICK.get()), has(GenerationsBlocks.ICE_BRICK.get()))
                .save(consumer);

        //Broken Ice Pillar
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BROKEN_ICE_PILLAR.get())
                .define('X', GenerationsBlocks.ICE_BRICK.get())
                .pattern("X")
                .pattern("X")
                .unlockedBy(getHasName(GenerationsBlocks.ICE_BRICK.get()), has(GenerationsBlocks.ICE_BRICK.get()))
                .save(consumer);

        //Ice Pillar Side
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR_SIDE.get(), 4)
                .define('X', GenerationsBlocks.ICE_BRICK.get())
                .define('#', Blocks.ICE)
                .pattern("#X")
                .pattern("X#")
                .unlockedBy(getHasName(GenerationsBlocks.ICE_BRICK.get()), has(GenerationsBlocks.ICE_BRICK.get()))
                .save(consumer);

        //Ice Pillar Top
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_PILLAR_TOP.get(), 4)
                .define('X', GenerationsBlocks.ICE_BRICK.get())
                .define('#', Blocks.PACKED_ICE)
                .pattern("#X")
                .pattern("X#")
                .unlockedBy(getHasName(GenerationsBlocks.ICE_BRICK.get()), has(GenerationsBlocks.ICE_BRICK.get()))
                .save(consumer);

        /*
         * Rock Pallets
         */
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ROCK.get(), 4)
                .define('E', Blocks.GRANITE)
                .define('Q', Blocks.STONE)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(Blocks.GRANITE), has(Blocks.GRANITE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CAVE_ROCK.get(), 4)
                .define('E', Blocks.CLAY)
                .define('Q', Blocks.STONE)
                .pattern("EQ")
                .pattern("QE")
                .unlockedBy(getHasName(Blocks.CLAY), has(Blocks.CLAY))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GRAY_CAVE_ROCK_FLOOR.get())
                .define('X', Blocks.GRAVEL)
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(Blocks.GRAVEL), has(Blocks.GRAVEL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_CAVE_ROCK_FLOOR.get())
                .define('X', Blocks.GRAVEL)
                .define('Y', Blocks.ICE)
                .pattern("XY")
                .pattern("XY")
                .unlockedBy(getHasName(Blocks.ICE), has(Blocks.ICE))
                .save(consumer);

        //Bridge Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRIDGE_BLOCK.get(), 3)
                .define('E', Blocks.OAK_LOG)
                .pattern("EEE")
                .unlockedBy(getHasName(Blocks.OAK_LOG), has(Blocks.OAK_LOG))
                .save(consumer);


        //Compressed Polished Blocks
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS,GenerationsBlocks.COMPRESSED_POLISHED_ANDESITE.get(), Blocks.POLISHED_ANDESITE);
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS,GenerationsBlocks.COMPRESSED_POLISHED_DIORITE.get(), Blocks.POLISHED_DIORITE);
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS,GenerationsBlocks.COMPRESSED_POLISHED_GRANITE.get(), Blocks.POLISHED_GRANITE);
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS,GenerationsBlocks.COMPRESSED_POLISHED_DEEPSLATE.get(), Blocks.POLISHED_DEEPSLATE);

        //Cobble Ruins Pallet
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_1.get(), 4)
                .define('E', Blocks.COBBLESTONE)
                .define('Q', GenerationsBlocks.ROCK.get())
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(GenerationsBlocks.ROCK.get()), has(GenerationsBlocks.ROCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_2.get(), 4)
                .define('E', GenerationsBlocks.ROCK.get())
                .define('Q', Blocks.COBBLESTONE)
                .pattern("EQ")
                .pattern("EQ")
                .unlockedBy(getHasName(GenerationsBlocks.ROCK.get()), has(GenerationsBlocks.ROCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_3.get(), 4)
                .define('E', GenerationsBlocks.ROCK.get())
                .define('Q', Blocks.COBBLESTONE)
                .pattern("EE")
                .pattern("QQ")
                .unlockedBy(getHasName(GenerationsBlocks.ROCK.get()), has(GenerationsBlocks.ROCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.COBBLE_RUINS_4.get(), 4)
                .define('E', Blocks.COBBLESTONE)
                .define('Q', GenerationsBlocks.ROCK.get())
                .pattern("EE")
                .pattern("QQ")
                .unlockedBy(getHasName(GenerationsBlocks.ROCK.get()), has(GenerationsBlocks.ROCK.get()))
                .save(consumer);

        //House Floors
        //Missing Blocks for 1+2 recipes
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_1.get(), GenerationsBlocks.BURST_TURF.get());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_2.get(), GenerationsBlocks.POKE_GRASS.get());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_3.get(), GenerationsBlocks.RUINS_SAND.get());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_4.get(), GenerationsBlocks.OCEAN_BLOCK.get());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_5.get(), GenerationsBlocks.MIRROR_GLASS.get());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_6.get(), GenerationsBlocks.NORMAL_SANDSTONE.get());
        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_7.get(), GenerationsBlocks.ICE_PILLAR_TOP.get());

        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.HOUSE_FLOOR_8.get())
                .define('X', GenerationsBlocks.COPPER_JUNK.get())
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(GenerationsBlocks.COPPER_JUNK.get()), has(GenerationsBlocks.COPPER_JUNK.get()))
                .save(consumer);
         */

        //Inside Wall bottom
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.INSIDE_WALL_BOTTOM.get())
                .define('X', GenerationsBlocks.BURST_TURF.get())
                .pattern("X X")
                .pattern("X X")
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsBlocks.BURST_TURF.get()), has(GenerationsBlocks.BURST_TURF.get()))
                .save(consumer);

        //Inside wall middle
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.INSIDE_WALL_MIDDLE.get())
                .define('X', GenerationsBlocks.BURST_TURF.get())
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(GenerationsBlocks.BURST_TURF.get()), has(GenerationsBlocks.BURST_TURF.get()))
                .save(consumer);

        //Inside wall top
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.INSIDE_WALL_TOP.get())
                .define('X', GenerationsBlocks.BURST_TURF.get())
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsBlocks.BURST_TURF.get()), has(GenerationsBlocks.BURST_TURF.get()))
                .save(consumer);

        //Ruins Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RUINS_WALL.get(), 4)
                .define('X', ItemTags.SAND)
                .define('Y', GenerationsBlocks.TEMPLE_BLOCK.get())
                .pattern("XY")
                .pattern("XY")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK.get()), has(GenerationsBlocks.TEMPLE_BLOCK.get()))
                .save(consumer);

        //Dusty Ruins Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.DUSTY_RUINS_WALL.get(), 4)
                .define('X', ItemTags.SAND)
                .define('Y', GenerationsBlocks.RUINS_WALL.get())
                .pattern("XY")
                .pattern("XY")
                .unlockedBy(getHasName(GenerationsBlocks.RUINS_WALL.get()), has(GenerationsBlocks.RUINS_WALL.get()))
                .save(consumer);

        //ChargeStone Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CHARGE_STONE_BRICKS.get(), 4)
                .define('E', GenerationsBlocks.CHARGE_STONE.get())
                .pattern("EE")
                .pattern("EE")
                .unlockedBy(getHasName(GenerationsBlocks.CHARGE_STONE.get()), has(GenerationsBlocks.CHARGE_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOSSY_CHARGE_COBBLESTONE.get())
                .define('E', GenerationsBlocks.CHARGE_COBBLESTONE.get())
                .define('Q', Blocks.VINE)
                .pattern("EQ")
                .unlockedBy(getHasName(GenerationsBlocks.CHARGE_COBBLESTONE.get()), has(GenerationsBlocks.CHARGE_COBBLESTONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRIGHT_CHARGE_COBBLESTONE.get())
                .define('Q', GenerationsBlocks.CHARGE_COBBLESTONE.get()).define('E', Items.GLOWSTONE_DUST)
                .pattern("EEE")
                .pattern("EQE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsBlocks.CHARGE_COBBLESTONE.get()), has(GenerationsBlocks.CHARGE_COBBLESTONE.get()))
                .save(consumer);

        //Volcanic Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.VOLCANIC_STONE_BRICKS.get(), 4)
                .define('E', GenerationsBlocks.VOLCANIC_STONE.get())
                .pattern("EE")
                .pattern("EE")
                .unlockedBy(getHasName(GenerationsBlocks.VOLCANIC_STONE.get()), has(GenerationsBlocks.VOLCANIC_STONE.get()))
                .save(consumer);

        //Golden Temple Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get()).define('#', GenerationsBlocks.GOLDEN_TEMPLE_SAND.get()).pattern("##").pattern("##").unlockedBy(getHasName(GenerationsBlocks.GOLDEN_TEMPLE_SAND.get()), has(GenerationsBlocks.GOLDEN_TEMPLE_SAND.get())).save(consumer);
        chiseled(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GOLDEN_TEMPLE_CHISELED_SANDSTONE.get(), GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get());
        cut(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE.get(), GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get());


        //Aluminum recipes
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.ALUMINUM_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ALUMINUM_BLOCK.get(), "aluminum_ingot_from_aluminum_block", "aluminum_ingot");

        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.ALUMINUM_NUGGET.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.ALUMINUM_INGOT.get(), "aluminum_ingot_from_nuggets", "aluminum_ingot");

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.RAW_ALUMINUM.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RAW_ALUMINUM_BLOCK.get());

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.CRYSTAL.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.CRYSTAL_BLOCK.get());

        //buildStairsCraftingRecipes(consumer, PixelmonBlocks.CRYSTAL_STAIRS, PixelmonBlocks.CRYSTAL_BLOCK, true);

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SAPPHIRE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SAPPHIRE_BLOCK.get());

        //buildStairsCraftingRecipes(consumer, PixelmonBlocks.SAPPHIRE_STAIRS, PixelmonBlocks.SAPPHIRE_BLOCK, true);

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.RUBY.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.RUBY_BLOCK.get());

        //buildStairsCraftingRecipes(consumer, PixelmonBlocks.RUBY_STAIRS, PixelmonBlocks.RUBY_BLOCK, true);

        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SILICON.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SILICON_BLOCK.get());

        //Evolution Recipes
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.DAWN_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.DAWN_STONE.get(), "dawn_stone_from_dawn_stone_shard", "dawn_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.DAWN_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.DAWN_STONE_BLOCK.get(), "dawn_stone_from_dusk_stone_block", "dawn_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.DUSK_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.DUSK_STONE.get(), "dusk_stone_from_dusk_stone_shard", "dusk_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.DUSK_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.DUSK_STONE_BLOCK.get(), "dusk_stone_from_dusk_stone_block", "dusk_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.FIRE_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.FIRE_STONE.get(), "fire_stone_from_fire_stone_shard", "fire_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.FIRE_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.FIRE_STONE_BLOCK.get(), "fire_stone_from_fire_stone_block", "fire_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.WATER_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.WATER_STONE.get(), "water_stone_from_water_stone_shard", "water_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.WATER_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.WATER_STONE_BLOCK.get(), "water_stone_from_water_stone_block", "water_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.THUNDER_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.THUNDER_STONE.get(), "thunder_stone_from_thunder_stone_shard", "thunder_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.THUNDER_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.THUNDER_STONE_BLOCK.get(), "thunder_stone_from_thunder_stone_block", "thunder_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.LEAF_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.LEAF_STONE.get(), "leaf_stone_from_leaf_stone_shard", "leaf_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.LEAF_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.LEAF_STONE_BLOCK.get(), "leaf_stone_from_leaf_stone_block", "leaf_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.MOON_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.MOON_STONE.get(), "moon_stone_from_moon_stone_shard", "moon_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.MOON_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MOON_STONE_BLOCK.get(), "moon_stone_from_moon_stone_block", "moon_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SUN_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SUN_STONE.get(), "sun_stone_from_sun_stone_shard", "sun_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SUN_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SUN_STONE_BLOCK.get(), "sun_stone_from_sun_stone_block", "sun_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SHINY_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SHINY_STONE.get(), "shiny_stone_from_shiny_stone_shard", "shiny_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.SHINY_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.SHINY_STONE_BLOCK.get(), "shiny_stone_from_shiny_stone_block", "shiny_stone");
        nineBlockStorageRecipesWithCustomPacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.ICE_STONE_SHARD.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsItems.ICE_STONE.get(), "ice_stone_from_ice_stone_shard", "ice_stone");
        nineBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.BUILDING_BLOCKS, GenerationsItems.ICE_STONE.get(), RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ICE_STONE_BLOCK.get(), "ice_stone_from_ice_stone_block", "ice_stone");

        /*
         * PokeChests
         */
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POKEBALL_CHEST.get())
                .define('E', GenerationsItems.POKE_BALL.get())
                .define('X', Blocks.CHEST)
                .pattern("EEE")
                .pattern("EXE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsItems.POKE_BALL.get()), has(GenerationsItems.POKE_BALL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GREATBALL_CHEST.get())
                .define('E', GenerationsItems.GREAT_BALL.get())
                .define('X', GenerationsBlocks.POKEBALL_CHEST.get())
                .pattern("EEE")
                .pattern("EXE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsBlocks.POKEBALL_CHEST.get()), has(GenerationsBlocks.POKEBALL_CHEST.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.ULTRABALL_CHEST.get())
                .define('E', GenerationsItems.ULTRA_BALL.get())
                .define('X', GenerationsBlocks.GREATBALL_CHEST.get())
                .pattern("EEE")
                .pattern("EXE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsBlocks.GREATBALL_CHEST.get()), has(GenerationsBlocks.GREATBALL_CHEST.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.MASTERBALL_CHEST.get())
                .define('E', GenerationsItems.MASTER_BALL.get())
                .define('X', GenerationsBlocks.ULTRABALL_CHEST.get())
                .pattern("EEE")
                .pattern("EXE")
                .pattern("EEE")
                .unlockedBy(getHasName(GenerationsBlocks.ULTRABALL_CHEST.get()), has(GenerationsBlocks.ULTRABALL_CHEST.get()))
                .save(consumer);



        //pokebrick Recipes
        buildBuildingBlockRecipes(consumer, Items.BLACK_DYE, GenerationsBlocks.BLACK_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.BLUE_DYE, GenerationsBlocks.BLUE_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.BROWN_DYE, GenerationsBlocks.BROWN_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.CYAN_DYE, GenerationsBlocks.CYAN_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.GRAY_DYE, GenerationsBlocks.GRAY_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.GREEN_DYE, GenerationsBlocks.GREEN_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.LIGHT_BLUE_DYE, GenerationsBlocks.LIGHT_BLUE_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.LIGHT_GRAY_DYE, GenerationsBlocks.LIGHT_GRAY_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.LIME_DYE, GenerationsBlocks.LIME_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.MAGENTA_DYE, GenerationsBlocks.MAGENTA_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.ORANGE_DYE, GenerationsBlocks.ORANGE_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.PINK_DYE, GenerationsBlocks.PINK_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.PURPLE_DYE, GenerationsBlocks.PURPLE_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.RED_DYE, GenerationsBlocks.RED_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.WHITE_DYE, GenerationsBlocks.WHITE_POKE_BRICK, Blocks.BRICKS);
        buildBuildingBlockRecipes(consumer, Items.YELLOW_DYE, GenerationsBlocks.YELLOW_POKE_BRICK, Blocks.BRICKS);

        buildBuildingBlockRecipes(consumer, Items.BLACK_DYE, GenerationsBlocks.BLACK_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.BLUE_DYE, GenerationsBlocks.BLUE_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.BROWN_DYE, GenerationsBlocks.BROWN_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.CYAN_DYE, GenerationsBlocks.CYAN_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.GRAY_DYE, GenerationsBlocks.GRAY_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.GREEN_DYE, GenerationsBlocks.GREEN_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.LIGHT_BLUE_DYE, GenerationsBlocks.LIGHT_BLUE_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.LIGHT_GRAY_DYE, GenerationsBlocks.LIGHT_GRAY_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.LIME_DYE, GenerationsBlocks.LIME_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.MAGENTA_DYE, GenerationsBlocks.MAGENTA_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.ORANGE_DYE, GenerationsBlocks.ORANGE_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.PINK_DYE, GenerationsBlocks.PINK_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.PURPLE_DYE, GenerationsBlocks.PURPLE_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.RED_DYE, GenerationsBlocks.RED_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.WHITE_DYE, GenerationsBlocks.WHITE_MARBLE, Blocks.QUARTZ_BLOCK);
        buildBuildingBlockRecipes(consumer, Items.YELLOW_DYE, GenerationsBlocks.YELLOW_MARBLE, Blocks.QUARTZ_BLOCK);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.POWDER_BLUE_MARBLE.get(), 4)
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
                .define('X', GenerationsBlocks.TEMPLE_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK.get()), has(GenerationsBlocks.TEMPLE_BLOCK.get()))
                .save(consumer);

        //unownBlock(consumer, GenerationsBlocks.UNOWN_BLOCK_A, GenerationsItems.UNOWN);

        /*
        * Braille Blocks
         */
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get(), 6)
                .define('X', GenerationsBlocks.TEMPLE_BLOCK.get())
                .pattern("XX")
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(GenerationsBlocks.TEMPLE_BLOCK.get()), has(GenerationsBlocks.TEMPLE_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_A.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern("  X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_B.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern("# X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_C.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern("  X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_D.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern(" #X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_E.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern(" #X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_F.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern("# X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_G.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern("##X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_H.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern("##X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_I.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern(" # ")
                .pattern("# X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_J.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern(" # ")
                .pattern("##X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_K.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern("  X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_L.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern("# X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_M.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern("  X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_N.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern(" #X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_O.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern(" #X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_P.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern("# X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_Q.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern("##X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_R.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern("##X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_S.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern(" # ")
                .pattern("# X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_T.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern(" # ")
                .pattern("##X")
                .pattern("#  ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_U.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern("  X")
                .pattern("## ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_V.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern("# X")
                .pattern("## ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_W.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern(" # ")
                .pattern("##X")
                .pattern(" # ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_X.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern("  X")
                .pattern("## ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_Y.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("## ")
                .pattern(" #X")
                .pattern("## ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_Z.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("#  ")
                .pattern(" #X")
                .pattern("## ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_PERIOD.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("##X")
                .pattern(" # ")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.BRAILLE_BLOCK_COMMA.get())
                .define('X', GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get())
                .define('#', Items.STONE_BUTTON)
                .pattern("# X")
                .unlockedBy(getHasName(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()), has(GenerationsBlocks.BRAILLE_BLOCK_UNDERSCORE.get()))
                .save(consumer);
        /*
         * Ghost Block Recipes
         */

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_BRICKS.get(), 16)
                .requires(Blocks.BRICKS)
                .requires(GenerationsItems.GHOST_GEM.get())
                .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                .unlockedBy(getHasName(GenerationsItems.GHOST_GEM.get()), has(GenerationsItems.GHOST_GEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_OBELISK.get())
                .define('X', GenerationsBlocks.GHOST_BRICKS.get())
                .pattern("XX")
                .pattern("XX")
                .unlockedBy(getHasName(GenerationsBlocks.GHOST_BRICKS.get()), has(GenerationsBlocks.GHOST_BRICKS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_PILLAR_SIDE.get(), 2)
                .define('X', GenerationsBlocks.GHOST_BRICKS.get())
                .define('Y', GenerationsWood.GHOST_PLANKS.get())
                .pattern("XY")
                .pattern("YX")
                .pattern("XY")
                .unlockedBy(getHasName(GenerationsBlocks.GHOST_BRICKS.get()), has(GenerationsBlocks.GHOST_BRICKS.get()))
                .unlockedBy(getHasName(GenerationsWood.GHOST_PLANKS.get()), has(GenerationsWood.GHOST_PLANKS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, GenerationsBlocks.GHOST_PILLAR_TOP.get())
                .define('X', GenerationsBlocks.GHOST_BRICKS.get())
                .define('Y', GenerationsWood.GHOST_PLANKS.get())
                .pattern("YX")
                .pattern("XY")
                .unlockedBy(getHasName(GenerationsBlocks.GHOST_BRICKS.get()), has(GenerationsBlocks.GHOST_BRICKS.get()))
                .unlockedBy(getHasName(GenerationsWood.GHOST_PLANKS.get()), has(GenerationsWood.GHOST_PLANKS.get()))
                .save(consumer);

        //Ghost Log from Log
        //1 Gem 1 Log = 8 Logs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, GenerationsWood.GHOST_LOG.get(), 8)
                .requires(GenerationsItems.GHOST_GEM.get())
                .requires(GenerationsWood.GHOST_LOG.get())
                .unlockedBy(getHasName(GenerationsItems.GHOST_GEM.get()), has(GenerationsItems.GHOST_GEM.get()))
                .unlockedBy(getHasName(GenerationsWood.GHOST_LOG.get()), has(GenerationsWood.GHOST_LOG.get()))
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

    private void buildBuildingBlockRecipes(@NotNull Consumer<FinishedRecipe> consumer, Item dye, RegistrySupplier<Block> pokebrick, Block block) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pokebrick.get(), 4)
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
            Field material = ObfuscationReflectionHelper.findField(BlockBehaviour.class, "material");
            material.setAccessible(true);
            try {
                if (material.get(arg.getBaseBlock()) == Material.STONE) generateStoneCutterRecipesForFamily(consumer, arg);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        GenerationsBlockFamilies.getAllUltraFamilies().forEach(arg -> generateRecipes(consumer, arg));
    }

    private void generateStoneCutterRecipesForFamily(@NotNull Consumer<FinishedRecipe> consumer, @NotNull BlockFamily family) {
        if (family.getVariants().containsKey(BlockFamily.Variant.SLAB)) stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.SLAB), family.getBaseBlock(), 2);
        if (family.getVariants().containsKey(BlockFamily.Variant.STAIRS)) stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.STAIRS), family.getBaseBlock());
        if (family.getVariants().containsKey(BlockFamily.Variant.WALL)) stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.WALL), family.getBaseBlock());
        if (family.getVariants().containsKey(BlockFamily.Variant.CHISELED)) stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, family.get(BlockFamily.Variant.CHISELED), family.getBaseBlock());
    }
}
