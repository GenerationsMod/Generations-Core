package generations.gg.generations.core.generationscore.common.client.render

import com.cobblemon.mod.common.client.render.SpriteType
import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer
import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.client.render.models.blockbench.repository.VaryingModelRepository
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.util.math.fromEulerXYZDegrees
import com.mojang.blaze3d.platform.Lighting
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferUploader
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import generations.gg.generations.core.generationscore.common.world.item.PokemonProvidingItem
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import org.joml.Quaternionf
import org.joml.Vector3f

class TimeCapsuleItemRender : CobblemonBuiltinItemRenderer {
    val context = RenderContext().also {
        it.put(RenderContext.RENDER_STATE, RenderContext.RenderState.PROFILE)
        it.put(RenderContext.DO_QUIRKS, false)
    }

    override fun render(stack: ItemStack, mode: ItemDisplayContext, matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int, overlay: Int) {
        val pokemonItem = stack.item as? PokemonProvidingItem ?: return
        val (species, aspects) = pokemonItem.getSpeciesAndAspectsPair(stack) ?: return
        val state = FloatingState()
        state.currentAspects = aspects
        matrices.pushPose()

        val transformations = positions[mode]!!

        if (mode == ItemDisplayContext.GUI) {
            Lighting.setupForFlatItems()
        }

        val sprite = VaryingModelRepository.getSprite(species.resourceIdentifier, state, SpriteType.PROFILE)

        val packedLight = if (mode == ItemDisplayContext.GUI) {
            LightTexture.pack(13, 13)
        } else {
            light
        }

        matrices.scale(transformations.scale.x, transformations.scale.y, transformations.scale.z)
        matrices.translate(
            transformations.translation.x,
            transformations.translation.y,
            transformations.translation.z
        )

        val tint = pokemonItem.stackTint(stack)


        if(sprite == null) {

            val renderLayer = RenderType.entityCutout(VaryingModelRepository.getTexture(species.resourceIdentifier, state))
            val model = VaryingModelRepository.getPoser(species.resourceIdentifier, state)
            model.context = context
            context.put(RenderContext.RENDER_STATE, RenderContext.RenderState.PROFILE)
            context.put(RenderContext.SPECIES, species.resourceIdentifier)
            context.put(RenderContext.ASPECTS, aspects)
            context.put(RenderContext.POSABLE_STATE, state)
            state.currentModel = model

            state.setPoseToFirstSuitable(PoseType.PORTRAIT)
            model.applyAnimations(null, state, 0F, 0F, 0F, 0F, 0F)

            matrices.translate(model.profileTranslation.x, model.profileTranslation.y, -4.0)
            matrices.scale(model.profileScale, model.profileScale, 0.15F)

            val rotation = Quaternionf().fromEulerXYZDegrees(
                Vector3f(
                    transformations.rotation.x,
                    transformations.rotation.y,
                    transformations.rotation.z
                )
            )
            matrices.mulPose(rotation)
            rotation.conjugate()
            val vertexConsumer: VertexConsumer = vertexConsumers.getBuffer(renderLayer)
            matrices.pushPose()


            // x = red, y = green, z = blue, w = alpha
            model.withLayerContext(
                vertexConsumers,
                state,
                VaryingModelRepository.getLayers(species.resourceIdentifier, state)
            ) {
                val tintRed = (tint.x * 255).toInt()
                val tintGreen = (tint.y * 255).toInt()
                val tintBlue = (tint.z * 255).toInt()
                val tintAlpha = (tint.w * 255).toInt()
                val color = (tintAlpha shl 24) or (tintRed shl 16) or (tintGreen shl 8) or tintBlue

                model.render(context, matrices, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, color)
            }

            model.setDefault()
            matrices.popPose()
        } else {
            val tintRed = (tint.x * 255).toInt()
            val tintGreen = (tint.y * 255).toInt()
            val tintBlue = (tint.z * 255).toInt()
            val tintAlpha = (tint.w * 255).toInt()
            val color = (tintAlpha shl 24) or (tintRed shl 16) or (tintGreen shl 8) or tintBlue

            matrices.translate(-1f, 0f, -4f)

            if (mode == ItemDisplayContext.GUI) {
                renderSprite(matrices, sprite, color)
            } else {
                val pose = matrices.last()
                val matrix = pose.pose()

                val renderLayer = RenderType.entityCutoutNoCull(sprite)
                val buffer: VertexConsumer = vertexConsumers.getBuffer(renderLayer)
                buffer.addVertex(matrix, 2f, 0f, 0.0f).setUv(1f, 0f).setColor(color).setLight(light).setOverlay(overlay).setNormal(pose, 0f, 0f, -1f)
                buffer.addVertex(matrix, 0f, 0f, 0.0f).setUv(0f, 0f).setColor(color).setLight(light).setOverlay(overlay).setNormal(pose, 0f, 0f, -1f)
                buffer.addVertex(matrix, 0f, 2f, 0.0f).setUv(0f, 1f).setColor(color).setLight(light).setOverlay(overlay).setNormal(pose, 0f, 0f, -1f)
                buffer.addVertex(matrix, 2f, 2f, 0.0f).setUv(1f, 1f).setColor(color).setLight(light).setOverlay(overlay).setNormal(pose, 0f, 0f, -1f)
            }
        }


        matrices.popPose()

        if (mode == ItemDisplayContext.GUI) {
            Lighting.setupFor3DItems()
        }
    }

