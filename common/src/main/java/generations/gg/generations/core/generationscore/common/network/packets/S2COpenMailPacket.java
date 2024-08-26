package generations.gg.generations.core.generationscore.common.network.packets;

import generations.gg.generations.core.generationscore.common.client.screen.mails.MailEditScreen;
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen;
import generations.gg.generations.core.generationscore.common.client.screen.mails.MailViewScreen.WrittenMailAccess;
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;

import static generations.gg.generations.core.generationscore.common.GenerationsCore.id;

public record S2COpenMailPacket(InteractionHand hand) implements GenerationsNetworkPacket<S2COpenMailPacket> {
    public ResourceLocation getId() {
        return ID;
    }
    public void encode(FriendlyByteBuf buf) {
        buf.writeEnum(hand);
    }

    public static ResourceLocation ID = id("open_mail");
    public static S2COpenMailPacket decode(FriendlyByteBuf buf) {
        return new S2COpenMailPacket(buf.readEnum(InteractionHand.class));
    }

    public static class Handler implements ClientNetworkPacketHandler<S2COpenMailPacket> {
        public static final Handler INSTANCE = new Handler();
        public void handle(S2COpenMailPacket packet, Minecraft minecraft) {
            var itemStack = Minecraft.getInstance().player.getItemInHand(packet.hand());
            if (itemStack.is(GenerationsItemTags.CLOSED_POKEMAIL)) Minecraft.getInstance().setScreen(
                    new MailViewScreen(
                            new WrittenMailAccess(itemStack)));
            else if (itemStack.is(GenerationsItemTags.POKEMAIL)) Minecraft.getInstance().setScreen(
                    new MailEditScreen(
                            Minecraft.getInstance().player,
                        itemStack,
                        packet.hand)
            );
        }
    }
}