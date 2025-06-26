package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class GenericModelProvidingBlockEntity(pos: BlockPos, state: BlockState) : ModelProvidingBlockEntity(GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, pos, state)
