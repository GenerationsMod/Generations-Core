package generations.gg.generations.core.generationscore.common.network.packets.shop;

import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialoguePlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SCloseShopHandler implements ServerNetworkPacketHandler<C2SCloseShopPacket> {
    @Override
    public void handle(C2SCloseShopPacket packet, MinecraftServer server, ServerPlayer player) {
        ServerPlayer sender = player;
        DialoguePlayer dialoguePlayer = DialogueManager.DIALOGUE_MAP.get(sender);
        if (dialoguePlayer == null)
            return;

        dialoguePlayer.openAndNextNode();
    }
}
