package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.entity.block.MagmaCrystalEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class MagmaCrystal(properties: Properties) : ItemWithLangTooltipImpl(properties) {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val itemStack = player.getItemInHand(usedHand)
        if (!level.isClientSide && !GenerationsCore.CONFIG.caught.capped(
                player as ServerPlayer,
                LegendKeys.HEATRAN
            )
        ) return InteractionResultHolder.fail(itemStack)

        level.playSound(
            null,
            player.x,
            player.y,
            player.z,
            SoundEvents.ENDER_PEARL_THROW,
            SoundSource.NEUTRAL,
            0.5f,
            0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
        )

        if (!level.isClientSide) {
            val magmaCrystal = MagmaCrystalEntity(level, player)
            magmaCrystal.item = itemStack
            magmaCrystal.shootFromRotation(player, player.xRot, player.yRot, 0.0f, 1.5f, 1.0f)
            level.addFreshEntity(magmaCrystal)
        }
        player.cooldowns.addCooldown(this, 20)

        player.awardStat(Stats.ITEM_USED[this])
        if (!player.abilities.instabuild) {
            itemStack.shrink(1)
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide())
    }
}
