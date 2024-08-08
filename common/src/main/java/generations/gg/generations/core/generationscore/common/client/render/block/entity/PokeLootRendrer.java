package generations.gg.generations.core.generationscore.common.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.common.world.level.block.BallLootBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BallLootBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;

public class PokeLootRendrer implements BlockEntityRenderer<BallLootBlockEntity> {

    public PokeLootRendrer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(BallLootBlockEntity blockEntity, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (!(blockEntity.isVisible() && blockEntity.getBlockState().getBlock() instanceof BallLootBlock block && block.canRender(blockEntity.getLevel(), blockEntity.getBlockPos(), blockEntity.getBlockState()))) return;

        stack.pushPose();

        var angle = -block.getAngle(blockEntity.getBlockState()) + 180;

        stack.translate(0.5-(0.015625), 0.125, 0.5-(0.015625));

        stack.mulPose(Axis.YP.rotationDegrees(angle));
//        stack.scale(1);

        Minecraft.getInstance().getItemRenderer().renderStatic(block.ball(), ItemDisplayContext.GROUND, packedLight, packedLight, stack, buffer, blockEntity.getLevel(), 0);
        stack.popPose();
    }


}
