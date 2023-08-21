package generations.gg.generations.core.generationscore.forge;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.GenerationsImplementation;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class GenerationsForgeNetworkManager implements GenerationsImplementation.NetworkManager {
    public static final GenerationsForgeNetworkManager INSTANCE = new GenerationsForgeNetworkManager();

    private static final String PROTOCOL_VERSION = "1";
    private int id = 0;

    private SimpleChannel channel = NetworkRegistry.newSimpleChannel(
            GenerationsCore.id("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public void registerClientBound() {
        GenerationsNetwork.INSTANCE.registerClientBound();
    }

    public void registerServerBound() {
        GenerationsNetwork.INSTANCE.registerServerBound();
    }

    public <T extends GenerationsNetworkPacket<T>> void createClientBound(
            ResourceLocation identifier,
            Class<T> kClass,
            BiConsumer<T, FriendlyByteBuf> encoder,
            Function<FriendlyByteBuf, T> decoder,
            ClientNetworkPacketHandler<T> handler
    ) {
        this.channel.registerMessage(this.id++, kClass, encoder, decoder, (msg, ctx) -> {
            var context = ctx.get();
            handler.handleOnNettyThread(msg);
            context.setPacketHandled(true);
        });
    }

    public <T extends GenerationsNetworkPacket<T>> void createServerBound(
            ResourceLocation identifier,
            Class<T> kClass,
            BiConsumer<T, FriendlyByteBuf> encoder,
            Function<FriendlyByteBuf, T> decoder,
            ServerNetworkPacketHandler<T> handler
    ) {
        this.channel.registerMessage(this.id++, kClass, encoder, decoder, (msg, ctx) -> {
            var context = ctx.get();
            handler.handleOnNettyThread(msg, Objects.requireNonNull(Objects.requireNonNull(context.getSender()).getServer()), context.getSender());
            context.setPacketHandled(true);
        });
    }

    public void sendPacketToPlayer(@NotNull ServerPlayer player, @NotNull GenerationsNetworkPacket<?> packet) {
        this.channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public void sendPacketToServer(GenerationsNetworkPacket<?> packet) {
        this.channel.sendToServer(packet);
    }

    public <T extends GenerationsNetworkPacket<?>> @NotNull Packet<ClientGamePacketListener> asVanillaClientBound(T packet) {
        return (Packet<ClientGamePacketListener>) this.channel.toVanillaPacket(packet, NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public <T extends GenerationsNetworkPacket<?>, V extends Entity> void sendToAllTracking(T packet, V entity) {
        this.channel.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), packet);
    }
}