package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;

public class C2SRequestNodesDialoguePacket implements GenerationsNetworkPacket<C2SRequestNodesDialoguePacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void encode(FriendlyByteBuf buf) {}

    public static ResourceLocation ID = id("request_nodes_dialogue");
    public static C2SRequestNodesDialoguePacket decode(FriendlyByteBuf buffer) {
        return new C2SRequestNodesDialoguePacket();
    }


}
