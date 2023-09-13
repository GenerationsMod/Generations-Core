package generations.gg.generations.core.generationscore.world.entity;

import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.shop.Offers;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface ShopOfferProvider {
    @Nullable
    Offers getOffers();

    GenerationsNetworkPacket<?> createItemPacket(ItemStack itemStack, int price, int amount, boolean isBuyPage);
}
