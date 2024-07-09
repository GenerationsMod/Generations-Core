package generations.gg.generations.core.generationscore.client.render.entity

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.client.GenerationsTextureLoader
import generations.gg.generations.core.generationscore.client.render.rarecandy.Pipelines
import generations.gg.generations.core.generationscore.client.render.rarecandy.StatueInstance
import generations.gg.generations.core.generationscore.world.entity.StatueEntity
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader
import net.minecraft.client.model.EntityModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.ItemRenderer
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity

class StatueEntityRenderer(arg: EntityRendererProvider.Context) : EntityRenderer<StatueEntity>(arg) {

    override fun getTextureLocation(entity: StatueEntity) = entity.material?.takeIf { GenerationsTextureLoader.textureEntries.contains(it) }?.let { GenerationsTextureLoader.getLocation(it) } ?: PokemonModelRepository.getTexture(entity.getSpecies(), entity.getAspects(), 0f)

    override fun render(entity: StatueEntity, yaw: Float, partialTicks: Float, stack: PoseStack, buffer: MultiBufferSource?, light: Int) {
        if(entity.isInvisible) return

        val (species, aspects) = entity.properties.asRenderablePokemon()
        val model = PokemonModelRepository.getPoser(species.resourceIdentifier, aspects)


        stack.pushPose()
        stack.mulPose(Axis.YP.rotationDegrees(entity.orientation))
        stack.scale(-1f, -1f, 1f)
        val scale = entity.getScale()
        stack.translate(0.0, -1.501 * scale, 0.0)
        stack.scale(scale, scale, scale)
        val state = entity.delegate
        val texture = getTextureLocation(entity)
        if (state.getInstance() is StatueInstance) {
            if (texture.path == "pk") instance.setVariant(texture.toString())
            instance.setMaterial(entity.statueData.material())
        }
        val model = PokemonModelRepository.getPoser(
            species.resourceIdentifier, aspects
        ) as PoseableEntityModel<PokemonEntity>
        val context = model.context
        context.pop()
        context.put<Entity>(RenderContext.Companion.getENTITY(), entity)
        context.put<Float>(RenderContext.Companion.getSCALE(), scale)
        context.put(RenderContext.Companion.getSPECIES(), entity.getSpecies())
        context.put<Set<String>>(RenderContext.Companion.getASPECTS(), entity.getAspects())
        context.put(Pipelines.INSTANCE, state.getInstance())
        val pose = model.getPose(entity.statueData.poseType)
        if (pose != null) state.setPose(pose.poseName)
        state.updatePartialTicks(partialTicks)
        model.setupAnimStateful(null, entity.delegate, 0f, 0f, 0f, 0f, 0f)
        model.setLayerContext(
            buffer!!,
            entity.delegate,
            PokemonModelRepository.getLayers(
                entity.statueData.asRenderablePokemon().species.resourceIdentifier,
                entity.statueData.properties.aspects
            )
        )
        val vertexConsumer = ItemRenderer.getFoilBuffer(buffer, model.getLayer(texture, false, false), false, false)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        model.render(context, stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f)
        model.resetLayerContext()
        stack.popPose()
        if (shouldShowName(entity)) {
            renderNameTag(entity, entity.displayName, stack, buffer, light)
        }
    }

    override fun scale(livingEntity: StatueEntity, matrixStack: PoseStack, partialTickTime: Float) {
        val species = livingEntity.statueData.properties.asRenderablePokemon().form
        val scale = species.baseScale
        matrixStack.scale(scale, scale, scale)
    }

    override fun getAttackAnim(livingBase: StatueEntity, partialTickTime: Float): Float {
        return 0.0f
    }
    }