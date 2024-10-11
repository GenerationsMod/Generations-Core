package generations.gg.generations.core.generationscore.common.client.render.block.entity

import com.cobblemon.mod.common.entity.pokeball.EmptyPokeBallEntity
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.world.level.block.BallLootBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BallLootBlockEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.block.model.ItemTransform
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import org.joml.Quaternionf

class PokeLootRendrer(ctx: BlockEntityRendererProvider.Context) : BlockEntityRenderer<BallLootBlockEntity> {
    var entity: Lazy<EmptyPokeBallEntity> = lazy {  EmptyPokeBallEntity(Minecraft.getInstance().level!!) }
    override fun render(
        blockEntity: BallLootBlockEntity,
        partialTick: Float,
        stack: PoseStack,
        buffer: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int,
    ) {


        val block = blockEntity.blockState.block

        if (!(blockEntity.isVisible && block is BallLootBlock && block.canRender(
                blockEntity.level,
                blockEntity.blockPos,
                blockEntity.blockState
            ))
        ) return
        stack.pushPose()
        val angle: Float = blockEntity.angle

        entity.value.pokeBall = block.ball()

        stack.translate(0.5, 0.25, 0.5)
        stack.mulPose(Axis.YP.rotationDegrees(angle))
        stack.translate(0.0, 0.0, 0.25)
        stack.mulPose(Axis.XP.rotationDegrees(94.0f))
        stack.mulPose(Axis.ZP.rotationDegrees(180.0f))

        stack.scale(0.9f, 0.9f, 0.9f)

//                stack.translate(0.5, 0.0, 0.0);
        stack.scale(1/0.7F, 1/0.7F, 1/0.7F)

//        stack.mulPose(Quaternionf().fromEulerXYZDegrees(Vector3f(0f, angle, 0f)))

        Minecraft.getInstance().entityRenderDispatcher.render(entity.value, 0.0, 0.0, 0.0,0.0f, 0.0f, stack, buffer, packedLight)

        stack.popPose()
    }

    fun ItemTransform.applyReverse(leftHand: Boolean = false, poseStack: PoseStack) {
        if (this !== ItemTransform.NO_TRANSFORM) {
            val f: Float = this.rotation.x()
            var g: Float = this.rotation.y()
            var h: Float = this.rotation.z()
            if (leftHand) {
                g = -g
                h = -h
            }
            val i = if (leftHand) -1 else 1
            poseStack.translate(i.toFloat() * this.translation.x(), this.translation.y(), this.translation.z())
            poseStack.mulPose(
                Quaternionf().rotationXYZ(
                    f * (Math.PI / 180.0).toFloat(),
                    g * (Math.PI / 180.0).toFloat(),
                    h * (Math.PI / 180.0).toFloat()
                )
            )
            poseStack.scale(this.scale.x(), this.scale.y(), this.scale.z())
        }
    }
}
