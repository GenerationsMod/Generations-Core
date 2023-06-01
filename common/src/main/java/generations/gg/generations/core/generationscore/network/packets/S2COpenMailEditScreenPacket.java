package generations.gg.generations.core.generationscore.network.packets;

import dev.architectury.networking.NetworkManager;
import generations.gg.generations.core.generationscore.client.screen.mails.MailEditScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;

import java.util.function.Supplier;

public class S2COpenMailEditScreenPacket {

    private final InteractionHand hand;

    public S2COpenMailEditScreenPacket(FriendlyByteBuf buf) {
        this.hand = buf.readEnum(InteractionHand.class);
    }

    public S2COpenMailEditScreenPacket(InteractionHand hand) {
        this.hand = hand;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeEnum(hand);
    }

    public void process(Supplier<NetworkManager.PacketContext> ctx) {
        var client = Minecraft.getInstance();
        var itemStack = ctx.get().getPlayer().getItemInHand(hand);
        if (itemStack.is(GenerationsItemTags.CLOSED_POKEMAIL))
            client.setScreen(new MailViewScreen(new MailViewScreen.WrittenMailAccess(itemStack)));
        else if (itemStack.is(GenerationsItemTags.POKEMAIL))
            client.setScreen(new MailEditScreen(client.player, itemStack, hand));    }
}