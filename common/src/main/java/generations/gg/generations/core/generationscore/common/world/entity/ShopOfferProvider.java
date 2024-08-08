package generations.gg.generations.core.generationscore.common.world.entity;

import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.common.world.shop.Offers;
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface ShopOfferProvider {
    @Nullable
    Offers getOffers();

    GenerationsNetworkPacket<?> createItemPacket(ItemStack itemStack, int price, int amount, boolean isBuyPage);
}
