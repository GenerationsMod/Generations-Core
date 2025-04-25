package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.PokemonItem
import generations.gg.generations.core.generationscore.common.api.events.general.CameraEvents
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import generations.gg.generations.core.generationscore.common.world.sound.GenerationsSounds
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.world.ContainerHelper
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.EntityHitResult

class CameraItem(properties: Properties) : Item(properties) {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND && !player.cooldowns.isOnCooldown(
                this
            ) && player.inventory.hasAnyMatching { stack: ItemStack ->
                stack.`is`(
                    GenerationsItems.FILM.get()
                )
            }
        ) {
            val pokemon = GenerationsUtils.raycast(player, 30.0, 1.0f) { it is PokemonEntity }.instanceOrNull<EntityHitResult>()?.entity.instanceOrNull<PokemonEntity>()?.pokemon

            if (pokemon != null) {
                val photo: ItemStack = PokemonItem.from(pokemon)

                val changed =
                    CameraEvents.MODIFY_PHOTO.invoker().modify(player as ServerPlayer, level as ServerLevel, photo)

                if (player.getInventory().freeSlot > -1) {
                    level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        GenerationsSounds.CAMERA_SHUTTER.get(),
                        SoundSource.MASTER,
                        1.0f,
                        1.0f
                    )
                    ContainerHelper.clearOrCountMatchingItems(
                        player.getInventory(),
                        { stack: ItemStack -> stack.`is`(GenerationsItems.FILM.get()) }, 1, false
                    )
                    player.addItem(changed ?: photo)
                    player.getCooldowns().addCooldown(this, 5)
                }
            }
        }

        return super.use(level, player, usedHand)
    }
}
