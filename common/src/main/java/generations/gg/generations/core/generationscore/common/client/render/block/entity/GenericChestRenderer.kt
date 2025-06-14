package generations.gg.generations.core.generationscore.common.client.render.block.entity

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericChestBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock
import net.minecraft.Util
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.Sheets
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.resources.model.Material
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.ChestBlock
import java.util.function.Consumer

class GenericChestRenderer(arg: BlockEntityRendererProvider.Context) :
    BlockEntityRenderer<GenericChestBlockEntity> {
    private val lid: ModelPart
    private val bottom: ModelPart
    private val lock: ModelPart

    override fun render(
        blockEntity: GenericChestBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        val level = blockEntity.level
        val flag = level != null
        val blockstate =
            if (flag) blockEntity.blockState else GenerationsBlocks.POKEBALL_CHEST.defaultBlockState().setValue(
                ChestBlock.FACING, Direction.SOUTH
            )

        poseStack.pushPose()
        val f = blockstate.getValue(ChestBlock.FACING).toYRot()
        poseStack.translate(0.5, 0.5, 0.5)
        poseStack.mulPose(Axis.YP.rotationDegrees(-f))
        poseStack.translate(-0.5, -0.5, -0.5)

        var f1 = blockEntity.getOpenNess(partialTick)
        f1 = 1.0f - f1
        f1 = 1.0f - f1 * f1 * f1
        val material = getMaterial(blockEntity)
        val vertexconsumer = material.buffer(bufferSource) { location -> RenderType.entityCutout(location) }
        this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, packedLight, packedOverlay)
        poseStack.popPose()
    }

    private fun render(
        poseStack: PoseStack,
        consumer: VertexConsumer,
        lidPart: ModelPart,
        lockPart: ModelPart,
        bottomPart: ModelPart,
        lidAngle: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        lidPart.xRot = -(lidAngle * ((Math.PI / 2).toFloat()))
        lockPart.xRot = lidPart.xRot
        lidPart.render(poseStack, consumer, packedLight, packedOverlay)
        lockPart.render(poseStack, consumer, packedLight, packedOverlay)
        bottomPart.render(poseStack, consumer, packedLight, packedOverlay)
    }

    init {
        val modelpart = arg.bakeLayer(ModelLayers.CHEST)
        this.bottom = modelpart.getChild("bottom")
        this.lid = modelpart.getChild("lid")
        this.lock = modelpart.getChild("lock")
    }

    companion object {
        private const val BOTTOM = "bottom"
        private const val LID = "lid"
        private const val LOCK = "lock"
        private fun getMaterial(blockEntity: GenericChestBlockEntity): Material {
            return map[blockEntity.blockState.block.instanceOrNull<GenericChestBlock>()?.materialType ?: "pokeball_chest"]!!
        }

        private val map: HashMap<String, Material> = Util.make(
            HashMap()
        ) { map: HashMap<String, Material> ->
            val consumer = { id: String ->
                    map[id] = Material(
                        Sheets.CHEST_SHEET, GenerationsCore.id(
                            "entity/chest/$id"
                        )
                    )
                }
            consumer.invoke("pokeball_chest")
            consumer.invoke("greatball_chest")
            consumer.invoke("ultraball_chest")
            consumer.invoke("masterball_chest")
        }

        val genericChestTextures: Collection<Material>
            get() = map.values
    }
}
