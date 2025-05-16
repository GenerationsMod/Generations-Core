package generations.gg.generations.core.generationscore.fabric.mixin;

import com.cobblemon.mod.common.api.Priority;
import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
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
            EntityEvents.jump((Entity) (Object) this);
        }

}
