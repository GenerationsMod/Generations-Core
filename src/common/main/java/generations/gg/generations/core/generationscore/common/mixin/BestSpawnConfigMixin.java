package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.api.spawning.SpawnBucket;
import com.cobblemon.mod.common.api.spawning.preset.BestSpawnerConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BestSpawnerConfig.Companion.class)
public class BestSpawnConfigMixin {
    @Inject(method = "load", at = @At("RETURN"))
    public void appendLoad(CallbackInfoReturnable<BestSpawnerConfig> cir) {
        var buckets = cir.getReturnValue().getBuckets();

        if(buckets.stream().noneMatch(a -> a.name.equals("collectibles"))) {
            buckets.add(new SpawnBucket("collectibles", 0.2f));
        }
    }
}
