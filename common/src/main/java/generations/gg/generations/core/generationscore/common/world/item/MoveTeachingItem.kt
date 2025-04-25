package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.moves.BenchedMove
import com.cobblemon.mod.common.api.moves.MoveTemplate
import com.cobblemon.mod.common.api.moves.Moves.getByName
import com.cobblemon.mod.common.api.pokemon.moves.LearnsetQuery
import com.cobblemon.mod.common.api.text.bold
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

abstract class MoveTeachingItem(properties: Properties) : Item(properties), PokemonInteraction {

    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        val data = entity.pokemon
        val template = getMove(stack)
        return when {
            template == null -> {
                player.displayClientMessage("move.doesntexist".asTranslated(), true)
                false
            }
            LearnsetQuery.TM_MOVE.canLearn(template, data.form.moves) -> {
                val moveSet = data.moveSet
                if((moveSet.map { it.template } + data.benchedMoves.map { it.moveTemplate }).any { it == template }) {
                    player.displayClientMessage("move.alreadyknows".asTranslated(data.getDisplayName(), template.displayName).bold(), true)
                    return false
                }

                data.benchedMoves.add(BenchedMove(template, 0))
                player.displayClientMessage("move.newmove1".asTranslated().bold(), true)
                player.displayClientMessage("move.newmove3".asTranslated(template.displayName).bold(), true)
                true
            }
            else -> {
                player.sendSystemMessage("move.cantlearn".asTranslated(data.getDisplayName(), template.displayName).bold())
                false
            }
        }
    }

    fun getType(stack: ItemStack?): ElementalType = getMove(stack)?.elementalType ?: ElementalTypes.NORMAL

    fun getMove(itemStack: ItemStack?): MoveTemplate? = getMoveString(itemStack)?.let(::getByName)

    protected abstract fun getMoveString(itemStack: ItemStack): String?
}
