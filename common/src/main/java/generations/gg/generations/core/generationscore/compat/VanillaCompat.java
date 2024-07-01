package generations.gg.generations.core.generationscore.compat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import generations.gg.generations.core.generationscore.world.entity.GenerationsBoatEntity;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.FlabebeFlowerBlock;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
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

		GenerationsBlocks.BLOCKS.forEach(block -> {
			if (block.get() instanceof FlabebeFlowerBlock)
				registerCompostables(block.get(), 0.65F);
		});
	}

	public static void dispenserBehavior() {
		DispenserBlock.registerBehavior(GenerationsItems.GHOST_BOAT_ITEM.get(), new BoatDispenseItemBehavior(GenerationsBoatEntity.Type.GHOST));
		DispenserBlock.registerBehavior(GenerationsItems.GHOST_CHEST_BOAT_ITEM.get(), new BoatDispenseItemBehavior(GenerationsBoatEntity.Type.GHOST, true));
		DispenserBlock.registerBehavior(GenerationsItems.ULTRA_DARK_BOAT_ITEM.get(), new BoatDispenseItemBehavior(GenerationsBoatEntity.Type.ULTRA_DARK));
		DispenserBlock.registerBehavior(GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM.get(), new BoatDispenseItemBehavior(GenerationsBoatEntity.Type.ULTRA_DARK, true));
		DispenserBlock.registerBehavior(GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.get(), new BoatDispenseItemBehavior(GenerationsBoatEntity.Type.ULTRA_JUNGLE));
		DispenserBlock.registerBehavior(GenerationsItems.ULTRA_JUNGLE_CHEST_BOAT_ITEM.get(), new BoatDispenseItemBehavior(GenerationsBoatEntity.Type.ULTRA_JUNGLE, true));
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


	/**
	 * Register a block as compostable.
	 * @param block The block to register
	 * @param chance The chance of the block to compost
	 */
	@ExpectPlatform
	private static void registerCompostables(@NotNull Block block, float chance) {
		throw new RuntimeException();
	}
}
