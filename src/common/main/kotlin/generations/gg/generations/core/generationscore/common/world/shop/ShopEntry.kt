package generations.gg.generations.core.generationscore.common.world.shop

import com.cobblemon.mod.common.util.readItemStack
import com.cobblemon.mod.common.util.writeItemStack
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.random.WeightedEntry
import net.minecraft.world.item.ItemStack
import java.util.*

class ShopEntry(
    var item: ItemStack,
    var amount: Int,
    var description: String,
    var buyPrice: Int,
    var sellPrice: Int,
    var priceVariation: Double = 0.0,
    var order: Int = 0,
    var weight: Int
) {
    fun encode(buf: RegistryFriendlyByteBuf) {
        buf.writeItemStack(item)
        buf.writeInt(amount)
        buf.writeUtf(description)
        buf.writeInt(buyPrice)
        buf.writeInt(sellPrice)
        buf.writeDouble(priceVariation)
        buf.writeInt(order)
        buf.writeInt(weight)
    }

    fun toWeightedEntry(): WeightedEntry.Wrapper<ShopEntry> {
        return WeightedEntry.wrap(this, weight)
    }

    fun toSimpleEntry(): SimpleShopEntry {
        return SimpleShopEntry(this)
    }

    companion object {
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ShopEntry> = StreamCodec.of({ a, b -> b.encode(a)}, Companion::decode)
        val LIST_STREAM_CODEC = STREAM_CODEC.apply(ByteBufCodecs.list())

        @JvmStatic
        fun decode(buf: RegistryFriendlyByteBuf): ShopEntry {
            return ShopEntry(
                buf.readItemStack(),
                buf.readInt(),
                buf.readUtf(),
                buf.readInt(),
                buf.readInt(),
                buf.readDouble(),
                buf.readInt(),
                buf.readInt()
            )
        }
    }
}