package generations.gg.generations.core.generationscore.common.world.level.block.entities

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class BreederBlockEntity(pos: BlockPos, state: BlockState) : ModelProvidingBlockEntity(GenerationsBlockEntities.BREEDER.get(), pos, state)
