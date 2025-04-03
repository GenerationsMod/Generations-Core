package generations.gg.generations.core.generationscore.common

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Function
import kotlin.reflect.KClass


interface NetworkManager {
    fun

    fun registerClientBound()

    fun registerServerBound()

    fun <T : GenerationsNetworkPacket<T>?> createClientBound(
        identifier: ResourceLocation?,
        kClass: KClass<T>?,
        encoder: BiConsumer<T, FriendlyByteBuf?>?,
        decoder: Function<FriendlyByteBuf?, T>?,
        handler: Consumer<T>?
    )

    fun <T : GenerationsNetworkPacket<T>?> createServerBound(
        identifier: ResourceLocation?,
        kClass: KClass<T>?,
        encoder: BiConsumer<T, FriendlyByteBuf?>?,
        decoder: Function<FriendlyByteBuf?, T>?,
        handler: ServerNetworkPacketHandler<T>?
    )

    fun sendPacketToPlayer(player: ServerPlayer?, packet: GenerationsNetworkPacket<*>?)

    fun sendPacketToServer(packet: GenerationsNetworkPacket<*>?)

    fun <T : GenerationsNetworkPacket<T>?> createBothBound(
        identifier: ResourceLocation?,
        kClass: KClass<T>?,
        encoder: BiConsumer<T, FriendlyByteBuf?>?,
        decoder: Function<FriendlyByteBuf?, T>?,
        clientHandler: Consumer<T>?,
        serverHandler: ServerNetworkPacketHandler<T>?
    )
}
