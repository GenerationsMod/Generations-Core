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

public class C2SUpdateNpcDialoguePacket implements GenerationsNetworkPacket<C2SUpdateNpcDialoguePacket> {
    public static final ResourceLocation ID = GenerationsCore.id("update_npc_dialogue");

    private final int entityId;
    @Nullable
    private final ResourceLocation dialogue;

    public C2SUpdateNpcDialoguePacket(int entityId, @Nullable ResourceLocation dialogue) {
        this.entityId = entityId;
        this.dialogue = dialogue;
    }

    public C2SUpdateNpcDialoguePacket(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.dialogue = buf.readBoolean() ? null : buf.readResourceLocation();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        boolean isDialogueNull = this.dialogue == null;
        if (!isDialogueNull)
            buf.writeResourceLocation(this.dialogue);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ServerNetworkPacketHandler<C2SUpdateNpcDialoguePacket> {
        @Override
        public void handle(C2SUpdateNpcDialoguePacket packet, MinecraftServer server, ServerPlayer player) {
            PlayerNpcEntity npcEntity = (PlayerNpcEntity) player.level().getEntity(packet.entityId);
            if (npcEntity != null) {
                npcEntity.setDialogue(packet.dialogue);
            }
        }
    }
}