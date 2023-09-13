package generations.gg.generations.core.generationscore.network;

import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.GenerationsImplementation;
import generations.gg.generations.core.generationscore.network.packets.*;
import generations.gg.generations.core.generationscore.network.packets.dialogue.*;
import generations.gg.generations.core.generationscore.network.packets.shop.C2SCloseShopPacket;
import generations.gg.generations.core.generationscore.network.packets.shop.C2SShopItemPacket;
import generations.gg.generations.core.generationscore.network.packets.shop.S2COpenShopPacket;
import generations.gg.generations.core.generationscore.network.packets.shop.S2CSyncPlayerMoneyPacket;
import generations.gg.generations.core.generationscore.network.packets.statue.C2SUpdateStatueInfoPacket;
import generations.gg.generations.core.generationscore.network.packets.statue.S2COpenStatueEditorScreenPacket;
import generations.gg.generations.core.generationscore.network.packets.statue.S2CUpdateStatueInfoPacket;
import generations.gg.generations.core.generationscore.world.dialogue.network.DialogueGraphRegistrySyncPacket;
import generations.gg.generations.core.generationscore.world.shop.ShopRegistrySyncPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.cobblemon.mod.common.util.DistributionUtilsKt.server;

public class GenerationsNetwork implements GenerationsImplementation.NetworkManager {
    public static final GenerationsNetwork INSTANCE = new GenerationsNetwork();
    public void sendToServer(GenerationsNetworkPacket<?> packet) {
        this.sendPacketToServer(packet);
    }

    public void sendToAllPlayers(GenerationsNetworkPacket<?> packet) {
        sendPacketToPlayers(server().getPlayerList().getPlayers(), packet);
    }
    public void sendPacketToPlayers(Iterable<ServerPlayer> players, GenerationsNetworkPacket<?> packet) {
        players.forEach(it -> sendPacketToPlayer(it, packet));
    }

    public void registerClientBound() {
        this.createClientBound(S2COpenMailEditScreenPacket.ID, S2COpenMailEditScreenPacket.class, S2COpenMailEditScreenPacket::decode, () -> S2COpenMailEditScreenPacket.Handler.INSTANCE);
        this.createClientBound(S2COpenMailPacket.ID, S2COpenMailPacket.class, S2COpenMailPacket::decode, S2COpenMailPacket.Handler::new);
        this.createClientBound(S2CChooseDialoguePacket.ID, S2CChooseDialoguePacket.class, S2CChooseDialoguePacket::decode, S2CChooseDialoguePacket.Handler::new);
        this.createClientBound(S2CHealDialoguePacket.ID, S2CHealDialoguePacket.class, S2CHealDialoguePacket::decode, S2CHealDialoguePacket.Handler::new);
        this.createClientBound(S2COpenDialogueEditorScreenPacket.ID, S2COpenDialogueEditorScreenPacket.class, S2COpenDialogueEditorScreenPacket::decode, S2COpenDialogueEditorScreenPacket.Handler::new);
        this.createClientBound(S2COpenDialogueMenuPacket.ID, S2COpenDialogueMenuPacket.class, S2COpenDialogueMenuPacket::decode, S2COpenDialogueMenuPacket.Handler::new);
        this.createClientBound(S2CSayDialoguePacket.ID, S2CSayDialoguePacket.class, S2CSayDialoguePacket::decode, S2CSayDialoguePacket.Handler::new);
        this.createClientBound(S2CCloseScreenPacket.ID, S2CCloseScreenPacket.class, S2CCloseScreenPacket::decode, S2CCloseScreenPacket.Handler::new);
        this.createClientBound(S2CUnlockReloadPacket.ID, S2CUnlockReloadPacket.class, S2CUnlockReloadPacket::decode, S2CUnlockReloadPacket.UnlockReloadPacketHandler::new);
        this.createClientBound(S2COpenStatueEditorScreenPacket.ID, S2COpenStatueEditorScreenPacket.class, S2COpenStatueEditorScreenPacket::decode, S2COpenStatueEditorScreenPacket.Handler::new);
        this.createClientBound(S2CUpdateStatueInfoPacket.ID, S2CUpdateStatueInfoPacket.class, S2CUpdateStatueInfoPacket::decode, S2CUpdateStatueInfoPacket.Handler::new);
        this.createClientBound(DialogueGraphRegistrySyncPacket.ID, DialogueGraphRegistrySyncPacket.class, DialogueGraphRegistrySyncPacket::decode, DataRegistrySyncPacketHandler::new);
        this.createClientBound(ShopRegistrySyncPacket.ID, ShopRegistrySyncPacket.class, ShopRegistrySyncPacket::decode, DataRegistrySyncPacketHandler::new);
        this.createClientBound(S2COpenShopPacket.ID, S2COpenShopPacket.class, S2COpenShopPacket::new, S2COpenShopPacket.Handler::new);
        this.createClientBound(S2CSyncPlayerMoneyPacket.ID, S2CSyncPlayerMoneyPacket.class, S2CSyncPlayerMoneyPacket::new, S2CSyncPlayerMoneyPacket.Handler::new);
    }

