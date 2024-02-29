package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import gg.generations.rarecandy.legacy.pipeline.ITexture;
import gg.generations.rarecandy.legacy.pipeline.TextureReference;
import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import net.minecraft.client.renderer.texture.DynamicTexture;

import javax.imageio.ImageIO;
import java.io.IOException;

import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;

public class Texture extends DynamicTexture implements ITexture {
    public Texture(TextureReference pixels) {
        super(getFromBuffered(pixels));
    }

    private static NativeImage getFromBuffered(TextureReference image) {
        var texture = new NativeImage(NativeImage.Format.RGBA, image.width(), image.height(), true);

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                texture.setPixelRGBA(x, y, image.rgbaBytes().getInt(x + y * image.height()));
            }
        }

        return texture;
    }

    @Override
    public void bind(int slot) {
        assert slot >= 0 && slot <= 31;

        RenderSystem.activeTexture(GL_TEXTURE0 + slot);
        bind();
    }
}
