package generations.gg.generations.core.generationscore.compat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

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
	@ExpectPlatform
	private static void registerStrippable(@NotNull Block log, @NotNull Block stripped) {
		throw new RuntimeException();
	}

	/**
	 * Register a block as flammable.
	 * @param blockIn The block to register
	 * @param encouragement How much more likely the block is to catch fire compared to vanilla
	 * @param flammability How much more likely the block is to spread fire compared to vanilla
	 */
	@ExpectPlatform
	private static void registerFlammable(@NotNull Block blockIn, int encouragement, int flammability) {
		throw new RuntimeException();
	}
}
