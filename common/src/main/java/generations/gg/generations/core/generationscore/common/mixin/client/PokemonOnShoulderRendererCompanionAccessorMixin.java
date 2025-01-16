package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer;
import generations.gg.generations.core.generationscore.common.client.render.entity.GenerationsPokemonOnShoulderProxy;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

/**
 * A Mixin Accessor to expose the private static field "playerCache"
 * from PokemonOnShoulderRenderer.Companion.
 */
@Mixin(value = PokemonOnShoulderRenderer.Companion.class, remap = false)
public class PokemonOnShoulderRendererCompanionAccessorMixin {

    @Inject(method = "shoulderDataOf", at = @At("HEAD"), cancellable = true)
    public void of(Player player, CallbackInfoReturnable<Tuple<PokemonOnShoulderRenderer.ShoulderData, PokemonOnShoulderRenderer.ShoulderData>> cir) {
        cir.setReturnValue(GenerationsPokemonOnShoulderProxy.shoulderDataOf(player));
    }
}