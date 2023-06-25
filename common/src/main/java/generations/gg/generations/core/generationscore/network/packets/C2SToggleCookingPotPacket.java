package generations.gg.generations.core.generationscore.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public record C2SToggleCookingPotPacket(BlockPos pos) {
    public C2SToggleCookingPotPacket(FriendlyByteBuf byteBuf) {
        this(byteBuf.readBlockPos());
    }

    public void encode(FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(pos);
    }


}
