package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class GenericShrineBlockEntity(pos: BlockPos, state: BlockState) : ShrineBlockEntity(GenerationsBlockEntities.GENERIC_SHRINE.get(), pos, state)
