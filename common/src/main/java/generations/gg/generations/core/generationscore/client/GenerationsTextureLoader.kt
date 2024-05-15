package generations.gg.generations.core.generationscore.client

import com.cobblemon.mod.common.util.asResource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel
import generations.gg.generations.core.generationscore.client.render.rarecandy.Texture
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader
import gg.generations.rarecandy.pokeutils.reader.TextureReference
import gg.generations.rarecandy.renderer.loading.ITexture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.GsonHelper
import java.awt.image.BufferedImage
import java.io.IOException
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import javax.imageio.ImageIO

object GenerationsTextureLoader : ITextureLoader() {
    private val imagesToBeLoaded = ConcurrentLinkedQueue<Triple<CompiledModel, String, ByteArray>>()
    val MAP: MutableMap<String, ResourceLocation> = HashMap()

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
                            register(key, TextureReference(fromResourceLocation(manager, value.asResource()), key))
                        }
                    }
                }
            }
        } catch (e: Exception) { throw RuntimeException(e)
        }
    }

    private fun fromResourceLocation(manager: ResourceManager, location: ResourceLocation): BufferedImage = try {
            ImageIO.read(manager.getResourceOrThrow("${location.namespace}:textures/${location.path}.png".asResource()).open())
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    override fun getTexture(s: String?): ITexture? {
        val texture = MAP.getOrDefault(s, null)?.let { texture -> Minecraft.getInstance().textureManager.getTexture(texture) }
        return if(texture is ITexture) texture else null
    }

    override fun register(s: String, iTexture: ITexture) {
        if (iTexture is DynamicTexture) {
            val location: ResourceLocation = Minecraft.getInstance().textureManager.register(
                s.replace(":", "_").lowercase(Locale.getDefault()),
                iTexture
            )
            MAP.putIfAbsent(s, location)
        }
    }

    override fun register(s: String, textureReference: TextureReference) = register(s, Texture(textureReference))

    fun register(model: CompiledModel, s: String, imageData: ByteArray) {
        val pair: Triple<CompiledModel, String, ByteArray> = Triple(model, s, imageData)

        imagesToBeLoaded.add(pair);
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
        if ((ticks % 20) == 0 && imagesToBeLoaded.isNotEmpty()) {
            if (job == null || job?.isActive == false) {
                job = scope.launch {
                    while (true) {
                        val image = imagesToBeLoaded.poll() ?: break
                        val (first, second, third) = image
                        var reference = TextureReference.read(third, second, true)
                        image.first.map[second] = reference
                        if(first.isUploaded) register(second, reference)
                    }
                }
            }
        }
        ticks += 1
    }

    override fun loadFromReference(textureReference: TextureReference): ITexture? = null

    override fun remove(s: String) {
        MAP.remove(s)?.run { Minecraft.getInstance().textureManager.release(this) }
    }

    override fun clear() = MAP.clear()

    override fun generateDirectReference(s: String): TextureReference? = null

    override fun getDarkFallback(): ITexture? = getTexture("dark")

    override fun getBrightFallback(): ITexture? = getTexture("light")

    override fun getNuetralFallback(): ITexture? = getTexture("neutral")

    override fun getTextureEntries(): Set<String> = MAP.keys

    fun has(texture: String): Boolean = MAP.containsKey(texture)

    fun getLocation(material: String): ResourceLocation? = MAP.getOrDefault(material, null)

    private val RARE_CANDY_TYPE: TypeToken<Map<String, String>> = object : TypeToken<Map<String, String>>() {}
}