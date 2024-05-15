package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import gg.generations.rarecandy.pokeutils.reader.TextureReference;
import gg.generations.rarecandy.renderer.loading.ITexture;
import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import net.minecraft.client.renderer.texture.DynamicTexture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;

public class Texture extends DynamicTexture implements ITexture {
    public Texture(TextureReference pixels) {
        super(getFromBuffered(pixels.data()));
    }

    private static NativeImage getFromBuffered(BufferedImage image) {
        NativeImage nativeImage = new NativeImage(image.getWidth(), image.getHeight(), true);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                nativeImage.setPixelRGBA(x, y, rgb);
            }
        }

        return nativeImage;
    }

    @Override
    public void bind(int slot) {
        assert slot >= 0 && slot <= 31;

        RenderSystem.activeTexture(GL_TEXTURE0 + slot);
        bind();
    }
}
