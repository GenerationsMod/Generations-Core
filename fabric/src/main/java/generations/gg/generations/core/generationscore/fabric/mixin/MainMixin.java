package generations.gg.generations.core.generationscore.fabric.mixin;

import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
public class MainMixin {

    @Unique
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void kiollME(CallbackInfo ci) {
        System.loadLibrary("renderdoc");
    }
}
