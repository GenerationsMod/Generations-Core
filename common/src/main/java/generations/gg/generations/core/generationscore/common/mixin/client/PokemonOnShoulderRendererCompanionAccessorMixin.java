package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer;
import org.spongepowered.asm.mixin.Mixin;

/**
 * A Mixin Accessor to expose the private static field "playerCache"
 * from PokemonOnShoulderRenderer.Companion.
 */
@Mixin(value = PokemonOnShoulderRenderer.Companion.class, remap = false)
public class PokemonOnShoulderRendererCompanionAccessorMixin {

//    @Inject(method = "shoulderDataOf", at = @At("HEAD"), cancellable = true)
//    public void of(Player player, CallbackInfoReturnable<Tuple<PokemonOnShoulderRenderer.ShoulderData, PokemonOnShoulderRenderer.ShoulderData>> cir) {
//        cir.setReturnValue(GenerationsPokemonOnShoulderProxy.shoulderDataOf(player));
//    }
}