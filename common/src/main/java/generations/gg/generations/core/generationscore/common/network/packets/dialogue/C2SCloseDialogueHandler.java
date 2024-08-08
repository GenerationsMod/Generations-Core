package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SCloseDialogueHandler implements ServerNetworkPacketHandler<C2SCloseDialoguePacket> {
    @Override
    public void handle(C2SCloseDialoguePacket packet, MinecraftServer server, ServerPlayer player) {
        if (DialogueManager.DIALOGUE_MAP.containsKey(player)) DialogueManager.DIALOGUE_MAP.remove(player).discard();
    }

}
