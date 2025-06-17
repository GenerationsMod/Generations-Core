package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

open class ShrineBlockEntity(supplier: Holder<BlockEntityType<*>>, pos: BlockPos, state: BlockState) : ModelProvidingBlockEntity(supplier, pos, state), VariantProvider
