package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class VendingMachineBlockEntity extends DyedVariantBlockEntity<VendingMachineBlockEntity> /*implements ShopOfferProvider*/ {
//    public static Offers offers = Offers.of(BuiltinShops.VENDING_MACHINE, null);
    public VendingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.VENDING_MACHINE.get(), pos, state);
    }

//    @Nullable
//    @Override
//    public Offers getOffers() {
//        return offers;
//    }

//    @Override
//    public C2SShopItemPacket createItemPacket(ItemStack itemStack, int price, int amount, boolean isBuyPage) {
//        return new C2SShopItemPacket(getBlockPos(), itemStack, price, amount, isBuyPage);
//    }
}
