package generations.gg.generations.core.generationscore.common.mixin.client;

import generations.gg.generations.core.generationscore.common.client.screen.CameraOverlay;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        if(CameraOverlay.renderCamera(guiGraphics, partialTick)) ci.cancel();
    }
}
