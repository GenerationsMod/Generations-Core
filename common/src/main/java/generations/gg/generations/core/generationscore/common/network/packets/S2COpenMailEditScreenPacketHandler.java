package generations.gg.generations.core.generationscore.common.network.packets;

import generations.gg.generations.core.generationscore.common.client.screen.mails.MailEditScreen;
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags;
import net.minecraft.client.Minecraft;

public class S2COpenMailEditScreenPacketHandler implements ClientNetworkPacketHandler<S2COpenMailEditScreenPacket> {
    public void handle(S2COpenMailEditScreenPacket packet, Minecraft minecraft) {
        var itemStack = Minecraft.getInstance().player.getItemInHand(packet.hand());
        if (itemStack.is(GenerationsItemTags.CLOSED_POKEMAIL))
            Minecraft.getInstance().setScreen(new MailViewScreen(new MailViewScreen.WrittenMailAccess(itemStack)));
        else if (itemStack.is(GenerationsItemTags.POKEMAIL)) Minecraft.getInstance().setScreen(
                new MailEditScreen(
                        Minecraft.getInstance().player, itemStack,
                        packet.hand())
        );
    }
}
