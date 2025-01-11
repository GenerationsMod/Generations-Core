package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.abilities.PotentialAbility;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.pokemon.FormData;
import com.cobblemon.mod.common.pokemon.Species;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

@Mixin(PokemonSpecies.ShowdownSpecies.class)
public abstract class ShowdownSpeciesMixin {

    @Unique
    private static final String NO_ABILITY = "No Ability";

    @Shadow(remap = false)
    @Final
    @Mutable
    private Map<String, String> abilities;

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void injectConstructor(Species species, FormData form, CallbackInfo ci) {
        if (form == null || Stream.of("mega", "mega_x", "mega_y").noneMatch(aspect -> form.getAspects().contains(aspect))) return;

        Iterator<PotentialAbility> abilityIterator = form.getAbilities().iterator();
        abilities = Map.of("0", abilityIterator.hasNext() ? abilityIterator.next().getTemplate().getName() : NO_ABILITY);
    }

}