package generations.gg.generations.core.generationscore.fabric.mixin;

import com.cobblemon.mod.common.battles.ShowdownThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Skips Cobblemon's GraalJS-backed Showdown service during data generation.
 */
@Mixin(ShowdownThread.class)
public class ShowdownThreadMixin {
    @Inject(method = "launch", at = @At("HEAD"), cancellable = true)
    private void generations$skipShowdownDuringDatagen(CallbackInfo ci) {
        if (System.getProperty("fabric-api.datagen") != null) {
            ci.cancel();
        }
    }
}
