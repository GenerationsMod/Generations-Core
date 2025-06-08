package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.BattleRegistry;
import com.cobblemon.mod.common.client.entity.PokemonClientDelegate;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import generations.gg.generations.core.generationscore.common.client.render.tera.TeraVisualEffectHandler;
import generations.gg.generations.core.generationscore.common.client.render.tera.TeraStateTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.network.syncher.EntityDataAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = PokemonClientDelegate.class, remap = false)
public class PokemonClientDelegateMixin {
    @Shadow
    public PokemonEntity currentEntity;

    @Inject(
            method = "onSyncedDataUpdated",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Set;contains(Ljava/lang/Object;)Z",
                    shift = At.Shift.AFTER
            ),
            remap = false
    )
    public void inject(EntityDataAccessor<?> data, CallbackInfo ci) {
        var currentAspects = currentEntity.getAspects();
        var pokemon = currentEntity.getPokemon();
        var battleId = currentEntity.getBattleId();

        UUID uuid =  currentEntity.getOwnerUUID();

        if (battleId != null) {
            PokemonBattle battle = BattleRegistry.INSTANCE.getBattle(battleId);
            boolean isTera = currentAspects.contains("terastal_active");
            if (uuid == null) return;

            if (isTera && !TeraStateTracker.hasPlayed(uuid)) {
                TeraStateTracker.markPlayed(uuid);
                TeraVisualEffectHandler.playTeraAmbient(currentEntity);

                Minecraft.getInstance().execute(() -> {
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ignored) {}
                        Minecraft.getInstance().execute(() -> {
                            pokemon.setForcedAspects(currentAspects);
                            pokemon.updateForm();
                            currentEntity.refreshDimensions();
                            TeraVisualEffectHandler.spawnTeraParticles(currentEntity, pokemon.getTeraType().getId().getPath());
                            TeraVisualEffectHandler.playTeraSounds(currentEntity);
                        });
                    }).start();
                });

            } else {
                pokemon.setForcedAspects(currentAspects);
                pokemon.updateForm();
                currentEntity.refreshDimensions();
            }
        } else {
            TeraStateTracker.reset(uuid);
            pokemon.setForcedAspects(currentAspects);
            pokemon.updateForm();
            currentEntity.refreshDimensions();
        }
    }
}
