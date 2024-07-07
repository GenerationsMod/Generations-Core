package generations.gg.generations.core.generationscore.client.render.entity;

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.client.GenerationsTextureLoader;
import generations.gg.generations.core.generationscore.client.render.rarecandy.StatueInstance;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class StatueEntityRenderer extends LivingEntityRenderer<StatueEntity, EntityModel<StatueEntity>> {
    public StatueEntityRenderer(EntityRendererProvider.Context arg) {
        super(arg, null, 0.0f);
    }

    @Override
    public void render(@NotNull StatueEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack stack, @Nullable MultiBufferSource buffer, int light) {
        var renderable = entity.getStatueData().asRenderablePokemon();


        var variation = PokemonModelRepository.INSTANCE.getVariations().getOrDefault(entity.getStatueData().getProperties().asRenderablePokemon().getSpecies().getResourceIdentifier(), null);
        if(variation == null) return;

        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(entity.getStatueData().getOrientation()));
        stack.scale(-1, -1, 1);
        var scale = entity.getScale();
        stack.translate(0, -1.501 * scale, 0);
        stack.scale(scale, scale, scale);


        var state = entity.delegate;

        var texture = getTextureLocation(entity);

        if(state.getInstance() instanceof StatueInstance instance) {
            if(texture.getPath().equals("pk")) instance.setVariant(texture.toString());
            instance.setMaterial(entity.getStatueData().material().toString());
        }

        var model = (PoseableEntityModel<PokemonEntity>)PokemonModelRepository.INSTANCE.getPoser(renderable.getSpecies().getResourceIdentifier(), renderable.getAspects());

        var context = model.getContext();

        context.pop();
        context.put(RenderContext.Companion.getENTITY(), entity);
        context.put(RenderContext.Companion.getSCALE(), scale);
        context.put(RenderContext.Companion.getSPECIES(), entity.species());
        context.put(RenderContext.Companion.getASPECTS(), entity.aspects());

        var pose = model.getPose(entity.getStatueData().getPoseType());
        if(pose != null) state.setPose(pose.getPoseName());

        state.updatePartialTicks(partialTicks);
        model.setupAnimStateful(null, entity.delegate, 0, 0F, 0F, 0F, 0F);
        model.setLayerContext(buffer, entity.delegate, PokemonModelRepository.INSTANCE.getLayers(entity.getStatueData().asRenderablePokemon().getSpecies().getResourceIdentifier(), entity.getStatueData().getProperties().getAspects()));
        var vertexConsumer = ItemRenderer.getFoilBuffer(buffer, model.getLayer(texture, false, false), false, false);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        model.render(context, stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);


        model.resetLayerContext();
        stack.popPose();

        if (this.shouldShowName(entity)) {
            this.renderNameTag(entity, entity.getDisplayName(), stack, buffer, light);
        }
    }

    @Override
    protected void scale(StatueEntity livingEntity, PoseStack matrixStack, float partialTickTime) {
        var species = livingEntity.getStatueData().getProperties().asRenderablePokemon().getForm();
        var scale = species.getBaseScale();
        matrixStack.scale(scale, scale, scale);
    }

    @Override
    protected float getAttackAnim(StatueEntity livingBase, float partialTickTime) {
        return 0.0f;
    }

    @Override


    public @NotNull ResourceLocation getTextureLocation(@NotNull StatueEntity entity) {
//        var state = entity.getStatueData();
//
//        if(state.material() != null && state.material().getNamespace().equals("statue") && ITextureLoader.instance().getTextureEntries().contains(state.material().getPath())) {
//            var texture = ((GenerationsTextureLoader) ITextureLoader.instance()).getLocation(state.material().getPath());
//
//            if(texture != null) return texture;
//
//        }

        return PokemonModelRepository.INSTANCE.getTexture(entity.species(), entity.aspects(), 0f);
    }
}