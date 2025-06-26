package generations.gg.generations.core.generationscore.common.client.render.entity

import com.cobblemon.mod.common.client.entity.PokemonClientDelegate
import com.cobblemon.mod.common.client.render.ModelLayer
import com.cobblemon.mod.common.client.render.ModelTextureSupplier
import com.cobblemon.mod.common.client.render.models.blockbench.PosableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.client.GenerationsTextureLoader
import generations.gg.generations.core.generationscore.common.client.entity.StatueClientDelegate
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines
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
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.Vec3

class StatueEntityRenderer(arg: EntityRendererProvider.Context) : EntityRenderer<StatueEntity>(arg) {
    val model = PoseableStatueEntityModel()

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
        val state = entity.delegate as StatueClientDelegate
        val model = PokemonModelRepository.getPoser(renderable.species.resourceIdentifier, state)
        this.model.posableModel = model
        model.context = this.model.context
        this.model.setupEntityTypeContext(entity)

        stack.pushPose()
        stack.scale(-1f, -1f, 1f)
        val scale: Float = entity.scale
        stack.translate(0.0, -1.501 * scale, 0.0)
        stack.scale(scale, scale, scale)
        stack.mulPose(Axis.YP.rotationDegrees(entity.orientation))

        val material = entity.material?.let { GenerationsTextureLoader.getLocation(it) }
            .also { ModifiableTextureSupplier.texture = it }

        val vertexConsumer = buffer.getBuffer(
            material?.type()
                ?: model.getLayer(
                    getTextureLocation(entity),
                    emissive = false,
                    translucent = false
                )
        )

        state.updatePartialTicks(partialTicks)

        model.setLayerContext(
            buffer,
            state,
            if (material == null) PokemonModelRepository.getLayers(
                renderable.species.resourceIdentifier,
                state
            ) else layerList
        )

        this.model.setupAnim(
            entity,
            0f,
            0f,
            state.getAge(),
            0f,
            0f
        ) //TODO: Come back to make sure animations aren't scuffed


        this.model.renderToBuffer(stack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 0xffffffff.toInt())

        model.resetLayerContext()

        stack.popPose()

        if (this.shouldShowName(entity)) {
            val yOffset = renderable.form.hitbox.height * scale

            super.renderNameTag(entity, entity.displayName!!, stack, buffer, light, yOffset)
        }
    }
    override fun getTextureLocation(entity: StatueEntity): ResourceLocation {
        val material = entity.material
        if (material != null && GenerationsTextureLoader.has(material)) {
            val texture: ResourceLocation? = GenerationsTextureLoader.getLocation(material = material)
            if (texture != null) return texture
        }

        val renderable = entity.renderablePokemon() ?: return MissingTextureAtlasSprite.getLocation()

        return PokemonModelRepository.getTexture(renderable.species.resourceIdentifier, entity.delegate as StatueClientDelegate)
    }

    override fun shouldShowName(entity: StatueEntity): Boolean {
        return entity.label?.let { it.isNotEmpty() && it.isNotBlank() } ?: false
    }

}

class PoseableStatueEntityModel : PosableEntityModel<StatueEntity>() {
    override fun setupEntityTypeContext(entity: Entity?) {
        super.setupEntityTypeContext(entity)

        (entity as? StatueEntity)?.let {
            val renderable = entity.renderablePokemon()!!
            var delegate = entity.delegate as StatueClientDelegate

            context.put(RenderContext.SCALE, renderable.form.baseScale)
            context.put(RenderContext.SPECIES, renderable.species.resourceIdentifier)
            context.put(RenderContext.ASPECTS, renderable.aspects)
            context.put(RenderContext.ENTITY, entity)
            context.put(RenderContext.POSABLE_STATE, delegate)
        }
    }
}

object ModifiableTextureSupplier : ModelTextureSupplier {
    var texture = MissingTextureAtlasSprite.getLocation()

    override fun invoke(state: PosableState): ResourceLocation {
        return texture
    }

}

private fun <T:ModelTextureSupplier> ModelLayer.setTextureSupplier(supplier: T): ModelLayer {
    val textureField = ModelLayer::class.java.getDeclaredField("texture")
    textureField.isAccessible = true
    textureField.set(this, supplier)

    return this
}

private fun ResourceLocation.type(): RenderType = RenderType.entitySolid(this)
