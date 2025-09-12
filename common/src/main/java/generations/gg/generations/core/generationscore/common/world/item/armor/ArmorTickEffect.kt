package generations.gg.generations.core.generationscore.common.world.item.armor

import net.minecraft.core.Holder
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

interface ArmorTickEffect : ArmorEffect {
    fun inventoryTick(
        itemStack: ItemStack,
        world: Level,
        entity: LivingEntity,
        slotId: Int,
        isSelected: Boolean,
        generationsArmorItem: GenerationsArmorItem
    )

    companion object {
        fun isWearingFullSet(player: LivingEntity, material: Holder<ArmorMaterial>): Boolean {
            val requiredSlots = listOf(
                EquipmentSlot.HEAD,
                EquipmentSlot.CHEST,
                EquipmentSlot.LEGS,
                EquipmentSlot.FEET
            )

            for (slot in requiredSlots) {
                val stack = player.getItemBySlot(slot)
                val item = stack.item as? ArmorItem ?: return false
                if (!item.material.`is`(material)) return false
            }

            return true
        }
    }
}
