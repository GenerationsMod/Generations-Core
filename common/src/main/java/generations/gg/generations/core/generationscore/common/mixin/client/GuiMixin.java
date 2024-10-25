package generations.gg.generations.core.generationscore.common.mixin.client;

import generations.gg.generations.core.generationscore.common.client.screen.Overlays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Shadow @Final protected Minecraft minecraft;

    @Shadow protected int screenWidth;

    @Shadow protected int screenHeight;

    @Inject(method = "render", at = @At("HEAD"))
    public void render(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Overlays.render(minecraft, guiGraphics, partialTick, guiGraphics.guiWidth(), guiGraphics.guiHeight());
    }
}
