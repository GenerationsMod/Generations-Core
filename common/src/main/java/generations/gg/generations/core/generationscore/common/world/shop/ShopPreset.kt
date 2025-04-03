package generations.gg.generations.core.generationscore.common.world.shop

import com.cobblemon.mod.common.util.writeCollection
import com.cobblemon.mod.common.util.writeItemStack
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.world.shop.ShopPreset
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

data class ShopPreset(val id: ResourceLocation, val entries: List<ShopEntry>, val maxAppearingItems: Int) {
    companion object {
        fun decode(buf: RegistryFriendlyByteBuf): ShopPreset {
            val id = buf.readResourceLocation()
            val entries = ShopEntry.LIST_STREAM_CODEC.decode(buf)
            val maxAppearingItems = buf.readVarInt()
            return ShopPreset(id, entries, maxAppearingItems)
        }
    }

    fun encode(buf: RegistryFriendlyByteBuf) {
        buf.writeResourceLocation(id)
        ShopEntry.LIST_STREAM_CODEC.encode(buf, entries)
        buf.writeVarInt(maxAppearingItems)
    }
}