package generations.gg.generations.core.generationscore.common.network.packets.npc;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public record C2SUpdateNpcShopPacket(int entityId, @Nullable ResourceLocation shop) implements GenerationsNetworkPacket<C2SUpdateNpcShopPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("update_npc_shop");

    public C2SUpdateNpcShopPacket(FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readNullable(FriendlyByteBuf::readResourceLocation));
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeNullable(this.shop, FriendlyByteBuf::writeResourceLocation);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}