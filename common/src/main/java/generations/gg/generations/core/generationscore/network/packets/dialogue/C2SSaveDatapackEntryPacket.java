package generations.gg.generations.core.generationscore.network.packets.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public record C2SSaveDatapackEntryPacket(ResourceLocation location, String data) implements GenerationsNetworkPacket<C2SSaveDatapackEntryPacket> {
    public C2SSaveDatapackEntryPacket(ResourceLocation location, JsonElement object) {
        this(location, GSON.toJson(object));
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeResourceLocation(location);
        buf.writeUtf(data);
    }

    public static ResourceLocation ID = GenerationsCore.id("save_datapack_entry");
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static C2SSaveDatapackEntryPacket decode(FriendlyByteBuf buf) {
            return new C2SSaveDatapackEntryPacket(buf.readResourceLocation(), GSON.toJsonTree(buf.readUtf()));
        }

    public static class Handler implements ServerNetworkPacketHandler<C2SSaveDatapackEntryPacket> {

        @Override
        public void handle(C2SSaveDatapackEntryPacket packet, MinecraftServer server, ServerPlayer player) {
//            TODO("Not yet implemented"); //TODO Implement datapack updating.
        }
    }
}

