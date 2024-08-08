package generations.gg.generations.core.generationscore.common.client.model

import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager

object SpriteRegistry {
    val RARE_CANDY = FileToIdConverter("textures", "sprite_mapping.json")
    val CODEC = Codec.unboundedMap(ResourceLocation.CODEC, Codec.unboundedMap(Codec.STRING, Codec.unboundedMap(Codec.STRING, ResourceLocation.CODEC)))

    var MAP: MutableMap<ResourceLocation, MutableMap<String, MutableMap<String, ResourceLocation>>> = mutableMapOf()

    val GSON = Gson()

    fun onResourceManagerReload(resourceManager: ResourceManager) {
        MAP.clear()

        RARE_CANDY.listMatchingResourceStacks(resourceManager).forEach { (name, list) ->
            list.forEach { resource ->
                run {

                    val obj = resource.openAsReader().use { GSON.fromJson(it, JsonObject::class.java) }
                    val map = GenerationsUtils.decode(CODEC, obj);

                    if (map.isNotEmpty()) {

                        map.forEach { (species, types) ->
                            types.forEach { (variant, variants) ->
                                variants.forEach { (type, entry) ->
                                    val modelMap = MAP.computeIfAbsent(species) { mutableMapOf() }
                                    val variantMap = modelMap.computeIfAbsent(variant) { mutableMapOf() }
                                    variantMap[type] = entry
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun getPokemonSprite(state: RenderContext.RenderState, model: ResourceLocation, variant: String): ResourceLocation? = MAP[model]?.get(variant)?.get(if(state == RenderContext.RenderState.PROFILE) "profile" else "portrait")
}
