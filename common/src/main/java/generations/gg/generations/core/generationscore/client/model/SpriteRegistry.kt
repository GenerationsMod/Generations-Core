package generations.gg.generations.core.generationscore.client.model

import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.fromJson
import com.cobblemon.mod.common.util.isEmpty
import com.google.common.collect.Table
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import generations.gg.generations.core.generationscore.client.GenerationsTextureLoader
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.ResourceManagerReloadListener
import net.minecraft.util.GsonHelper
import java.util.function.BiConsumer

object SpriteRegistry {
    val RARE_CANDY = FileToIdConverter("textures", "sprite_mapping.json")

    val MAP: MutableMap<String, MutableMap<ResourceLocation, MutableMap<String, ResourceLocation>>> = mutableMapOf()

    val GSON = Gson()

    fun onResourceManagerReload(resourceManager: ResourceManager) {
        MAP.clear()

        RARE_CANDY.listMatchingResources(resourceManager).forEach { (name, resouce) ->
            val obj = resouce.openAsReader().use { GSON.fromJson(it, JsonObject::class.java) }
//            System.out.println("Group: " + name + " " + obj.toString())
            if(!obj.isEmpty()) {
                obj.entrySet().forEach { pair1 ->
                    val category = pair1.key
                    val categoryObj = pair1.value

                    categoryObj.asJsonObject.entrySet().forEach { pair2 ->
                        val entry = pair2.key
                        val entryObject = pair2.value

                        entryObject.asJsonObject.entrySet().forEach { pair3 ->
                            val variant = pair3.key
                            val variantObj = pair3.value

                            MAP.computeIfAbsent(category) { mutableMapOf() }.computeIfAbsent(entry.asResource()) { mutableMapOf() }[variant] = variantObj.asString.asResource().withPrefix("textures/")
                        }
                    }
                }
            }
        }

        System.out.println("Blep:" + MAP)
    }

    fun getPokemonSprite(model: ResourceLocation, variant: String): ResourceLocation? {
        return MAP["pokemon"]?.get(model)?.get(variant)
    }
}
