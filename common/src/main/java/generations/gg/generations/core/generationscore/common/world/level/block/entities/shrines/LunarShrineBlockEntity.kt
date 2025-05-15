package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.LunarShrineBlock
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.state.BlockState

class LunarShrineBlockEntity(pos: BlockPos, state: BlockState) : ShrineBlockEntity(GenerationsBlockEntities.LUNAR_SHRINE.get(), pos, state) {
    override fun getModel(): ResourceLocation = if (blockState.getValue(LunarShrineBlock.IS_LIGHT)) GenerationsBlockEntityModels.LIGHT_MODEL else GenerationsBlockEntityModels.DARK_MODEL
}
