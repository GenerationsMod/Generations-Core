package generations.gg.generations.core.generationscore.common.network.packets.shop;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public record C2SShopItemPacket(int npcId, BlockPos pos, ItemStack itemStack, int price, int amount, boolean isBuy) implements GenerationsNetworkPacket<C2SShopItemPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("shop_item");

    public C2SShopItemPacket(int npcId, ItemStack stack, int price, int amount, boolean isBuy) {
        this(npcId, null, stack, price, amount, isBuy);
    }

    public C2SShopItemPacket(BlockPos pos, ItemStack stack, int price, int amount, boolean isBuy) {
        this(-1, pos, stack, price, amount, isBuy);
    }

    public static C2SShopItemPacket decode(FriendlyByteBuf buf) {
        var isNpc = buf.readBoolean();

        return new C2SShopItemPacket(isNpc ? buf.readInt() : -1, !isNpc ? buf.readBlockPos() : null, buf.readItem(), buf.readInt(), buf.readInt(), buf.readBoolean());
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        if(this.npcId >= 0) {
            buf.writeBoolean(true);
            buf.writeInt(npcId);
        } else {
            buf.writeBoolean(false);
            buf.writeBlockPos(pos);
        }

        buf.writeItem(itemStack);
        buf.writeInt(price);
        buf.writeInt(amount);
        buf.writeBoolean(isBuy);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}