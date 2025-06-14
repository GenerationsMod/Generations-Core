package generations.gg.generations.core.generationscore.common.world.level.block.entities

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.state.BlockState

class DefaultPcBlockEntity(blockPos: BlockPos, blockState: BlockState) : PcBlockEntity<DefaultPcBlockEntity>(GenerationsBlockEntities.PC, blockPos, blockState) {
    companion object {
        val TICKER: BlockEntityTicker<DefaultPcBlockEntity> =
            BlockEntityTicker<DefaultPcBlockEntity> { world, _, _, blockEntity ->
                if (!world.isClientSide) {
                    blockEntity.togglePCOn(blockEntity.getInRangeViewerCount(world, blockEntity.blockPos, 0.5) > 0)
                }
            }
    }
}
