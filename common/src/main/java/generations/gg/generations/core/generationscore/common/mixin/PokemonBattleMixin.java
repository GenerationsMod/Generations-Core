package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(PokemonBattle.class)
public abstract class PokemonBattleMixin {

    @Inject(method = "end", at = @At("TAIL"), remap = false)
    private void injectEnd(CallbackInfo ci) {
        GenerationsInstructionProcessor.processBattleEnd((PokemonBattle) (Object) this);
    }

}