    public void registerServerBound() {
        this.createServerBound(C2SToggleCookingPotPacket.ID, C2SToggleCookingPotPacket.class, C2SToggleCookingPotPacket::decode, new C2SToggleCookingPotPacket.Handler());
        this.createServerBound(C2SEditMailPacket.ID, C2SEditMailPacket.class, C2SEditMailPacket::decode, new C2SEditMailPacket.Handler());
        this.createServerBound(C2SCloseDialoguePacket.ID, C2SCloseDialoguePacket.class, C2SCloseDialoguePacket::decode, new C2SCloseDialoguePacket.Handler());
        this.createServerBound(C2SRequestNodesDialoguePacket.ID, C2SRequestNodesDialoguePacket.class, C2SRequestNodesDialoguePacket::decode, new C2SRequestNodesDialoguePacket.Handler());
        this.createServerBound(C2SRespondDialoguePacket.ID, C2SRespondDialoguePacket.class, C2SRespondDialoguePacket::decode, new C2SRespondDialoguePacket.Handler());
        this.createServerBound(C2SSaveDatapackEntryPacket.ID, C2SSaveDatapackEntryPacket.class, C2SSaveDatapackEntryPacket::decode, new C2SSaveDatapackEntryPacket.Handler());
        this.createServerBound(C2SUpdateStatueInfoPacket.ID, C2SUpdateStatueInfoPacket.class, C2SUpdateStatueInfoPacket::decode, new C2SUpdateStatueInfoPacket.Handler());
        this.createServerBound(C2SCloseShopPacket.ID, C2SCloseShopPacket.class, C2SCloseShopPacket::new, new C2SCloseShopPacket.Handler());
        this.createServerBound(C2SShopItemPacket.ID, C2SShopItemPacket.class, C2SShopItemPacket::new, new C2SShopItemPacket.Handler());
    }

    private <T extends GenerationsNetworkPacket<T>> void createClientBound(ResourceLocation identifier, Class<T> kClass, Function<FriendlyByteBuf, T> decoder, Supplier<ClientNetworkPacketHandler<T>> handler) {
        createClientBound(identifier, kClass, T::encode, decoder, Platform.getEnvironment() == Env.CLIENT ? handler.get() : (packet) -> {});
    }

    private <T extends GenerationsNetworkPacket<T>> void createServerBound(ResourceLocation identifier, Class<T> kClass, Function<FriendlyByteBuf, T> decoder, ServerNetworkPacketHandler<T> handler) {
        createServerBound(identifier, kClass, T::encode, decoder, handler);
    }

    public <T extends GenerationsNetworkPacket<T>> void createClientBound(
            ResourceLocation identifier,
            Class<T> kClass,
            BiConsumer<T, FriendlyByteBuf> encoder,
            Function<FriendlyByteBuf, T> decoder,
            ClientNetworkPacketHandler<T> handler
    ) {
        GenerationsCore.implementation.getNetworkManager().createClientBound(identifier, kClass, encoder, decoder, handler);
    }

    public <T extends GenerationsNetworkPacket<T>> void createServerBound(
            ResourceLocation identifier,
            Class<T> kClass,
            BiConsumer<T, FriendlyByteBuf> encoder,
            Function<FriendlyByteBuf, T> decoder,
            generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler<T> handler
    ) {
        GenerationsCore.implementation.getNetworkManager().createServerBound(identifier, kClass, encoder, decoder, handler);
    }

    public void sendPacketToPlayer(ServerPlayer player, GenerationsNetworkPacket<?> packet) {
        GenerationsCore.implementation.getNetworkManager().sendPacketToPlayer(player, packet);
    }

    public void sendPacketToServer(GenerationsNetworkPacket<?> packet) {
        GenerationsCore.implementation.getNetworkManager().sendPacketToServer(packet);
    }

    @Override
    public <T extends GenerationsNetworkPacket<?>, V extends Entity> void sendToAllTracking(T packet, V entity) {
        GenerationsCore.implementation.getNetworkManager().sendToAllTracking(packet, entity);
    }

    public <T extends GenerationsNetworkPacket<?>> Packet<ClientGamePacketListener> asVanillaClientBound(T packet) {
        return GenerationsCore.implementation.getNetworkManager().asVanillaClientBound(packet);
    }
}