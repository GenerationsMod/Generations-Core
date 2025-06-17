package generations.gg.generations.core.generationscore.common.compat

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.FlabebeFlowerBlock
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import net.minecraft.world.level.block.DispenserBlock

object VanillaCompat {
    /**
     * Registers blocks as flammable or strippable on setup
     */
    fun setup() {
        val implementation = GenerationsCore.implementation

        GenerationsWood.WOOD_BLOCKS.all().forEach { wood -> implementation.registerFlammable(wood, 5, 5) }

        implementation.registerStrippable(
            GenerationsWood.ULTRA_DARK_LOG,
            GenerationsWood.STRIPPED_ULTRA_DARK_LOG
        )
        implementation.registerStrippable(
            GenerationsWood.ULTRA_DARK_WOOD,
            GenerationsWood.STRIPPED_ULTRA_DARK_WOOD
        )
        implementation.registerStrippable(
            GenerationsWood.ULTRA_JUNGLE_LOG,
            GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG
        )
        implementation.registerStrippable(
            GenerationsWood.ULTRA_JUNGLE_WOOD,
            GenerationsWood.STRIPPED_ULTRA_JUNGLE_WOOD
        )
        implementation.registerStrippable(GenerationsWood.GHOST_LOG, GenerationsWood.STRIPPED_GHOST_LOG)
        implementation.registerStrippable(GenerationsWood.GHOST_WOOD, GenerationsWood.STRIPPED_GHOST_WOOD)

        GenerationsBlocks.BLOCKS.all().forEach { block ->
            if (block is FlabebeFlowerBlock) implementation.registerCompostables(block, 0.65f)
        }
    }

    fun dispenserBehavior() {
        DispenserBlock.registerBehavior(
            GenerationsItems.GHOST_BOAT_ITEM.value(),
            BoatDispenseItemBehavior(GenerationsBoatEntity.Type.GHOST)
        )
        DispenserBlock.registerBehavior(
            GenerationsItems.GHOST_CHEST_BOAT_ITEM.value(),
            BoatDispenseItemBehavior(GenerationsBoatEntity.Type.GHOST, true)
        )
        DispenserBlock.registerBehavior(
            GenerationsItems.ULTRA_DARK_BOAT_ITEM.value(),
            BoatDispenseItemBehavior(GenerationsBoatEntity.Type.ULTRA_DARK)
        )
        DispenserBlock.registerBehavior(
            GenerationsItems.ULTRA_DARK_CHEST_BOAT_ITEM.value(),
            BoatDispenseItemBehavior(GenerationsBoatEntity.Type.ULTRA_DARK, true)
        )
        DispenserBlock.registerBehavior(
            GenerationsItems.ULTRA_JUNGLE_BOAT_ITEM.value(),
            BoatDispenseItemBehavior(GenerationsBoatEntity.Type.ULTRA_JUNGLE)
        )
        DispenserBlock.registerBehavior(
            GenerationsItems.ULTRA_JUNGLE_CHEST_BOAT_ITEM.value(),
            BoatDispenseItemBehavior(GenerationsBoatEntity.Type.ULTRA_JUNGLE, true)
        )
    }
}
