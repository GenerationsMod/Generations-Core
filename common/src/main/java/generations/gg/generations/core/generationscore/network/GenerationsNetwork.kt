package generations.gg.generations.core.generationscore.network

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.NetworkManager
import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import com.cobblemon.mod.common.net.serverhandling.trade.*
import com.cobblemon.mod.common.util.server
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.network.packets.C2SEditMailPacket
import generations.gg.generations.core.generationscore.network.packets.C2SToggleCookingPotPacket
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailEditScreenPacket
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailPacket
import generations.gg.generations.core.generationscore.network.packets.dialogue.*
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import kotlin.reflect.KClass

object GenerationsNetwork : NetworkManager {
    fun ServerPlayer.sendPacket(packet: NetworkPacket<*>) = sendPacketToPlayer(this, packet)
    fun sendToServer(packet: NetworkPacket<*>) = this.sendPacketToServer(packet)
    fun sendToAllPlayers(packet: NetworkPacket<*>) = sendPacketToPlayers(server()!!.playerList.players, packet)
    fun sendPacketToPlayers(players: Iterable<ServerPlayer>, packet: NetworkPacket<*>) = players.forEach { sendPacketToPlayer(it, packet) }

    override fun registerClientBound() {
        this.createClientBound(S2COpenMailEditScreenPacket.ID, S2COpenMailEditScreenPacket::decode, S2COpenMailEditScreenPacket.Handler);
        this.createClientBound(S2COpenMailPacket.ID, S2COpenMailPacket::decode, S2COpenMailPacket.Handler());
        this.createClientBound(S2CChooseDialoguePacket.ID, S2CChooseDialoguePacket::decode, S2CChooseDialoguePacket.Handler());
        this.createClientBound(S2CHealDialoguePacket.ID, S2CHealDialoguePacket::decode, S2CHealDialoguePacket.Handler());
        this.createClientBound(S2COpenDialogueEditorScreenPacket.ID, S2COpenDialogueEditorScreenPacket::decode, S2COpenDialogueEditorScreenPacket.Handler());
        this.createClientBound(S2COpenDialogueMenuPacket.ID, S2COpenDialogueMenuPacket::decode, S2COpenDialogueMenuPacket.Handler());
        this.createClientBound(S2CSayDialoguePacket.ID, S2CSayDialoguePacket::decode, S2CSayDialoguePacket.Handler());
        this.createClientBound(S2CCloseScreenPacket.ID, S2CCloseScreenPacket::decode, S2CCloseScreenPacket.Handler())
        this.createClientBound(S2CUnlockReloadPacket.ID, S2CUnlockReloadPacket::decode, S2CUnlockReloadPacket.UnlockReloadPacketHandler)
    }

    override fun registerServerBound() {
        this.createServerBound(C2SToggleCookingPotPacket.ID, C2SToggleCookingPotPacket::decode, C2SToggleCookingPotPacket.Handler())
        this.createServerBound(C2SEditMailPacket.ID, C2SEditMailPacket::decode, C2SEditMailPacket.Handler())
        this.createServerBound(C2SCloseDialoguePacket.ID, C2SCloseDialoguePacket::decode, C2SCloseDialoguePacket.Handler())
        this.createServerBound(C2SRequestNodesDialoguePacket.ID, C2SRequestNodesDialoguePacket::decode, C2SRequestNodesDialoguePacket.Handler());
        this.createServerBound(C2SRespondDialoguePacket.ID, C2SRespondDialoguePacket::decode, C2SRespondDialoguePacket.Handler());
        this.createServerBound(C2SSaveDatapackEntryPacket.ID, C2SSaveDatapackEntryPacket::decode, C2SSaveDatapackEntryPacket.Handler())
    }

    private inline fun <reified T : NetworkPacket<T>> createClientBound(identifier: ResourceLocation, noinline decoder: (FriendlyByteBuf) -> T, handler: ClientNetworkPacketHandler<T>) {
        GenerationsCore.implementation.networkManager.createClientBound(identifier, T::class, { message, buffer -> message.encode(buffer) }, decoder, handler)
    }

    private inline fun <reified T : NetworkPacket<T>> createServerBound(identifier: ResourceLocation, noinline decoder: (FriendlyByteBuf) -> T, handler: ServerNetworkPacketHandler<T>) {
        GenerationsCore.implementation.networkManager.createServerBound(identifier, T::class, { message, buffer -> message.encode(buffer) }, decoder, handler)
    }

    override fun <T : NetworkPacket<T>> createClientBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: (T, FriendlyByteBuf) -> Unit,
        decoder: (FriendlyByteBuf) -> T,
        handler: ClientNetworkPacketHandler<T>,
    ) {
        Cobblemon.implementation.networkManager.createClientBound(identifier, kClass, encoder, decoder, handler)
    }

    override fun <T : NetworkPacket<T>> createServerBound(
        identifier: ResourceLocation,
        kClass: KClass<T>,
        encoder: (T, FriendlyByteBuf) -> Unit,
        decoder: (FriendlyByteBuf) -> T,
        handler: ServerNetworkPacketHandler<T>,
    ) {
        Cobblemon.implementation.networkManager.createServerBound(identifier, kClass, encoder, decoder, handler)
    }

    override fun sendPacketToPlayer(player: ServerPlayer, packet: NetworkPacket<*>) = Cobblemon.implementation.networkManager.sendPacketToPlayer(player, packet)

    override fun sendPacketToServer(packet: NetworkPacket<*>) = Cobblemon.implementation.networkManager.sendPacketToServer(packet)

    override fun <T : NetworkPacket<*>> asVanillaClientBound(packet: T): Packet<ClientGamePacketListener> = Cobblemon.implementation.networkManager.asVanillaClientBound(packet)
}