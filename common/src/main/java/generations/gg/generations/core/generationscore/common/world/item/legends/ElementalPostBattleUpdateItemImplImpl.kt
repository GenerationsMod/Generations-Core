package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.api.types.ElementalType
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

open class ElementalPostBattleUpdateItemImplImpl(
    properties: Properties?,
    private val lang: String = DEFAULT_LANG_KEY,
    private val key: SpeciesKey,
    private val itemToGiveUponSpawn : Item? = null,
    types: List<ElementalType> = listOf()
) : ElementalPostBattleUpdateItemImpl(properties, types) {

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(usedHand)

        if (!level.isClientSide()) {
            val damage = stack.damageValue

            if (damage >= stack.maxDamage) {
                stack.shrink(1)
                PokemonUtil.spawn(key.createProperties(70), level, player.onPos)
                postSpawn(level, player, usedHand)
            } else {
                player.displayClientMessage(Component.translatable(lang, stack.maxDamage - damage), true)
            }

            return InteractionResultHolder.success(stack)
        }

        return InteractionResultHolder.pass(stack)
    }

    protected fun postSpawn(level: Level, player: Player, usedHand: InteractionHand) {
        itemToGiveUponSpawn?.defaultInstance?.let { player.setItemInHand(usedHand, it) }
    }

    companion object {
        const val DEFAULT_LANG_KEY: String = "generations_core.orb.amountfull"
    }
}
