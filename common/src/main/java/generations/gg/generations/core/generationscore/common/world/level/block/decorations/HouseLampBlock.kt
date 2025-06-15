package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.world.level.block.entity.BlockEntityType

class HouseLampBlock(properties: Properties) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties = properties,
        model = GenerationsBlockEntityModels.HOUSE_LAMP
    ) {
    override val blockEntityType: BlockEntityType<GenericModelProvidingBlockEntity>
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<HouseLampBlock> = CODEC

    companion object {
        val CODEC: MapCodec<HouseLampBlock> = simpleCodec(::HouseLampBlock)
    }
}