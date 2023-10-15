package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SCloseDialogueHandler implements ServerNetworkPacketHandler<C2SCloseDialoguePacket> {
    @Override
    public void handle(C2SCloseDialoguePacket packet, MinecraftServer server, ServerPlayer player) {
        if (DialogueManager.DIALOGUE_MAP.containsKey(player)) DialogueManager.DIALOGUE_MAP.get(player).discard();
    }

}
