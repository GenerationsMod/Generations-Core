package generations.gg.generations.core.generationscore.common.client.render.block.entity

import com.mojang.blaze3d.vertex.PoseStack
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BreederBlockEntity
import gg.generations.rarecandy.renderer.rendering.ObjectInstance
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.resources.ResourceLocation

class BreederBlocEntityRenderer(ctx: BlockEntityRendererProvider.Context) : GeneralUseBlockEntityRenderer<BreederBlockEntity>(ctx) {
    override fun renderModels(
        stack: PoseStack,
        buffersource: MultiBufferSource,
        blockEntity: BreederBlockEntity,
        packedLight: Int
    ) {
        super.renderModels(stack, buffersource, blockEntity, packedLight)

        blockEntity.objectInstance?.run {
            this[1]?.run { renderInstance(buffersource, stack, this, AUTO_FEEDER_FILL, packedLight) }
            this[2]?.run { renderInstance(buffersource, stack, this, AUTO_FEEDER, packedLight) }
            this[3]?.run { renderInstance(buffersource, stack, this, EGG, packedLight) }
        }
    }

    fun renderInstance(buffersource: MultiBufferSource, stack: PoseStack, instance: ObjectInstance, model: ResourceLocation, packedLight: Int) {
        instance.instanceOrNull<BlockObjectInstance>()?.light = packedLight
        renderResourceLocation(buffersource, model, stack, instance)
    }

    override fun instanceAmount(): Int {
        return 4
    }

    companion object {
        val AUTO_FEEDER: ResourceLocation = GenerationsCore.id("models/block/utility_blocks/breeder/auto_feeder.pk")
        val AUTO_FEEDER_FILL: ResourceLocation =
            GenerationsCore.id("models/block/utility_blocks/breeder/auto_feeder_fill.pk")
        val EGG: ResourceLocation = GenerationsCore.id("models/block/utility_blocks/breeder/egg.pk")
    }
}
