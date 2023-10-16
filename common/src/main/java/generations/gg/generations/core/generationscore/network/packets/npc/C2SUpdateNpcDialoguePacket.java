package generations.gg.generations.core.generationscore.network.packets.npc;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public record C2SUpdateNpcDialoguePacket(int entityId, @Nullable ResourceLocation dialogue) implements GenerationsNetworkPacket<C2SUpdateNpcDialoguePacket> {
    public static final ResourceLocation ID = GenerationsCore.id("update_npc_dialogue");

    public C2SUpdateNpcDialoguePacket(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readNullable(FriendlyByteBuf::readResourceLocation));
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeNullable(this.dialogue, FriendlyByteBuf::writeResourceLocation);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}