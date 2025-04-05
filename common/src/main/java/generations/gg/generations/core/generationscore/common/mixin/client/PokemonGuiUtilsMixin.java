package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.client.gui.PokemonGuiUtilsKt;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PokemonGuiUtilsKt.class)
public class PokemonGuiUtilsMixin {

//    @Inject(method = "drawProfilePokemon(Lnet/minecraft/resources/ResourceLocation;Ljava/util/Set;Lcom/mojang/blaze3d/vertex/PoseStack;Lorg/joml/Quaternionf;Lcom/cobblemon/mod/common/client/render/models/blockbench/PoseableEntityState;FF)V", at = @At("HEAD"), cancellable = true)
//    private static void drawPokemonPortrait(ResourceLocation species, Set<String> aspects, PoseStack matrixStack, Quaternionf rotation, PoseableEntityState<PokemonEntity> state, float partialTicks, float scale, CallbackInfo ci) {
//        var model = PokemonModelRepository.INSTANCE.getPoser(species, aspects);
//
//        if(model.getRootPart() instanceof RareCandyBone bone) {
//            var context = new RenderContext();
//
//            RenderSystem.applyModelViewMatrix();
//            matrixStack.scale(scale, scale, -scale);
//
//            context.put(RenderContext.Companion.getSPECIES(), species);
//            context.put(RenderContext.Companion.getASPECTS(), aspects);
//            context.put(RenderContext.Companion.getRENDER_STATE(), RenderContext.RenderState.PROFILE);
//
//            bone.renderSprite(context, matrixStack, LightTexture.pack(11, 7), OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
//            Lighting.setupFor3DItems();
//            ci.cancel();
//        }
//
//    }
}
