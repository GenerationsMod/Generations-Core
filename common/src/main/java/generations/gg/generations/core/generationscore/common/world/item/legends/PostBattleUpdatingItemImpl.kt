package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

open class PostBattleUpdatingItemImpl(
    settings: Properties,
    private val speciesId: SpeciesKey,
    private val lang: String,
    private val predicate: (PlayerBattleActor, ItemStack, BattleData) -> Boolean
) :
    Item(settings), PostBattleUpdatingItem {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(usedHand)

        if (!level.isClientSide() && GenerationsCore.CONFIG!!.caught.capped(
                player as ServerPlayer,
                speciesId
            )
        ) {
            val damage = stack.damageValue

            if (damage >= stack.maxDamage) {
                stack.shrink(1)
                if (spawnPokemon()) PokemonUtil.spawn(
                    speciesId.createPokemon(spawnedLevel()),
                    level,
                    player.getOnPos(),
                    player.getYRot()
                )
                postSpawn(level, player, usedHand)
            } else {
                player.displayClientMessage(Component.translatable(lang, stack.maxDamage - damage), true)
            }

            return InteractionResultHolder.success(stack)
        }

        return InteractionResultHolder.pass(stack)
    }

    protected open fun postSpawn(level: Level, player: Player, usedHand: InteractionHand) {
    }

    protected fun spawnPokemon(): Boolean {
        return true
    }

    protected open fun spawnedLevel(): Int {
        return 1
    }

    override fun checkData(player: PlayerBattleActor, stack: ItemStack, pixelmonData: BattleData): Boolean {
        return predicate.invoke(player, stack, pixelmonData)
    }
}
