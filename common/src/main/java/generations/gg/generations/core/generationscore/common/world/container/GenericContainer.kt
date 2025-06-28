package generations.gg.generations.core.generationscore.common.world.container

import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.component.DataComponents
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.Container
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemContainerContents

object GenericContainer {
    fun openScreen(storage: ItemStack, width: Int, height: Int, title: Component, player: Player, lock: Int = -1, canTake: (Int, ItemStack) -> Boolean = { _, _ -> true }, canPlace: (Int, ItemStack) -> Boolean = { _, _ -> true }) {
        openScreen(object : SimpleContainer(width * height) {
            val stack = storage

            init {
                storage.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(items)
            }

            override fun canTakeItem(target: Container, slot: Int, stack: ItemStack): Boolean {
                return canTake.invoke(slot, stack)
            }

            override fun canPlaceItem(slot: Int, stack: ItemStack): Boolean {
                return canPlace.invoke(slot, stack)
            }

            override fun setChanged() {
                super.setChanged()
                storage.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(items))
            }
        }, width, height, title, player, lock, {})
    }

    fun openScreen(storage: Container, width: Int, height: Int, title: Component, player: Player, lock: Int = -1, onClose: () -> Unit = {}) {

        if (!player.isLocalPlayer) GenerationsCore.implementation.openExtendedMenu(
            player as ServerPlayer,
            object : ExtendedMenuProvider {

                override fun createMenu(id: Int, inventory: Inventory, player: Player): AbstractContainerMenu {
                    return GenericChestContainer(id, inventory, storage, width, height, if(lock != -1) { slot -> slot == lock } else { _ -> false }, onClose)
                }

                override fun getDisplayName(): Component {
                    return title
                }

                override fun saveExtraData(buffer: FriendlyByteBuf) {
                    buffer.writeVarInt(width)
                    buffer.writeVarInt(height)
                }
            }
        )
    }
}
