package generations.gg.generations.core.generationscore.client.render.rarecandy

import com.cobblemon.mod.common.util.asResource
import com.mojang.blaze3d.platform.NativeImage
import com.mojang.blaze3d.systems.RenderSystem
import generations.gg.generations.core.generationscore.GenerationsCore
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import org.lwjgl.opengl.GL13C
import java.io.IOException
import kotlin.random.Random

class Texture(override var location: ResourceLocation, image: NativeImage) : DynamicTexture(image), ITextureWithResourceLocation {
    init {
        Minecraft.getInstance().textureManager.register(location, this)
    }

    override fun bind(slot: Int) {

        assert(slot in 0..31)
        RenderSystem.activeTexture(GL13C.GL_TEXTURE0 + slot)
        RenderSystem.bindTexture(id)
    }

    @Throws(IOException::class)
    override fun load(resourceManager: ResourceManager) {
    }

    override fun close() {
        Minecraft.getInstance().textureManager.release(location);
    }

    companion object {
        @Throws(IOException::class)
        fun read(imageBytes: ByteArray?, name: String): Texture? {
            if(imageBytes == null) return null

            val resource = if(name.contains(":")) {
                name.lowercase().asResource()
            } else {
                GenerationsCore.id((1..4).joinToString("") { Random.nextInt(0, 10).toString() }  + name.lowercase())
            }

            return Texture(resource, imageBytes.let { NativeImage.read(imageBytes) })
        }

    }
}

