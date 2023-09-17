package generations.gg.generations.core.generationscore.network.packets.npc;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
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

    public static class Handler implements ServerNetworkPacketHandler<C2SSetNpcPresetPacket> {
        @Override
        public void handle(C2SSetNpcPresetPacket packet, MinecraftServer server, ServerPlayer player) {
            PlayerNpcEntity npcEntity = (PlayerNpcEntity) player.level().getEntity(packet.entityId);
            if (npcEntity != null && player.hasPermissions(4)) {
                npcEntity.loadPreset(packet.preset);
            }
        }
    }
}
