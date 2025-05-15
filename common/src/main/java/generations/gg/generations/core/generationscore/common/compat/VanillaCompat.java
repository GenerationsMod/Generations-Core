package generations.gg.generations.core.generationscore.common.compat;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.FlabebeFlowerBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood;
import net.minecraft.world.level.block.DispenserBlock;

public class VanillaCompat {

	/**
	 * Registers blocks as flammable or strippable on setup
	 */
	public static void setup() {
		var implementation = GenerationsCore.implementation;

		GenerationsWood.WOOD_BLOCKS.forEach(wood -> implementation.registerFlammable(wood.get(), 5, 5));

		implementation.registerStrippable(GenerationsWood.ULTRA_DARK_LOG.get(), GenerationsWood.STRIPPED_ULTRA_DARK_LOG.get());
		implementation.registerStrippable(GenerationsWood.ULTRA_DARK_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_DARK_WOOD.get());
		implementation.registerStrippable(GenerationsWood.ULTRA_JUNGLE_LOG.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG.get());
		implementation.registerStrippable(GenerationsWood.ULTRA_JUNGLE_WOOD.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD.get());
		implementation.registerStrippable(GenerationsWood.GHOST_LOG.get(), GenerationsWood.STRIPPED_GHOST_LOG.get());
		implementation.registerStrippable(GenerationsWood.GHOST_WOOD.get(), GenerationsWood.STRIPPED_GHOST_WOOD.get());

		GenerationsBlocks.BLOCKS.forEach(block -> {
			if (block.get() instanceof FlabebeFlowerBlock)
				implementation.registerCompostables(block.get(), 0.65F);
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
}
