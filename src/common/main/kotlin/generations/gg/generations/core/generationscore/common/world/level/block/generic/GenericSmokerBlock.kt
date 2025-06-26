package generations.gg.generations.core.generationscore.common.world.level.block.generic

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.asValue
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericSmokerBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SmokerBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class GenericSmokerBlock(properties: Properties = Properties.ofFullCopy(Blocks.SMOKER)) : SmokerBlock(properties) {
    override fun codec(): MapCodec<SmokerBlock> = CODEC

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return GenericSmokerBlockEntity(pos, state)
    }

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        blockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return createFurnaceTicker(level, blockEntityType, GenerationsBlockEntities.GENERIC_SMOKER.asValue())
    }

    companion object {
        val CODEC = simpleCodec<SmokerBlock>(::GenericSmokerBlock)
    }
}
