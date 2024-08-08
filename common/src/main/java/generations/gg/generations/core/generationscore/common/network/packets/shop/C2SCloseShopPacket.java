package generations.gg.generations.core.generationscore.common.network.packets.shop;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class C2SCloseShopPacket implements GenerationsNetworkPacket<C2SCloseShopPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("close_shop");

    public C2SCloseShopPacket() {
    }

    public C2SCloseShopPacket(FriendlyByteBuf buf) {
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

}