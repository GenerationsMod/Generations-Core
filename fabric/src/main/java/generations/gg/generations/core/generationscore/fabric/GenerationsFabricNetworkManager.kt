package generations.gg.generations.core.generationscore.fabric

import com.cobblemon.mod.common.NetworkManager
import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.network.GenerationsNetwork
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import kotlin.reflect.KClass

object GenerationsFabricNetworkManager : NetworkManager {

    override fun registerClientBound() {
        GenerationsNetwork.registerClientBound()
    }

    override fun registerServerBound() {
        GenerationsNetwork.registerServerBound()
    }

    override fun <T : NetworkPacket<T>> createClientBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: (T, FriendlyByteBuf) -> Unit,
        decoder: (FriendlyByteBuf) -> T,
        handler: ClientNetworkPacketHandler<T>
    ) {
        ClientPlayNetworking.registerGlobalReceiver(identifier, this.createClientBoundHandler(decoder::invoke) { msg, _ ->
            handler.handleOnNettyThread(msg)
        })
    }

    override fun <T : NetworkPacket<T>> createServerBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: (T, FriendlyByteBuf) -> Unit,
        decoder: (FriendlyByteBuf) -> T,
        handler: ServerNetworkPacketHandler<T>
    ) {
        ServerPlayNetworking.registerGlobalReceiver(identifier, this.createServerBoundHandler(decoder::invoke, handler::handleOnNettyThread))
    }

    private fun <T : NetworkPacket<*>> createServerBoundHandler(
        decoder: (FriendlyByteBuf) -> T,
        handler: (T, MinecraftServer, ServerPlayer) -> Unit
    ) = ServerPlayNetworking.PlayChannelHandler { server, player, _, buffer, _ ->
        handler(decoder(buffer), server, player)
    }

    private fun <T : NetworkPacket<*>> createClientBoundHandler(
        decoder: (FriendlyByteBuf) -> T,
        handler: (T, Minecraft) -> Unit
    ) = ClientPlayNetworking.PlayChannelHandler { client, _,  buffer, _ ->
        handler(decoder(buffer), client)
    }

    override fun sendPacketToPlayer(player: ServerPlayer, packet: NetworkPacket<*>) {
        ServerPlayNetworking.send(player, packet.id, packet.toBuffer())
    }

    override fun sendPacketToServer(packet: NetworkPacket<*>) {
        ClientPlayNetworking.send(packet.id, packet.toBuffer())
    }

    override fun <T : NetworkPacket<*>> asVanillaClientBound(packet: T): Packet<ClientGamePacketListener> {
        return ServerPlayNetworking.createS2CPacket(packet.id, packet.toBuffer())
    }
}