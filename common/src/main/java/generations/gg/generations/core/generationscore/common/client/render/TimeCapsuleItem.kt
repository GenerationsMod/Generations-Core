package generations.gg.generations.core.generationscore.common.client.render

import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer
import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.util.math.fromEulerXYZDegrees
import com.mojang.blaze3d.platform.Lighting
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import generations.gg.generations.core.generationscore.common.util.getRenderablePokemon
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import org.joml.Quaternionf
import org.joml.Vector3f

class TimeCapsuleItem : CobblemonBuiltinItemRenderer {
    val context = RenderContext().also {
        it.put(RenderContext.RENDER_STATE, RenderContext.RenderState.PROFILE)
        it.put(RenderContext.DO_QUIRKS, false)
    }

    override fun render(stack: ItemStack, mode: ItemDisplayContext, matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int, overlay: Int) {
        val pokemonItem = stack.getRenderablePokemon() ?: return
        val state = FloatingState()
        state.currentAspects = pokemonItem.aspects
        matrices.pushPose()
        val model = PokemonModelRepository.getPoser(pokemonItem.species.resourceIdentifier, state)
        model.context = context
        context.put(RenderContext.RENDER_STATE, RenderContext.RenderState.PROFILE)
        context.put(RenderContext.SPECIES, pokemonItem.species.resourceIdentifier)
        context.put(RenderContext.ASPECTS, pokemonItem.aspects)
        context.put(RenderContext.POSABLE_STATE, state)
        state.currentModel = model

        val renderLayer = RenderType.entityCutout(PokemonModelRepository.getTexture(pokemonItem.species.resourceIdentifier, state))

        val transformations = positions[mode]!!

        if (mode == ItemDisplayContext.GUI) {
            Lighting.setupForFlatItems()
        }

        matrices.scale(transformations.scale.x, transformations.scale.y, transformations.scale.z)
        matrices.translate(transformations.translation.x, transformations.translation.y, transformations.translation.z)
        state.setPoseToFirstSuitable(PoseType.PORTRAIT)
        model.applyAnimations(null, state, 0F, 0F, 0F, 0F, 0F)

        matrices.translate(model.profileTranslation.x, model.profileTranslation.y, -4.0)
        matrices.scale(model.profileScale, model.profileScale, 0.15F)

        val rotation = Quaternionf().fromEulerXYZDegrees(Vector3f(transformations.rotation.x, transformations.rotation.y, transformations.rotation.z))
        matrices.mulPose(rotation)
        rotation.conjugate()
        val vertexConsumer: VertexConsumer = vertexConsumers.getBuffer(renderLayer)
        matrices.pushPose()
        val packedLight = if (mode == ItemDisplayContext.GUI) {
            LightTexture.pack(13, 13)
        } else {
            light
        }

//        TODO: Tint Maybe?
        // x = red, y = green, z = blue, w = alpha
//        val tint = pokemonItem.tint(stack)
        model.withLayerContext(vertexConsumers, state, PokemonModelRepository.getLayers(pokemonItem.species.resourceIdentifier, state)) {
            val tintRed = (/*tint.x*/ 1f * 255).toInt()
            val tintGreen = (/*tint.y*/ 1f * 255).toInt()
            val tintBlue = (/*tint.z*/ 1f * 255).toInt()
            val tintAlpha = (/*tint.w*/ 1f * 255).toInt()
            val color = (tintAlpha shl 24) or (tintRed shl 16) or (tintGreen shl 8) or tintBlue
            model.render(context, matrices, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, color)
        }

        model.setDefault()
        matrices.popPose()
        matrices.popPose()

        if (mode == ItemDisplayContext.GUI) {
            Lighting.setupFor3DItems()
        }
    }

    companion object {
        val positions: MutableMap<ItemDisplayContext, Transformations> = mutableMapOf()

        init {
            positions[ItemDisplayContext.GUI] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(1.0, -1.9, -0.5),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, 35F, 0F)
            )
            positions[ItemDisplayContext.FIXED] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(1.0, -2.0, 3.0),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, 35F - 180F, 0F)
            )
            positions[ItemDisplayContext.FIRST_PERSON_RIGHT_HAND] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(2.75, -1.2, 5.0),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, 35F, 0F)
            )
            positions[ItemDisplayContext.FIRST_PERSON_LEFT_HAND] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(-0.75, -1.2, 5.0),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, -35F, 0F)
            )
            positions[ItemDisplayContext.THIRD_PERSON_RIGHT_HAND] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(1.0, -2.6, 2.75),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, 35F, 0F)
            )
            positions[ItemDisplayContext.THIRD_PERSON_LEFT_HAND] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(1.0, -2.6, 2.75),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, -35F, 0F)
            )
            positions[ItemDisplayContext.GROUND] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(1.0, -2.6, 3.0),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, 35F, 0F)
            )
            positions[ItemDisplayContext.HEAD] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(1.0, -3.5, 3.0),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, 215F, 0F)
            )
            positions[ItemDisplayContext.NONE] = TimeCapsuleItem().Transformations(
                TimeCapsuleItem().Transformation(0.0, 0.0, 0.0),
                TimeCapsuleItem().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItem().Transformation(0F, 0F, 0F)
            )
        }
    }

    inner class Transformations(val translation: Transformation<Double>, val scale: Transformation<Float>, val rotation: Transformation<Float>)
    inner class Transformation<T>(val x: T, val y: T, val z: T)
}