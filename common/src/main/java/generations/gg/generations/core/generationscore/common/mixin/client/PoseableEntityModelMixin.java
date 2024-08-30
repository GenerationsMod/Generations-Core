package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel;
import generations.gg.generations.core.generationscore.common.client.model.RareCandyAnimationFactory;
import kotlin.jvm.functions.Function1;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PoseableEntityModel.class)
public abstract class PoseableEntityModelMixin {

    @Inject(method = "<init>(Lkotlin/jvm/functions/Function1;)V", at = @At("TAIL"))
    public void init(Function1 renderTypeFunc, CallbackInfo ci) {
        RareCandyAnimationFactory.addAnimationFunctions((PoseableEntityModel) (Object) this);
    }
}
