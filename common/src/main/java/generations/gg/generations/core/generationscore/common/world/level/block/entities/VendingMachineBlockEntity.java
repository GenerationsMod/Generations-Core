package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import generations.gg.generations.core.generationscore.common.network.packets.shop.C2SShopItemPacket;
import generations.gg.generations.core.generationscore.common.world.entity.ShopOfferProvider;
import generations.gg.generations.core.generationscore.common.world.shop.BuiltinShops;
import generations.gg.generations.core.generationscore.common.world.shop.Offers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class VendingMachineBlockEntity extends DyedVariantBlockEntity<VendingMachineBlockEntity> {
    public VendingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.VENDING_MACHINE.get(), pos, state);
    }
}
