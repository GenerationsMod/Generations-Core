package generations.gg.generations.core.generationscore.common.world.item.armor.effects

import generations.gg.generations.core.generationscore.common.world.item.armor.ArmorTickEffect
import generations.gg.generations.core.generationscore.common.world.item.armor.GenerationsArmorItem
import net.minecraft.core.Holder
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

data class PotionArmorEffect(val potionEffect: Holder<MobEffect>, val amplifier: Int) : ArmorTickEffect {
    override fun inventoryTick(
        itemStack: ItemStack,
        world: Level,
        entity: LivingEntity,
        slotId: Int,
        isSelected: Boolean,
        generationsArmorItem: GenerationsArmorItem
    ) {
        if (world.isClientSide()) return
        if (!ArmorTickEffect.isWearingFullSet(entity, generationsArmorItem.material)) return
        val currentEffect = entity.getEffect(potionEffect)
        if (currentEffect != null && currentEffect.amplifier > amplifier) return
        if (currentEffect != null && currentEffect.amplifier == amplifier && currentEffect.getDuration() >= 20) return
        entity.addEffect(MobEffectInstance(potionEffect, 20, amplifier))
    }
}
