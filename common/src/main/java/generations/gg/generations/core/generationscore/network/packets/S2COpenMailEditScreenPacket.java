package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.client.screen.mails.MailEditScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen.WrittenMailAccess;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;

public record S2COpenMailEditScreenPacket(InteractionHand hand) implements GenerationsNetworkPacket<S2COpenMailEditScreenPacket> {
    @NotNull
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeEnum(hand);
    }

    public static class Handler implements ClientNetworkPacketHandler<S2COpenMailEditScreenPacket> {
        public final static Handler INSTANCE = new Handler();

        public void handle(S2COpenMailEditScreenPacket packet) {
            var itemStack = Minecraft.getInstance().player.getItemInHand(packet.hand());
            if (itemStack.is(GenerationsItemTags.CLOSED_POKEMAIL))
                Minecraft.getInstance().setScreen(new MailViewScreen(new WrittenMailAccess(itemStack)));
            else if (itemStack.is(GenerationsItemTags.POKEMAIL)) Minecraft.getInstance().setScreen(
                    new MailEditScreen(
                            Minecraft.getInstance().player, itemStack,
                            packet.hand)
            );
        }
    }

    public static final ResourceLocation ID = id("open_mail_edit_screen");
    public static S2COpenMailEditScreenPacket decode(FriendlyByteBuf buf) {
        return new S2COpenMailEditScreenPacket(buf.readEnum(InteractionHand.class));
    }
}