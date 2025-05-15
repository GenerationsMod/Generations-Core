package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class AbundantShrineBlockEntity(pos: BlockPos, state: BlockState) :
    ShrineBlockEntity(GenerationsBlockEntities.ABUNDANT_SHRINE.get(), pos, state)
