package generations.gg.generations.core.generationscore.common.mixin.client;

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ILightTexture;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LightTexture.class)
public abstract class LightTextureMixin implements ILightTexture {
    @Shadow @Final private DynamicTexture lightTexture;

    @Override
    public int getTextureId() {
        return lightTexture.getId();
    }
}
