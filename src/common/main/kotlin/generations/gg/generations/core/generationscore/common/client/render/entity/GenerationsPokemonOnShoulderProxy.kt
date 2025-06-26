package generations.gg.generations.core.generationscore.common.client.render.entity

import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer.ShoulderData
import com.cobblemon.mod.common.client.render.models.blockbench.FloatingState
import com.cobblemon.mod.common.client.render.models.blockbench.PosableModel
//import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.PokemonFloatingState
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.util.isPokemonEntity
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.client.model.RareCandyBone
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.entity.player.Player
import java.util.*

object GenerationsPokemonOnShoulderProxy {
    private val playerCache = hashMapOf<UUID, ShoulderCache>()


    val context = RenderContext().also {
        it.put(RenderContext.RENDER_STATE, RenderContext.RenderState.WORLD)
    }

    var leftState = FloatingState()
    var lastRenderedLeft: ShoulderData? = null
    var rightState = FloatingState()
    var lastRenderedRight: ShoulderData? = null

    fun configureState(state: FloatingState, model: PosableModel, leftShoulder: Boolean): FloatingState {
        state.currentModel = model
        state.setPoseToFirstSuitable(if (leftShoulder) PoseType.SHOULDER_LEFT else PoseType.SHOULDER_RIGHT)
        return state
    }

    @JvmStatic
    fun render(
        access: PokemonOnShoulderRenderAccess,
        matrixStack: PoseStack,
        buffer: MultiBufferSource,
        packedLight: Int,
        livingEntity: Player,
        limbSwing: Float,
        limbSwingAmount: Float,
        partialTicks: Float,
        ageInTicks: Float,
        netHeadYaw: Float,
        headPitch: Float,
        pLeftShoulder: Boolean,
    ) {
        val compoundTag = if (pLeftShoulder) livingEntity.shoulderEntityLeft else livingEntity.shoulderEntityRight
        if (compoundTag.isPokemonEntity()) {
            matrixStack.pushPose()
            val uuid = access.invokeExtractUuid(compoundTag)
            val cache = playerCache.getOrPut(livingEntity.uuid) { ShoulderCache() }
            var shoulderData: ShoulderData? = null

            if (pLeftShoulder && cache.lastKnownLeft?.uuid != uuid) {
                shoulderData = access.invokeExtractData(compoundTag, uuid)
                cache.lastKnownLeft = shoulderData
            }
            else if (!pLeftShoulder && cache.lastKnownRight?.uuid != uuid) {
                shoulderData = access.invokeExtractData(compoundTag, uuid)
                cache.lastKnownRight = shoulderData
            }

            if (shoulderData == null){
                // Could be null
                shoulderData = (if (pLeftShoulder) cache.lastKnownLeft else cache.lastKnownRight) ?: return
            }

            var state = FloatingState()
            state.currentAspects = shoulderData.aspects
            val model = PokemonModelRepository.getPoser(shoulderData.species.resourceIdentifier, state)
            model.context = context
            context.put(RenderContext.SPECIES, shoulderData.species.resourceIdentifier)
            context.put(RenderContext.ASPECTS, shoulderData.aspects)
            val scale = shoulderData.form.baseScale * shoulderData.scaleModifier
            val width = shoulderData.form.hitbox.width
            val heightOffset = -1.5 * scale
            val widthOffset = width / 2 - (model.rootPart.instanceOrNull<RareCandyBone>()?.let { 0.7 + 0.175 } ?: 0.7)
            // If they're sneaking, the pokemon needs to rotate a little bit and push forward
            // Shoulders move a bit when sneaking which is why the translation is necessary.
            // Shoulder exact rotation according to testing (miasmus) is 0.4 radians, the -0.15 is eyeballed though.
            if (livingEntity.isCrouching) {
                matrixStack.mulPose(Axis.XP.rotation(0.4F))
                matrixStack.translate(0F, 0F, -0.15F)
            }
            matrixStack.translate(
                if (pLeftShoulder) -widthOffset else widthOffset,
                (if (livingEntity.isCrouching) heightOffset + 0.2 else heightOffset),
                0.0
            )


            matrixStack.scale(scale, scale, scale)



//            if(model.rootPart !is RareCandyBone) {
//                matrixStack.popPose()
//                return false
//            }

            state = if (pLeftShoulder && shoulderData != lastRenderedLeft) {
                leftState = configureState(state, model, true)
                lastRenderedLeft = shoulderData
                leftState
            } else if (!pLeftShoulder && shoulderData != lastRenderedRight) {
                rightState = configureState(state, model, false)
                lastRenderedRight = shoulderData
                rightState
            } else {
                if (pLeftShoulder) leftState else rightState
            }
            state.updatePartialTicks(partialTicks)
            context.put(RenderContext.POSABLE_STATE, state)
            state.currentModel = model
            val vertexConsumer = buffer.getBuffer(RenderType.entityCutout(PokemonModelRepository.getTexture(shoulderData.species.resourceIdentifier, state)))
            val i = LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0f)

            model.applyAnimations(
                entity = null,
                state = state,
                headYaw = netHeadYaw,
                headPitch = headPitch,
                limbSwing = limbSwing,
                limbSwingAmount = limbSwingAmount,
                ageInTicks = livingEntity.tickCount.toFloat()
            )
            model.render(context, matrixStack, vertexConsumer, packedLight, i, -0x1)
            model.withLayerContext(buffer, state, PokemonModelRepository.getLayers(shoulderData.species.resourceIdentifier, state)) {
                model.render(context, matrixStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, -0x1)
            }
            model.setDefault()
            matrixStack.popPose()
        }
    }

    private data class ShoulderCache(
        var lastKnownLeft: ShoulderData? = null,
        var lastKnownRight: ShoulderData? = null,
    )

}