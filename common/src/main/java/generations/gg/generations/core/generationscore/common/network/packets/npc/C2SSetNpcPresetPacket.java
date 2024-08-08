package generations.gg.generations.core.generationscore.common.network.packets.npc;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record C2SSetNpcPresetPacket(int entityId, ResourceLocation preset) implements GenerationsNetworkPacket<C2SSetNpcPresetPacket> {
    public static final ResourceLocation ID = GenerationsCore.id(("set_npc_preset"));

    public C2SSetNpcPresetPacket(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readResourceLocation());
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(entityId);
        friendlyByteBuf.writeResourceLocation(preset);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}
