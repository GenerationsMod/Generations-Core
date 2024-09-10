package generations.gg.generations.core.generationscore.common.client.render

import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer.Companion.positions
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.util.math.fromEulerXYZ
import com.cobblemon.mod.common.util.math.geometry.toRadians
import com.mojang.blaze3d.platform.Lighting
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import generations.gg.generations.core.generationscore.common.client.model.RareCandyBone
import generations.gg.generations.core.generationscore.common.world.item.StatueSpawnerItem
import generations.gg.generations.core.generationscore.common.world.item.TimeCapsule
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import org.joml.Quaternionf
import org.joml.Vector4f

object TimeCapsuleItemRenderer : CobblemonBuiltinItemRenderer {
    override fun render(stack: ItemStack, mode: ItemDisplayContext, matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int, overlay: Int,
    ) {
        if (stack.item is TimeCapsule || stack.item is StatueSpawnerItem || stack.item is PokemonItem) {
            val (species, aspects) = (if(stack.item is PokemonItem) (stack.item as PokemonItem).getSpeciesAndAspects(stack) else TimeCapsule.getRenderablePokmon(stack)) ?: return

            matrices.pushPose()
            val model = PokemonModelRepository.getPoser(species.resourceIdentifier, aspects)

            val isSprite = model.rootPart is RareCandyBone;

            val context = model.context
            context.put(RenderContext.SPECIES, species.resourceIdentifier)
            context.put(RenderContext.ASPECTS, aspects)
            context.put(RenderContext.RENDER_STATE, RenderContext.RenderState.PORTRAIT)

            val renderLayer = model.getLayer(PokemonModelRepository.getTexture(species.resourceIdentifier, aspects, 0f), false, false)
            val transformations: PokemonItemRenderer.Transformations = (if(isSprite) positionsSprite[mode] else positions[mode]) ?: return

            Lighting.setupFor3DItems()
            matrices.scale(transformations.scale.x, transformations.scale.y, transformations.scale.z)
            matrices.translate(transformations.translation.x, transformations.translation.y, transformations.translation.z
            )

            if(!isSprite) {
                model.setupAnimStateless(PoseType.PROFILE, 0f, 0f, 0f, 0f, 0f)
                matrices.translate(model.profileTranslation.x, model.profileTranslation.y, model.profileTranslation.z - 4.0)
                matrices.scale(model.profileScale, model.profileScale, 0.15f)
            }

            val rotation = Quaternionf().fromEulerXYZ(transformations.rotation.x, transformations.rotation.y + 180f.toRadians(), transformations.rotation.z)


            matrices.mulPose(rotation)
            rotation.conjugate()

            val tint: Vector4f = if (stack.item is PokemonItem) (stack.item as PokemonItem).tint(stack) else Vector4f(1f, 1f, 1f, 1f)
            
            if(isSprite) {
                Lighting.setupForFlatItems()

                val packedLight = if (mode == ItemDisplayContext.GUI) LightTexture.pack(13, 13) else light
                RenderSystem.disableCull()
                (model.rootPart as RareCandyBone).renderSprite(context, matrices, packedLight, OverlayTexture.NO_OVERLAY, tint.x, tint.y, tint.z, tint.w, isSprite)
                RenderSystem.disableCull()
            } else {
                val packedLight = if (mode == ItemDisplayContext.GUI) LightTexture.pack(13, 13) else light
                val vertexConsumer = vertexConsumers.getBuffer(renderLayer)
                matrices.pushPose()

                model.withLayerContext(vertexConsumers, null, PokemonModelRepository.getLayers(species.resourceIdentifier, aspects)) {
                    model.render(model.context, matrices, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, tint.x, tint.y, tint.z, tint.w)
                }
                model.setDefault()
                matrices.popPose()
            }

            matrices.popPose()
            Lighting.setupForFlatItems()
        }
    }

            val positionsSprite: MutableMap<ItemDisplayContext, PokemonItemRenderer.Transformations> = mutableMapOf()

            init {
                positionsSprite[ItemDisplayContext.GUI] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(0.5, -1.0, -0.5),
                PokemonItemRenderer().Transformation(1F, -1F, -1F),
                PokemonItemRenderer().Transformation(0F, 0F, 0F))

                positionsSprite[ItemDisplayContext.FIXED] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(0.5, -1.0, -0.5),
                PokemonItemRenderer().Transformation(1F, -1F, -1F),
                PokemonItemRenderer().Transformation(0F, 0F, 0F))

                positionsSprite[ItemDisplayContext.FIRST_PERSON_RIGHT_HAND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(2.75, -1.2, 5.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 35F, 0F))

                positionsSprite[ItemDisplayContext.FIRST_PERSON_LEFT_HAND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(-0.75, -1.2, 5.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, -35F, 0F))

                positionsSprite[ItemDisplayContext.THIRD_PERSON_RIGHT_HAND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -2.6, 2.75),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 35F, 0F))

                positionsSprite[ItemDisplayContext.THIRD_PERSON_LEFT_HAND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -2.6, 2.75),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, -35F, 0F))

                positionsSprite[ItemDisplayContext.GROUND] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -1.6, -1.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 0f, 0F))

                positionsSprite[ItemDisplayContext.HEAD] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(1.0, -3.5, 3.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 215F, 0F))

                positionsSprite[ItemDisplayContext.NONE] = PokemonItemRenderer().Transformations(
                PokemonItemRenderer().Transformation(0.0, 0.0, 0.0),
                PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
                PokemonItemRenderer().Transformation(0F, 0F, 0F))
    }
}
