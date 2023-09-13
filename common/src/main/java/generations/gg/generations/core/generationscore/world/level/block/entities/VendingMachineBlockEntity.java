package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.network.packets.shop.C2SShopItemPacket;
import generations.gg.generations.core.generationscore.world.entity.ShopOfferProvider;
import generations.gg.generations.core.generationscore.world.shop.Offers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class VendingMachineBlockEntity extends DyedVariantBlockEntity<VendingMachineBlockEntity> implements ShopOfferProvider {
    public static Offers offers = Offers.of(BuiltinShops.VENDING_MACHINE.location(), null);
    public VendingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.VENDING_MACHINE.get(), pos, state);
    }

    @Nullable
    @Override
    public Offers getOffers() {
        return offers;
    }

    @Override
    public C2SShopItemPacket createItemPacket(ItemStack itemStack, int price, int amount, boolean isBuyPage) {
        return new C2SShopItemPacket(getBlockPos(), itemStack, price, amount, isBuyPage);
    }
}
