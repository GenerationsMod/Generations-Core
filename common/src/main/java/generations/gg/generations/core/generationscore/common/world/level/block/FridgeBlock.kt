package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.world.level.block.BaseEntityBlock

class FridgeBlock(properties: Properties) : GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties,
        GenerationsBlockEntities.GENERIC_MODEL_PROVIDING,
        model = GenerationsBlockEntityModels.FRIDGE,
        width = 0,
        height = 1,
        length = 0
    ) {

    override fun codec(): MapCodec<FridgeBlock> = CODEC

    companion object {
        val CODEC = simpleCodec(::FridgeBlock)
    }
}
