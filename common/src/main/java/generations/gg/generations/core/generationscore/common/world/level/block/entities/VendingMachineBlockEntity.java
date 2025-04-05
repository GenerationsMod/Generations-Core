package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import com.cobblemon.mod.common.api.net.NetworkPacket;
import generations.gg.generations.core.generationscore.common.network.packets.shop.C2SShopItemPacket;
import generations.gg.generations.core.generationscore.common.world.entity.ShopOfferProvider;
import generations.gg.generations.core.generationscore.common.world.shop.BuiltinShops;
import generations.gg.generations.core.generationscore.common.world.shop.Offers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class VendingMachineBlockEntity extends DyedVariantBlockEntity<VendingMachineBlockEntity> implements ShopOfferProvider {
    public VendingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.VENDING_MACHINE.get(), pos, state);
    }


    @Override
    public @Nullable Offers getOffers() {
        return null;
    }

    @Override
    public NetworkPacket<?> createItemPacket(ItemStack itemStack, int price, int amount, boolean isBuyPage) {
        return new C2SShopItemPacket();
    }
}
