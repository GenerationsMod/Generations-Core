package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.item.ability.AbilityChanger;
import com.cobblemon.mod.common.item.interactive.ability.AbilityTypeChanger;
import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbilityTypeChanger.class)
public class AbilityChangerMixin {


    @Inject(method = "performChange", at = @At("HEAD"), cancellable = true)
    public void onChange(Pokemon pokemon, CallbackInfoReturnable<Boolean> cir) {
        if(pokemon.getSpecies().resourceIdentifier.equals(LegendKeys.ZYGARDE.species())) {
            cir.setReturnValue(false);
        }
    }
}