    fun renderSprite(matrixStack: PoseStack, sprite: ResourceLocation, color: Int) {
        val matrix: PoseStack.Pose = matrixStack.last()

        RenderSystem.setShaderTexture(0, sprite);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader)

        var buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR)

        buffer.addVertex(matrix, 2f, 0f, 0.0f).setUv(1f, 0f).setColor(color)
        buffer.addVertex(matrix, 0f, 0f, 0.0f).setUv(0f, 0f).setColor(color)
        buffer.addVertex(matrix, 0f, 2f, 0.0f).setUv(0f, 1f).setColor(color)
        buffer.addVertex(matrix, 2f, 2f, 0.0f).setUv(1f, 1f).setColor(color)

        BufferUploader.drawWithShader(buffer.buildOrThrow())
    }

    companion object {
        val positions: MutableMap<ItemDisplayContext, Transformations> = mutableMapOf()

        init {
            positions[ItemDisplayContext.GUI] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(1.0, -1.9, -0.5),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, 35F, 0F)
            )
            positions[ItemDisplayContext.FIXED] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(1.0, -2.0, 3.0),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, 35F - 180F, 0F)
            )
            positions[ItemDisplayContext.FIRST_PERSON_RIGHT_HAND] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(2.75, -1.2, 5.0),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, 35F, 0F)
            )
            positions[ItemDisplayContext.FIRST_PERSON_LEFT_HAND] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(-0.75, -1.2, 5.0),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, -35F, 0F)
            )
            positions[ItemDisplayContext.THIRD_PERSON_RIGHT_HAND] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(1.0, -2.6, 2.75),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, 35F, 0F)
            )
            positions[ItemDisplayContext.THIRD_PERSON_LEFT_HAND] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(1.0, -2.6, 2.75),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, -35F, 0F)
            )
            positions[ItemDisplayContext.GROUND] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(1.0, -2.6, 3.0),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, 35F, 0F)
            )
            positions[ItemDisplayContext.HEAD] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(1.0, -3.5, 3.0),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, 215F, 0F)
            )
            positions[ItemDisplayContext.NONE] = TimeCapsuleItemRender().Transformations(
                TimeCapsuleItemRender().Transformation(0.0, 0.0, 0.0),
                TimeCapsuleItemRender().Transformation(0.5F, -0.5F, -0.5F),
                TimeCapsuleItemRender().Transformation(0F, 0F, 0F)
            )
        }
    }

    inner class Transformations(val translation: Transformation<Double>, val scale: Transformation<Float>, val rotation: Transformation<Float>)
    inner class Transformation<T>(val x: T, val y: T, val z: T)
}