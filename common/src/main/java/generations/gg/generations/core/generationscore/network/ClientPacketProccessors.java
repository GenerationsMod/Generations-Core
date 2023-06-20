package generations.gg.generations.core.generationscore.network;

import dev.architectury.networking.NetworkManager;
import generations.gg.generations.core.generationscore.client.screen.mails.MailEditScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailEditScreenPacket;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailPacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.C2SCloseDialoguePacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CSayDialoguePacket;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class ClientPacketProccessors extends PacketProccessors {
    public void process(S2COpenMailEditScreenPacket packet, Supplier<NetworkManager.PacketContext> ctx) {
        ctx.get().queue(() -> {
            var client = Minecraft.getInstance();
            var itemStack = ctx.get().getPlayer().getItemInHand(packet.hand());
            if (itemStack.is(GenerationsItemTags.CLOSED_POKEMAIL))
                client.setScreen(new MailViewScreen(new MailViewScreen.WrittenMailAccess(itemStack)));
            else if (itemStack.is(GenerationsItemTags.POKEMAIL))
                client.setScreen(new MailEditScreen(client.player, itemStack, packet.hand()));
        });
    }

    public void process(S2COpenMailPacket packet, Supplier<NetworkManager.PacketContext> ctx) {
        ctx.get().queue(() -> {
            var client = Minecraft.getInstance();
            var itemStack = client.player.getItemInHand(packet.hand());
            if (itemStack.is(GenerationsItemTags.CLOSED_POKEMAIL))
                client.setScreen(new MailViewScreen(new MailViewScreen.WrittenMailAccess(itemStack)));
            else if (itemStack.is(GenerationsItemTags.POKEMAIL))
                client.setScreen(new MailEditScreen(client.player, itemStack, packet.hand()));
        });
    }

    @Override
    void process(S2CSayDialoguePacket packet, Supplier<NetworkManager.PacketContext> ctx) {
        if (Minecraft.getInstance().screen instanceof DialogueScreen dialogueScreen)
            dialogueScreen.activeInfo = new DialogueScreen.SayActiveInfo(text, showArrow);
    }
}
