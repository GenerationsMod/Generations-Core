package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies.getByIdentifier
import com.cobblemon.mod.common.api.text.text
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.legends.DistanceTraveledImplItem
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class LegendaryEggItem(properties: Properties, private val speciesKey: SpeciesKey) :
    DistanceTraveledImplItem(properties, 10000.0), LangTooltip {
    //    @Override
    //    public double getMaxDistance() {
    //        var species = PokemonSpecies.INSTANCE.getByIdentifier(speciesKey.species());
    //        return species.getEggCycles() * GenerationsCore.CONFIG.breeding.blocksPerEggCcyle;
    //    }
    override fun onCompletion(level: ServerLevel, player: Player, usedHand: InteractionHand?) {
        PokemonUtil.spawn(GenerationsUtils.parseProperties(speciesKey.species.path), level, player.position())
    }

    override fun checkPlayerState(player: ServerPlayer): Boolean {
        return GenerationsCore.CONFIG.caught.capped(player, speciesKey)
    }

    override fun addText(
        stack: ItemStack,
        level: TooltipContext,
        tooltipComponents: MutableList<Component>,
        isAdvanced: TooltipFlag
    ) {
        val distance = remainingNeededDistance(stack)

        if (distance > 0) tooltipComponents.add("Distance needed to hatch: %.2f".format(distance).text())
        else {
            val species = getByIdentifier(
                speciesKey.species
            )
            tooltipComponents.add(
                Component.nullToEmpty(
                    "Ready to Hatch. Right Click to hatch " + (species?.name
                        ?: "Redacted") + "!"
                )
            )
        }
    }
}
