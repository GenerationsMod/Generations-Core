package generations.gg.generations.core.generationscore.common.client.render.block.entity;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class TimeSpaceAltarEntityRenderer extends GeneralUseBlockEntityRenderer<TimeSpaceAltarBlockEntity> {
    public TimeSpaceAltarEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

//    @Override
//    public void render(@NotNull TimeSpaceAltarBlockEntity blockEntity, float partialTick, PoseStack stack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
//        stack.pushPose();
//        ModelRegistry.prepForBER(stack, blockEntity);
//        ModelRegistry.get(blockEntity, "block").render(new ObjectInstance(new Matrix4f(), stack.last().pose(), "default"), RenderSystem.getProjectionMatrix());
//
//        var trio = blockEntity.getOrb();
//        if (trio != null) ModelRegistry.get(trio, "block").render(new ObjectInstance(new Matrix4f(), stack.last().pose(), "default"), RenderSystem.getProjectionMatrix());
//        RenderSystem.disableDepthTest();
//        stack.popPose();
//    }
}
