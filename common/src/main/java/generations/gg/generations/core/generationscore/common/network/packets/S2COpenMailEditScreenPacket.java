package generations.gg.generations.core.generationscore.common.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.common.GenerationsCore.id;

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

    public static final ResourceLocation ID = id("open_mail_edit_screen");
    public static S2COpenMailEditScreenPacket decode(FriendlyByteBuf buf) {
        return new S2COpenMailEditScreenPacket(buf.readEnum(InteractionHand.class));
    }
}