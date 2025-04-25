package generations.gg.generations.core.generationscore.common.world.item

import com.cobblemon.mod.common.api.pokemon.feature.ChoiceSpeciesFeatureProvider
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.util.cycle
import generations.gg.generations.core.generationscore.common.util.extensions.component
import generations.gg.generations.core.generationscore.common.util.getOrCreate
import generations.gg.generations.core.generationscore.common.util.getProviderOrNull
import generations.gg.generations.core.generationscore.common.util.isSpecies
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsItemComponents
import generations.gg.generations.core.generationscore.common.world.item.legends.EnchantableItem
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level


class MeteoriteItem(arg: Properties) : EnchantableItem(arg.component(GenerationsItemComponents.USED, false)), LangTooltip, GenerationsCobblemonInteractions.PokemonInteraction {
    override fun neededEnchantmentLevel(player: ServerPlayer): Int {
        val caught = GenerationsCore.CONFIG.caught
        return if (caught.capped(player, LegendKeys.DEOXYS)) super.neededEnchantmentLevel(player) else 0
    }

    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (!level.isClientSide()) {
            val stack = player.getItemInHand(usedHand)
            if (isEnchanted(stack)) {
                PokemonUtil.spawn(LegendKeys.DEOXYS.createProperties(70), level, player.onPos)
                setEnchanted(stack, false)
                setUsed(stack, true)

                return InteractionResultHolder.success(stack)
            }
        }
        return super.use(level, player, usedHand)
    }

    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {
        if (entity.pokemon.isSpecies("deoxys")) {

            val provider = entity.pokemon.getProviderOrNull<ChoiceSpeciesFeatureProvider>("deoxys_form") ?: return false
            val feature = provider.getOrCreate(entity.pokemon)
            feature.value = provider.cycle(feature.value)
            feature.apply(entity)

            player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
            return true
        }

        return false
    }
}
