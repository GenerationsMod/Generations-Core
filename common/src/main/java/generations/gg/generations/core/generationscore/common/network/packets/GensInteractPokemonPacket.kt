package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.net.serverhandling.pokemon.interact.InteractPokemonHandler
import com.cobblemon.mod.common.util.cobblemonResource
import com.cobblemon.mod.common.util.readString
import com.cobblemon.mod.common.util.readUUID
import com.cobblemon.mod.common.util.writeString
import com.cobblemon.mod.common.util.writeUUID
import net.minecraft.network.RegistryFriendlyByteBuf
import java.util.UUID

/**
 * Tells the server to handle Pokémon interaction.
 *
 * Handled by [InteractPokemonHandler].
 *
 * @author Village
 * @since January 7th, 2023
 */
class GensInteractPokemonPacket(val pokemonID: UUID, val changeFormData: String) : NetworkPacket<GensInteractPokemonPacket> {
    override val id = ID
    override fun encode(buffer: RegistryFriendlyByteBuf) {
        buffer.writeUUID(pokemonID)
        buffer.writeString(changeFormData)
    }
    companion object {
        val ID = cobblemonResource("gens_interact_pokemon")
        fun decode(buffer: RegistryFriendlyByteBuf) = GensInteractPokemonPacket(buffer.readUUID(), buffer.readString())
    }
}
