package generations.gg.generations.core.generationscore.common.world.level.block.entities

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.network.packets.shop.C2SShopItemPacket
import generations.gg.generations.core.generationscore.common.world.entity.ShopOfferProvider
import generations.gg.generations.core.generationscore.common.world.shop.Offers
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState

class VendingMachineBlockEntity(pos: BlockPos, state: BlockState) : DyedVariantBlockEntity<VendingMachineBlockEntity>(GenerationsBlockEntities.VENDING_MACHINE, pos, state)/*, ShopOfferProvider*/ {
//    override fun getOffers(): Offers? {
//        return null
//    }

/*    override fun createItemPacket(itemStack: ItemStack, price: Int, amount: Int, isBuyPage: Boolean): NetworkPacket<*> {
        return C2SShopItemPacket()
    }*/
}
