package generations.gg.generations.core.generationscore.client

import com.cobblemon.mod.common.util.asResource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mojang.blaze3d.systems.RenderSystem
import generations.gg.generations.core.generationscore.GenerationsCore
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel
import generations.gg.generations.core.generationscore.client.render.rarecandy.ITextureWithResourceLocation
import generations.gg.generations.core.generationscore.client.render.rarecandy.Texture
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader
import gg.generations.rarecandy.renderer.loading.ITexture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.AbstractTexture
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.client.renderer.texture.SimpleTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.GsonHelper
import org.lwjgl.opengl.GL13C
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.collections.HashMap

object GenerationsTextureLoader : ITextureLoader() {
    val REGULAR: MutableMap<String, ITexture> = HashMap()

    init {
        setInstance(this)
    }

    fun initialize(manager: ResourceManager) {
        clear()
        val gson = Gson()
        try {
            for (namespace in manager.namespaces) {
                manager.getResourceStack(ResourceLocation(namespace, "rare_candy_texture.json")).forEach { resource ->
                    resource.openAsReader().use { reader ->
                        GsonHelper.fromJson(gson, reader, RARE_CANDY_TYPE).forEach { (key: String, value: String?) ->
                            register(key, SimpleTextureEnhancedK(value.asResource()))
                        }
                    }
                }
            }
        } catch (e: Exception) { throw RuntimeException(e)
        }
    }

    private fun fromResourceLocation(manager: ResourceManager, location: ResourceLocation): InputStream = try {
            manager.getResourceOrThrow("${location.namespace}:textures/${location.path}.png".asResource()).open()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    override fun getTexture(s: String?): ITexture? {
        val texture = REGULAR.getOrDefault(s, null)//?.let { texture -> Minecraft.getInstance().textureManager.getTexture(texture) }
        return if(texture is ITexture) texture else null
    }

    override fun register(s: String, iTexture: ITexture) {
        REGULAR.putIfAbsent(s, iTexture)
    }

    override fun register(id: String, name: String, data: ByteArray) {
        register(id, Texture.read(data, name))
    }

    val scope = CoroutineScope(Dispatchers.Default)

    private var ticks = 0

    var job: Job? = null

//    fun tick() {
//        if((ticks % 600) == 0 && imagesToBeLoaded.isNotEmpty()) {
//            imagesToBeLoaded.forEach { register(it.first, TextureReference.read(it.second, it.first, true)) }
//        }
//        ticks += 1
//    }

    fun tick() {
//        if ((ticks % 20) == 0 && imagesToBeLoaded.isNotEmpty()) {
//            if (job == null || job?.isActive == false) {
//                job = scope.launch {
//                    while (true) {
//                        val image = imagesToBeLoaded.poll() ?: break
//                        val (first, second, third) = image
//                        var reference = TextureReference.read(third, second, true)
//                        image.first.map[second] = reference
//                        if(first.isUploaded) register(second, reference)
//                    }
//                }
//            }
//        }
//        ticks += 1
    }

    override fun remove(s: String) {
        REGULAR.remove(s)?.run { this.close() }
    }

    fun clear() = REGULAR.clear()

    override fun getDarkFallback(): ITexture? = getTexture("dark")

    override fun getBrightFallback(): ITexture? = getTexture("light")

    override fun getNuetralFallback(): ITexture? = getTexture("neutral")

    override fun getTextureEntries(): Set<String> = REGULAR.keys

    fun has(texture: String): Boolean = REGULAR.containsKey(texture)

    fun getLocation(material: String): ResourceLocation? = REGULAR.getOrDefault(material, null)?.takeIf { it is ITextureWithResourceLocation }.let { it as ITextureWithResourceLocation }.location

    private val RARE_CANDY_TYPE: TypeToken<Map<String, String>> = object : TypeToken<Map<String, String>>() {}

    private class SimpleTextureEnhancedK(var location: ResourceLocation) : SimpleTexture(location), ITexture {
        init {
            Minecraft.getInstance().textureManager.register(location, this)
        }

        override fun bind(slot: Int) {
            RenderSystem.activeTexture(GL13C.GL_TEXTURE0 + slot)
            bind()
        }

        override fun close() {
            Minecraft.getInstance().textureManager.release(location)
            super.close()
        }
    }
}