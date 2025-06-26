package generations.gg.generations.core.generationscore.common.world.level.block.entities

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

class VendingMachineBlockEntity(pos: BlockPos, state: BlockState) : DyedVariantBlockEntity(GenerationsBlockEntities.VENDING_MACHINE, pos, state)/*, ShopOfferProvider*/ {
//    override fun getOffers(): Offers? {
//        return null
//    }

/*    override fun createItemPacket(itemStack: ItemStack, price: Int, amount: Int, isBuyPage: Boolean): NetworkPacket<*> {
        return C2SShopItemPacket()
    }*/
}
