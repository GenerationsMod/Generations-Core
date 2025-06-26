package generations.gg.generations.core.generationscore.common.world.shop

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.network.packets.DataRegistrySyncPacket
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class ShopPresetRegistrySyncPacket(presets: Collection<ShopPreset>) : DataRegistrySyncPacket<ShopPreset, ShopPresetRegistrySyncPacket>(presets) {
    override fun encodeEntry(buffer: RegistryFriendlyByteBuf, entry: ShopPreset) {
        entry.encode(buffer)
    }

    override fun decodeEntry(buffer: RegistryFriendlyByteBuf): ShopPreset {
        return ShopPreset.decode(buffer)
    }

    override fun synchronizeDecoded(entries: Collection<ShopPreset>) {
        ShopPresets.reload(entries.associateBy { it.id })
    }

    override val id: ResourceLocation = ID

    companion object {
        val ID: ResourceLocation = GenerationsCore.id("shop_registry_sync")

        fun decode(buffer: RegistryFriendlyByteBuf): ShopPresetRegistrySyncPacket = ShopPresetRegistrySyncPacket(emptyList()).apply { this.decodeBuffer(buffer) }
    }
}