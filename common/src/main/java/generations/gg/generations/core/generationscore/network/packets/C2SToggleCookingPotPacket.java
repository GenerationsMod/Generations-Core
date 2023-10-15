package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.level.block.entities.CookingPotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;

public record C2SToggleCookingPotPacket(BlockPos pos) implements GenerationsNetworkPacket<C2SToggleCookingPotPacket> {
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public C2SToggleCookingPotPacket(FriendlyByteBuf byteBuf) {
        this(byteBuf.readBlockPos());
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public static final ResourceLocation ID = id("toggle_cooking_pot");

    public static C2SToggleCookingPotPacket decode(FriendlyByteBuf buffer) {
        return new C2SToggleCookingPotPacket(buffer.readBlockPos());
    }


}
