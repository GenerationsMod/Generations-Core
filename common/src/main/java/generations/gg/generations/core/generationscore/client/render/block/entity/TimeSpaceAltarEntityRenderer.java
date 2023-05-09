package generations.gg.generations.core.generationscore.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.world.level.block.entities.TimeSpaceAltarBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.jetbrains.annotations.NotNull;

public class TimeSpaceAltarEntityRenderer extends GeneralUseBlockEntityRenderer<TimeSpaceAltarBlockEntity> {
    public TimeSpaceAltarEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(@NotNull TimeSpaceAltarBlockEntity blockEntity, float partialTick, PoseStack stack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
//        stack.pushPose();
//        ModelRegistry.prepForBER(stack, blockEntity);
//        ModelRegistry.get(blockEntity, "fullbright").render(new ObjectInstance(new Matrix4f(), stack.last().pose(), null), RenderSystem.getProjectionMatrix());
//
//        var trio = blockEntity.getOrb();
//        if (trio != null) ModelRegistry.get(trio, "fullbright").render(new ObjectInstance(new Matrix4f(), stack.last().pose(), null), RenderSystem.getProjectionMatrix());
//        RenderSystem.disableDepthTest();
//        stack.popPose();
    }
}
