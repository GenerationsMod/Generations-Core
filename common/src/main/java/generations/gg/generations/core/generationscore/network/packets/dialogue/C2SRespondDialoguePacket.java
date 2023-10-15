package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.ResponseTakingNode;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;


public record C2SRespondDialoguePacket(String stringResponse) implements GenerationsNetworkPacket<C2SRespondDialoguePacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeUtf(stringResponse);
    }

    public static final ResourceLocation ID = id("respond_dialogue");
    public static C2SRespondDialoguePacket decode(FriendlyByteBuf buf) {
        return new C2SRespondDialoguePacket(buf.readUtf());
    }

}
