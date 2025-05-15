package generations.gg.generations.core.generationscore.common.world.level.block.decorations

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock

class HouseLampBlock(properties: Properties) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties = properties,
        blockEntityFunction = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING,
        model = GenerationsBlockEntityModels.HOUSE_LAMP
    ) {

    override fun codec(): MapCodec<HouseLampBlock> = CODEC

    companion object {
        val CODEC: MapCodec<HouseLampBlock> = simpleCodec(::HouseLampBlock)
    }
}