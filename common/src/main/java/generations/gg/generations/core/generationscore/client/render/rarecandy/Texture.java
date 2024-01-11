package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import gg.generations.rarecandy.pokeutils.reader.TextureReference;
import gg.generations.rarecandy.renderer.loading.ITexture;
import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13C;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;

public class Texture extends DynamicTexture implements ITexture {
    public Texture(TextureReference pixels) {
        super(getFromBuffered(pixels.data()));
    }

    private static NativeImage getFromBuffered(BufferedImage image) {
        try (FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()) {
            ImageIO.write(image, "PNG", outputStream);
            return NativeImage.read(new FastByteArrayInputStream(outputStream.array));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void bind(int slot) {
        assert slot >= 0 && slot <= 31;

        RenderSystem.activeTexture(GL_TEXTURE0 + slot);
        bind();
    }
}
