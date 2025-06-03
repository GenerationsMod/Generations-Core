package generations.gg.generations.core.generationscore.common.client

import com.cobblemon.mod.common.client.render.SpriteType
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer.Companion.positions
import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.util.math.fromEulerXYZDegrees
import com.mojang.blaze3d.platform.Lighting
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import generations.gg.generations.core.generationscore.common.client.model.RareCandyBone
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.item.PokemonProvidingItem
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import org.joml.Quaternionf
import org.joml.Vector3f

object PokemonItemRendererProxy {
    val context = RenderContext().also {
        it.put(RenderContext.RENDER_STATE, RenderContext.RenderState.PROFILE)
        it.put(RenderContext.DO_QUIRKS, false)
    }

    @JvmStatic
    fun render(
        stack: ItemStack,
        mode: ItemDisplayContext,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int,
    ) {
        val pokemonItem = stack.item as? PokemonProvidingItem ?: return
        val (species, aspects) = pokemonItem.getSpeciesAndAspectsPair(stack) ?: return
        val state = FloatingState()
        state.currentAspects = aspects
        matrices.pushPose()
        val model = PokemonModelRepository.getPoser(species.resourceIdentifier, state)

        val sprite  = model.rootPart.instanceOrNull<RareCandyBone>()?.let { RenderType.entityCutoutNoCull(PokemonModelRepository.getSprite(species.resourceIdentifier, state, SpriteType.PROFILE) ?: MissingTextureAtlasSprite.getLocation()) }

        if(sprite == null) {

            model.context = context
            context.put(RenderContext.RENDER_STATE, RenderContext.RenderState.PROFILE)
            context.put(RenderContext.SPECIES, species.resourceIdentifier)
            context.put(RenderContext.ASPECTS, aspects)
            context.put(RenderContext.POSABLE_STATE, state)
            state.currentModel = model
        }

        val renderLayer = if(sprite == null) RenderType.entityCutout(PokemonModelRepository.getTexture(species.resourceIdentifier, state)) else sprite

        val transformations = if(sprite == null) positions[mode]!! else positionsSprite[mode]!!

        if (mode == ItemDisplayContext.GUI) {
            if(sprite == null) Lighting.setupForFlatItems()
            else Lighting.setupForEntityInInventory()
        }

        matrices.scale(transformations.scale.x, transformations.scale.y, transformations.scale.z)
        matrices.translate(transformations.translation.x, transformations.translation.y, transformations.translation.z)

        if(sprite == null) {
            state.setPoseToFirstSuitable(PoseType.PORTRAIT)
            model.applyAnimations(null, state, 0F, 0F, 0F, 0F, 0F)

            matrices.translate(model.profileTranslation.x, model.profileTranslation.y, -4.0)
            matrices.scale(model.profileScale, model.profileScale, 0.15F)
        }

        val rotation = Quaternionf().fromEulerXYZDegrees(Vector3f(transformations.rotation.x, transformations.rotation.y, transformations.rotation.z))
        matrices.mulPose(rotation)
        rotation.conjugate()
        val buffer = vertexConsumers.getBuffer(renderLayer)
        matrices.pushPose()
        val packedLight = if (mode == ItemDisplayContext.GUI) {
            if(sprite == null) LightTexture.pack(13, 13)
            else LightTexture.FULL_BRIGHT
        } else {
            light
        }

        // x = red, y = green, z = blue, w = alpha
        val tint = pokemonItem.stackTint(stack)
        val tintRed = (tint.x * 255).toInt()
        val tintGreen = (tint.y * 255).toInt()
        val tintBlue = (tint.z * 255).toInt()
        val tintAlpha = (tint.w * 255).toInt()
        val color = (tintAlpha shl 24) or (tintRed shl 16) or (tintGreen shl 8) or tintBlue

        if(sprite == null) {
            model.withLayerContext(
                vertexConsumers,
                state,
                PokemonModelRepository.getLayers(species.resourceIdentifier, state)
            ) {
                model.render(context, matrices, buffer, packedLight, OverlayTexture.NO_OVERLAY, color)
            }

            model.setDefault()
        } else {
            val matrix = matrices.last()
            matrix.pose().translate(-0.5f, 0f, 0f)
            buffer.addVertex(matrix, 1f, 0f, 0f).setColor(color).setUv(1f, 0f).setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight).setNormal(matrix, 0f, 1f, 0f)
            buffer.addVertex(matrix, 0f, 0f, 0f).setColor(color).setUv(0f, 0f).setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight).setNormal(matrix, 0f, 1f, 0f)
            buffer.addVertex(matrix, 0f, 1f, 0f).setColor(color).setUv(0f, 1f).setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight).setNormal(matrix, 0f, 1f, 0f)
            buffer.addVertex(matrix, 1f, 1f, 0f).setColor(color).setUv(1f, 1f).setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight).setNormal(matrix, 0f, 1f, 0f)
        }

        matrices.popPose()
        matrices.popPose()

        if (mode == ItemDisplayContext.GUI) {
            Lighting.setupFor3DItems()
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
            PokemonItemRenderer().Transformation(2.75, -1.2, 0.0),
            PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
            PokemonItemRenderer().Transformation(0F, 35F, 0F))

        positionsSprite[ItemDisplayContext.FIRST_PERSON_LEFT_HAND] = PokemonItemRenderer().Transformations(
            PokemonItemRenderer().Transformation(-0.75, -1.2, 0.0),
            PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
            PokemonItemRenderer().Transformation(0F, -35F, 0F))

        positionsSprite[ItemDisplayContext.THIRD_PERSON_RIGHT_HAND] = PokemonItemRenderer().Transformations(
            PokemonItemRenderer().Transformation(1.0, -2.6, 0.75),
            PokemonItemRenderer().Transformation(0.5F, -0.5F, -0.5F),
            PokemonItemRenderer().Transformation(0F, 35F, 0F))

        positionsSprite[ItemDisplayContext.THIRD_PERSON_LEFT_HAND] = PokemonItemRenderer().Transformations(
            PokemonItemRenderer().Transformation(1.0, -2.6, -0.75),
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
