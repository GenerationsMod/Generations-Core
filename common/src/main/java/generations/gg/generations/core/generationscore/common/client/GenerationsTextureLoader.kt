package generations.gg.generations.core.generationscore.common.client

import com.cobblemon.mod.common.util.asResource
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.model.SpriteRegistry
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader
import gg.generations.rarecandy.renderer.loading.ITexture
import net.minecraft.Util
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.AbstractTexture
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.client.renderer.texture.PreloadedTexture
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import org.lwjgl.opengl.GL13C

object GenerationsTextureLoader : ITextureLoader() {

//    val missingTexture: ITexture
//        get() = MinecraftTexture.also { it.resourceLocation = MissingTextureAtlasSprite.getLocation() }

    val PROXY_ID: ResourceLocation = GenerationsCore.id("proxy_rarecandy")
    val REGULAR = mutableMapOf<String, ITexture>()
    val CODEC = Codec.unboundedMap(Codec.STRING, ResourceLocation.CODEC.xmap({ "${it.namespace}:textures/${it.path}.png".asResource() }, { throw RuntimeException("Don't use.")}))
    val RARE_CANDY = FileToIdConverter("textures", "rare_candy_texture.json")
    val gson = Gson()


    object MinecraftTexture : ITexture {
        override fun close() {
        }
        override fun bind(slot: Int) {
            val texture = Minecraft.getInstance().textureManager.getTexture(MissingTextureAtlasSprite.getLocation())

            RenderSystem.activeTexture(GL13C.GL_TEXTURE0 + slot)
            RenderSystem.bindTexture(texture.id)
        }

        override fun width(): Int = 16

        override fun height(): Int = 16

    }

    object ProxyTexture : AbstractTexture() {
        lateinit var iTexture: Texture

        override fun load(resourceManager: ResourceManager) {

        }

        override fun releaseId() {

        }

        override fun bind() {
            if (!RenderSystem.isOnRenderThreadOrInit()) {
                RenderSystem.recordRenderCall {
                    GlStateManager._bindTexture(this.getId())
                }
            } else {
                GlStateManager._bindTexture(this.getId())
            }
        }

        override fun getId(): Int {
            return iTexture.id
        }
    }

    fun initialize(manager: ResourceManager) {
        clear()
        Minecraft.getInstance().textureManager.register(PROXY_ID, ProxyTexture)
        try {
            RARE_CANDY.listMatchingResourceStacks(manager).forEach { (name, list) ->
                list.forEach { resource ->
                    val obj = resource.openAsReader().use { SpriteRegistry.GSON.fromJson(it, JsonObject::class.java) }
                    val map = GenerationsUtils.decode(CODEC, obj)

                    if (map.isNotEmpty()) {
                        map.forEach { (key, location) ->
                            manager.getResource(location).ifPresent({ it ->
                                register(key, Texture.read(it.open().readAllBytes()))
                            })
                        }
                    }
                }
            }
        } catch (e: Exception) { throw RuntimeException(e)
        }
    }

    override fun getTexture(s: String?): ITexture = REGULAR[s] ?: MinecraftTexture

    override fun register(s: String, iTexture: ITexture) {
        REGULAR.putIfAbsent(s, iTexture)
    }

    override fun register(id: String, name: String, data: ByteArray) {
        Texture.read(data)?.let { register(id, it) }
    }


    override fun remove(s: String) {
        REGULAR.remove(s)?.run { this.close() }
    }

    fun clear() {
        val iterator = REGULAR.iterator()
        while(iterator.hasNext()) {
            val entry = iterator.next()
            iterator.remove()

            var texture = entry.value

            if(texture is SimpleTextureIndependentData) {
                Minecraft.getInstance().textureManager.release(texture.imageId)
            } else {
                texture.close()
            }
        }
    }

    override fun getDarkFallback(): ITexture = getTexture("dark")

    override fun getBrightFallback(): ITexture = getTexture("bright")

    override fun getNuetralFallback(): ITexture = getTexture("neutral")

    override fun getTextureEntries(): Set<String> = REGULAR.keys

    fun has(texture: String): Boolean = REGULAR.containsKey(texture)

    fun getLocation(material: String): ResourceLocation? {
        val texture = REGULAR[material] ?: return null

//        if(texture is SimpleTextureIndependentData) {
//            return texture.imageId
//        }

        ProxyTexture.iTexture = texture as Texture

        return PROXY_ID

//        ProxyTexture.iTexture = texture as Texture
//        return PROXY_ID
    }

//    private class SimpleTextureEnhanced : SimpleTexture(), ITexture {
//
//        override fun bind(slot: Int) {
//            RenderSystem.activeTexture(GL13C.GL_TEXTURE0 + slot)
//            RenderSystem.bindTexture(id)
//        }
//
//        override fun width(): Int {
//            TODO("Not yet implemented")
//        }
//
//        override fun height(): Int {
//            TODO("Not yet implemented")
//        }
//    }

    private class SimpleTextureIndependentData(textureManager: ResourceManager, location: ResourceLocation) : PreloadedTexture(textureManager, location, Util.backgroundExecutor()), ITexture {
        override fun bind(p0: Int) {
            if (!RenderSystem.isOnRenderThreadOrInit()) {
                RenderSystem.recordRenderCall {
                    GlStateManager.glActiveTexture(p0)
                    GlStateManager._bindTexture(this.getId())
                }
            } else {
                GlStateManager.glActiveTexture(p0)
                GlStateManager._bindTexture(this.getId())
            }
        }

        override fun width(): Int {
            TODO("Not yet implemented")
        }

        override fun height(): Int {
            TODO("Not yet implemented")
        }

        val imageId: ResourceLocation = location
    }
}