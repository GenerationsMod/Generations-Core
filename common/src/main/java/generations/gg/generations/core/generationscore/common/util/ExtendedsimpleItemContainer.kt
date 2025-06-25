package generations.gg.generations.core.generationscore.common.util

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage
import generations.gg.generations.core.generationscore.common.world.item.components.SimpleContainer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import java.util.function.Consumer

open class ExtendedsimpleItemContainer(size: Int) : SimpleContainer(size) {
    fun updateStack(index: Int, consumer: (ItemStack) -> Unit) {
        val stack = this.getItem(index)

        consumer.invoke(stack)

        setItem(index, stack)
    }
}
