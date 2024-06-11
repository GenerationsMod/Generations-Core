package generations.gg.generations.core.generationscore.client.render.rarecandy

import com.cobblemon.mod.common.util.asResource
import com.mojang.blaze3d.pipeline.RenderCall
import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.platform.NativeImage
import com.mojang.blaze3d.systems.RenderSystem
import com.traneptora.jxlatte.JXLDecoder
import com.traneptora.jxlatte.JXLOptions
import generations.gg.generations.core.generationscore.GenerationsCore
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.AbstractTexture
import net.minecraft.client.renderer.texture.DynamicTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.Mth
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL13C
import org.lwjgl.system.MemoryUtil
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.awt.image.DataBufferFloat
import java.awt.image.DataBufferInt
import java.io.ByteArrayInputStream
import java.io.Closeable
import java.io.IOException
import java.nio.ByteBuffer
import javax.imageio.ImageIO
import kotlin.math.pow
import kotlin.random.Random

class Texture(override var location: ResourceLocation, var image: NativeImage) : DynamicTexture(image), ITextureWithResourceLocation {
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
        private var options: JXLOptions = JXLOptions().apply { this.threads = 4 }

        @Throws(IOException::class)
        fun read(imageBytes: ByteArray?, name: String): Texture? {
            if(imageBytes == null) return null

            var resource = if(name.contains(":")) {
                name.lowercase().asResource()
            } else {
                GenerationsCore.id((1..4).joinToString("") { Random.nextInt(0, 10).toString() }  + name.lowercase())
            }

            return imageBytes?.let { NativeImage.read(imageBytes) }?.let { Texture(resource, it) }
        }

        fun read(image: BufferedImage?): ByteArray? {
            if (image == null) {
                return null
            }
            val buffer = image.data.dataBuffer
            val readyData: ByteBuffer
            when (buffer) {
                is DataBufferFloat -> {
                    val rawData: FloatArray = buffer.data
                    readyData = ByteBuffer.wrap(ByteArray(rawData.size * 4))
                    for (hdrChannel in rawData) {
                        val channelValue = hdrToRgb(hdrChannel)
                        readyData.put(channelValue.toByte())
                    }
                    readyData.flip()
                }

                is DataBufferInt -> {
                    val rawData: IntArray = buffer.data
                    readyData = ByteBuffer.wrap(ByteArray(rawData.size * 4))
                    for (pixel in rawData) {
                        readyData.put((pixel shr 16 and 0xFF).toByte())
                        readyData.put((pixel shr 8 and 0xFF).toByte())
                        readyData.put((pixel and 0xFF).toByte())
                        readyData.put((pixel shr 24 and 0xFF).toByte())
                    }
                    readyData.flip()
                }

                is DataBufferByte -> {
                    val rawData: ByteArray = buffer.data
                    readyData = ByteBuffer.wrap(ByteArray(rawData.size))
                    readyData.put(rawData)
                    readyData.flip()
                }

                else -> throw RuntimeException("Unknown Data Type: " + buffer.javaClass.name)
            }
            return readyData.array()
        }

        private fun hdrToRgb(hdr: Float): Int {
            return (hdr.toDouble().pow(1.0 / 2.2) * 255).clamp(0.0, 255.0).toInt()
        }
    }
}

private fun Double.clamp(min: Double, max: Double) = Mth.clamp(this, min, max)
