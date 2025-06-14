package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class BallDisplayBlockEntity(pos: BlockPos, state: BlockState) : ModelProvidingBlockEntity(GenerationsBlockEntities.BALL_DISPLAY, pos, state), VariantProvider {
    override fun getVariant(): String {
        return blockState.block.instanceOrNull<BallDisplayBlock>()?.variant ?: "empty"
    }
}
