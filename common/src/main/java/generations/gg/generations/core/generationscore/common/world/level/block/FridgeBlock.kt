package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock

class FridgeBlock(properties: Properties) : GenericRotatableModelBlock(
        properties,
        model = GenerationsBlockEntityModels.FRIDGE,
        width = 0,
        height = 1,
        length = 0
    ) {
    override val blockEntityType
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun codec(): MapCodec<FridgeBlock> = CODEC

    companion object {
        val CODEC = simpleCodec(::FridgeBlock)
    }
}
