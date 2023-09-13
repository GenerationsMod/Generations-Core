package generations.gg.generations.core.generationscore.client.render.entity;

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import com.cobblemon.mod.common.entity.PoseType;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.GenerationsCore;
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
    public static final ResourceLocation CONCRETE = GenerationsCore.id("textures/key/statue_material/concrete.png");
    public StatueEntityRenderer(EntityRendererProvider.Context arg) {
        super(arg, null, 0.0f);
    }

    public RenderContext context = new RenderContext();
    public static RenderContext.Key<StatueEntity> STATUE = RenderContext.Companion.key(GenerationsCore.id("statue"), StatueEntity.class);

    @Override
    public void render(@NotNull StatueEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack stack, @Nullable MultiBufferSource buffer, int light) {
        var renderable = entity.getStatueData().asRenderablePokemon();

        var model = (PoseableEntityModel<PokemonEntity>)PokemonModelRepository.INSTANCE.getPoser(renderable.getSpecies().getResourceIdentifier(), renderable.getAspects());

        var variation = PokemonModelRepository.INSTANCE.getVariations().getOrDefault(entity.getStatueData().getProperties().asRenderablePokemon().getSpecies().getResourceIdentifier(), null);
        if(variation == null) return;
        var texture = variation.getTexture(entity.getStatueData().getProperties().getAspects(), 0.0f);

        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(entityYaw));
        stack.scale(-1, -1, 1);
        var scale = entity.getScale();
        stack.translate(0, -1.501 * scale, 0);
        stack.scale(scale, scale, scale);
        var state = entity.delegate;

//        key.delegate.getInstance().setVariant("statue:concrete");

        var pose = model.getPose(PoseType.PROFILE);
        if(pose != null) state.setPose(pose.getPoseName());
        state.setTimeEnteredPose(0F);
        if(entity.getStatueData().isAnimated()) state.updatePartialTicks(partialTicks);
        else state.setCurrentTicks(entity.getStatueData().getFrame());
        model.setupAnimStateful(null, state, 0F, 0F, 0F, 0F, 0F);

        model.setLayerContext(buffer, entity.delegate, PokemonModelRepository.INSTANCE.getLayers(entity.getStatueData().getProperties().asRenderablePokemon().getSpecies().getResourceIdentifier(), entity.getStatueData().getProperties().getAspects()));
        var vertexConsumer = ItemRenderer.getFoilBuffer(buffer, model.getLayer(getTextureLocation(entity), false, false), false, false);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        context.pop();
        context.put(RenderContext.Companion.getRENDER_STATE(), RenderContext.RenderState.WORLD);
        context.put(STATUE, entity);
        context.put(RenderContext.Companion.getSCALE(), scale);
        context.put(RenderContext.Companion.getTEXTURE(), texture);

        model.render(context, stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);


        model.resetLayerContext();
        stack.popPose();

        if (this.shouldShowName(entity)) {
            this.renderNameTag(entity, entity.getDisplayName(), stack, buffer, light);
        }

//        super.render(key, entityYaw, partialTicks, stack, buffer, light);
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
//        return CONCRETE;
        var renderable = entity.getStatueData().asRenderablePokemon();
        return PokemonModelRepository.INSTANCE.getTexture(renderable.getSpecies().getResourceIdentifier(), renderable.getAspects(), entity.delegate);
    }
}