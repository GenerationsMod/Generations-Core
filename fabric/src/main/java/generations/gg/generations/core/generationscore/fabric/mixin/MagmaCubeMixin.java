package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.api.events.general.EntityEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.MagmaCube;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MagmaCube.class)
public abstract class MagmaCubeMixin {
    @Inject(method = "jumpFromGround", at = @At("TAIL"))
        public void onJump(CallbackInfo ci) {
            EntityEvents.JUMP.invoker().jump((Entity) (Object) this);
        }

}
