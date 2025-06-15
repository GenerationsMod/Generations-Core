package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.resources.ResourceLocation

abstract class ShrineBlock<T : ShrineBlockEntity>(
    properties: Properties,
    model: ResourceLocation? = null,
    width: Int = 0,
    height: Int = 0,
    length: Int = 0
) : GenericRotatableModelBlock<T>(properties = properties, model = model, width = width, height = height, length = length) {

    init {
        reassignStateDefinition()
        this.registerDefaultState(createDefaultState())
    }

    open val isActivatable: Boolean
        get() = false
}
