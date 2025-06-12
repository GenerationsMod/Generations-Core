//package generations.gg.generations.core.generationscore.common.world.item.armor.effects
//
//import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorTickEffect
//import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
//import net.minecraft.nbt.CompoundTag
//import net.minecraft.world.entity.Entity
//import net.minecraft.world.entity.player.Player
//import net.minecraft.world.item.ItemStack
//import net.minecraft.world.level.Level
//
//class DoubleSpeedArmorEffect : ArmorTickEffect {
//    override fun onArmorTick(
//        itemStack: ItemStack,
//        world: Level,
//        player: Player,
//        generationsArmorItem: GenerationsArmorItem
//    ) {
////        if (world.isClientSide) return
////        if (ArmorTickEffect.isWearingFullSet(player, generationsArmorItem.material)) {
////            val armorEffectTagElement: CompoundTag = itemStack.getTagElement("ArmorEffect") ?: return
////            armorEffectTagElement.remove("DoubleSpeed")
////            if (!armorEffectTagElement.isEmpty) return
////            itemStack.removeTagKey("ArmorEffect")
////            return
////        }
////        val armorEffectTagElement: CompoundTag = itemStack.getOrCreateTagElement("ArmorEffect")
////        if (armorEffectTagElement.getBoolean("DoubleSpeed")) return
////        armorEffectTagElement.putBoolean("DoubleSpeed", true)
//    }
//
//    override fun inventoryTick(
//        itemStack: ItemStack,
//        world: Level,
//        entity: Entity,
//        slotId: Int,
//        isSelected: Boolean,
//        generationsArmorItem: GenerationsArmorItem
//    ) {
////        if (world.isClientSide) return
////        for (armorSlot in entity.getArmorSlots()) if (itemStack == armorSlot) return
////        val armorEffectTagElement: CompoundTag = itemStack.getTagElement("ArmorEffect") ?: return
////        armorEffectTagElement.remove("DoubleSpeed")
////        if (!armorEffectTagElement.isEmpty) return
////        itemStack.removeTagKey("ArmorEffect")
//    }
//}
