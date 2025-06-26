package generations.gg.generations.core.generationscore.common.world.shop

import generations.gg.generations.core.generationscore.common.util.Time
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class Shop(
    val id: ResourceLocation,
    val maxEntries: Int,
    val refreshType: ShopRefreshType,
    val refreshTime: Time,
    val presetKeys: MutableList<ResourceLocation>,
    val entries: MutableList<ShopEntry>
) {

    fun encode(buffer: RegistryFriendlyByteBuf) {
        buffer.writeInt(maxEntries)
        buffer.writeEnum(refreshType)
        refreshTime.encode(buffer)
        buffer.writeCollection(
            presetKeys
        ) { obj: FriendlyByteBuf, resourceLocation: ResourceLocation ->
            obj.writeResourceLocation(
                resourceLocation
            )
        }
        ShopEntry.LIST_STREAM_CODEC.encode(buffer, entries)
    }
    val presets: List<ShopPreset>
        get() = presetKeys.mapNotNull { ShopPresets[it] }.toList()

    companion object {
        fun decode(buffer: RegistryFriendlyByteBuf): Shop {
            val id = buffer.readResourceLocation()
            val maxEntries = buffer.readVarInt()
            val refreshType = buffer.readEnum(ShopRefreshType::class.java);
            val refreshTime = Time.decode(buffer)
            val presetKeys = buffer.readList(FriendlyByteBuf::readResourceLocation)
            val entries = ShopEntry.LIST_STREAM_CODEC.decode(buffer)
            return Shop(id, maxEntries, refreshType, refreshTime, presetKeys, entries)
        }
    }
}