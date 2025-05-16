package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "jumpFromGround", at = @At("TAIL"))
    public void onJump(CallbackInfo ci) {
        EntityEvents.jump((Entity) (Object) this);
    }
}
