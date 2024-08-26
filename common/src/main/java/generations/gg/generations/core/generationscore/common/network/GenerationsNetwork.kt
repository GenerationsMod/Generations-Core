package generations.gg.generations.core.generationscore.common.network

import com.cobblemon.mod.common.util.server
import dev.architectury.platform.Platform
import dev.architectury.utils.Env
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsImplementation
import generations.gg.generations.core.generationscore.common.network.packets.*
import generations.gg.generations.core.generationscore.common.network.packets.dialogue.*
import generations.gg.generations.core.generationscore.common.network.packets.npc.*
import generations.gg.generations.core.generationscore.common.network.packets.shop.*
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenHandler
import generations.gg.generations.core.generationscore.common.network.packets.statue.S2COpenStatueEditorScreenPacket
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatueHandler
import generations.gg.generations.core.generationscore.common.network.packets.statue.UpdateStatuePacket
import generations.gg.generations.core.generationscore.common.network.spawn.SpawnExtraDataEntityHandler
import generations.gg.generations.core.generationscore.common.network.spawn.SpawnStatuePacket
import generations.gg.generations.core.generationscore.common.world.dialogue.network.DialogueGraphRegistrySyncPacket
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

object GenerationsNetwork : GenerationsImplementation.NetworkManager {
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
        this.createClientBound(S2COpenMailEditScreenPacket.ID, S2COpenMailEditScreenPacket::class.java, S2COpenMailEditScreenPacket::decode) { ::S2COpenMailEditScreenPacketHandler }
        this.createClientBound(S2COpenMailPacket.ID, S2COpenMailPacket::class.java, S2COpenMailPacket::decode) { S2COpenMailPacket::Handler }
        this.createClientBound(S2CChooseDialoguePacket.ID, S2CChooseDialoguePacket::class.java, S2CChooseDialoguePacket::decode) { ::S2CChooseDialogueHandler }
        this.createClientBound(S2CHealDialoguePacket.ID, S2CHealDialoguePacket::class.java, S2CHealDialoguePacket::decode) { S2CHealDialoguePacket::Handler }
        this.createClientBound(S2COpenDialogueEditorScreenPacket.ID, S2COpenDialogueEditorScreenPacket::class.java, S2COpenDialogueEditorScreenPacket::decode) { ::S2COpenDialogueEditorScreenHandler }
        this.createClientBound(S2COpenDialogueMenuPacket.ID, S2COpenDialogueMenuPacket::class.java, S2COpenDialogueMenuPacket::decode) { ::S2COpenDialogueMenuHandler }
        this.createClientBound(S2CSayDialoguePacket.ID, S2CSayDialoguePacket::class.java, S2CSayDialoguePacket::decode) { ::S2CSayDialogueHandler }
        this.createClientBound(S2CCloseScreenPacket.ID, S2CCloseScreenPacket::class.java, S2CCloseScreenPacket::decode) { ::S2CCloseScreenHandler }
        this.createClientBound(S2CUnlockReloadPacket.ID, S2CUnlockReloadPacket::class.java, S2CUnlockReloadPacket::decode) { ::UnlockReloadPacketHandler }
        this.createClientBound(S2COpenStatueEditorScreenPacket.ID, S2COpenStatueEditorScreenPacket::class.java, S2COpenStatueEditorScreenPacket::decode) { ::S2COpenStatueEditorScreenHandler }
        //        this.createClientBound(S2CUpdateStatueInfoPacket.ID, S2CUpdateStatueInfoPacket.class, S2CUpdateStatueInfoPacket::decode, () -> S2CUpdateStatueInfoHandler::new);
        this.createClientBound(DialogueGraphRegistrySyncPacket.ID, DialogueGraphRegistrySyncPacket::class.java, DialogueGraphRegistrySyncPacket::decode) { { DataRegistrySyncPacketHandler() } }
        this.createClientBound(ShopRegistrySyncPacket.ID, ShopRegistrySyncPacket::class.java, ShopRegistrySyncPacket::decode) { { DataRegistrySyncPacketHandler() } }
        this.createClientBound(ShopPresetRegistrySyncPacket.ID, ShopPresetRegistrySyncPacket::class.java, ShopPresetRegistrySyncPacket::decode) { { DataRegistrySyncPacketHandler() } }
        this.createClientBound(NpcPresetsRegistrySyncPacket.ID, NpcPresetsRegistrySyncPacket::class.java, NpcPresetsRegistrySyncPacket::decode) { { DataRegistrySyncPacketHandler() } }
        this.createClientBound(S2COpenShopPacket.ID, S2COpenShopPacket::class.java, S2COpenShopPacket::decode) { ::S2COpenShopHandler }
        this.createClientBound(S2CSyncPlayerMoneyPacket.ID, S2CSyncPlayerMoneyPacket::class.java, ::S2CSyncPlayerMoneyPacket) { ::S2CSyncPlayerMoneyHandler }
        this.createClientBound(S2COpenNpcCustomizationScreenPacket.ID, S2COpenNpcCustomizationScreenPacket::class.java, ::S2COpenNpcCustomizationScreenPacket) { ::S2COpenNpcCustomizationScreenHandler }
        this.createClientBound(S2CUpdateNpcDisplayDataPacket.ID, S2CUpdateNpcDisplayDataPacket::class.java, ::S2CUpdateNpcDisplayDataPacket) { ::S2CUpdateNpcDisplayDataHandler }
        this.createClientBound(SpawnStatuePacket.ID, SpawnStatuePacket::class.java, SpawnStatuePacket::decode) { ::SpawnExtraDataEntityHandler }
    }

    override fun registerServerBound() {
        this.createServerBound(C2STogglePacket.ID, C2STogglePacket::class.java, C2STogglePacket::decode, C2SToggleHandler())
        this.createServerBound(C2SEditMailPacket.ID, C2SEditMailPacket::class.java, C2SEditMailPacket::decode, C2SEditMailHandler())
        this.createServerBound(C2SCloseDialoguePacket.ID, C2SCloseDialoguePacket::class.java, C2SCloseDialoguePacket::decode, C2SCloseDialogueHandler())
        this.createServerBound(C2SRequestNodesDialoguePacket.ID, C2SRequestNodesDialoguePacket::class.java, C2SRequestNodesDialoguePacket::decode, C2SRequestNodesDialogueHandler())
        this.createServerBound(C2SRespondDialoguePacket.ID, C2SRespondDialoguePacket::class.java, C2SRespondDialoguePacket::decode, C2SRespondDialogueHandler())
        this.createServerBound(C2SSaveDatapackEntryPacket.ID, C2SSaveDatapackEntryPacket::class.java, C2SSaveDatapackEntryPacket::decode, C2SSaveDatapackEntryHandler())
        this.createServerBound(C2SCloseShopPacket.ID, C2SCloseShopPacket::class.java, ::C2SCloseShopPacket, C2SCloseShopHandler())
        this.createServerBound(C2SShopItemPacket.ID, C2SShopItemPacket::class.java, C2SShopItemPacket::decode, C2SShopItemHandler())
        this.createServerBound(C2SUpdateNpcDialoguePacket.ID, C2SUpdateNpcDialoguePacket::class.java, ::C2SUpdateNpcDialoguePacket, C2SUpdateNpcDialogueHandler())
        this.createServerBound(C2SUpdateNpcDisplayDataPacket.ID, C2SUpdateNpcDisplayDataPacket::class.java, ::C2SUpdateNpcDisplayDataPacket, C2SUpdateNpcDisplayDataHandler())
        this.createServerBound(C2SUpdateNpcShopPacket.ID, C2SUpdateNpcShopPacket::class.java, ::C2SUpdateNpcShopPacket, C2SUpdateNpcShopHandler())
        this.createServerBound(C2SSetNpcPresetPacket.ID, C2SSetNpcPresetPacket::class.java, ::C2SSetNpcPresetPacket, C2SSetNpcPresetHandler())
        this.createServerBound(HeadPatPacket.ID, HeadPatPacket::class.java, ::HeadPatPacket, HeadPatPacketHandler())

        createServerStatueUpdate()
    }

    private fun createServerStatueUpdate() {
        this.createServerBound(UpdateStatuePacket.PROPERTIES_ID, UpdateStatuePacket.Properties::class.java, UpdateStatuePacket.Companion::propertiesDecode, UpdateStatueHandler.Properties)
        this.createServerBound(UpdateStatuePacket.LABEL_ID, UpdateStatuePacket.Label::class.java, UpdateStatuePacket.Companion::labelDecode, UpdateStatueHandler.Label)
        this.createServerBound(UpdateStatuePacket.SCALE_ID, UpdateStatuePacket.Scale::class.java, UpdateStatuePacket.Companion::scaleDecode, UpdateStatueHandler.Scale)
        this.createServerBound(UpdateStatuePacket.POSE_TYPE_ID, UpdateStatuePacket.PoseType::class.java, UpdateStatuePacket.Companion::poseTypeDecode, UpdateStatueHandler.PoseType)
        this.createServerBound(UpdateStatuePacket.STATIC_TOGGLE_ID, UpdateStatuePacket.StaticToggle::class.java, UpdateStatuePacket.Companion::staticToggleDecode, UpdateStatueHandler.StaticToggle)
        this.createServerBound(UpdateStatuePacket.STATIC_PARTIAL_ID, UpdateStatuePacket.StaticPartial::class.java, UpdateStatuePacket.Companion::staticPartialDecode, UpdateStatueHandler.StaticPartial)
        this.createServerBound(UpdateStatuePacket.STATIC_AGE_ID, UpdateStatuePacket.StaticAge::class.java, UpdateStatuePacket.Companion::staticAgeDecode, UpdateStatueHandler.StaticAge)
        this.createServerBound(UpdateStatuePacket.INTERACTABLE_ID, UpdateStatuePacket.Interactable::class.java, UpdateStatuePacket.Companion::interactableDecode, UpdateStatueHandler.Interactable)
        this.createServerBound(UpdateStatuePacket.MATERIAL_ID, UpdateStatuePacket.Material::class.java, UpdateStatuePacket.Companion::materialDecode, UpdateStatueHandler.Material)
        this.createServerBound(UpdateStatuePacket.ORIENTATION_ID, UpdateStatuePacket.Orientation::class.java, UpdateStatuePacket.Companion::orientationDecode, UpdateStatueHandler.Orientation)
    }

    private fun <T : GenerationsNetworkPacket<T>> createClientBound(
        identifier: ResourceLocation,
        kClass: Class<T>,
        decoder: (FriendlyByteBuf) -> T,
        handler: () -> () -> ClientNetworkPacketHandler<T>) {
        createClientBound(
            identifier,
            kClass,
            { obj: T, buffer: FriendlyByteBuf -> obj.encode(buffer) },
            decoder,
            if (Platform.getEnvironment() == Env.CLIENT) handler.invoke().invoke() else ClientNetworkPacketHandler { _, _ -> })
    }

    private fun <T : GenerationsNetworkPacket<T>> createServerBound(
        identifier: ResourceLocation,
        kClass: Class<T>,
        decoder: (FriendlyByteBuf) -> T,
        handler: ServerNetworkPacketHandler<T>
    ) {
        createServerBound(identifier, kClass, { obj: T, buffer: FriendlyByteBuf -> obj.encode(buffer) }, decoder, handler)
    }

    override fun <T : GenerationsNetworkPacket<T>> createClientBound(
        identifier: ResourceLocation,
        kClass: Class<T>,
        encoder: BiConsumer<T, FriendlyByteBuf>,
        decoder: Function<FriendlyByteBuf, T>,
        handler: ClientNetworkPacketHandler<T>
    ) {
        GenerationsCore.implementation.networkManager.createClientBound(identifier, kClass, encoder, decoder, handler)
    }

    override fun <T : GenerationsNetworkPacket<T>?> createServerBound(
        identifier: ResourceLocation,
        kClass: Class<T>,
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