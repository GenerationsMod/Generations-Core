package generations.gg.generations.core.generationscore.common.network.packets.shop;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record S2COpenShopPacket(int entityId, BlockPos pos) implements GenerationsNetworkPacket<S2COpenShopPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("open_shop");

    public S2COpenShopPacket(int entityId) {
        this(entityId, null);
    }

    public S2COpenShopPacket(BlockPos pos) {
        this(-1, pos);
    }

    public static S2COpenShopPacket decode(FriendlyByteBuf buf) {
        return buf.readBoolean() ? new S2COpenShopPacket(buf.readInt()) : new S2COpenShopPacket(buf.readBlockPos());
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        if (entityId >= 0) {
            buf.writeBoolean(true);
            buf.writeInt(this.entityId);
        } else {
            buf.writeBoolean(false);
            buf.writeBlockPos(pos);
        }
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}