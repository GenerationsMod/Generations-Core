//package generations.gg.generations.core.generationscore.common.client.render.entity
//
//import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer.ShoulderData
//import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.PokemonFloatingState
//import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
//import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
//import com.cobblemon.mod.common.entity.PoseType
//import com.cobblemon.mod.common.util.isPokemonEntity
//import com.mojang.blaze3d.vertex.PoseStack
//import com.mojang.math.Axis
//import dev.architectury.event.events.client.ClientTooltipEvent.Render
//import generations.gg.generations.core.generationscore.common.client.model.RareCandyBone
//import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
//import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines
//import net.minecraft.client.renderer.MultiBufferSource
//import net.minecraft.client.renderer.entity.LivingEntityRenderer
//import net.minecraft.client.renderer.texture.OverlayTexture
//import net.minecraft.util.Tuple
//import net.minecraft.world.entity.player.Player
//import org.joml.Matrix4f
//import java.util.*
//
//object GenerationsPokemonOnShoulderProxy {
//    private val playerCache = hashMapOf<UUID, ShoulderCache>()
//
//    private val context: RenderContext = RenderContext()
//
//    @JvmStatic
//    fun render(
//        access: PokemonOnShoulderRenderAccess,
//        matrixStack: PoseStack,
//        buffer: MultiBufferSource,
//        packedLight: Int,
//        livingEntity: Player,
//        limbSwing: Float,
//        limbSwingAmount: Float,
//        partialTicks: Float,
//        ageInTicks: Float,
//        netHeadYaw: Float,
//        headPitch: Float,
//        pLeftShoulder: Boolean,
//    ) {
//        val compoundTag = if (pLeftShoulder) livingEntity.shoulderEntityLeft else livingEntity.shoulderEntityRight
//        if (compoundTag.isPokemonEntity()) {
//            matrixStack.pushPose()
//            val uuid = access.invokeExtractUuid(compoundTag)
//            val cache = playerCache.getOrPut(livingEntity.uuid) { ShoulderCache() }
//            var shoulderData: ShoulderData? = null
//
//            if (pLeftShoulder && cache.lastKnownLeft?.uuid != uuid) {
//                shoulderData = access.invokeExtractData(compoundTag, uuid)
//                cache.lastKnownLeft = shoulderData
//            }
//            else if (!pLeftShoulder && cache.lastKnownRight?.uuid != uuid) {
//                shoulderData = access.invokeExtractData(compoundTag, uuid)
//                cache.lastKnownRight = shoulderData
//            }
//
//            if (shoulderData == null){
//                // Could be null
//                shoulderData = (if (pLeftShoulder) cache.lastKnownLeft else cache.lastKnownRight) ?: return
//            }
//
//            val model = PokemonModelRepository.getPoser(shoulderData.species.resourceIdentifier, shoulderData.aspects)
//
//            val scale = shoulderData.form.baseScale * shoulderData.scaleModifier
//            val width = shoulderData.form.hitbox.width
//            val offset = width / 2 - if(model.rootPart !is RareCandyBone) 0.7 else 0.7 + 0.175 //TODO: Check to see if rarecandy correction is accurate for all shoulder pokemon.
//            // If they're sneaking, the pokemon needs to rotate a little bit and push forward
//            // Shoulders move a bit when sneaking which is why the translation is necessary.
//            // Shoulder exact rotation according to MC code is 0.5 radians, the -0.15 is eyeballed though.
//            if (livingEntity.isShiftKeyDown) {
//                matrixStack.mulPose(Axis.XP.rotation(0.5F))
//                matrixStack.translate(0F, 0F, -0.15F)
//            }
//            matrixStack.translate(
//                if (pLeftShoulder) -offset else offset,
//                (if (livingEntity.isShiftKeyDown) -1.3 else -1.5) * scale,
//                0.0
//            )
//
//            matrixStack.scale(scale, scale, scale)
//
//
//
////            if(model.rootPart !is RareCandyBone) {
////                matrixStack.popPose()
////                return false
////            }
//
//            context.put(Pipelines.INSTANCE, if(pLeftShoulder) cache.leftInstance else cache.rightInstance)
//            context.put(RenderContext.RENDER_STATE, RenderContext.RenderState.WORLD)
//            context.put(RenderContext.SPECIES, shoulderData.species.resourceIdentifier)
//            context.put(RenderContext.ASPECTS, shoulderData.aspects)
//            context.put(RenderContext.SCALE, scale)
//
//            val state = PokemonFloatingState()
//            state.updatePartialTicks(ageInTicks + partialTicks)
//            val vertexConsumer = buffer.getBuffer(model.getLayer(PokemonModelRepository.getTexture(shoulderData.species.resourceIdentifier, shoulderData.aspects, state.animationSeconds),
//                emissive = false,
//                translucent = false
//            ))
//            val i = LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0f)
//
//            val pose = model.poses.values
//                .firstOrNull { (if (pLeftShoulder) PoseType.SHOULDER_LEFT else PoseType.SHOULDER_RIGHT) in it.poseTypes  }
//                ?: model.poses.values.first()
//            state.setPose(pose.poseName)
//            state.timeEnteredPose = 0F
//            model.setupAnimStateful(
//                entity = null,
//                state = state,
//                headYaw = netHeadYaw,
//                headPitch = headPitch,
//                limbSwing = limbSwing,
//                limbSwingAmount = limbSwingAmount,
//                ageInTicks = livingEntity.tickCount.toFloat()
//            )
//
//            model.render(context, matrixStack, vertexConsumer, packedLight, i, 1.0f, 1.0f, 1.0f, 1.0f)
//            model.withLayerContext(buffer, state, PokemonModelRepository.getLayers(shoulderData.species.resourceIdentifier, shoulderData.aspects)) {
//                model.render(context, matrixStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F)
//            }
//            model.setDefault()
//            matrixStack.popPose()
//        }
//    }
//
//    private data class ShoulderCache(
//        var lastKnownLeft: ShoulderData? = null,
//        var leftInstance: CobblemonInstance = CobblemonInstance(Matrix4f(), Matrix4f(), null),
//        var lastKnownRight: ShoulderData? = null,
//        var rightInstance: CobblemonInstance = CobblemonInstance(Matrix4f(), Matrix4f(), null)
//    )
//
//    @JvmStatic
//    fun shoulderDataOf(player: Player): Tuple<ShoulderData?, ShoulderData?> {
//        val cache = playerCache[player.uuid] ?: return Tuple(null, null)
//        return Tuple(cache.lastKnownLeft, cache.lastKnownRight)
//    }
//}