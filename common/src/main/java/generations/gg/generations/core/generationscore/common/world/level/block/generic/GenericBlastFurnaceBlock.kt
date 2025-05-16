package generations.gg.generations.core.generationscore.common.world.level.block.generic

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericBlastFurnaceBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BlastFurnaceBlock
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class GenericBlastFurnaceBlock : BlastFurnaceBlock(Properties.ofFullCopy(Blocks.BLAST_FURNACE)) {
    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return GenericBlastFurnaceBlockEntity(pos, state)
    }

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return createFurnaceTicker(level, blockEntityType, GenerationsBlockEntities.GENERIC_BLAST_FURNACE.get())
    }
}
