package generations.gg.generations.core.generationscore.compat;

import com.google.common.collect.ImmutableMap;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class VanillaCompat {

	/**
	 * Registers blocks as flammable or strippable on setup
	 */
	public static void setup() {
		GenerationsWood.WOOD_BLOCKS.forEach(wood -> registerFlammable(wood.get(), 5, 5));

		registerStrippable(GenerationsWood.ULTRA_DARK_LOG.get(), GenerationsWood.STRIPPED_ULTRA_DARK_LOG.get());
		registerStrippable(GenerationsWood.ULTRA_DARK_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_DARK_WOOD.get());
		registerStrippable(GenerationsWood.ULTRA_JUNGLE_LOG.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG.get());
		registerStrippable(GenerationsWood.ULTRA_JUNGLE_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD.get());
		registerStrippable(GenerationsWood.GHOST_LOG.get(), GenerationsWood.STRIPPED_GHOST_LOG.get());
		registerStrippable(GenerationsWood.GHOST_WOOD.get(), GenerationsWood.STRIPPED_GHOST_WOOD.get());
	}

	/**
	 * Register a block as Strippable.
	 * @param log The log block of wood
	 * @param stripped The stripped log block of wood
	 */
	private static void registerStrippable(@NotNull Block log, @NotNull Block stripped) {
		if (!log.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS))
			throw new IllegalArgumentException("Input block is missing required 'AXIS' property!");
		if (!stripped.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS))
			throw new IllegalArgumentException("Result block is missing required 'AXIS' property!");
		if (AxeItem.STRIPPABLES instanceof ImmutableMap)
			AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);

		AxeItem.STRIPPABLES.put(log, stripped);
	}

	/**
	 * Register a block as flammable.
	 * @param blockIn The block to register
	 * @param encouragement How much more likely the block is to catch fire compared to vanilla
	 * @param flammability How much more likely the block is to spread fire compared to vanilla
	 */
	private static void registerFlammable(Block blockIn, int encouragement, int flammability) {
		((FireBlock) Blocks.FIRE).setFlammable(blockIn, encouragement, flammability);
	}
}
