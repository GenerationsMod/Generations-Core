package generations.gg.generations.core.generationscore.common.util

import earth.terrarium.common_storage_lib.item.impl.SimpleItemSlot
import earth.terrarium.common_storage_lib.resources.item.ItemResource
import net.minecraft.world.item.ItemStack

fun SimpleItemSlot.shrink(amount: Int) {
    this.amount -= amount
}

fun SimpleItemSlot.grow(amount: Int) {
    this.amount += amount
}

fun ItemStack.asResource(): ItemResource = ItemResource.of(this)