package generations.gg.generations.core.generationscore.common.world.level.block.entities

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.level.block.state.BlockState

class GenerationsSignBlockEntity(pos: BlockPos, state: BlockState) : SignBlockEntity(pos, state) {
    override fun getType(): BlockEntityType<*> = GenerationsBlockEntities.SIGN_BLOCK_ENTITIES.value()
}
