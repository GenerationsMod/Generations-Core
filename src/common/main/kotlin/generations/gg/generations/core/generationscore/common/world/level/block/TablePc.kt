package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.DefaultPcBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.PcBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.PcBlock
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntityTicker

class TablePc(properties: Properties) : PcBlock<DefaultPcBlockEntity>(DefaultPcBlockEntity::class.java, properties, GenerationsBlockEntityModels.TABLE_PC, 0, 0, 0) {
    override val blockEntityType
        get() = GenerationsBlockEntities.PC

    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return CODEC
    }

    override fun getTicker(): BlockEntityTicker<PcBlockEntity> {
        return DefaultPcBlockEntity.TICKER
    }

    companion object {
        val CODEC: MapCodec<TablePc> = simpleCodec(::TablePc)
    }
}
