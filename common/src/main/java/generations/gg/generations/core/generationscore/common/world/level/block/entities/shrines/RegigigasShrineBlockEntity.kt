package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import generations.gg.generations.core.generationscore.common.GenerationsStorage
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class RegigigasShrineBlockEntity(pos: BlockPos, state: BlockState) : InteractShrineBlockEntity(GenerationsBlockEntities.REGIGIGAS_SHRINE, pos, state) {
    val container: SimpleItemStorage = SimpleItemStorage(this, GenerationsStorage.REGI_ORBS, 5)
        .filter(0) { it.isOf(GenerationsItems.REGICE_ORB.value()) }
        .filter(1) { it.isOf(GenerationsItems.REGIROCK_ORB.value()) }
        .filter(2) { it.isOf(GenerationsItems.REGISTEEL_ORB.value()) }
        .filter(3) { it.isOf(GenerationsItems.REGIDRAGO_ORB.value()) }
        .filter(4) { it.isOf(GenerationsItems.REGIELEKI_ORB.value()) }
}
