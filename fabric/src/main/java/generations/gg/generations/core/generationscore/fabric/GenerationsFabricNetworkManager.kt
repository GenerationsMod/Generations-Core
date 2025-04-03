package generations.gg.generations.core.generationscore.fabric

import dev.architectury.utils.EnvExecutor
import generations.gg.generations.core.generationscore.common.NetworkManager
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork
import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import org.apache.logging.log4j.util.TriConsumer
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Function
import kotlin.reflect.KClass

class GenerationsFabricNetworkManager : NetworkManager {
    override fun registerClientBound() {
        GenerationsNetwork.registerClientBound()
    }

    override fun registerServerBound() {
        GenerationsNetwork.registerServerBound()
    }

    override fun <T : GenerationsNetworkPacket<T>?> createClientBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: Consumer<T>
    ) {
        EnvExecutor.runInEnv(
            EnvType.CLIENT
        ) {
            Runnable {
                ClientPlayNetworking.registerGlobalReceiver<T>(
                    identifier,
                    createClientBoundHandler(decoder, handler)
                )
            }
        }
    }

    override fun <T : GenerationsNetworkPacket<T>?> createServerBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: ServerNetworkPacketHandler<T>
    ) {
        ServerPlayNetworking.registerGlobalReceiver<T>(identifier, this.createServerBoundHandler(
            decoder
        ) { packet: T, server: MinecraftServer?, player: ServerPlayer? ->
            handler.handleOnNettyThread(
                packet,
                server,
                player
            )
        })
    }

    override fun <T : GenerationsNetworkPacket<T>?> createBothBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        clientHandler: Consumer<T>,
        serverHandler: ServerNetworkPacketHandler<T>
    ) {
        createClientBound(identifier, kClass, encoder, decoder, clientHandler)
        createServerBound(identifier, kClass, encoder, decoder, serverHandler)
    }


    private fun <T : GenerationsNetworkPacket<*>?> createServerBoundHandler(
        decoder: Function<FriendlyByteBuf, T>,
        handler: TriConsumer<T, MinecraftServer, ServerPlayer>
    ): ServerPlayNetworking.PlayChannelHandler {
        return ServerPlayNetworking.PlayChannelHandler { server, player, listner, buffer, sender ->
            handler.accept(
                decoder.apply(buffer),
                server,
                player
            )
        }
    }

    private fun <T : GenerationsNetworkPacket<*>?> createClientBoundHandler(
        decoder: Function<FriendlyByteBuf, T>,
        handler: Consumer<T>
    ): ClientPlayNetworking.PlayChannelHandler {
        return ClientPlayNetworking.PlayChannelHandler { client, a, buffer, b -> handler.accept(decoder.apply(buffer)) }
    }

    override fun sendPacketToPlayer(player: ServerPlayer, packet: GenerationsNetworkPacket<*>) {
        ServerPlayNetworking.send(player, packet.getId(), packet.toBuffer())
    }

    override fun sendPacketToServer(packet: GenerationsNetworkPacket<*>) {
        ClientPlayNetworking.send(packet.getId(), packet.toBuffer())
    }

    override fun <T : GenerationsNetworkPacket<*>?> asVanillaClientBound(packet: T): Packet<ClientGamePacketListener> {
        return ServerPlayNetworking.createS2CPacket<T>(packet!!.getId(), packet.toBuffer())
    }

    override fun <T : GenerationsNetworkPacket<*>?, V : Entity?> sendToAllTracking(packet: T, entity: V) {
        val players = PlayerLookup.around(entity!!.level() as ServerLevel, entity.position(), 50.0)
        players.forEach(Consumer { player: ServerPlayer ->
            sendPacketToPlayer(
                player,
                packet!!
            )
        })
    }

    companion object {
        @JvmField
        val INSTANCE: GenerationsFabricNetworkManager = GenerationsFabricNetworkManager()
    }
}