package generations.gg.generations.core.generationscore.common.world.item.armor.effects

import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorTickEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class RepairArmorEffect : ArmorTickEffect {
    override fun onArmorTick(
        itemStack: ItemStack,
        world: Level,
        player: Player,
        generationsArmorItem: GenerationsArmorItem
    ) {
        if (world.isClientSide) return
        if (!itemStack.isDamaged) return
        itemStack.damageValue = 0
    }

    override fun inventoryTick(
        itemStack: ItemStack,
        world: Level,
        entity: Entity,
        slotId: Int,
        isSelected: Boolean,
        generationsArmorItem: GenerationsArmorItem
    ) {
    }
}
