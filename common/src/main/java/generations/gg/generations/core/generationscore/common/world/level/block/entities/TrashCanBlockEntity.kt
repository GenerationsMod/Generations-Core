package generations.gg.generations.core.generationscore.common.world.level.block.entities

import earth.terrarium.common_storage_lib.item.impl.noops.NoOpsItemContainer
import earth.terrarium.common_storage_lib.item.util.ItemProvider
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import earth.terrarium.common_storage_lib.storage.base.CommonStorage
import generations.gg.generations.core.generationscore.common.world.data.TrashItemStorage
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.block.state.BlockState

class TrashCanBlockEntity(pos: BlockPos, state: BlockState) : ModelProvidingBlockEntity(GenerationsBlockEntities.TRASH_CAN.get(), pos, state), ItemProvider.BlockEntity {
    override fun getItems(dir: Direction?): CommonStorage<ItemResource> = if(dir == Direction.UP) { TrashItemStorage } else NoOpsItemContainer.NO_OPS
}
