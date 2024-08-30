package generations.gg.generations.core.generationscore.common.client.render.entity

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
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

        PokemonModelRepository.variations.getOrDefault(renderable.species.resourceIdentifier, null) ?: return
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
        val model = PokemonModelRepository.getPoser(renderable.species.resourceIdentifier, renderable.aspects) as PoseableEntityModel<PokemonEntity>
        val context = model.context

        context.pop()
        context.put(RenderContext.SCALE, renderable.form.baseScale)
        context.put(RenderContext.SPECIES, renderable.species.resourceIdentifier)
        context.put(RenderContext.ASPECTS, renderable.aspects)
        context.put(RenderContext.ENTITY, entity)
        context.put(Pipelines.INSTANCE, entity.delegate.instance)
        model.getPose(entity.poseType)?.let { state.setPose(it.poseName) }

        state.updatePartialTicks(partialTicks)
        setupAnimStateful(model, entity.delegate, 0f, 0f, 0f, 0f, 0f)
        model.setLayerContext(buffer, entity.delegate, PokemonModelRepository.getLayers(entity.delegate.species(), entity.delegate.aspects()))
        val vertexConsumer = ItemRenderer.getFoilBuffer(buffer, model.getLayer(texture, emissive = false, translucent = false), false, false)
        model.render(context, stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f)

        model.resetLayerContext()

        context.pop()

        stack.popPose()

        if (shouldShowName(entity)) {
            renderNameTag(entity, entity.displayName, stack, buffer, light)
        }
    }

    fun setupAnimStateful(
        model: PoseableEntityModel<PokemonEntity>,
        state: PoseableEntityState<PokemonEntity>,
        limbSwing: Float,
        limbSwingAmount: Float,
        ageInTicks: Float,
        headYaw: Float,
        headPitch: Float
    ) {
        model.setupEntityTypeContext(null)
        state.currentModel = model
        model.setDefault()

        state.preRender()
        val poseName = state.getPose() ?: model.poses.values.first().poseName
        val currentPose = model.getPose(poseName)
        model.applyPose(poseName, 1F)

        val primaryAnimation = state.primaryAnimation

        if (currentPose != null && primaryAnimation == null) {
            // Remove any quirk animations that don't exist in our current pose
            state.quirks.keys.filterNot(currentPose.quirks::contains).forEach(state.quirks::remove)
            // Tick all the quirks
            currentPose.quirks.forEach {
                it.tick(null, model, state, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, 1F)
            }
        }

        if (primaryAnimation != null) {
            val portion = (state.animationSeconds - primaryAnimation.started) / primaryAnimation.duration
            state.primaryOverridePortion = 1 - primaryAnimation.curve(portion)
            if (!primaryAnimation.run(null, model, state, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, 1 - state.primaryOverridePortion)) {
                primaryAnimation.afterAction.accept(Unit)
                state.primaryAnimation = null
                state.primaryOverridePortion = 1F
            }
        }

        val removedStatefuls = state.statefulAnimations.toList().filterNot { it.run(null, model, state, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, 1F) }
        state.statefulAnimations.removeAll(removedStatefuls)
        state.currentPose?.let { model.getPose(it) }?.idleStateful(null, model, state, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch)
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