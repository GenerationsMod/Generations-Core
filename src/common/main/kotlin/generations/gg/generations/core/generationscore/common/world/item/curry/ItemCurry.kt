package generations.gg.generations.core.generationscore.common.world.item.curry

import com.cobblemon.mod.common.api.interaction.PokemonEntityInteraction
import com.cobblemon.mod.common.api.pokemon.experience.SidemodExperienceSource
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.api.events.CurryEvents
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.getFlavorLocalizedName
import generations.gg.generations.core.generationscore.common.util.extensions.dsl
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class ItemCurry(properties: Properties) : Item(properties.stacksTo(64)), PokemonEntityInteraction {
    override fun getName(stack: ItemStack): Component {
        val data = stack.getOrDefault(GenerationsDataComponents.CURRY_DATA.value(), CurryData())
        var name = this.description;
        if (data.curryType != CurryType.None) name = (data.curryType.localizedName + " ").text().append(name)
        if (data.flavor != null) name = (getFlavorLocalizedName(data.flavor) + " ").text().append(name)
        return name
    }

    override fun appendHoverText(
        stack: ItemStack,
        contex: TooltipContext,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        tooltipComponents.dsl {
            val data = stack.getOrDefault(GenerationsDataComponents.CURRY_DATA.value(), CurryData())

            +"Rating: ${data.rating.name}"
            +"Restores PP: ${data.canRestorePP().text()}"
            +"Heal Statue Effects: ${data.canHealStatus().text()}"
            +"Friendship Given: ${data.friendship}"
            +"Experience Given: ${data.experience}"
            +"Health Restored: ${data.healthPercentage * 100}%"
        }
    }

    override fun onCraftedBy(stack: ItemStack, level: Level, player: Player) {
        if (player is ServerPlayer) {

            val data = stack.getOrDefault(GenerationsDataComponents.CURRY_DATA.value(), CurryData())

            CurryEvents.MODIFY_RATING.post(CurryEvents.ModifyRating(player, data, CurryTasteRating.Milcery), then = { event ->
                data.setRating(event.rating)
                event.rating.configureData(data)
                stack.set(GenerationsDataComponents.CURRY_DATA.value(), data)
//            CurryDex.add(player, data)
            })
        }
    }

    override val accepted: Set<PokemonEntityInteraction.Ownership>
        get() = setOf(PokemonEntityInteraction.Ownership.OWNER)

    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        val curry = stack.getOrDefault(GenerationsDataComponents.CURRY_DATA.value(), CurryData())

        val pokemon = entity.pokemon
        pokemon.incrementFriendship(curry.friendship, true)
        pokemon.addExperienceWithPlayer(player, CurryExperienceSource(player, stack), curry.experience)
        if(curry.canHealStatus()) pokemon.status = null
        if(curry.canRestorePP()) {
            pokemon.moveSet.doWithoutEmitting {
                pokemon.moveSet.getMoves().forEach {
                    if (it.currentPp < it.maxPp) {
                        it.currentPp = it.maxPp
                    }
                }
            }
        }

        this.consumeItem(player, stack)
        return true
    }

    class CurryExperienceSource(val player: ServerPlayer, val stack: ItemStack) : SidemodExperienceSource(
        GenerationsCore.MOD_ID) {
        val curry = stack.get(GenerationsDataComponents.CURRY_DATA.value())

        override fun isInteraction(): Boolean {
            return true
        }
    }

    companion object {
        @JvmStatic
        fun createStack(data: CurryData): ItemStack {
            val stack = ItemStack(GenerationsItems.CURRY)
            stack.set(GenerationsDataComponents.CURRY_DATA.value(), data)
            return stack
        }
    }
}

private fun Boolean.text(): String {
    return if(this) "Yes" else "No"
}

