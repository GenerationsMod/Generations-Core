package generations.gg.generations.core.generationscore.common.client.render

import com.cobblemon.mod.common.client.render.SpriteType
import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer.Companion.positions
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
import com.mojang.blaze3d.vertex.VertexMultiConsumer
import generations.gg.generations.core.generationscore.common.world.item.PokemonProvidingItem
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.component.DataComponents
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

        val sprite = VaryingModelRepository.getSprite(species.resourceIdentifier, state, SpriteType.PROFILE)

        if (sprite == null) {
            val model = VaryingModelRepository.getPoser(species.resourceIdentifier, state)
            model.context = context
            context.put(RenderContext.RENDER_STATE, RenderContext.RenderState.PROFILE)
            context.put(RenderContext.SPECIES, species.resourceIdentifier)
            context.put(RenderContext.ASPECTS, aspects)
            context.put(RenderContext.POSABLE_STATE, state)
            state.currentModel = model

            val transformations = positions[mode]!!

            if (mode == ItemDisplayContext.GUI) {
                Lighting.setupForFlatItems()
            }

            matrices.scale(transformations.scale.x, transformations.scale.y, transformations.scale.z)
            matrices.translate(transformations.translation.x, transformations.translation.y, transformations.translation.z)
            state.setPoseToFirstSuitable(PoseType.PORTRAIT)
            model.applyAnimations(null, state, 0F, 0F, 0F, 0F, 0F)

            matrices.scale(model.profileScale, model.profileScale, 0.15F)

            val rotation = Quaternionf().fromEulerXYZDegrees(Vector3f(transformations.rotation.x, transformations.rotation.y, transformations.rotation.z))
            matrices.mulPose(rotation)
            rotation.conjugate()

            val renderLayer = RenderType.entityCutout(VaryingModelRepository.getTexture(species.resourceIdentifier, state))
            val isEnchanted = stack.get(DataComponents.ENCHANTMENTS)?.isEmpty == false
            val vertexConsumer: VertexConsumer =
                if (isEnchanted) {
                    VertexMultiConsumer.create(
                        vertexConsumers.getBuffer(RenderType.entityGlintDirect()),
                        vertexConsumers.getBuffer(renderLayer),
                    )
                } else {
                    vertexConsumers.getBuffer(renderLayer)
                }

            matrices.pushPose()
            val packedLight = if (mode == ItemDisplayContext.GUI) {
                LightTexture.pack(13, 13)
            } else {
                light
            }

            // x = red, y = green, z = blue, w = alpha
            val tint = pokemonItem.stackTint(stack)
            model.withLayerContext(vertexConsumers, state, VaryingModelRepository.getLayers(species.resourceIdentifier, state)) {
                val tintRed = (tint.x * 255).toInt()
                val tintGreen = (tint.y * 255).toInt()
                val tintBlue = (tint.z * 255).toInt()
                val tintAlpha = (tint.w * 255).toInt()
                val color = (tintAlpha shl 24) or (tintRed shl 16) or (tintGreen shl 8) or tintBlue
                model.render(context, matrices, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, color)
            }

            model.setDefault()
            matrices.popPose()
            matrices.popPose()

            if (mode == ItemDisplayContext.GUI) {
                Lighting.setupFor3DItems()
            }
        } else {
            val transformations = spritePositions[mode]!!

            if (mode == ItemDisplayContext.GUI) {
                Lighting.setupForFlatItems()
            }

            matrices.scale(transformations.scale.x, transformations.scale.y, transformations.scale.z)
            matrices.translate(transformations.translation.x - 1, transformations.translation.y, transformations.translation.z - 4)

            val tint = pokemonItem.stackTint(stack)
            val tintRed = (tint.x * 255).toInt()
            val tintGreen = (tint.y * 255).toInt()
            val tintBlue = (tint.z * 255).toInt()
            val tintAlpha = (tint.w * 255).toInt()
            val color = (tintAlpha shl 24) or (tintRed shl 16) or (tintGreen shl 8) or tintBlue

            val packedLight = if (mode == ItemDisplayContext.GUI) {
                LightTexture.pack(13, 13)
            } else {
                light
            }

            if (mode == ItemDisplayContext.GUI) {
                renderSprite(matrices, sprite, color)
            } else {
                val pose = matrices.last()
                val matrix = pose.pose()

                val renderLayer = RenderType.entityCutoutNoCull(sprite)
                val buffer: VertexConsumer = vertexConsumers.getBuffer(renderLayer)
                buffer.addVertex(matrix, 2f, 0f, 0.0f).setUv(1f, 0f).setColor(color).setLight(packedLight).setOverlay(overlay).setNormal(pose, 0f, 0f, -1f)
                buffer.addVertex(matrix, 0f, 0f, 0.0f).setUv(0f, 0f).setColor(color).setLight(packedLight).setOverlay(overlay).setNormal(pose, 0f, 0f, -1f)
                buffer.addVertex(matrix, 0f, 2f, 0.0f).setUv(0f, 1f).setColor(color).setLight(packedLight).setOverlay(overlay).setNormal(pose, 0f, 0f, -1f)
                buffer.addVertex(matrix, 2f, 2f, 0.0f).setUv(1f, 1f).setColor(color).setLight(packedLight).setOverlay(overlay).setNormal(pose, 0f, 0f, -1f)
            }

            matrices.popPose()

            if (mode == ItemDisplayContext.GUI) {
                Lighting.setupFor3DItems()
            }
        }
    }

    fun renderSprite(matrixStack: PoseStack, sprite: ResourceLocation, color: Int) {
        val matrix: PoseStack.Pose = matrixStack.last()

        RenderSystem.setShaderTexture(0, sprite)
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader)

        val buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR)

        buffer.addVertex(matrix, 2f, 0f, 0.0f).setUv(1f, 0f).setColor(color)
        buffer.addVertex(matrix, 0f, 0f, 0.0f).setUv(0f, 0f).setColor(color)
        buffer.addVertex(matrix, 0f, 2f, 0.0f).setUv(0f, 1f).setColor(color)
        buffer.addVertex(matrix, 2f, 2f, 0.0f).setUv(1f, 1f).setColor(color)

        BufferUploader.drawWithShader(buffer.buildOrThrow())
    }

    companion object {
        val spritePositions: MutableMap<ItemDisplayContext, PokemonItemRenderer.Transformations> = mutableMapOf()

        init {
            spritePositions[ItemDisplayContext.GUI] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -1.9, -0.5),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 35F, 0F)
            )
            spritePositions[ItemDisplayContext.FIXED] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -2.0, 3.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 35F - 180F, 0F)
            )
            spritePositions[ItemDisplayContext.FIRST_PERSON_RIGHT_HAND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(2.75, -1.2, 5.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 35F, 0F)
            )
            spritePositions[ItemDisplayContext.FIRST_PERSON_LEFT_HAND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(-0.75, -1.2, 5.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, -35F, 0F)
            )
            spritePositions[ItemDisplayContext.THIRD_PERSON_RIGHT_HAND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -2.6, 2.75),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 35F, 0F)
            )
            spritePositions[ItemDisplayContext.THIRD_PERSON_LEFT_HAND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -2.6, 2.75),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, -35F, 0F)
            )
            spritePositions[ItemDisplayContext.GROUND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -2.6, 3.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 35F, 0F)
            )
            spritePositions[ItemDisplayContext.HEAD] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -3.5, 3.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 215F, 0F)
            )
            spritePositions[ItemDisplayContext.NONE] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(0.0, 0.0, 0.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 0F, 0F)
            )
        }
    }
}