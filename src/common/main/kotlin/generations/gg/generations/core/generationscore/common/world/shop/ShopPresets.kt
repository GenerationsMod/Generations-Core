package generations.gg.generations.core.generationscore.common.world.shop

import com.cobblemon.mod.common.api.data.JsonDataRegistry
import com.cobblemon.mod.common.api.reactive.SimpleObservable
import com.cobblemon.mod.common.util.adapters.IdentifierAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.adapters.ITEM_STACK_ADAPTER
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.world.item.ItemStack
import java.util.*

object ShopPresets : JsonDataRegistry<ShopPreset> {
    override val id: ResourceLocation = GenerationsCore.id("shop_presets")
    override val observable = SimpleObservable<ShopPresets>()

    override val gson: Gson = GsonBuilder().setPrettyPrinting()
        .registerTypeAdapter(ResourceLocation::class.java, IdentifierAdapter)
        .registerTypeAdapter(ItemStack::class.java, ITEM_STACK_ADAPTER)
        .create()

    override val type: PackType = PackType.SERVER_DATA


    override val typeToken: TypeToken<ShopPreset> = TypeToken.get(ShopPreset::class.java)

    override val resourcePath = "shop_presets"

    val presets = mutableMapOf<ResourceLocation, ShopPreset>()

    override fun reload(data: Map<ResourceLocation, ShopPreset>) {
        data.forEach { (_, preset) ->
            presets[preset.id] = preset
        }

        observable.emit(this)
    }

    operator fun get(id: ResourceLocation): ShopPreset? {
        return presets[id]
    }

    override fun sync(player: ServerPlayer) {
        ShopPresetRegistrySyncPacket(presets.values).sendToPlayer(player)
    }
}
