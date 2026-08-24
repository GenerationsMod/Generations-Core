package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.spawning.preset.BestSpawnerConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(BestSpawnerConfig.Companion.class)
public class BestSpawnConfigMixin {
    @Inject(method = "load", at = @At("RETURN"))
    public void appendLoad(CallbackInfoReturnable<BestSpawnerConfig> cir) {
        var config = cir.getReturnValue();

        Stream.of(
            config.getWorldBuckets(),
            config.getFishingBuckets(),
            config.getActivatedHabitatBuckets(),
            config.getPokeSnackBuckets()
        ).forEach(map -> {
            if(!map.containsKey("collectibles")) map.put("collectibles", 0.2f); //TODO: Figure out if this is still works.
        });
    }
}
