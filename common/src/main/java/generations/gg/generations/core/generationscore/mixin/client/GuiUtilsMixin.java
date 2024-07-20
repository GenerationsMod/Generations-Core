package generations.gg.generations.core.generationscore.mixin.client;

import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
import com.cobblemon.mod.common.client.gui.battle.BattleOverlay;
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Species;
import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.client.model.RareCandyBone;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(GuiUtilsKt.class)
public class GuiUtilsMixin {

    @Inject(method = "drawPortraitPokemon", at = @At("HEAD"), remap = false, cancellable = true)
    private static void drawPokemonPortrait(Species species, Set<String> aspects, PoseStack matrixStack, float scale, boolean reversed, PoseableEntityState<PokemonEntity> state, float partialTicks, CallbackInfo ci) {
        var model = PokemonModelRepository.INSTANCE.getPoser(species.resourceIdentifier, aspects);

        if(model.getRootPart() instanceof RareCandyBone bone) {
            var context = new RenderContext();

            matrixStack.pushPose();
            matrixStack.translate(0.0, BattleOverlay.PORTRAIT_DIAMETER + 2.0, 0.0);
            matrixStack.scale(scale, scale, -scale);
            matrixStack.translate(0.0, -BattleOverlay.PORTRAIT_DIAMETER / 18.0, 0.0);
            matrixStack.translate(0, 0, -4);


            context.put(RenderContext.Companion.getSPECIES(), species.getResourceIdentifier());
            context.put(RenderContext.Companion.getASPECTS(), aspects);
            context.put(RenderContext.Companion.getRENDER_STATE(), RenderContext.RenderState.PORTRAIT);


            bone.renderSprite(context, matrixStack, LightTexture.pack(11, 7), OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

            matrixStack.popPose();
            ci.cancel();
        }

    }

}
