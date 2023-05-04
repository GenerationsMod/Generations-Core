package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.network.api.PokeModPacket;
import com.pokemod.pokemod.network.packets.shop.C2SShopItemPacket;
import com.pokemod.pokemod.registries.builtin.BuiltinShops;
import com.pokemod.pokemod.world.entity.ShopOfferProvider;
import com.pokemod.pokemod.world.npc.shop.Offers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class VendingMachineBlockEntity extends DyedVariantBlockEntity implements ShopOfferProvider {
    public static Offers offers = Offers.of(BuiltinShops.VENDING_MACHINE.location(), null);
    public VendingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(PokeModBlockEntities.VENDING_MACHINE.get(), pos, state);
    }

    @Nullable
    @Override
    public Offers getOffers() {
        return offers;
    }

    @Override
    public PokeModPacket createItemPacket(ItemStack itemStack, int price, int amount, boolean isBuyPage) {
        return new C2SShopItemPacket(getBlockPos(), itemStack, price, amount, isBuyPage);
    }
}
