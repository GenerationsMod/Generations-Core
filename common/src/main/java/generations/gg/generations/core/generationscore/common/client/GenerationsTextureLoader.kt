package generations.gg.generations.core.generationscore.common.client

import com.cobblemon.mod.common.util.asResource
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.mojang.blaze3d.platform.NativeImage
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.model.SpriteRegistry
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ITextureWithResourceLocation
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader
import gg.generations.rarecandy.renderer.loading.ITexture
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.SimpleTexture
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import org.lwjgl.opengl.GL13C
import java.io.ByteArrayInputStream
import java.io.IOException
import kotlin.random.Random

object GenerationsTextureLoader : ITextureLoader() {
    val REGULAR = mutableMapOf<String, ITexture>()
    val CODEC = Codec.unboundedMap(Codec.STRING, ResourceLocation.CODEC)
    val RARE_CANDY = FileToIdConverter("textures", "rare_candy_texture.json")
    val gson = Gson()
    init {}

    fun initialize(manager: ResourceManager) {
        clear()
        try {
            RARE_CANDY.listMatchingResourceStacks(manager).forEach { name, list ->
                list.forEach { resource ->
                    val obj = resource.openAsReader().use { SpriteRegistry.GSON.fromJson(it, JsonObject::class.java) }
                    val map = GenerationsUtils.decode(CODEC, obj)

                    if(map.isNotEmpty()) {
                        map.forEach { (key, value) ->
                            register(key, SimpleTextureEnhanced(value.let { "${it.namespace}:textures/${it.path}.png" }.asResource()))
                        }
                    }
                }
            }

//            RARE_CANDY.listMatchingResources(manager).values.forEach { resouce ->
//                resouce.openAsReader().use { GsonHelper.fromJson(gson, it, RARE_CANDY_TYPE) }.forEach { (key, value) ->
//                    register(key, SimpleTextureEnhanced(value.asResource().let { "${it.namespace}:textures/${it.path}.png" }.asResource()))
//                }
//            }
        } catch (e: Exception) { throw RuntimeException(e)
        }
    }

    override fun getTexture(s: String?): ITexture? {
        val texture = REGULAR.getOrDefault(s, null)
        return if(texture is ITexture) texture else null
    }

    override fun register(s: String, iTexture: ITexture) {
        REGULAR.putIfAbsent(s, iTexture)
    }

    override fun register(id: String, name: String, data: ByteArray) {
        SimpleTextureIndependentData.read(data, name)?.let { register(id, it) }
    }


    override fun remove(s: String) {
        REGULAR.remove(s)?.run { this.close() }
    }

    fun clear() {
        val iterator = REGULAR.iterator()
        while(iterator.hasNext()) {
            val entry = iterator.next()
            iterator.remove()
            entry.value?.close()
        }
    }

    override fun getDarkFallback(): ITexture? = getTexture("dark")

    override fun getBrightFallback(): ITexture? = getTexture("light")

    override fun getNuetralFallback(): ITexture? = getTexture("neutral")

    override fun getTextureEntries(): Set<String> = REGULAR.keys

    fun has(texture: String): Boolean = REGULAR.containsKey(texture)

    fun getLocation(material: String): ResourceLocation? =
        REGULAR.getOrDefault(material, null).takeIf { it is ITextureWithResourceLocation? }?.let { it as ITextureWithResourceLocation }?.location

    private val RARE_CANDY_TYPE: TypeToken<Map<String, String>> = object : TypeToken<Map<String, String>>() {}

    private class SimpleTextureEnhanced(override var location: ResourceLocation) : SimpleTexture(location), ITextureWithResourceLocation {
        init {
            Minecraft.getInstance().textureManager.register(location, this)
        }

        override fun bind(slot: Int) {
            RenderSystem.activeTexture(GL13C.GL_TEXTURE0 + slot)
            bind()
        }
    }

    private class SimpleTextureIndependentData(location: ResourceLocation, private val texture: ByteArray?) : SimpleTexture(location), ITexture {

        init {
            Minecraft.getInstance().textureManager.register(location, this)
        }

        override fun bind(slot: Int) {
            RenderSystem.activeTexture(GL13C.GL_TEXTURE0 + slot)
            bind()
        }

        override fun getTextureImage(resourceManager: ResourceManager): TextureImage {
            return load()!!
        }

        fun load(): TextureImage? {
            return try {
                val inputStream = ByteArrayInputStream(texture)
                val nativeImage: NativeImage = try {
                    NativeImage.read(inputStream)
                } catch (var9: Throwable) {
                    try {
                        inputStream.close()
                    } catch (var7: Throwable) {
                        var9.addSuppressed(var7)
                    }
                    throw var9
                }

                inputStream.close()
//                var textureMetadataSection: TextureMetadataSection? = null //TODO: See if implmenting is viable
//                try {
//                    textureMetadataSection = resource.metadata().getSection(TextureMetadataSection.SERIALIZER)
//                        .orElse(null as Any?) as TextureMetadataSection
//                } catch (var8: java.lang.RuntimeException) {
//                    LOGGER.warn("Failed reading metadata of: {}", location, var8)
//                }
                TextureImage(null, nativeImage)
            } catch (var10: IOException) {
                TextureImage(var10)
            }
        }

        companion object {
            @Throws(IOException::class)
            fun read(imageBytes: ByteArray?, name: String): SimpleTextureIndependentData? {
                if(imageBytes == null) return null

                val resource = if(name.contains(":")) {
                    name.lowercase().asResource()
                } else {
                    GenerationsCore.id((1..4).joinToString("") { Random.nextInt(0, 10).toString() }  + name.lowercase())
                }

                return SimpleTextureIndependentData(resource, imageBytes)
            }

        }
    }
}