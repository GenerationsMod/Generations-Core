package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

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
