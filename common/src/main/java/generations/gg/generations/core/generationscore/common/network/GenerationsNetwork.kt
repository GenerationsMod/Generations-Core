package generations.gg.generations.core.generationscore.common.network

import com.cobblemon.mod.common.util.server
import dev.architectury.utils.EnvExecutor
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsImplementation
import generations.gg.generations.core.generationscore.common.network.packets.*
import generations.gg.generations.core.generationscore.common.network.packets.shop.*
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenPacket
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatueHandler
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatuePacket
import generations.gg.generations.core.generationscore.common.network.spawn.SpawnStatuePacket
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import kotlin.reflect.KClass

object GenerationsNetwork : GenerationsImplementation.NetworkManager {
    private var clientProxy: PacketProxy = EnvExecutor.getEnvSpecific(
        { Supplier { ClientPacketProxy() } },
        { Supplier { PacketProxy() } })

    fun sendToServer(packet: GenerationsNetworkPacket<*>?) {
        sendPacketToServer(packet)
    }

    fun sendToAllPlayers(packet: GenerationsNetworkPacket<*>?) {
        sendPacketToPlayers(server()!!.playerList.players, packet)
    }

    fun sendPacketToPlayers(players: Iterable<ServerPlayer>, packet: GenerationsNetworkPacket<*>?) {
        players.forEach(Consumer { it: ServerPlayer -> sendPacketToPlayer(it, packet) })
    }

    override fun registerClientBound() {
        this.createClientBound(S2COpenMailEditScreenPacket.ID, S2COpenMailEditScreenPacket::decode, clientProxy.processS2COpenMailEditScreenPacket)
        this.createClientBound(S2COpenMailPacket.ID, S2COpenMailPacket::decode, clientProxy.processS2COpenMailPacket)
        this.createClientBound(S2CUnlockReloadPacket.ID, S2CUnlockReloadPacket::decode, clientProxy.processS2CUnlockReloadPacket)
        this.createClientBound(S2COpenStatueEditorScreenPacket.ID, S2COpenStatueEditorScreenPacket::decode, clientProxy.processS2COpenStatueEditorScreenPacket)
//        this.createClientBound(ShopRegistrySyncPacket.ID, ShopRegistrySyncPacket::decode, clientProxy.processShopRegistrySyncPacket)
//        this.createClientBound(ShopPresetRegistrySyncPacket.ID, ShopPresetRegistrySyncPacket::decode, clientProxy.processShopPresetRegistrySyncPacket)
//        this.createClientBound(S2COpenShopPacket.ID, S2COpenShopPacket::decode, clientProxy.processS2COpenShopPacket)
        this.createClientBound(S2CSyncPlayerMoneyPacket.ID, ::S2CSyncPlayerMoneyPacket, clientProxy.processS2CSyncPlayerMoneyPacket)
        this.createClientBound(SpawnStatuePacket.ID, SpawnStatuePacket::decode, clientProxy.processSpawnStatuePacket)
        this.createClientBound(S2CPlaySoundPacket.ID, S2CPlaySoundPacket::decode, clientProxy.processS2CPlaySoundPacket)
    }

    override fun registerServerBound() {
        this.createServerBound(C2STogglePacket.ID, C2STogglePacket::decode, C2SToggleHandler())
        this.createServerBound(C2SEditMailPacket.ID, C2SEditMailPacket::decode, C2SEditMailHandler())
//        this.createServerBound(C2SShopItemPacket.ID, C2SShopItemPacket::decode, C2SShopItemHandler())
        this.createServerBound(HeadPatPacket.ID, ::HeadPatPacket, HeadPatPacketHandler())

        createServerStatueUpdate()
    }

