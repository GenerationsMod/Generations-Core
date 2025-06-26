package generations.gg.generations.core.generationscore.common.world.item.armor.effects

import com.cobblemon.mod.common.util.enchantmentRegistry
import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorTickEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.ItemEnchantments
import net.minecraft.world.level.Level
import kotlin.jvm.optionals.getOrNull

data class EnchantmentArmorEffect(val enchantment: ResourceKey<Enchantment>, val level: Int) : ArmorTickEffect {
    override fun inventoryTick(
        itemStack: ItemStack,
        world: Level,
        entity: LivingEntity,
        slotId: Int,
        isSelected: Boolean,
        generationsArmorItem: GenerationsArmorItem
    ) {
        if (world.isClientSide()) return
        val holder = world.enchantmentRegistry.getHolder(enchantment).getOrNull() ?: return
        if (!ArmorTickEffect.isWearingFullSet(entity, generationsArmorItem.material)) {
            itemStack.removeEnchantment(holder, level)
            return
        }
        val enchantments = itemStack.enchantments.takeUnless { it.isEmpty }?.mutable() ?: return
        if (enchantments.getLevel(holder) >= level) return
        enchantments[holder] = level
        itemStack.set(DataComponents.ENCHANTMENTS, enchantments.toImmutable())
    }
}

private fun ItemStack.removeEnchantment(enchantment: Holder<Enchantment>, level: Int) {
    val enchantments = this.enchantments.takeUnless { it.isEmpty }?.mutable() ?: return

    if(enchantments.getLevel(enchantment) == level) enchantments.removeIf { it.`is`(enchantment::`is`) }

    this.set(DataComponents.ENCHANTMENTS, enchantments.toImmutable())
}

private fun ItemEnchantments.mutable(): ItemEnchantments.Mutable = ItemEnchantments.Mutable(this)
