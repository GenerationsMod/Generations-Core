package generations.gg.generations.core.generationscore.mixin.client;

import generations.gg.generations.core.generationscore.client.render.rarecandy.ILightTexture;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LightTexture.class)
public class LightTextureMixin implements ILightTexture {
    @Shadow @Final private DynamicTexture lightTexture;

    @Override
    public int getTextureId() {
        return lightTexture.getId();
    }
}
