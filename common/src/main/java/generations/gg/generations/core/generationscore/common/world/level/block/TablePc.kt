package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.DefaultPcBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.PcBlock
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType

class TablePc(properties: Properties) : PcBlock<DefaultPcBlockEntity, TablePc>(DefaultPcBlockEntity::class.java, properties, GenerationsBlockEntityModels.TABLE_PC, 0, 0, 0) {
    override val blockEntityType: BlockEntityType<DefaultPcBlockEntity>
        get() = GenerationsBlockEntities.PC

    override fun codec(): MapCodec<out BaseEntityBlock?> {
        return CODEC
    }

    override fun getTicker(): BlockEntityTicker<DefaultPcBlockEntity> {
        return DefaultPcBlockEntity.TICKER
    }

    companion object {
        val CODEC: MapCodec<TablePc?> = simpleCodec { properties: Properties ->
            TablePc(
                properties
            )
        }
    }
}
