package generations.gg.generations.core.generationscore.common.network.packets


import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.client.net.gui.InteractPokemonUIPacketHandler
import com.cobblemon.mod.common.util.cobblemonResource
import com.cobblemon.mod.common.util.readString
import com.cobblemon.mod.common.util.readUUID
import com.cobblemon.mod.common.util.writeString
import com.cobblemon.mod.common.util.writeUUID
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.world.item.Item
import java.util.UUID

/**
 * Tells the client to open the Pokémon interaction interface.
 *
 * Handled by [InteractPokemonUIPacketHandler].
 *
 * @author Village
 * @since January 7th, 2023
 */
class GensInteractPokemonUIPacket(
    val pokemonID: UUID,
    val canMountShoulder: Boolean,
    val canGiveHeld: Boolean,
    val canGiveCosmetic: Boolean,
    val canRide: Boolean,
    val changeFormData: Pair<Boolean, String>
): NetworkPacket<GensInteractPokemonUIPacket> {

    override val id = ID

    override fun encode(buffer: RegistryFriendlyByteBuf) {
        buffer.writeUUID(pokemonID)
        buffer.writeBoolean(canMountShoulder)
        buffer.writeBoolean(canGiveHeld)
        buffer.writeBoolean(canGiveCosmetic)
        buffer.writeBoolean(canRide)
        buffer.writeBoolean(changeFormData.first)
        buffer.writeString(changeFormData.second)
    }

    companion object {
        val ID = cobblemonResource("gens_interact_pokemon_ui")
        fun decode(buffer: RegistryFriendlyByteBuf) = GensInteractPokemonUIPacket(
            buffer.readUUID(),
            buffer.readBoolean(),
            buffer.readBoolean(),
            buffer.readBoolean(),
            buffer.readBoolean(),
            Pair(buffer.readBoolean(), buffer.readString()))
    }
}
