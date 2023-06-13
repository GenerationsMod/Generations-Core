package generations.gg.generations.core.generationscore.network.packets;

import dev.architectury.networking.NetworkManager;
import generations.gg.generations.core.generationscore.world.level.block.entities.CookingPotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public record C2SToggleCookingPotPacket(BlockPos pos) {
    public C2SToggleCookingPotPacket(FriendlyByteBuf byteBuf) {
        this(byteBuf.readBlockPos());
    }

    public void encode(FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(pos);
    }


}
