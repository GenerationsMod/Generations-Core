package generations.gg.generations.core.generationscore.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {
    @Inject(method = "enableScissor", at = @At("HEAD"), cancellable = true, remap = false)
    private static void ensableScissor(int i, int j, int k, int l, CallbackInfo ci) {
        ci.cancel();
    }
}
