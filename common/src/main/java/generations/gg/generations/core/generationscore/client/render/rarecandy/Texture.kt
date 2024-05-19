package generations.gg.generations.core.generationscore.client.render.rarecandy

import com.cobblemon.mod.common.util.asResource
import com.traneptora.jxlatte.JXLDecoder
import com.traneptora.jxlatte.JXLOptions
import generations.gg.generations.core.generationscore.GenerationsCore
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.AbstractTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.Mth
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL13
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

class Texture(override var location: ResourceLocation, buffer: ByteBuffer?, width: Int, height: Int) : AbstractTexture(), ITextureWithResourceLocation {
    init {
        Minecraft.getInstance().textureManager.register(location, this)
    }
    private var details: TextureDetails?

    init {
        details = TextureDetails(buffer, width, height)
    }

    private data class TextureDetails(val buffer: ByteBuffer?, val width: Int, val height: Int) : Closeable {
        override fun close() {
            MemoryUtil.memFree(buffer)
        }

        fun init(texture: Texture) {
            val id = texture.getId()
            GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, id)
            GL11C.glTexImage2D(
                GL11C.GL_TEXTURE_2D,
                0,
                GL11C.GL_RGBA8,
                width,
                height,
                0,
                GL11C.GL_RGBA,
                GL11C.GL_UNSIGNED_BYTE,
                buffer
            )
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_S, GL11C.GL_REPEAT)
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_T, GL11C.GL_REPEAT)
            GL11C.glTexParameterf(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MIN_FILTER, GL11C.GL_NEAREST.toFloat())
            GL11C.glTexParameterf(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MAG_FILTER, GL11C.GL_NEAREST.toFloat())
            MemoryUtil.memFree(buffer)
        }
    }

    override fun bind(slot: Int) {
        if (details != null) {
            details!!.init(this)
            details = null

            return
        }
        assert(slot in 0..31)
        GL13C.glActiveTexture(GL13C.GL_TEXTURE0 + slot)
        GL11C.glBindTexture(GL11.GL_TEXTURE_2D, id)
    }

    @Throws(IOException::class)
    override fun load(resourceManager: ResourceManager) {
    }

    override fun close() {
        if (details != null) details!!.close()
        releaseId()

        Minecraft.getInstance().textureManager.release(location);
    }

    companion object {
        private var options: JXLOptions = JXLOptions().apply { this.threads = 4 }

        @Throws(IOException::class)
        fun read(imageBytes: ByteArray?, name: String): Texture {
            val pixelData: BufferedImage
            val temp: BufferedImage = if (name.endsWith("jxl")) {
                JXLDecoder(
                    ByteArrayInputStream(imageBytes),
                    options
                ).decode().asBufferedImage()
            } else {
                ImageIO.read(ByteArrayInputStream(imageBytes))
            }
            val width = temp.width
            val height = temp.height
            pixelData = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
            pixelData.graphics.drawImage(temp, 0, 0, null)
            var resource = if(name.contains(":")) {
                name.lowercase().asResource()
            } else {
                GenerationsCore.id((1..4).joinToString("") { Random.nextInt(0, 10).toString() }  + name.lowercase())
            }

            return Texture(resource, read(pixelData), pixelData.width, pixelData.height)
        }

        fun read(image: BufferedImage?): ByteBuffer? {
            if (image == null) {
                return null
            }
            val buffer = image.data.dataBuffer
            val readyData: ByteBuffer
            when (buffer) {
                is DataBufferFloat -> {
                    val rawData: FloatArray = buffer.data
                    readyData = MemoryUtil.memAlloc(rawData.size * 4)
                    for (hdrChannel in rawData) {
                        val channelValue = hdrToRgb(hdrChannel)
                        readyData.put(channelValue.toByte())
                    }
                    readyData.flip()
                }

                is DataBufferInt -> {
                    val rawData: IntArray = buffer.data
                    readyData = MemoryUtil.memAlloc(rawData.size * 4)
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
                    readyData = MemoryUtil.memAlloc(rawData.size)
                    readyData.put(rawData)
                    readyData.flip()
                }

                else -> throw RuntimeException("Unknown Data Type: " + buffer.javaClass.name)
            }
            return readyData
        }

        private fun hdrToRgb(hdr: Float): Int {
            return (hdr.toDouble().pow(1.0 / 2.2) * 255).clamp(0.0, 255.0).toInt()
        }
    }
}

private fun Double.clamp(min: Double, max: Double) = Mth.clamp(this, min, max)
