package generations.gg.generations.core.generationscore.fabric.mixin.client;

import generations.gg.generations.core.generationscore.common.client.model.Keybinds;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "keyPress", at = @At("RETURN"), cancellable = true)
    public void onRawKey(long handle, int key, int scanCode, int action, int modifiers, CallbackInfo info) {
        if (handle == this.minecraft.getWindow().getWindow()) {
            if (Keybinds.INSTANCE.pressDown(key, scanCode, action, modifiers)) info.cancel();
        }
    }
}
