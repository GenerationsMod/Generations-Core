package generations.gg.generations.core.generationscore.client.render.entity;

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.model.RareCandyBone;
import generations.gg.generations.core.generationscore.client.render.rarecandy.LightingSettings;
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import static net.minecraft.client.renderer.texture.MissingTextureAtlasSprite.getTexture;

public class StatueEntityRenderer extends LivingEntityRenderer<StatueEntity, EntityModel<StatueEntity>> {
    public StatueEntityRenderer(EntityRendererProvider.Context arg) {
        super(arg, null, 0.0f);
    }

    @Override
    public void render(@NotNull StatueEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack stack, @Nullable MultiBufferSource buffer, int light) {
        var model = (PoseableEntityModel<PokemonEntity>)PokemonModelRepository.INSTANCE.getPoser(entity.getStatueData().getProperties().asRenderablePokemon().getSpecies().getResourceIdentifier(), entity.getStatueData().getProperties().getAspects());
        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(entityYaw));
        stack.scale(-1, -1, 1);
        var scale = entity.getScale();
        stack.translate(0, -1.501 * scale, 0);
        stack.scale(scale, scale, scale);
        var state = entity.delegate;

        state.updatePartialTicks(partialTicks);
        model.setupAnimStateful(null, state, 0.f, 0.0f, partialTicks, 0.0f,0.0f);
        model.setLayerContext(buffer, entity.delegate, PokemonModelRepository.INSTANCE.getLayers(entity.getStatueData().getProperties().asRenderablePokemon().getSpecies().getResourceIdentifier(), entity.getStatueData().getProperties().getAspects()));
        var vertexConsumer = ItemRenderer.getFoilBuffer(buffer, model.getLayer(getTextureLocation(entity), false, false), false, false);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        RareCandyBone.statueProxy = entity;
        model.renderModel(null, stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        RareCandyBone.statueProxy = null;

        model.resetLayerContext();
        stack.popPose();
//        super.render(entity, entityYaw, partialTicks, stack, buffer, light);
    }

//    @Override
//    protected void scale(StatueEntity livingEntity, PoseStack matrixStack, float partialTickTime) {
//        var species = livingEntity.getStatueData().getProperties().asRenderablePokemon().getForm();
//        var scale = species.getBaseScale();
//        matrixStack.scale(scale, scale, scale)
//    }

    @Override
    protected float getAttackAnim(StatueEntity livingBase, float partialTickTime) {
        return 0.0f;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull StatueEntity entity) {
        return PokemonModelRepository.INSTANCE.getTexture(entity.getStatueData().getProperties().asRenderablePokemon().getSpecies().getResourceIdentifier(), entity.getStatueData().getProperties().getAspects(), entity.delegate);
    }
}