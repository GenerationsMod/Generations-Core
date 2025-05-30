package generations.gg.generations.core.generationscore.common.mixin.showdown;

import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.interpreter.instructions.TerastallizeInstruction;
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TerastallizeInstruction.class)
public abstract class TerastallizeInstructionMixin {
    @Shadow(remap = false) public abstract BattleMessage getMessage();

    @Inject(method = "invoke", at = @At("TAIL"), remap = false)
    private void weatherCount(PokemonBattle battle, CallbackInfo ci) {
        //GenerationsInstructionProcessor.processTerastillization(battle, getMessage());
    }
}
