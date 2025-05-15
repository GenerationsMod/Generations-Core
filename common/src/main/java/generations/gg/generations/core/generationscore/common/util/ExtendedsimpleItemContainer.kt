package generations.gg.generations.core.generationscore.common.util

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import generations.gg.generations.core.generationscore.common.GenerationsStorage.ITEM_CONTENTS
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import java.util.function.Consumer

open class ExtendedsimpleItemContainer(entity: BlockEntity, size: Int) : SimpleItemStorage(entity, ITEM_CONTENTS, size) {
    fun updateStack(index: Int, consumer: (ItemStack) -> Unit) {
        val slot = this[index]

        val stack = slot.toItemStack()
        consumer.invoke(stack)

        slot.set(stack)
    }
}
