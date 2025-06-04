package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.battles.BattleFormat;
import com.cobblemon.mod.common.battles.BattleSide;
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(PokemonBattle.class)
public abstract class PokemonBattleMixin {

    @Shadow public abstract void setMute(boolean b);

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void onConstruct(BattleFormat format, BattleSide side1, BattleSide side2, CallbackInfo ci) {
        this.setMute(false);
    }

    @Inject(method = "end", at = @At("TAIL"), remap = false)
    private void injectEnd(CallbackInfo ci) {
        GenerationsInstructionProcessor.processBattleEnd((PokemonBattle) (Object) this);
    }

}