package generations.gg.generations.core.generationscore.common.client;

import com.mojang.blaze3d.pipeline.RenderCall;
import com.mojang.blaze3d.systems.RenderSystem;
import gg.generations.rarecandy.renderer.loading.ITexture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL13C;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;

public class Texture implements ITexture {
    private byte[] bytes;
    public int id;

    public Texture(byte[] bytes) {
        this.bytes = bytes;
    }

//    private int generateAndUpload() {
//        var id = -1;
//
//        ByteBuffer imageBuffer = MemoryUtil.memAlloc(bytes.length).put(bytes).flip();
//
//        IntBuffer wBuffer = MemoryUtil.memAllocInt(1);
//        IntBuffer hBuffer = MemoryUtil.memAllocInt(1);
//        IntBuffer compBuffer = MemoryUtil.memAllocInt(1);
//
//        // Use info to read image metadata without decoding the entire image.
//        // We don't need this for this demo, just testing the API.
//        if (!stbi_info_from_memory(imageBuffer, wBuffer, hBuffer, compBuffer)) {
//            MemoryUtil.memFree(wBuffer);
//            MemoryUtil.memFree(hBuffer);
//            MemoryUtil.memFree(compBuffer);
//            MemoryUtil.memFree(imageBuffer);
//            return -1;
//        }
//
//        // Decode the image
//        var image = stbi_load_from_memory(imageBuffer, wBuffer, hBuffer, compBuffer, 0);
//        if (image == null) {
//            MemoryUtil.memFree(wBuffer);
//            MemoryUtil.memFree(hBuffer);
//            MemoryUtil.memFree(compBuffer);
//            MemoryUtil.memFree(imageBuffer);
//            return -1;
//        }
//
//        var width = wBuffer.get(0);
//        var height = hBuffer.get(0);
//        var comp = compBuffer.get(0);
//
//        MemoryUtil.memFree(wBuffer);
//        MemoryUtil.memFree(hBuffer);
//        MemoryUtil.memFree(compBuffer);
//
//        if (comp != 3 && comp != 4) throw new RuntimeException("Inccorect amount of color channels");
//        var type = comp == 3 ? Type.RGB_BYTE : Type.RGBA_BYTE;
//
//        id = GL11.glGenTextures();
//        GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, id);
//        GL11C.glTexImage2D(GL11C.GL_TEXTURE_2D, 0, type.internalFormat, width, height, 0, type.format, type.type, imageBuffer);
//
//        GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_S, GL11C.GL_REPEAT);
//        GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_T, GL11C.GL_REPEAT);
//
//        GL11C.glTexParameterf(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MIN_FILTER, GL11C.GL_NEAREST);
//        GL11C.glTexParameterf(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MAG_FILTER, GL11C.GL_NEAREST);
//
//        MemoryUtil.memFree(imageBuffer);
//
//
//        return id;
//    }

    private int generateAndUpload() {
        int id = -1;

        // Allocate and initialize ByteBuffer
        ByteBuffer imageBuffer = MemoryUtil.memAlloc(bytes.length).put(bytes).flip();

        IntBuffer wBuffer = MemoryUtil.memAllocInt(1);
        IntBuffer hBuffer = MemoryUtil.memAllocInt(1);
        IntBuffer compBuffer = MemoryUtil.memAllocInt(1);

        try {
            // Check image metadata
            if (!stbi_info_from_memory(imageBuffer, wBuffer, hBuffer, compBuffer)) {
                throw new RuntimeException("Failed to read image info: " + stbi_failure_reason());
            }

            // Decode the image
            ByteBuffer image = stbi_load_from_memory(imageBuffer, wBuffer, hBuffer, compBuffer, 0);
            if (image == null) {
                throw new RuntimeException("Failed to load image: " + stbi_failure_reason());
            }

            int width = wBuffer.get(0);
            int height = hBuffer.get(0);
            int comp = compBuffer.get(0);

            // Determine texture format
            Type type = switch (comp) {
                case 3 -> Type.RGB_BYTE;
                case 4 -> Type.RGBA_BYTE;
                default -> throw new RuntimeException("Unsupported color channel count: " + comp);
            };

            // Generate texture
            id = GL11.glGenTextures();
            GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, id);

            // Upload texture data
            GL11C.glTexImage2D(GL11C.GL_TEXTURE_2D, 0, type.internalFormat, width, height, 0, type.format, type.type, image);

            // Set texture parameters
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_S, GL11C.GL_REPEAT);
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_T, GL11C.GL_REPEAT);
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MIN_FILTER, GL11C.GL_NEAREST);
            GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MAG_FILTER, GL11C.GL_NEAREST);

            // Free the decoded image
            stbi_image_free(image);
        } finally {
            // Ensure buffers are freed
            MemoryUtil.memFree(wBuffer);
            MemoryUtil.memFree(hBuffer);
            MemoryUtil.memFree(compBuffer);
            MemoryUtil.memFree(imageBuffer);
        }

        return id;
    }

    public void init() {
        if(bytes != null) {
//            Texture.this.id = generateAndUpload();
//            Texture.this.bytes = null;

            RenderSystem.assertInInitPhase();
                this.id = generateAndUpload();
                bytes = null;
//            } else RenderSystem.recordRenderCall(() -> {
//                Texture.this.id = generateAndUpload();
//                Texture.this.bytes = null;
//            });

        }
    }

    public void bind(int slot) {
        init();

        assert (slot >= 0 && slot <= 31);
        GL13C.glActiveTexture(GL13C.GL_TEXTURE0 + slot);
        GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, this.id);
    }

    @Override
    public int width() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public void close() throws IOException {
        GL11.glDeleteTextures(id);
    }

    public static Texture read(byte[] imageBytes) throws IOException {
        return new Texture(imageBytes);
    }

    public enum Type {
        RGBA_BYTE(GL30.GL_RGBA8, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE),
        RGB_BYTE(GL30.GL_RGB8, GL30.GL_RGB, GL30.GL_UNSIGNED_BYTE);

        private final int internalFormat;
        private final int format;
        private final int type;

        Type(int internalFormat, int format, int type) {
            this.internalFormat = internalFormat;
            this.format = format;
            this.type = type;
        }
    }
}