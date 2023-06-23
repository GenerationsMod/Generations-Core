package generations.gg.generations.core.generationscore.network.packets.dialogue

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import generations.gg.generations.core.generationscore.GenerationsCore
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer

class C2SSaveDatapackEntryPacket(private val location: ResourceLocation, data: JsonElement?) : NetworkPacket<C2SSaveDatapackEntryPacket> {
    private val data: String

    override val id = ID

    init {
        this.data = GSON.toJson(data)
    }

    override fun encode(buf: FriendlyByteBuf) {
        buf.writeResourceLocation(location)
        buf.writeUtf(data)
    }

    companion object {
        val ID = GenerationsCore.id("save_datapack_entry")
        private val GSON = GsonBuilder().setPrettyPrinting().create()

        fun decode(buf: FriendlyByteBuf) = C2SSaveDatapackEntryPacket(buf.readResourceLocation(), GSON.toJsonTree(buf.readUtf()))
    }

    class Handler : ServerNetworkPacketHandler<C2SSaveDatapackEntryPacket> {
        override fun handle(packet: C2SSaveDatapackEntryPacket, server: MinecraftServer, player: ServerPlayer) {
            TODO("Not yet implemented") //TODO Implement datapack updating.
        }
    }
}