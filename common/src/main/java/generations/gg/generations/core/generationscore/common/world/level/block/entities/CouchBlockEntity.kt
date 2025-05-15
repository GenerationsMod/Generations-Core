package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.TintProvider
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState
import org.joml.Vector3f

class CouchBlockEntity(pos: BlockPos, state: BlockState) : DyedVariantBlockEntity<CouchBlockEntity>(GenerationsBlockEntities.COUCH.get(), pos, state), TintProvider {
    override fun getTint(): Vector3f? {
        return COLOR_MAP[color]
    }

    override fun getVariant(): String? {
        return null
    }
}
