package generations.gg.generations.core.generationscore.client.model

import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.fromJson
import com.cobblemon.mod.common.util.isEmpty
import com.google.common.collect.Table
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import generations.gg.generations.core.generationscore.client.GenerationsTextureLoader
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import net.minecraft.util.GsonHelper
import java.util.function.BiConsumer

object SpriteRegistry {
    val RARE_CANDY = FileToIdConverter("textures", "sprite_mapping.json")
    val CODEC = Codec.unboundedMap(ResourceLocation.CODEC, Codec.unboundedMap(Codec.STRING, Codec.unboundedMap(Codec.STRING, ResourceLocation.CODEC)))

    var MAP: MutableMap<ResourceLocation, MutableMap<String, MutableMap<String, ResourceLocation>>> = mutableMapOf()

    val GSON = Gson()

    fun onResourceManagerReload(resourceManager: ResourceManager) {
        MAP.clear()


        RARE_CANDY.listMatchingResources(resourceManager).forEach { (name, resouce) ->
            val obj = resouce.openAsReader().use { GSON.fromJson(it, JsonObject::class.java) }
            val map = CODEC.decode(JsonOps.INSTANCE, obj).getOrThrow(false, System.out::println).first

//            System.out.println("BLARGE: " + map)

            MAP.putAll(map)

//            if(map.isNotEmpty()) {
//                map.forEach { (species, types) ->
//                    types.forEach { (type, variants) ->
//                        variants.forEach { (variant, entry) ->
//                            MAP.putIfAbsent(species, mutableMapOf())?.putIfAbsent(type, mutableMapOf())?.put(variant, entry)
//                        }
//                    }
//                }
//            }
        }

//        System.out.println("Blep:" + MAP)
    }

    private fun getProfilePokemonSprite(model: ResourceLocation, variant: String): ResourceLocation? {
        return MAP[model]?.get(variant)?.get("profile")
    }

    private fun getPortraitPokemonSprite(model: ResourceLocation, variant: String): ResourceLocation? {
        return MAP[model]?.get(variant)?.get("portrait")
    }

    fun <T> getPokemonSprite(state: RenderContext.RenderState, model: ResourceLocation, variant: String): ResourceLocation? = MAP[model]?.get(variant)?.get(if(state == RenderContext.RenderState.PROFILE) "profile" else "portrait")
}
