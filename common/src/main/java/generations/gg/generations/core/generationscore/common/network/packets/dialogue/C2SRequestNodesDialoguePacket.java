package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import static generations.gg.generations.core.generationscore.common.GenerationsCore.id;

public class C2SRequestNodesDialoguePacket implements GenerationsNetworkPacket<C2SRequestNodesDialoguePacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void encode(FriendlyByteBuf buf) {}

    public static ResourceLocation ID = GenerationsCore.id("request_nodes_dialogue");
    public static C2SRequestNodesDialoguePacket decode(FriendlyByteBuf buffer) {
        return new C2SRequestNodesDialoguePacket();
    }


}
