package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions.PokemonInteraction

import com.cobblemon.mod.common.api.text.text
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class TeraSharditem(properties: Item.Properties, val teraType: TeraType) : Item(properties), PokemonInteraction {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        val requiredAmount = 50
        if (stack.count < requiredAmount) {
            player.sendSystemMessage("You need at least ${requiredAmount} shards to change a Pokemon's Tera Type.".text())
            return false
        }

        val pokemon = entity.pokemon

        if (pokemon.species.name.equals("Ogerpon") || pokemon.species.name.equals("Terapagos")) {
            player.sendSystemMessage("This Pokemon's Tera Type cannot be changed.".text())
            return false
        }

        if (pokemon.teraType == teraType) {
            player.sendSystemMessage("${pokemon.getDisplayName().string} already has the ${teraType.showdownId()} Tera Type.".text())
            return false
        }
        entity.pokemon.teraType = teraType
        stack.shrink(requiredAmount)
        player.sendSystemMessage(("${entity.pokemon.getDisplayName().string}'s Tera Type was changed to ${teraType.showdownId()}.".text()))
        return true
    }

    override val isConsumed: Boolean
        get() = false
}
