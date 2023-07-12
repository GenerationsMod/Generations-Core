package generations.gg.generations.core.generationscore.client.render.block.entity

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.client.render.rarecandy.BlockObjectInstance
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.world.level.block.entities.HealerBlockEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider

class HealerBlockEntityRenderer(ctx: BlockEntityRendererProvider.Context) : GeneralUseBlockEntityRenderer<HealerBlockEntity>(null) {
    companion object {
        private val offsets = listOf(
            0.127 to 0.19,
            -0.127 to 0.19,
            0.127 to 0.0,
            -0.127 to 0.0,
            0.127 to -0.19,
            -0.127 to -0.19
        )
    }

    override fun render(
        blockEntity: HealerBlockEntity,
        partialTick: Float,
        stack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int,
    ) {
        super.render(blockEntity, partialTick, stack, bufferSource, packedLight, packedOverlay)

        var rot = Axis.YP.rotationDegrees(((blockEntity.healTimeLeft % 20f) / 20f) * 360f)

        stack.pushPose()

        ModelRegistry.prepForBER(stack, blockEntity)

        val model = ModelRegistry.get(GenerationsBlockEntityModels.POKEBALL, "block")
        val scale = model.renderObject.scale * 0.16f

        for ((index, pokeBall) in blockEntity.pokeBalls.withIndex()) {
            val ballInstance = blockEntity.objectInstance[index + 1]
            ballInstance.setVariant(pokeBall.name.path.removeSuffix("_ball"))
            (ballInstance as BlockObjectInstance).light = packedLight

            stack.pushPose()
            val offset = offsets[index]
            stack.translate(offset.first, 0.78, offset.second)
            stack.mulPose(rot)
            stack.scale(scale, scale, scale)
            ballInstance.viewMatrix().set(stack.last().pose());
            model.render(ballInstance, RenderSystem.getProjectionMatrix())
            stack.popPose()
        }

        stack.popPose()
    }

    override fun instanceAmount(): Int = 7
}