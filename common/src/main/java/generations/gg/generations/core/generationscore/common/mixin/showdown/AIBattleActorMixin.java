package generations.gg.generations.core.generationscore.common.mixin.showdown;

import com.cobblemon.mod.common.api.battles.model.actor.AIBattleActor;
import generations.gg.generations.core.generationscore.common.battle.AIBattleActorProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AIBattleActor.class)
public abstract class AIBattleActorMixin {

    @Inject(method = "onChoiceRequested", at = @At("HEAD"), cancellable = true)
    private void generationscore$proxyOnChoiceRequested(CallbackInfo ci) {
        AIBattleActor self = (AIBattleActor)(Object)this;
        AIBattleActorProxy.INSTANCE.onChoiceRequestedd(self);
        ci.cancel();
    }
}
