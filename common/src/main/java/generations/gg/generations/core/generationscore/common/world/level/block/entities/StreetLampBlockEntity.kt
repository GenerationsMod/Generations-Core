package generations.gg.generations.core.generationscore.common.world.level.block.entities

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState
import org.joml.Vector3f

class StreetLampBlockEntity(arg2: BlockPos, arg3: BlockState) : DyedVariantBlockEntity<StreetLampBlockEntity>(GenerationsBlockEntities.STREET_LAMP.get(), arg2, arg3) {
    override fun getTint(): Vector3f? {
        return COLOR_MAP[color]
    }
}
