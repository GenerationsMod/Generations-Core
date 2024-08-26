package generations.gg.generations.core.generationscore.common.client.render.entity

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.client.GenerationsTextureLoader
import generations.gg.generations.core.generationscore.common.client.entity.StatueClientDelegate
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.StatueInstance
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity

class StatueEntityRenderer(arg: EntityRendererProvider.Context) : EntityRenderer<StatueEntity>(arg) {
    override fun render(
        entity: StatueEntity,
        entityYaw: Float,
        partialTicks: Float,
        stack: PoseStack,
        buffer: MultiBufferSource,
        light: Int
    ) {
        val renderable = entity.renderablePokemon()

        val variation = PokemonModelRepository.variations.getOrDefault(renderable.species.resourceIdentifier, null) ?: return
        stack.pushPose()
        stack.mulPose(Axis.YP.rotationDegrees(entity.yRot))
        stack.scale(-1f, -1f, 1f)
        val scale: Float = entity.scale
        stack.translate(0.0, -1.501 * scale, 0.0)
        stack.scale(scale, scale, scale)
        val state = entity.delegate as StatueClientDelegate
        val texture = getTextureLocation(entity)
        if (state.instance is StatueInstance) {
            if (texture.path == "pk") state.instance.setVariant(texture.toString())
            (state.instance as StatueInstance).material = entity.material
        }
        val model = PokemonModelRepository.getPoser(
            renderable.species.resourceIdentifier,
            renderable.aspects
        ) as PoseableEntityModel<PokemonEntity>
        val context = model.context
        context.pop()
        context.put<Entity>(RenderContext.ENTITY, entity)
        context.put(RenderContext.SCALE, renderable.form.baseScale)
        context.put(RenderContext.SPECIES, renderable.species.resourceIdentifier)
        context.put(RenderContext.ASPECTS, renderable.aspects)
        context.put(Pipelines.INSTANCE, state.getInstance())
        model.getPose(entity.poseType)?.let { state.setPose(it.poseName) }

        state.updatePartialTicks(partialTicks)
        model.setupAnimStateful(null, entity.delegate, 0f, 0f, 0f, 0f, 0f)
        model.setLayerContext(buffer, entity.delegate, PokemonModelRepository.getLayers(entity.delegate.species(), entity.delegate.aspects()))
        val vertexConsumer = ItemRenderer.getFoilBuffer(buffer, model.getLayer(texture, false, false), false, false)
        model.render(context, stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f)
        model.resetLayerContext()

        stack.popPose()

        if (shouldShowName(entity)) {
            renderNameTag(entity, entity.getDisplayName(), stack, buffer, light)
        }
    }

    override fun shouldShowName(entity: StatueEntity): Boolean = entity.label?.isNotBlank() == true && super.shouldShowName(entity)

//    override fun scale(livingEntity: StatueEntity, matrixStack: PoseStack, partialTickTime: Float) {
//        val species: Unit = livingEntity.getStatueData().properties.asRenderablePokemon().getForm()
//        val scale: Unit = species.getBaseScale() * livingEntity.scale
//        matrixStack.scale(scale, scale, scale)
//    }

//    override fun getAttackAnim(livingBase: StatueEntity, partialTickTime: Float): Float {
//        return 0.0f
//    }

    override fun getTextureLocation(entity: StatueEntity): ResourceLocation {
        val material = entity.material
        if (material != null && GenerationsTextureLoader.has(material)) {
            val texture: ResourceLocation? = GenerationsTextureLoader.getLocation(material = material)
            if (texture != null) return texture
        }

        val renderable = entity.renderablePokemon()

        return PokemonModelRepository.getTexture(renderable.species.resourceIdentifier, renderable.aspects, 0f)
    }
}