package generations.gg.generations.core.generationscore.common.network.packets.dialogue;

import generations.gg.generations.core.generationscore.common.client.screen.dialgoue.display.DialogueScreen;
import generations.gg.generations.core.generationscore.common.client.screen.dialgoue.display.DialogueScreen;
import generations.gg.generations.core.generationscore.common.client.screen.dialgoue.display.DialogueScreen;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import net.minecraft.client.Minecraft;

public class S2CChooseDialogueHandler implements ClientNetworkPacketHandler<S2CChooseDialoguePacket> {
    public static final S2CChooseDialogueHandler INSTANCE = new S2CChooseDialogueHandler();

    @Override
    public void handle(S2CChooseDialoguePacket packet) {
        if (Minecraft.getInstance().screen instanceof DialogueScreen dialogueScreen) {
            dialogueScreen.activeInfo = new DialogueScreen.ChooseActiveInfo(packet.text(), packet.options());
            dialogueScreen.init(
                    Minecraft.getInstance(),
                    dialogueScreen.width,
                    dialogueScreen.height
            ); // reinitialize to get the options widget to pop up
        }
    }

}
