package generations.gg.generations.core.generationscore.common.mixin.showdown;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.battles.ShowdownActionRequest;
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShowdownActionRequest.class)
public class ShowdownActionRequestMixin {
//    @Inject(method = "sanitize", at = @At("HEAD"), remap = false, cancellable = true)
//    public void sanitize(PokemonBattle battle, BattleActor battleActor, CallbackInfo ci) {
//        GenerationsInstructionProcessor.processRequestSanitize(battle, battleActor, (ShowdownActionRequest) (Object) this);
//    }
}