    private fun createServerStatueUpdate() {
        this.createStatueUpdate(UpdateStatuePacket.PROPERTIES_ID, UpdateStatuePacket.Companion::propertiesDecode, UpdateStatueHandler.Properties)
        this.createStatueUpdate(UpdateStatuePacket.LABEL_ID, UpdateStatuePacket.Companion::labelDecode, UpdateStatueHandler.Label)
        this.createStatueUpdate(UpdateStatuePacket.SCALE_ID, UpdateStatuePacket.Companion::scaleDecode, UpdateStatueHandler.Scale)
        this.createStatueUpdate(UpdateStatuePacket.POSE_TYPE_ID, UpdateStatuePacket.Companion::poseTypeDecode, UpdateStatueHandler.PoseType)
        this.createStatueUpdate(UpdateStatuePacket.STATIC_TOGGLE_ID, UpdateStatuePacket.Companion::staticToggleDecode, UpdateStatueHandler.StaticToggle)
        this.createStatueUpdate(UpdateStatuePacket.STATIC_PARTIAL_ID, UpdateStatuePacket.Companion::staticPartialDecode, UpdateStatueHandler.StaticPartial)
        this.createStatueUpdate(UpdateStatuePacket.STATIC_AGE_ID, UpdateStatuePacket.Companion::staticAgeDecode, UpdateStatueHandler.StaticAge)
        this.createStatueUpdate(UpdateStatuePacket.INTERACTABLE_ID, UpdateStatuePacket.Companion::interactableDecode, UpdateStatueHandler.Interactable)
        this.createStatueUpdate(UpdateStatuePacket.MATERIAL_ID, UpdateStatuePacket.Companion::materialDecode, UpdateStatueHandler.Material)
        this.createStatueUpdate(UpdateStatuePacket.ORIENTATION_ID, UpdateStatuePacket.Companion::orientationDecode, UpdateStatueHandler.Orientation)
    }

    private inline fun <reified V, reified T : UpdateStatuePacket<V, T>> createStatueUpdate(id: ResourceLocation, decoder: Function<FriendlyByteBuf,T>, handler: UpdateStatueHandler<V, T>) {
        GenerationsCore.implementation.networkManager.createBothBound(id, T::class, { message, buffer -> message.encode(buffer) }, decoder, {
            clientProxy.processStatueUpdate(it, handler)
        }, { packet, _, player ->
            handler.accept(packet, player)
            var entity = player.level().getEntity(packet.entityId) ?: return@createBothBound

            packet.sendToPlayersAround(entity.x, entity.y, entity.z, 128.0, entity.level().dimension()) { false }
        })
    }

    private inline fun <reified T : GenerationsNetworkPacket<T>> createClientBound(identifier: ResourceLocation, noinline decoder: (FriendlyByteBuf) -> T, handler: Consumer<T>) {
        GenerationsCore.implementation.networkManager.createClientBound(identifier, T::class, { message, buffer -> message.encode(buffer) }, decoder, handler)
    }

    private inline fun <reified T : GenerationsNetworkPacket<T>> createServerBound(identifier: ResourceLocation, noinline decoder: (FriendlyByteBuf) -> T, handler: ServerNetworkPacketHandler<T>) {
        GenerationsCore.implementation.networkManager.createServerBound(identifier, T::class, { message, buffer -> message.encode(buffer) }, decoder, handler)
    }

    override fun <T : GenerationsNetworkPacket<T>> createClientBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: Consumer<T>
    ) {
        GenerationsCore.implementation.networkManager.createClientBound(identifier, kClass, encoder, decoder, handler)
    }

    override fun <T : GenerationsNetworkPacket<T>> createServerBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: ServerNetworkPacketHandler<T>
    ) {
        GenerationsCore.implementation.networkManager.createServerBound(identifier, kClass, encoder, decoder, handler)
    }

    override fun <T : GenerationsNetworkPacket<T>> createBothBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        clientHandler: Consumer<T>,
        serverHandler: ServerNetworkPacketHandler<T>,
    ) {
        GenerationsCore.implementation.networkManager.createBothBound(identifier, kClass, encoder, decoder, clientHandler, serverHandler)
    }

    override fun sendPacketToPlayer(player: ServerPlayer, packet: GenerationsNetworkPacket<*>?) {
        GenerationsCore.implementation.networkManager.sendPacketToPlayer(player, packet)
    }

    override fun sendPacketToServer(packet: GenerationsNetworkPacket<*>?) {
        GenerationsCore.implementation.networkManager.sendPacketToServer(packet)
    }

    override fun <T : GenerationsNetworkPacket<*>?, V : Entity?> sendToAllTracking(packet: T, entity: V) {
        GenerationsCore.implementation.networkManager.sendToAllTracking(packet, entity)
    }

    override fun <T : GenerationsNetworkPacket<*>?> asVanillaClientBound(packet: T): Packet<ClientGamePacketListener> {
        return GenerationsCore.implementation.networkManager.asVanillaClientBound(packet)
    }
}