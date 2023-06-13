package generations.gg.generations.core.generationscore.network.packets;

import dev.architectury.networking.NetworkManager;
import generations.gg.generations.core.generationscore.client.screen.mails.MailEditScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;

import java.util.function.Supplier;

public record S2COpenMailEditScreenPacket(InteractionHand hand) {

    public S2COpenMailEditScreenPacket(FriendlyByteBuf buf) {
        this(buf.readEnum(InteractionHand.class));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeEnum(hand);
    }
}