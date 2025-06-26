package generations.gg.generations.core.generationscore.common.client.render.entity

import com.google.common.collect.ImmutableMap
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.datafixers.util.Pair
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity
import net.minecraft.client.model.BoatModel
import net.minecraft.client.model.ChestBoatModel
import net.minecraft.client.model.ListModel
import net.minecraft.client.model.WaterPatchModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.minecraft.world.entity.vehicle.Boat
import org.joml.Quaternionf
import java.util.function.Function
import java.util.stream.Stream

class GenerationsBoatRenderer<T : GenerationsBoatEntity>(context: EntityRendererProvider.Context, bl: Boolean) :
    EntityRenderer<T>(context) {
    private val boatResources: Map<GenerationsBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>>

    init {
        this.shadowRadius = 0.8f
        this.boatResources = GenerationsBoatEntity.Type.entries.associateWith { type: GenerationsBoatEntity.Type ->
            Pair(id(getTextureLocation(type, bl)),
                    createBoatModel(context, type, bl)
                )
            }
    }

    private fun createBoatModel(
        context: EntityRendererProvider.Context,
        type: GenerationsBoatEntity.Type,
        bl: Boolean
    ): ListModel<Boat> {
        val modelLayerLocation = if (bl) createChestBoatModelName(type) else createBoatModelName(type)
        val modelPart = context.bakeLayer(modelLayerLocation)
        return if (bl) ChestBoatModel(modelPart) else BoatModel(modelPart)
    }

    override fun render(
        boat: T,
        f: Float,
        g: Float,
        poseStack: PoseStack,
        multiBufferSource: MultiBufferSource,
        i: Int
    ) {
        poseStack.pushPose()
        poseStack.translate(0.0f, 0.375f, 0.0f)
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f - f))
        val h = boat!!.hurtTime.toFloat() - g
        var j = boat.damage - g
        if (j < 0.0f) {
            j = 0.0f
        }

        if (h > 0.0f) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(h) * h * j / 10.0f * boat.hurtDir.toFloat()))
        }

        val k = boat.getBubbleAngle(g)
        if (!Mth.equal(k, 0.0f)) {
            poseStack.mulPose((Quaternionf()).setAngleAxis(boat.getBubbleAngle(g) * 0.017453292f, 1.0f, 0.0f, 1.0f))
        }

        val pair =
            boatResources[boat.modBoatType]!!
        val resourceLocation = pair.first
        val listModel = pair.second
        poseStack.scale(-1.0f, -1.0f, 1.0f)
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0f))
        listModel.setupAnim(boat, g, 0.0f, -0.1f, 0.0f, 0.0f)
        val vertexConsumer = multiBufferSource.getBuffer(listModel.renderType(resourceLocation))
        listModel.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, -0x1)
        if (!boat.isUnderWater) {
            val vertexConsumer2 = multiBufferSource.getBuffer(RenderType.waterMask())
            if (listModel is WaterPatchModel) {
                listModel.waterPatch().render(poseStack, vertexConsumer2, i, OverlayTexture.NO_OVERLAY)
            }
        }

        poseStack.popPose()
        super.render(boat, f, g, poseStack, multiBufferSource, i)
    }

    override fun getTextureLocation(boat: T): ResourceLocation {
        return boatResources[boat.modBoatType]!!.first
    }

    companion object {
        fun createBoatModelName(type: GenerationsBoatEntity.Type): ModelLayerLocation {
            return createLocation("boat/" + type.id)
        }

        fun createChestBoatModelName(type: GenerationsBoatEntity.Type): ModelLayerLocation {
            return createLocation("chest_boat/" + type.id)
        }

        private fun createLocation(string: String): ModelLayerLocation {
            return ModelLayerLocation(id(string), "main")
        }

        private fun getTextureLocation(type: GenerationsBoatEntity.Type, bl: Boolean): String {
            return if (bl) "textures/entity/chest_boat/" + type.id + ".png" else "textures/entity/boat/" + type.id + ".png"
        }
    }
}
