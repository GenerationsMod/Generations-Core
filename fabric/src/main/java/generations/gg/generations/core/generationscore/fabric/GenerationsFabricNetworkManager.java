package generations.gg.generations.core.generationscore.fabric;

import generations.gg.generations.core.generationscore.GenerationsImplementation;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class GenerationsFabricNetworkManager implements GenerationsImplementation.NetworkManager {
    public static final GenerationsFabricNetworkManager INSTANCE = new GenerationsFabricNetworkManager();

    public void registerClientBound() {
        GenerationsNetwork.INSTANCE.registerClientBound();
    }

    public void registerServerBound() {
        GenerationsNetwork.INSTANCE.registerServerBound();
    }

    @Override
    public <T extends GenerationsNetworkPacket<T>> void createClientBound(ResourceLocation identifier, Class<T> kClass, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, ClientNetworkPacketHandler<T> handler) {
        ClientPlayNetworking.registerGlobalReceiver(identifier, this.createClientBoundHandler(decoder, (msg, a) -> handler.handleOnNettyThread(msg)));
    }

    @Override
    public <T extends GenerationsNetworkPacket<T>> void createServerBound(ResourceLocation identifier, Class<T> kClass, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, ServerNetworkPacketHandler<T> handler) {
        ServerPlayNetworking.registerGlobalReceiver(identifier, this.createServerBoundHandler(decoder, handler::handleOnNettyThread));
    }

    private <T extends GenerationsNetworkPacket<?>> ServerPlayNetworking.PlayChannelHandler createServerBoundHandler(
            Function<FriendlyByteBuf, T> decoder,
            TriConsumer<T, MinecraftServer, ServerPlayer> handler) {
        return (server, player, listner, buffer, sender) -> handler.accept(decoder.apply(buffer), server, player);
    }

    private <T extends GenerationsNetworkPacket<?>> ClientPlayNetworking.PlayChannelHandler createClientBoundHandler(
        Function<FriendlyByteBuf, T> decoder,
        BiConsumer<T, Minecraft> handler
    ) {
        return (client, a,  buffer, b) -> handler.accept(decoder.apply(buffer), client);
    }

    @Override
    public void sendPacketToPlayer(ServerPlayer player, GenerationsNetworkPacket<?> packet) {
        ServerPlayNetworking.send(player, packet.getId(), packet.toBuffer());
    }

    @Override
    public void sendPacketToServer(GenerationsNetworkPacket<?> packet) {
        ClientPlayNetworking.send(packet.getId(), packet.toBuffer());
    }

    @Override
    public <T extends GenerationsNetworkPacket<?>> Packet<ClientGamePacketListener> asVanillaClientBound(T packet) {
        return ServerPlayNetworking.createS2CPacket(packet.getId(), packet.toBuffer());
    }

    @Override
    public <T extends GenerationsNetworkPacket<?>, V extends Entity> void sendToAllTracking(T packet, V entity) {
        PlayerLookup.tracking(entity).forEach(player -> sendPacketToPlayer(player, packet));
    }
}