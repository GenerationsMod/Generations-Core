package generations.gg.generations.core.generationscore.forge

import dev.architectury.utils.Env
import dev.architectury.utils.EnvExecutor
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsImplementation
import generations.gg.generations.core.generationscore.common.network.GenerationsNetwork
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraftforge.fml.LogicalSide
import net.minecraftforge.network.NetworkDirection
import net.minecraftforge.network.NetworkEvent
import net.minecraftforge.network.NetworkRegistry
import net.minecraftforge.network.PacketDistributor
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import kotlin.reflect.KClass

object GenerationsForgeNetworkManager : GenerationsImplementation.NetworkManager {
    private var id = 0
    private val channel = NetworkRegistry.newSimpleChannel(
        GenerationsCore.id("main"),
        { PROTOCOL_VERSION },
        { anObject: String? -> PROTOCOL_VERSION.equals(anObject) }) { anObject: String? ->
        PROTOCOL_VERSION.equals(
            anObject
        )
    }

    override fun registerClientBound() {
        GenerationsNetwork.registerClientBound()
    }

    override fun registerServerBound() {
        GenerationsNetwork.registerServerBound()
    }

    override fun <T : GenerationsNetworkPacket<T>> createClientBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: Consumer<T>
    ) {
        channel.registerMessage(
            id++,
            kClass.java,
            encoder,
            decoder
        ) { msg: T, ctx: Supplier<NetworkEvent.Context> ->
            val context = ctx.get()
            handler.accept(msg)
            context.packetHandled = true
        }
    }



    override fun <T : GenerationsNetworkPacket<T>> createServerBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: ServerNetworkPacketHandler<T>
    ) {
        channel.registerMessage(id++, kClass.java, encoder, decoder) { msg: T, ctx: Supplier<NetworkEvent.Context> ->
            val context = ctx.get()
            handler.handleOnNettyThread(
                msg,
                Objects.requireNonNull(Objects.requireNonNull(context.sender)?.getServer()),
                context.sender
            )
            context.packetHandled = true
        }
    }

    override fun <T : GenerationsNetworkPacket<T>> createBothBound(
        identifier: ResourceLocation?,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        clientHandler: Consumer<T>,
        serverHandler: ServerNetworkPacketHandler<T>,
    ) {
        channel.registerMessage(id++, kClass.java, encoder, decoder) { msg: T, ctx: Supplier<NetworkEvent.Context> ->
            val context = ctx.get()

            if(context.direction.receptionSide == LogicalSide.SERVER) {
                clientHandler.accept(msg)
            } else {
                serverHandler.handleOnNettyThread(
                    msg,
                    Objects.requireNonNull(Objects.requireNonNull(context.sender)?.getServer()),
                    context.sender
                )
            }
            context.packetHandled = true
        }
    }

    override fun sendPacketToPlayer(player: ServerPlayer, packet: GenerationsNetworkPacket<*>) {
        channel.send(PacketDistributor.PLAYER.with { player }, packet)
    }

    override fun sendPacketToServer(packet: GenerationsNetworkPacket<*>?) {
        channel.sendToServer(packet)
    }

    override fun <T : GenerationsNetworkPacket<*>?> asVanillaClientBound(packet: T): Packet<ClientGamePacketListener> {
        return channel.toVanillaPacket(packet, NetworkDirection.PLAY_TO_CLIENT) as Packet<ClientGamePacketListener>
    }

    override fun <T : GenerationsNetworkPacket<*>?, V : Entity?> sendToAllTracking(packet: T, entity: V) {
        channel.send(PacketDistributor.TRACKING_ENTITY.with { entity }, packet)
    }

    private const val PROTOCOL_VERSION = "1"
}