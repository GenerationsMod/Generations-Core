package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.ResponseTakingNode;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.ResponseTakingNode;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.ResponseTakingNode;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SRespondDialogueHandler implements ServerNetworkPacketHandler<C2SRespondDialoguePacket> {
    @Override
    public void handle(C2SRespondDialoguePacket packet, MinecraftServer server, ServerPlayer player) {
        var dialoguePlayer = DialogueManager.DIALOGUE_MAP.get(player);
        var node = dialoguePlayer != null ? dialoguePlayer.currentNode : null;

        if (dialoguePlayer != null && node instanceof ResponseTakingNode responseTakingNode) {
            responseTakingNode.clientResponse(packet.stringResponse());
        }
    }
}
