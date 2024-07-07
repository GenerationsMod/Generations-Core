package generations.gg.generations.core.generationscore.client.render

import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer.Companion.positions
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.util.math.fromEulerXYZ
import com.mojang.blaze3d.platform.Lighting
import com.mojang.blaze3d.vertex.PoseStack
import generations.gg.generations.core.generationscore.mixin.client.PokemonItemRendererMixin
import generations.gg.generations.core.generationscore.world.item.StatueSpawnerItem
import generations.gg.generations.core.generationscore.world.item.TimeCapsule
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import org.joml.Quaternionf
import org.joml.Vector3f
import org.joml.Vector4f

class TimeCapsuleItemRenderer : CobblemonBuiltinItemRenderer {
    override fun render(
        stack: ItemStack,
        mode: ItemDisplayContext,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int,
    ) {
        if (stack.item is TimeCapsule || stack.item is StatueSpawnerItem) {
            val (first, second) = TimeCapsule.getRenderablePokmon(stack)
                ?: return
            matrices.pushPose()
            val model = PokemonModelRepository.getPoser(
                first.resourceIdentifier, second
            )
            val context = model.context
            context.put(RenderContext.SPECIES, first.resourceIdentifier)
            context.put(
                RenderContext.ASPECTS, second
            )
            val renderLayer =
                model.getLayer(PokemonModelRepository.getTexture(first.resourceIdentifier, second, 0f), false, false)
            val transformations: PokemonItemRenderer.Transformations = positions[mode] ?: return

            Lighting.setupFor3DItems()
            matrices.scale(transformations.scale.x, transformations.scale.y, transformations.scale.z)
            matrices.translate(
                transformations.translation.x,
                transformations.translation.y,
                transformations.translation.z
            )
            model.setupAnimStateless(PoseType.PROFILE, 0f, 0f, 0f, 0f, 0f)
            matrices.translate(model.profileTranslation.x, model.profileTranslation.y, model.profileTranslation.z - 4f)
            matrices.scale(model.profileScale, model.profileScale, 0.15f)
            val rotation = Quaternionf().fromEulerXYZ(
                transformations.rotation.x,
                transformations.rotation.y,
                transformations.rotation.z
            )

            matrices.mulPose(rotation)
            rotation.conjugate()
            val vertexConsumer = vertexConsumers.getBuffer(renderLayer)
            matrices.pushPose()
            val packedLight = if (mode == ItemDisplayContext.GUI) LightTexture.pack(13, 13) else light
//            val tint: Vector4f = pokemonItem.tint(stack)
            model.withLayerContext(
                vertexConsumers,
                null,
                PokemonModelRepository.getLayers(first.resourceIdentifier, second)
            ) {
                model.render(
                    model.context,
                    matrices,
                    vertexConsumer,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    1.0f,//tint.x,
                    1.0f,//tint.y,
                    1.0f,//tint.z,
                    1.0f,//tint.w
                )
                Unit
            }
            model.setDefault()
            matrices.popPose()
            matrices.popPose()
            Lighting.setupForFlatItems()
        }
    }

        companion object {
            val positions: MutableMap<ItemDisplayContext, PokemonItemRenderer.Transformations> = mutableMapOf()

            init {
                positions[ItemDisplayContext.GUI] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(1.0, -1.9, -0.5),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, 35F, 0F)
                )
                positions[ItemDisplayContext.FIXED] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(1.0, -2.0, 3.0),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, 35F - 180F, 0F)
                )
                positions[ItemDisplayContext.FIRST_PERSON_RIGHT_HAND] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(2.75, -1.2, 5.0),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, 35F, 0F)
                )
                positions[ItemDisplayContext.FIRST_PERSON_LEFT_HAND] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(-0.75, -1.2, 5.0),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, -35F, 0F)
                )
                positions[ItemDisplayContext.THIRD_PERSON_RIGHT_HAND] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(1.0, -2.6, 2.75),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, 35F, 0F)
                )
                positions[ItemDisplayContext.THIRD_PERSON_LEFT_HAND] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(1.0, -2.6, 2.75),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, -35F, 0F)
                )
                positions[ItemDisplayContext.GROUND] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(1.0, -2.6, 3.0),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, 35F, 0F)
                )
                positions[ItemDisplayContext.HEAD] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(1.0, -3.5, 3.0),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, 215F, 0F)
                )
                positions[ItemDisplayContext.NONE] = PokemonItemRenderer().Transformations(
                    PokemonItemRenderer().Transformation(0.0, 0.0, 0.0),
                    PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                    PokemonItemRenderer().Transformation(0F, 0F, 0F)
                )

        }
    }
}
