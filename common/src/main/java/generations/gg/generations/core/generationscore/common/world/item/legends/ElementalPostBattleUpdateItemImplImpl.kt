package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.api.types.ElementalType
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

open class ElementalPostBattleUpdateItemImplImpl(
    properties: Properties,
    private val lang: String = DEFAULT_LANG_KEY,
    private val key: SpeciesKey,
    types: List<ElementalType> = listOf(),
    private val itemToGiveUponSpawn : Holder<Item>? = null,
    private val predicate: (Player) -> Boolean = { true }
    ) : ElementalPostBattleUpdateItemImpl(properties, types) {

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(usedHand)

        if (!level.isClientSide() && predicate.invoke(player)) {

            val damage = stack.damageValue

            if (damage >= stack.maxDamage) {
                PokemonUtil.spawn(key.createProperties(70), level, player.onPos)
                stack.consume(1, player)
                postSpawn(player)
            } else {
                player.displayClientMessage(Component.translatable(lang, stack.maxDamage - damage), true)
            }

            return InteractionResultHolder.success(stack)
        }

        return InteractionResultHolder.pass(stack)
    }

    protected fun postSpawn(player: Player) {
        itemToGiveUponSpawn?.value()?.defaultInstance?.also(player::addItem)
    }

    companion object {
        const val DEFAULT_LANG_KEY: String = "generations_core.orb.amountfull"
    }
}
