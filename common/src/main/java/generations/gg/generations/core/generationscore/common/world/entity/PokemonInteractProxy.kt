package generations.gg.generations.core.generationscore.common.world.entity;

import com.cobblemon.mod.common.CobblemonCosmeticItems
import com.cobblemon.mod.common.entity.PlatformType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.net.messages.client.ui.InteractPokemonUIPacket
import com.cobblemon.mod.common.util.isInBattle
import com.cobblemon.mod.common.util.party
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack

object PokemonInteractProxy {
    @JvmStatic
    fun showInteractWeheel(pokemonEntity: PokemonEntity, player: ServerPlayer, itemStack: ItemStack) =
        pokemonEntity.run {
            if (pokemon.getOwnerPlayer() == player) {

                val canRide = ifRidingAvailableSupply(false) { behaviour, settings, state ->
                    if (platform != PlatformType.NONE) return@ifRidingAvailableSupply false
                    if (tethering != null) return@ifRidingAvailableSupply false;
                    if (seats.isEmpty()) return@ifRidingAvailableSupply false;
                    if ((owner as? ServerPlayer)?.isInBattle() == true) return@ifRidingAvailableSupply false;
                    if (this.owner != player && this.passengers.isEmpty()) return@ifRidingAvailableSupply false;
                    return@ifRidingAvailableSupply behaviour.isActive(settings, state, this);
                }
                if (pokemon.getOwnerPlayer() == player) {
                    val cosmeticItemDefinition = CobblemonCosmeticItems.findValidCosmeticForPokemonAndItem(
                        player.level().registryAccess(),
                        pokemon,
                        itemStack
                    )

                    InteractPokemonUIPacket(
                        this.getUUID(),
                        canSitOnShoulder() && pokemon in player.party(),
                        !(pokemon.heldItem().isEmpty && itemStack.isEmpty),
                        (!pokemon.cosmeticItem.isEmpty && itemStack.isEmpty) || cosmeticItemDefinition != null,
                        canRide
                    ).sendToPlayer(player)
                } else if (!pokemon.isWild() && canRide) {
                    player.isShiftKeyDown = false
                    tryRidingPokemon(player)

                }
            }
        }
}
