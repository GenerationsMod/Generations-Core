package generations.gg.generations.core.generationscore.common.world.entity;

import com.cobblemon.mod.common.api.net.NetworkPacket;
import generations.gg.generations.core.generationscore.common.NetworkManager;
import generations.gg.generations.core.generationscore.common.world.shop.Offers;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface ShopOfferProvider {
    @Nullable
    Offers getOffers();

    NetworkPacket<?> createItemPacket(ItemStack itemStack, int price, int amount, boolean isBuyPage);
}
