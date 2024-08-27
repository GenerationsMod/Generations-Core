package generations.gg.generations.core.generationscore.common.network

import com.cobblemon.mod.common.util.server
import dev.architectury.utils.EnvExecutor
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsImplementation
import generations.gg.generations.core.generationscore.common.network.packets.*
import generations.gg.generations.core.generationscore.common.network.packets.npc.*
import generations.gg.generations.core.generationscore.common.network.packets.shop.*
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenHandler
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenPacket
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatueHandler
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatuePacket
import generations.gg.generations.core.generationscore.common.network.spawn.SpawnExtraDataEntityHandler
import generations.gg.generations.core.generationscore.common.network.spawn.SpawnStatuePacket
import generations.gg.generations.core.generationscore.common.world.shop.ShopPresetRegistrySyncPacket
import generations.gg.generations.core.generationscore.common.world.shop.ShopRegistrySyncPacket
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
        this.createClientBound(ShopRegistrySyncPacket.ID, ShopRegistrySyncPacket::decode, clientProxy.processShopRegistrySyncPacket)
        this.createClientBound(ShopPresetRegistrySyncPacket.ID, ShopPresetRegistrySyncPacket::decode, clientProxy.processShopPresetRegistrySyncPacket)
        this.createClientBound(NpcPresetsRegistrySyncPacket.ID, NpcPresetsRegistrySyncPacket::decode, clientProxy.processNpcPresetsRegistrySyncPacket)
        this.createClientBound(S2COpenShopPacket.ID, S2COpenShopPacket::decode, clientProxy.processS2COpenShopPacket)
        this.createClientBound(S2CSyncPlayerMoneyPacket.ID, ::S2CSyncPlayerMoneyPacket, clientProxy.processS2CSyncPlayerMoneyPacket)
        this.createClientBound(S2COpenNpcCustomizationScreenPacket.ID, ::S2COpenNpcCustomizationScreenPacket, clientProxy.processS2COpenNpcCustomizationScreenPacket)
        this.createClientBound(S2CUpdateNpcDisplayDataPacket.ID, ::S2CUpdateNpcDisplayDataPacket, clientProxy.processS2CUpdateNpcDisplayDataPacket)
        this.createClientBound(SpawnStatuePacket.ID, SpawnStatuePacket::decode, clientProxy.processSpawnStatuePacket)
    }

    override fun registerServerBound() {
        this.createServerBound(C2STogglePacket.ID, C2STogglePacket::decode, C2SToggleHandler())
        this.createServerBound(C2SEditMailPacket.ID, C2SEditMailPacket::decode, C2SEditMailHandler())
        this.createServerBound(C2SShopItemPacket.ID, C2SShopItemPacket::decode, C2SShopItemHandler())
        this.createServerBound(C2SUpdateNpcDisplayDataPacket.ID, ::C2SUpdateNpcDisplayDataPacket, C2SUpdateNpcDisplayDataHandler())
        this.createServerBound(C2SUpdateNpcShopPacket.ID, ::C2SUpdateNpcShopPacket, C2SUpdateNpcShopHandler())
        this.createServerBound(C2SSetNpcPresetPacket.ID, ::C2SSetNpcPresetPacket, C2SSetNpcPresetHandler())
        this.createServerBound(HeadPatPacket.ID, ::HeadPatPacket, HeadPatPacketHandler())

        createServerStatueUpdate()
    }

    private fun createServerStatueUpdate() {
        this.createServerBound(UpdateStatuePacket.PROPERTIES_ID, UpdateStatuePacket.Companion::propertiesDecode, UpdateStatueHandler.Properties)
        this.createServerBound(UpdateStatuePacket.LABEL_ID, UpdateStatuePacket.Companion::labelDecode, UpdateStatueHandler.Label)
        this.createServerBound(UpdateStatuePacket.SCALE_ID, UpdateStatuePacket.Companion::scaleDecode, UpdateStatueHandler.Scale)
        this.createServerBound(UpdateStatuePacket.POSE_TYPE_ID, UpdateStatuePacket.Companion::poseTypeDecode, UpdateStatueHandler.PoseType)
        this.createServerBound(UpdateStatuePacket.STATIC_TOGGLE_ID, UpdateStatuePacket.Companion::staticToggleDecode, UpdateStatueHandler.StaticToggle)
        this.createServerBound(UpdateStatuePacket.STATIC_PARTIAL_ID, UpdateStatuePacket.Companion::staticPartialDecode, UpdateStatueHandler.StaticPartial)
        this.createServerBound(UpdateStatuePacket.STATIC_AGE_ID, UpdateStatuePacket.Companion::staticAgeDecode, UpdateStatueHandler.StaticAge)
        this.createServerBound(UpdateStatuePacket.INTERACTABLE_ID, UpdateStatuePacket.Companion::interactableDecode, UpdateStatueHandler.Interactable)
        this.createServerBound(UpdateStatuePacket.MATERIAL_ID, UpdateStatuePacket.Companion::materialDecode, UpdateStatueHandler.Material)
        this.createServerBound(UpdateStatuePacket.ORIENTATION_ID, UpdateStatuePacket.Companion::orientationDecode, UpdateStatueHandler.Orientation)
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