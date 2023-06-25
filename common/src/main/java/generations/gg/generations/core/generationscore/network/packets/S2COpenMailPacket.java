package generations.gg.generations.core.generationscore.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;

public record S2COpenMailPacket(InteractionHand hand) {
    public S2COpenMailPacket(FriendlyByteBuf buf) {
        this(buf.readEnum(InteractionHand.class));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeEnum(hand());
    }
}