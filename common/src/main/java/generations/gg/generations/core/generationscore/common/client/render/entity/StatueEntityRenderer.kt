package generations.gg.generations.core.generationscore.common.client.render.entity

import com.cobblemon.mod.common.client.render.ModelLayer
import com.cobblemon.mod.common.client.render.ModelTextureSupplier
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.client.GenerationsTextureLoader
import generations.gg.generations.core.generationscore.common.client.entity.StatueClientDelegate
import generations.gg.generations.core.generationscore.common.client.model.RareCandyBone
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.StatueInstance
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

object ModifiableTextureSupplier : ModelTextureSupplier {
    var texture = MissingTextureAtlasSprite.getLocation()
    override fun invoke(animationSeconds: Float): ResourceLocation {
        return texture
    }

}

private fun <T:ModelTextureSupplier> ModelLayer.setTextureSupplier(supplier: T): ModelLayer {
    val textureField = ModelLayer::class.java.getDeclaredField("texture")
    textureField.isAccessible = true
    textureField.set(this, supplier)

    return this
}

class StatueEntityRenderer(arg: EntityRendererProvider.Context) : EntityRenderer<StatueEntity>(arg) {


    val modelLayer = ModelLayer().setTextureSupplier(ModifiableTextureSupplier)
    val layerList = mutableListOf(modelLayer)

    override fun render(
        entity: StatueEntity,
        entityYaw: Float,
        partialTicks: Float,
        stack: PoseStack,
        buffer: MultiBufferSource,
        light: Int,
    ) {
        val renderable = entity.renderablePokemon() ?: return

        PokemonModelRepository.variations.getOrDefault(renderable.species.resourceIdentifier, null) ?: return
        stack.pushPose()
        stack.mulPose(Axis.YP.rotationDegrees(entity.orientation))
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

        var yOffset = renderable.form.hitbox.height * scale

        context.pop()
        context.put(RenderContext.SCALE, renderable.form.baseScale)
        context.put(RenderContext.SPECIES, renderable.species.resourceIdentifier)
        context.put(RenderContext.ASPECTS, renderable.aspects)
        context.put(RenderContext.ENTITY, entity)
        context.put(Pipelines.INSTANCE, entity.delegate.instance)
        model.getPose(entity.poseType)?.let { state.setPose(it.poseName) }

        state.updatePartialTicks(partialTicks)
        setupAnimStateful(model, entity.delegate, 0f, 0f, 0f, 0f, 0f)

        var materialLocation = entity.material?.let { GenerationsTextureLoader.getLocation(it) }.also { ModifiableTextureSupplier.texture = it }

        model.setLayerContext(buffer, entity.delegate, if(materialLocation == null) PokemonModelRepository.getLayers(entity.delegate.species(), entity.delegate.aspects()) else layerList)
        val vertexConsumer = ItemRenderer.getFoilBuffer(buffer, if(materialLocation == null) model.getLayer(texture, emissive = false, translucent = false) else materialLocation.type(), false, false)
        model.render(context, stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f)

        model.resetLayerContext()

        context.pop()

        stack.popPose()

        if (this.shouldShowName(entity)) {
            this.renderNameTag(entity, entity.displayName, stack, buffer, light, yOffset)
        }
    }

    fun renderNameTag(
        entity: StatueEntity,
        displayName: Component,
        poseStack: PoseStack,
        buffer: MultiBufferSource?,
        packedLight: Int,
        yOffsetY: Float,
    ) {
        val d = entityRenderDispatcher.distanceToSqr(entity)
        if (!(d > 4096.0)) {
            val bl: Boolean = !entity.isDiscrete()
            val f: Float = yOffsetY
            val i = if ("deadmau5" == displayName.string) -10 else 0
            poseStack.pushPose()
            poseStack.translate(0.0f, f, 0.0f)
            poseStack.mulPose(entityRenderDispatcher.cameraOrientation())
            poseStack.scale(-0.025f, -0.025f, 0.025f)
            val matrix4f = poseStack.last().pose()
            val g = Minecraft.getInstance().options.getBackgroundOpacity(0.25f)
            val j = (g * 255.0f).toInt() shl 24
            val font = this.font
            val h = (-font.width(displayName) / 2).toFloat()
            font.drawInBatch(
                displayName,
                h,
                i.toFloat(),
                553648127,
                false,
                matrix4f,
                buffer,
                if (bl) Font.DisplayMode.SEE_THROUGH else Font.DisplayMode.NORMAL,
                j,
                packedLight
            )
            if (bl) {
                font.drawInBatch(
                    displayName,
                    h,
                    i.toFloat(),
                    -1,
                    false,
                    matrix4f,
                    buffer,
                    Font.DisplayMode.NORMAL,
                    0,
                    packedLight
                )
            }

            poseStack.popPose()
        }
    }

    fun setupAnimStateful(
        model: PoseableEntityModel<PokemonEntity>,
        state: PoseableEntityState<PokemonEntity>,
        limbSwing: Float,
        limbSwingAmount: Float,
        ageInTicks: Float,
        headYaw: Float,
        headPitch: Float,
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

        val renderable = entity.renderablePokemon() ?: return MissingTextureAtlasSprite.getLocation()

        return PokemonModelRepository.getTexture(renderable.species.resourceIdentifier, renderable.aspects, 0f)
    }

    override fun shouldShowName(entity: StatueEntity): Boolean {
        return entity.label?.let { it.isNotEmpty() && it.isNotBlank() } ?: false
    }
}

private fun ResourceLocation.type(): RenderType = RenderType.entitySolid(this)
