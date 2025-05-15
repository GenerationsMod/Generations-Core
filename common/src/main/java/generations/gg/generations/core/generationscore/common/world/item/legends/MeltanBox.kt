package generations.gg.generations.core.generationscore.common.world.item.legends

import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import com.cobblemon.mod.common.util.party
import com.google.common.collect.Streams
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems.MELTAN_BOX_CHARGED
import generations.gg.generations.core.generationscore.common.world.item.PostBattleUpdatingItem.BattleData
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

fun <T> Iterable<T>?.any(function: (T) -> Boolean): Boolean = this?.any(function) ?: false

class MeltanBox(settings: Properties) : PostBattleUpdatingWithItem(
    settings,
    LegendKeys.MELMETAL,
    "pixelmon.meltanbox.amountfull", { player, _, battle: BattleData ->
        player.entity?.party()?.map(Pokemon::species)?.map(Species::resourceIdentifier)?.map(ResourceLocation::toString).any { it == "cobblemon:metalan" } && battle.pokemon.types.any { it == ElementalTypes.STEEL }
    }) {

    override fun postSpawn(level: Level, player: Player, usedHand: InteractionHand) {
        player.setItemInHand(usedHand, ItemStack(MELTAN_BOX_CHARGED.get()))
    }
}
