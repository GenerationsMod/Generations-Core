package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.CelestialAltarBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class CelestialAltarBlockEntity(pos: BlockPos, state: BlockState) : ShrineBlockEntity(GenerationsBlockEntities.CELESTIAL_ALTAR, pos, state) {
    override fun getVariant(): String = if (blockState.getValue(CelestialAltarBlock.IS_SUN)) "sun" else "moon"
}
