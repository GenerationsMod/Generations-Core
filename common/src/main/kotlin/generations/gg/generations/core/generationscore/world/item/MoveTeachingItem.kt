package generations.gg.generations.core.generationscore.world.item

import com.cobblemon.mod.common.api.moves.BenchedMove
import com.cobblemon.mod.common.api.moves.MoveTemplate
import com.cobblemon.mod.common.api.moves.Moves
import com.cobblemon.mod.common.api.pokemon.moves.LearnsetQuery
import com.cobblemon.mod.common.api.text.bold
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import dev.architectury.event.EventResult
import net.minecraft.ChatFormatting
import net.minecraft.locale.Language
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

open class MoveTeachingItem(properties: Properties) : Item(properties), PixelmonInteractions.PixelmonInteraction {
    override fun interact(pixelmonEntity: PokemonEntity, player: Player, itemInHand: ItemStack): EventResult {
        val data = pixelmonEntity.pokemon
        val template : MoveTemplate? = itemInHand.getMove()


        return if (template == null) {
            player.displayClientMessage("move.doesntexist".asTranslated(),true)
            EventResult.pass()
        } else if (LearnsetQuery.TM_MOVE.canLearn(template, data.form.moves)) {
            val moveSet = data.moveSet
            if ((moveSet.map { it.template } + data.benchedMoves.map { it.moveTemplate }).any { it == template }) {
                player.displayClientMessage("move.alreadyknows".asTranslated(data.getDisplayName(), template.displayName).bold(), true)
                EventResult.pass()
            }

            data.benchedMoves.add(BenchedMove(template, 0))

            player.displayClientMessage("move.newmove1".asTranslated().bold(), true)
            player.displayClientMessage("move.newmove3".asTranslated(template.displayName).bold(), true)

            EventResult.interruptTrue()
        } else {
            player.sendSystemMessage(
                "move.cantlearn".asTranslated(data.getDisplayName(), template.displayName).bold())
            EventResult.pass()
        }
    }

    companion object {
        fun getType(stack: ItemStack) : ElementalType = stack.getMove()?.elementalType ?: ElementalTypes.NORMAL
        fun createTm(number: Int, move: String): ItemStack {
            val stack = GenerationsItems.TM.get().defaultInstance
            val tag = stack.orCreateTag;
            tag.putInt("number", number)
            tag.putString("move", move)
            return stack;
        }
    }
}

fun ItemStack.getMove(): MoveTemplate? = this.tag.takeIf { it?.contains("move") != null }?.getString("move")?.let { Moves.getByName(it) }

