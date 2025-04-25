package generations.gg.generations.core.generationscore.common.client

import gg.generations.rarecandy.renderer.loading.ITexture
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11C
import org.lwjgl.opengl.GL13C
import org.lwjgl.opengl.GL30
import org.lwjgl.stb.STBImage
import org.lwjgl.system.MemoryUtil
import java.io.Closeable
import java.io.IOException
import java.nio.ByteBuffer

class Texture(private var details: TextureDetails?) : ITexture {
    var id: Int = 0

    @JvmRecord
    data class TextureDetails(val buffer: ByteBuffer, val type: Type, val width: Int, val height: Int) :
        Closeable {
        override fun close() {
            MemoryUtil.memFree(buffer)
        }

        fun init(): Int {
            val id = GL11.glGenTextures()
            GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, id)
            GL11C.glTexImage2D(
                GL11C.GL_TEXTURE_2D,
                0,
                type.internalFormat,
                width,
                height,
                0,
                type.format,
                type.type,
                buffer
            )

            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_S, GL11C.GL_REPEAT)
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_T, GL11C.GL_REPEAT)

            GL11C.glTexParameterf(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MIN_FILTER, GL11C.GL_NEAREST.toFloat())
            GL11C.glTexParameterf(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MAG_FILTER, GL11C.GL_NEAREST.toFloat())

            MemoryUtil.memFree(buffer)

            return id
        }
    }

    override fun bind(slot: Int) {
        if (details != null) {
            this.id = details!!.init()
            details = null
        }

        assert(slot >= 0 && slot <= 31)
        GL13C.glActiveTexture(GL13C.GL_TEXTURE0 + slot)
        GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, this.id)
    }

    override fun width(): Int {
        return 0
    }

    override fun height(): Int {
        return 0
    }

    @Throws(IOException::class)
    override fun close() {
        GL11.glDeleteTextures(id)
    }

    enum class Type(internal val internalFormat: Int, val format: Int, val type: Int) {
        RGBA_BYTE(GL30.GL_RGBA8, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE),
        RGB_BYTE(GL30.GL_RGB8, GL30.GL_RGB, GL30.GL_UNSIGNED_BYTE)
    }

    companion object {
        @Throws(IOException::class)
        fun read(imageBytes: ByteArray, name: String?): Texture {
            return Texture(read(imageBytes))
        }

        fun read(bytes: ByteArray): TextureDetails? {
            val imageBuffer = MemoryUtil.memAlloc(bytes.size).put(bytes).flip()

            val wBuffer = MemoryUtil.memAllocInt(1)
            val hBuffer = MemoryUtil.memAllocInt(1)
            val compBuffer = MemoryUtil.memAllocInt(1)

            // Use info to read image metadata without decoding the entire image.
            // We don't need this for this demo, just testing the API.
            if (!STBImage.stbi_info_from_memory(imageBuffer, wBuffer, hBuffer, compBuffer)) {
                return null
            }

            // Decode the image
            val image = STBImage.stbi_load_from_memory(imageBuffer, wBuffer, hBuffer, compBuffer, 0) ?: return null

            val w = wBuffer[0]
            val h = hBuffer[0]
            val comp = compBuffer[0]

            MemoryUtil.memFree(wBuffer)
            MemoryUtil.memFree(hBuffer)
            MemoryUtil.memFree(compBuffer)
            MemoryUtil.memFree(imageBuffer)

            if (comp != 3 && comp != 4) throw RuntimeException("Inccorect amount of color channels")


            return TextureDetails(image, if (comp == 3) Type.RGB_BYTE else Type.RGBA_BYTE, w, h)
        }
    }
}