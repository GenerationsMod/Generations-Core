package generations.gg.generations.core.generationscore.common.world.level.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState

interface IPortalBlock {
    fun isPortalFrame(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean
}
