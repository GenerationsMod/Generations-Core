package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.moves.MoveTemplate;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.pokemon.Pokemon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MoveTemplate.class)
public class MoveTypeMixin {

    @Inject(method = "getEffectiveElementalType", at = @At("HEAD"), cancellable = true)
    private void ivyCudgelTypeFix(Pokemon pokemon, CallbackInfoReturnable<ElementalType> cir) {
        if (pokemon == null) return;

        String moveName = ((MoveTemplate)(Object)this).getName();
        if (!moveName.equalsIgnoreCase("ivy_cudgel")) return;

        String matchedAspect = null;
        for (String aspect : pokemon.getAspects()) {
            if (aspect.endsWith("-mask")) {
                matchedAspect = aspect;
                break;
            }
        }

        String baseAspect = matchedAspect.substring(0, matchedAspect.length() - 5);

        ElementalType newType = switch (baseAspect) {
            case "wellspring" -> ElementalTypes.INSTANCE.getWATER();
            case "hearthflame" -> ElementalTypes.INSTANCE.getFIRE();
            case "cornerstone" -> ElementalTypes.INSTANCE.getROCK();
            default -> ElementalTypes.INSTANCE.getGRASS();
        };

        cir.setReturnValue(newType);
    }
}
