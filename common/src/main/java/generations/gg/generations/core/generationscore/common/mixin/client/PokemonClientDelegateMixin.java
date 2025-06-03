package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.entity.PokemonClientDelegate;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PokemonClientDelegate.class, remap = false)
public class PokemonClientDelegateMixin {
    @Shadow public PokemonEntity entity;
    @Inject(method = "onSyncedDataUpdated", at = @At(value = "INVOKE", target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z", shift= At.Shift.AFTER), remap = false)
    public void inject(EntityDataAccessor<?> data, CallbackInfo ci) {
        System.out.println("working");
        var currentAspects = entity.getAspects();
        var pokemon = entity.getPokemon();
        pokemon.setForcedAspects(currentAspects);
        pokemon.updateForm();
//        entity.calculateDimensions();
    }
}
