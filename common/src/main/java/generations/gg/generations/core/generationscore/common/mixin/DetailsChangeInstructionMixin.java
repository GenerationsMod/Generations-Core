package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.battles.interpreter.instructions.DetailsChangeInstruction;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.mojang.datafixers.util.Unit;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.battle.GenerationsInstructionProcessor;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DetailsChangeInstruction.class)
public abstract class DetailsChangeInstructionMixin {
    @Shadow(remap = false) public abstract BattleMessage getMessage();

    @Inject(method = "invoke", at = @At("TAIL"), remap = false)
    private void invokeStuff(PokemonBattle battle, CallbackInfo ci) {
        GenerationsInstructionProcessor.processDetailsChange(battle, getMessage());
    }
}
