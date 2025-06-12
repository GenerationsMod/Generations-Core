package generations.gg.generations.core.generationscore.common.world.level.block

import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeatureProvider
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.asTranslated
import generations.gg.generations.core.generationscore.common.util.getOrCreate
import generations.gg.generations.core.generationscore.common.util.getProviderOrNull
import generations.gg.generations.core.generationscore.common.world.item.BlockItemWithLang
import generations.gg.generations.core.generationscore.common.world.item.GenerationsCobblemonInteractions
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block

open class PokemonInteractBlockItem(block: Block, properties: Item.Properties, val form: String): BlockItemWithLang(block, properties), GenerationsCobblemonInteractions.PokemonInteraction {
    override fun processInteraction(player: ServerPlayer, entity: PokemonEntity, stack: ItemStack): Boolean {

        val provider = entity.pokemon.getProviderOrNull<FlagSpeciesFeatureProvider>("form") ?: return false
        val feature = provider.getOrCreate(entity.pokemon)

        feature.enabled = !feature.enabled
        feature.apply(entity)
        player.sendSystemMessage("generations_core.ability.formchange".asTranslated(entity.pokemon.getDisplayName().string), true)
    }

}
