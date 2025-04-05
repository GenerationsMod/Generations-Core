package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiUtilsKt.class)
public class GuiUtilsMixin {
    //TODO: purge once resource packs are converted to using cobblemon way now.
//    @Inject(method = "drawPortraitPokemon", at = @At("HEAD"), cancellable = true)
//    private static void drawPokemonPortrait(Species species, Set<String> aspects, PoseStack matrixStack, float scale, boolean reversed, PoseableEntityState<PokemonEntity> state, float partialTicks, CallbackInfo ci) {
//        var model = PokemonModelRepository.INSTANCE.getPoser(species.resourceIdentifier, aspects);
//
//        if(model.getRootPart() instanceof RareCandyBone bone) {
//            var context = new RenderContext();
//
//            matrixStack.pushPose();
//            matrixStack.translate(0.0, BattleOverlay.PORTRAIT_DIAMETER + 2.0, 0.0);
//            matrixStack.scale(scale, scale, -scale);
//            matrixStack.translate(0.0, -BattleOverlay.PORTRAIT_DIAMETER / 18.0, 0.0);
//            matrixStack.translate(0, 0, -4);
//
//
//            context.put(RenderContext.Companion.getSPECIES(), species.getResourceIdentifier());
//            context.put(RenderContext.Companion.getASPECTS(), aspects);
//            context.put(RenderContext.Companion.getRENDER_STATE(), RenderContext.RenderState.PORTRAIT);
//
//
//            bone.renderSprite(context, matrixStack, LightTexture.pack(11, 7), OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
//
//            matrixStack.popPose();
//            ci.cancel();
//        }
//
//    }

}
