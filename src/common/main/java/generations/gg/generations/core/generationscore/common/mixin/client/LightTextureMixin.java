package generations.gg.generations.core.generationscore.common.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ILightTexture;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL13C;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LightTexture.class)
public abstract class LightTextureMixin implements ILightTexture {
    @Shadow @Final private DynamicTexture lightTexture;

    @Shadow public abstract void turnOnLightLayer();

    @Override
    public void bind(int i) {
        RenderSystem.activeTexture(GL13C.GL_TEXTURE0 + i);
        turnOnLightLayer();
    }

    @Override
    public int width() {
        return 16;
    }

    @Override
    public int height() {
        return 16;
    }
}
