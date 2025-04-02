package generations.gg.generations.core.generationscore.forge.datagen.data.families;

import com.google.common.collect.Maps;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Generations Core Block Families
 * Used during Data Generation
 * @see BlockFamilies
 * @author Joseph T. McQuigg
 */
@SuppressWarnings("unused")
public class GenerationsBlockFamilies extends BlockFamilies {

	//Wood
	private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
	public static final BlockFamily GHOST_PLANKS = familyBuilder(GenerationsWood.GHOST_PLANKS.get()).button(GenerationsWood.GHOST_BUTTON.get()).fence(GenerationsWood.GHOST_FENCE.get()).fenceGate(GenerationsWood.GHOST_FENCE_GATE.get()).pressurePlate(GenerationsWood.GHOST_PRESSURE_PLATE.get()).sign(GenerationsWood.GHOST_SIGN.get(), GenerationsWood.GHOST_WALL_SIGN.get()).slab(GenerationsWood.GHOST_SLAB.get()).stairs(GenerationsWood.GHOST_STAIRS.get()).door(GenerationsWood.GHOST_DOOR.get()).trapdoor(GenerationsWood.GHOST_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
	public static final BlockFamily ULTRA_DARK_PLANKS = familyBuilder(GenerationsWood.ULTRA_DARK_PLANKS.get()).button(GenerationsWood.ULTRA_DARK_BUTTON.get()).fence(GenerationsWood.ULTRA_DARK_FENCE.get()).fenceGate(GenerationsWood.ULTRA_DARK_FENCE_GATE.get()).pressurePlate(GenerationsWood.ULTRA_DARK_PRESSURE_PLATE.get()).sign(GenerationsWood.ULTRA_DARK_SIGN.get(), GenerationsWood.ULTRA_DARK_WALL_SIGN.get()).slab(GenerationsWood.ULTRA_DARK_SLAB.get()).stairs(GenerationsWood.ULTRA_DARK_STAIRS.get()).door(GenerationsWood.ULTRA_DARK_DOOR.get()).trapdoor(GenerationsWood.ULTRA_DARK_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
	public static final BlockFamily ULTRA_JUNGLE_PLANKS = familyBuilder(GenerationsWood.ULTRA_JUNGLE_PLANKS.get()).button(GenerationsWood.ULTRA_JUNGLE_BUTTON.get()).fence(GenerationsWood.ULTRA_JUNGLE_FENCE.get()).fenceGate(GenerationsWood.ULTRA_JUNGLE_FENCE_GATE.get()).pressurePlate(GenerationsWood.ULTRA_JUNGLE_PRESSURE_PLATE.get()).sign(GenerationsWood.ULTRA_JUNGLE_SIGN.get(), GenerationsWood.ULTRA_JUNGLE_WALL_SIGN.get()).slab(GenerationsWood.ULTRA_JUNGLE_SLAB.get()).stairs(GenerationsWood.ULTRA_JUNGLE_STAIRS.get()).door(GenerationsWood.ULTRA_JUNGLE_DOOR.get()).trapdoor(GenerationsWood.ULTRA_JUNGLE_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();

	//Building Block Families
	public static final BlockFamily SMOOTH_CHARGE_STONE = familyBuilder(GenerationsBlocks.SMOOTH_CHARGE_STONE.get()).slab(GenerationsBlocks.SMOOTH_CHARGE_STONE_SLAB.get()).recipeGroupPrefix("smooth_charge_stone").recipeUnlockedBy("has_smooth_charge_stone").getFamily();
	public static final BlockFamily CHARGE_STONE_BRICKS = familyBuilder(GenerationsBlocks.CHARGE_STONE_BRICKS.get()).cracked(GenerationsBlocks.CRACKED_CHARGE_STONE_BRICKS.get()).slab(GenerationsBlocks.CHARGE_STONE_BRICK_SLAB.get()).stairs(GenerationsBlocks.CHARGE_STONE_BRICK_STAIRS.get()).wall(GenerationsBlocks.CHARGE_STONE_BRICK_WALL.get()).chiseled(GenerationsBlocks.CHISELED_CHARGE_STONE_BRICKS.get()).recipeGroupPrefix("charge_stone_bricks").recipeUnlockedBy("has_charge_stone_bricks").getFamily();
	public static final BlockFamily VOLCANIC_STONE = familyBuilder(GenerationsBlocks.VOLCANIC_STONE.get()).slab(GenerationsBlocks.VOLCANIC_STONE_SLAB.get()).stairs(GenerationsBlocks.VOLCANIC_STONE_STAIRS.get()).wall(GenerationsBlocks.VOLCANIC_STONE_WALL.get()).pressurePlate(GenerationsBlocks.VOLCANIC_STONE_PRESSURE_PLATE.get()).button(GenerationsBlocks.VOLCANIC_STONE_BUTTON.get()).recipeGroupPrefix("volcanic_stone").recipeUnlockedBy("has_volcanic_stone").getFamily();
	public static final BlockFamily SMOOTH_VOLCANIC_STONE = familyBuilder(GenerationsBlocks.SMOOTH_VOLCANIC_STONE.get()).slab(GenerationsBlocks.SMOOTH_VOLCANIC_STONE_SLAB.get()).recipeGroupPrefix("smooth_volcanic_stone").recipeUnlockedBy("has_smooth_volcanic_stone").getFamily();
	public static final BlockFamily VOLCANIC_STONE_BRICKS = familyBuilder(GenerationsBlocks.VOLCANIC_STONE_BRICKS.get()).cracked(GenerationsBlocks.CRACKED_VOLCANIC_STONE_BRICKS.get()).slab(GenerationsBlocks.VOLCANIC_STONE_BRICK_SLAB.get()).stairs(GenerationsBlocks.VOLCANIC_STONE_BRICK_STAIRS.get()).wall(GenerationsBlocks.VOLCANIC_STONE_BRICK_WALL.get()).chiseled(GenerationsBlocks.CHISELED_VOLCANIC_STONE_BRICKS.get()).recipeGroupPrefix("volcanic_stone_bricks").recipeUnlockedBy("has_volcanic_stone_bricks").getFamily();
	public static final BlockFamily SUBWAY_WALL = familyBuilder(GenerationsBlocks.SUBWAY_WALL.get()).slab(GenerationsBlocks.SUBWAY_WALL_SLAB.get()).stairs(GenerationsBlocks.SUBWAY_WALL_STAIRS.get()).recipeGroupPrefix("subway_wall").recipeUnlockedBy("has_subway_wall").getFamily();
	public static final BlockFamily SUBWAY_WALL_2 = familyBuilder(GenerationsBlocks.SUBWAY_WALL_2.get()).slab(GenerationsBlocks.SUBWAY_WALL_2_SLAB.get()).stairs(GenerationsBlocks.SUBWAY_WALL_2_STAIRS.get()).recipeGroupPrefix("subway_wall_2").recipeUnlockedBy("has_subway_wall_2").getFamily();
	public static final BlockFamily ULTRA_SANDSTONE = familyBuilder(GenerationsBlocks.ULTRA_SANDSTONE.get()).slab(GenerationsBlocks.ULTRA_SANDSTONE_SLAB.get()).stairs(GenerationsBlocks.ULTRA_SANDSTONE_STAIRS.get()).wall(GenerationsBlocks.ULTRA_SANDSTONE_WALL.get()).recipeGroupPrefix("ultra_sandstone").recipeUnlockedBy("has_ultra_sandstone").getFamily();
	public static final BlockFamily ULTRA_SMOOTH_SANDSTONE = familyBuilder(GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE.get()).slab(GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_SLAB.get()).stairs(GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_STAIRS.get()).wall(GenerationsBlocks.ULTRA_SMOOTH_SANDSTONE_WALL.get()).recipeGroupPrefix("ultra_smooth_sandstone").recipeUnlockedBy("has_ultra_smooth_sandstone").dontGenerateModel().getFamily();
	public static final BlockFamily GOLDEN_TEMPLE_SANDSTONE = familyBuilder(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE.get()).slab(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_SLAB.get()).stairs(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_STAIRS.get()).wall(GenerationsBlocks.GOLDEN_TEMPLE_SANDSTONE_WALL.get()).chiseled(GenerationsBlocks.GOLDEN_TEMPLE_CHISELED_SANDSTONE.get()).cut(GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE.get()).recipeGroupPrefix("golden_temple_sandstone").recipeUnlockedBy("has_golden_temple_sandstone").dontGenerateModel().getFamily();
	public static final BlockFamily GOLDEN_TEMPLE_CUT_SANDSTONE = familyBuilder(GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE.get()).slab(GenerationsBlocks.GOLDEN_TEMPLE_CUT_SANDSTONE_SLAB.get()).recipeGroupPrefix("golden_temple_cut_sandstone").recipeUnlockedBy("has_golden_temple_cut_sandstone").dontGenerateRecipe().dontGenerateModel().getFamily();
	public static final BlockFamily GOLDEN_TEMPLE_SMOOTH_SANDSTONE = familyBuilder(GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE.get()).slab(GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_SLAB.get()).stairs(GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_STAIRS.get()).wall(GenerationsBlocks.GOLDEN_TEMPLE_SMOOTH_SANDSTONE_WALL.get()).recipeGroupPrefix("golden_temple_smooth_sandstone").recipeUnlockedBy("has_golden_temple_smooth_sandstone").dontGenerateModel().getFamily();

	private static BlockFamily.Builder familyBuilder(Block baseBlock) {
		BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
		BlockFamily blockFamily = MAP.put(baseBlock, builder.getFamily());
		if (blockFamily != null)
			throw new IllegalStateException("Duplicate family definition for " + ForgeRegistries.BLOCKS.getKey(baseBlock));
		return builder;
	}

	public static @NotNull Stream<BlockFamily> getAllFamilies() {
		return MAP.values().stream();
	}
}