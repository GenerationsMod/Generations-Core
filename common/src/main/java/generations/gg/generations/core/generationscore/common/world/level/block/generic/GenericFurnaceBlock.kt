package generations.gg.generations.core.generationscore.common.world.level.block.generic

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericFurnaceBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FurnaceBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class GenericFurnaceBlock : FurnaceBlock(Properties.ofFullCopy(Blocks.FURNACE)) {
    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity = GenericFurnaceBlockEntity(pos, state)

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? = createFurnaceTicker(level, blockEntityType, GenerationsBlockEntities.GENERIC_FURNACE.get())
}
