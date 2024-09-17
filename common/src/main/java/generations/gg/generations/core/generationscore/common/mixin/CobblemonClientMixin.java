package generations.gg.generations.core.generationscore.common.mixin;

import com.cobblemon.mod.common.client.CobblemonClient;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CobblemonClient.class)
public class CobblemonClientMixin {

    @Inject(method = "reloadCodedAssets", at = @At("TAIL"))
    public void registerStrangeBall(ResourceManager resourceManager, CallbackInfo ci) {

    }
}
