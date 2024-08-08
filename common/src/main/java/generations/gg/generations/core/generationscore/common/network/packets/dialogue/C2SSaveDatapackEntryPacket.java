package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
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

}

