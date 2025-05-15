package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.TintProvider
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.DyeableBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.state.BlockState
import org.joml.Vector3f

class DyedPcBlockEntity(blockPos: BlockPos, blockState: BlockState) :
    PcBlockEntity<DyedPcBlockEntity>(GenerationsBlockEntities.DYED_PC.get(), blockPos, blockState), TintProvider {
    val color: DyeColor
        get() = (blockState.block as DyeableBlock<*, *>).color

    override fun getTint(): Vector3f? {
        return DyedVariantBlockEntity.COLOR_MAP.getOrDefault(color, null)
    }

    companion object {
        val TICKER: BlockEntityTicker<DyedPcBlockEntity> =
            BlockEntityTicker<DyedPcBlockEntity> { world: Level, blockPos: BlockPos?, blockState: BlockState?, blockEntity: DyedPcBlockEntity ->
                if (!world.isClientSide) {
                    blockEntity.togglePCOn(blockEntity.getInRangeViewerCount(world, blockEntity.blockPos, 0.5) > 0)
                }
            }
    }
}
