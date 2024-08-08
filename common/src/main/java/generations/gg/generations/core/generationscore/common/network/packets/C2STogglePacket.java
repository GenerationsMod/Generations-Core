package generations.gg.generations.core.generationscore.common.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.common.GenerationsCore.id;

public record C2STogglePacket(BlockPos pos) implements GenerationsNetworkPacket<C2STogglePacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public C2STogglePacket(FriendlyByteBuf byteBuf) {
        this(byteBuf.readBlockPos());
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public static final ResourceLocation ID = id("toggle_cooking_pot");

    public static C2STogglePacket decode(FriendlyByteBuf buffer) {
        return new C2STogglePacket(buffer.readBlockPos());
    }


}
