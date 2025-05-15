package generations.gg.generations.core.generationscore.common.world.level.block.entities

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.HangingSignBlockEntity
import net.minecraft.world.level.block.state.BlockState

class GenerationsHangingSignBlockEntity(pos: BlockPos, state: BlockState) : HangingSignBlockEntity(pos, state) {
    override fun getType(): BlockEntityType<*> = GenerationsBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get()
}