package generations.gg.generations.core.generationscore.common.world.item.armor

import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

interface ArmorTickEffect : ArmorEffect {
    fun onArmorTick(itemStack: ItemStack, world: Level, player: Player, generationsArmorItem: GenerationsArmorItem)
    fun inventoryTick(
        itemStack: ItemStack,
        world: Level,
        entity: Entity,
        slotId: Int,
        isSelected: Boolean,
        generationsArmorItem: GenerationsArmorItem
    )

    companion object {
        fun isWearingFullSet(player: Player, material: Holder<ArmorMaterial>): Boolean {
            for (equipmentSlot in EquipmentSlot.entries) {
                if (equipmentSlot.type == EquipmentSlot.Type.HAND) continue

                val equippedItemStack = player.getItemBySlot(equipmentSlot)
                if (equippedItemStack.item !is ArmorItem) return false
                if ((equippedItemStack.item as ArmorItem).material.`is` { resourceKey: ResourceKey<ArmorMaterial?>? ->
                        material.`is`(
                            resourceKey
                        )
                    }) return true
            }

            return true
            //        var event = new ItemEvents.EquipFullArmorSet(player, material); TODO: EIther find or PR equivalent event to arch
//        MinecraftForge.EVENT_BUS.post(event);
//        return event.isCanceled();
        }
    }
}
