package generations.gg.generations.core.generationscore.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;

public record S2COpenMailEditScreenPacket(InteractionHand hand) {

    public S2COpenMailEditScreenPacket(FriendlyByteBuf buf) {
        this(buf.readEnum(InteractionHand.class));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeEnum(hand);
    }
}