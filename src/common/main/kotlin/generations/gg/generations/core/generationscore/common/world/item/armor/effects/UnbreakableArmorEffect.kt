package generations.gg.generations.core.generationscore.common.world.item.armor.effects

import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorTickEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.Unbreakable
import net.minecraft.world.level.Level

class UnbreakableArmorEffect : ArmorTickEffect {
    override fun inventoryTick(
        itemStack: ItemStack,
        world: Level,
        entity: LivingEntity,
        slotId: Int,
        isSelected: Boolean,
        generationsArmorItem: GenerationsArmorItem
    ) {
        if (world.isClientSide()) return
        if (itemStack.has(DataComponents.UNBREAKABLE)) return
        itemStack.set(DataComponents.UNBREAKABLE, Unbreakable(false))
    }
}
