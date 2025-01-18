package generations.gg.generations.core.generationscore.common.world.item.curry

import com.cobblemon.mod.common.api.interaction.PokemonEntityInteraction
import com.cobblemon.mod.common.api.pokemon.experience.SidemodExperienceSource
import com.cobblemon.mod.common.api.text.plus
import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.api.player.CurryDex
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.getFlavorLocalizedName
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import net.minecraft.client.gui.screens.Screen
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class ItemCurry(properties: Properties) : Item(properties.stacksTo(64)), PokemonEntityInteraction {
    override fun getName(stack: ItemStack): Component {
        val data = getData(stack)
        var name = this.description;
        if (data.curryType != CurryType.None) name = (data.curryType.localizedName + " ").text() + name
        if (data.flavor != null) name = (getFlavorLocalizedName(data.flavor) + " ").text() + name
        return name
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        val info =
            "" //Language.getInstance().getOrDefault("gui.shopkeeper." + this.getDescriptionId().getTranslationKey());
        if (!hasHideFlag(stack)) {

            if (Screen.hasShiftDown()) {
//                tooltipComponents.add(Component.nullToEmpty(info))
                tooltipComponents.add(Component.nullToEmpty("Rating: " + getData(stack).rating.getName()))
            } else {
                tooltipComponents.add(Component.nullToEmpty("Hold shift for more info."))
            }
        }
    }

    private fun hasHideFlag(stack: ItemStack): Boolean {
        return stack.hasTag() && stack.tag!!.getBoolean("hide_tooltip")
    }

    override fun onCraftedBy(stack: ItemStack, level: Level, player: Player) {
        if (player is ServerPlayer) {
            val rating = CurryDex.of(player).currentTaste
            val data = getData(stack)
            data.setRating(rating)
            rating.configureData(data)
            setData(stack, data)
            CurryDex.add(player, data)
        }
    }

    override val accepted: Set<PokemonEntityInteraction.Ownership>
        get() = setOf(PokemonEntityInteraction.Ownership.OWNER)

    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        val curry = getData(stack)

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

        return true
    }

    class CurryExperienceSource(val player: ServerPlayer, val stack: ItemStack) : SidemodExperienceSource(
        GenerationsCore.MOD_ID) {
        val curry = getData(stack)

        override fun isInteraction(): Boolean {
            return true
        }
    }

    companion object {
        @JvmStatic
        fun createStack(data: CurryData): ItemStack {
            val stack = ItemStack(GenerationsItems.CURRY.get())
            setData(stack, data)
            return stack
        }

        fun setData(stack: ItemStack, data: CurryData) {
            stack.addTagElement("data", data.toNbt())
        }

        fun getData(stack: ItemStack): CurryData {
            return CurryData.fromNbt(stack.getOrCreateTagElement("data"))
        }
    }
}
