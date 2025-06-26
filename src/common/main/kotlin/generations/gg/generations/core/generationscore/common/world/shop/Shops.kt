package generations.gg.generations.core.generationscore.common.world.shop

import com.cobblemon.mod.common.api.data.JsonDataRegistry
import com.cobblemon.mod.common.api.reactive.SimpleObservable
import com.cobblemon.mod.common.util.adapters.IdentifierAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.adapters.ITEM_STACK_ADAPTER
import generations.gg.generations.core.generationscore.common.world.shop.Shop
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.Resource
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.world.item.ItemStack
import org.apache.commons.io.FilenameUtils
import java.nio.file.Path
import java.util.*

object Shops : JsonDataRegistry<Shop> {
    override val id = GenerationsCore.id("shops")
    override val type: PackType = PackType.SERVER_DATA
    override val observable = SimpleObservable<Shops>()
    override val gson = GsonBuilder().setPrettyPrinting()
        .registerTypeAdapter(ResourceLocation::class.java, IdentifierAdapter)
        .registerTypeAdapter(ItemStack::class.java, ITEM_STACK_ADAPTER)
        .create()

    override val typeToken: TypeToken<Shop> = TypeToken.get(Shop::class.java)
    override val resourcePath = "shop"

    val shops = mutableMapOf<ResourceLocation, Shop>()

    override fun reload(data: Map<ResourceLocation, Shop>) {
        data.forEach { (_, shop) ->
            shops[shop.id] = shop
        }

        observable.emit(this)
    }

    override fun sync(player: ServerPlayer) {
        ShopRegistrySyncPacket(shops.values).sendToPlayer(player)
    }

    fun get(id: ResourceLocation): Shop? {
        return shops[id]
    }
}
