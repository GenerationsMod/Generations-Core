package generations.gg.generations.core.generationscore;

import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

public interface GenerationsImplementation {
    NetworkManager getNetworkManager();

    void registerResourceReloader(ResourceLocation identifier, PreparableReloadListener reloader, PackType type, Collection<ResourceLocation> dependencies);

    public enum ModAPI {
        FABRIC,
        FORGE
    }

    public interface NetworkManager {

        void registerClientBound();

        void registerServerBound();

        <T extends GenerationsNetworkPacket<T>> void createClientBound(ResourceLocation identifier, Class<T> kClass, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, ClientNetworkPacketHandler<T> handler);

        <T extends GenerationsNetworkPacket<T>> void createServerBound(ResourceLocation identifier, Class<T> kClass, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, ServerNetworkPacketHandler<T> handler);

        void sendPacketToPlayer(ServerPlayer player, GenerationsNetworkPacket<?> packet);

        void sendPacketToServer(GenerationsNetworkPacket<?> packet);

        <T extends GenerationsNetworkPacket<?>> Packet<ClientGamePacketListener> asVanillaClientBound(T packet);

    }

    public enum Environment {
        CLIENT,
        SERVER

    }

}
