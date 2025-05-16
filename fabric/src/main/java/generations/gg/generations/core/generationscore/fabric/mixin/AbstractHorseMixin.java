package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractHorse.class)
public abstract class AbstractHorseMixin {
    @Inject(method = "executeRidersJump", at = @At("TAIL"))
    public void onJump(float playerJumpPendingScale, Vec3 travelVector, CallbackInfo ci) {
        EntityEvents.jump((Entity) (Object) this);
    }
}
