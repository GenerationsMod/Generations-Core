package generations.gg.generations.core.generationscore.common.network.packets.shop

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack

class C2SShopItemPacket(
    val npcId: Int,
    val pos: BlockPos?,
    val itemStack: ItemStack,
    val price: Int,
    val amount: Int,
    val isBuy: Boolean
) : NetworkPacket<C2SShopItemPacket> {
    override val id: ResourceLocation = ID

    constructor(npcId: Int, stack: ItemStack, price: Int, amount: Int, isBuy: Boolean) : this(
        npcId,
        null,
        stack,
        price,
        amount,
        isBuy
    )

    constructor(pos: BlockPos?, stack: ItemStack, price: Int, amount: Int, isBuy: Boolean) : this(
        -1,
        pos,
        stack,
        price,
        amount,
        isBuy
    )

    override fun encode(buf: RegistryFriendlyByteBuf) {
        if (this.npcId >= 0) {
            buf.writeBoolean(true)
            buf.writeInt(npcId)
        } else {
            buf.writeBoolean(false)
            buf.writeNullable(pos, RegistryFriendlyByteBuf::writeBlockPos)
        }

        ItemStack.STREAM_CODEC.encode(buf, itemStack)
        buf.writeInt(price)
        buf.writeInt(amount)
        buf.writeBoolean(isBuy)
    }

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("shop_item")

        fun decode(buf: RegistryFriendlyByteBuf): C2SShopItemPacket {
            val isNpc = buf.readBoolean()

            return C2SShopItemPacket(
                if (isNpc) buf.readInt() else -1,
                if (!isNpc) buf.readBlockPos() else null,
                ItemStack.STREAM_CODEC.decode(buf),
                buf.readInt(),
                buf.readInt(),
                buf.readBoolean()
            )
        }
    }
}