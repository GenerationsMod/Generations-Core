package generations.gg.generations.core.generationscore.common.world.level.block.shrines

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntityType

abstract class ShrineBlock<T : ShrineBlockEntity>(
    properties: Properties,
    blockEntityFunction: RegistrySupplier<BlockEntityType<T>>,
    model: ResourceLocation? = null,
    width: Int = 0,
    height: Int = 0,
    length: Int = 0
) : GenericRotatableModelBlock<T>(properties = properties, blockEntityFunction = blockEntityFunction, model = model, width = width, height = height, length = length) {

    init {
        reassignStateDefinition()
        this.registerDefaultState(createDefaultState())
    }

    open val isActivatable: Boolean
        get() = false
}
