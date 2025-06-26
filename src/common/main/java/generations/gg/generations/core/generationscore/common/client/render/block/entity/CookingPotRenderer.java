package generations.gg.generations.core.generationscore.common.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CookingPotBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.utilityblocks.CookingPotBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CookingPotRenderer extends GeneralUseBlockEntityRenderer<CookingPotBlockEntity> {

    public CookingPotRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    public static void renderStack(PoseStack stack, ItemStack item, float scale, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        stack.pushPose();
        stack.mulPose(Axis.XP.rotationDegrees(90));
        stack.pushPose();
        stack.scale(scale, scale, scale);
        Minecraft.getInstance().getItemRenderer().renderStatic(item, ItemDisplayContext.FIXED, packedLight, packedOverlay, stack, bufferSource, null, 0);
        stack.popPose();
        stack.popPose();
    }

    @Override
    public void render(CookingPotBlockEntity pot, float partialTick, PoseStack stack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        stack.pushPose();
        stack.translate(0.5f,0.5f, 0.5f);

        if(pot.getOuput().isEmpty()) {

            renderStack(stack, pot.getIngredient(), 0.25f, bufferSource, packedLight, packedOverlay);

            for (int i = 0; i < 10; i++) {
                ItemStack berry = pot.getBerry(i);
                stack.pushPose();
                stack.mulPose(Axis.YP.rotationDegrees(i * 36));
                stack.translate(0.24, 0.03 + -0.001 * i, 0);
                renderStack(stack, berry, 0.25f, bufferSource, packedLight, packedOverlay);
                stack.popPose();
            }
        } else {
            renderStack(stack, pot.getOuput(), 0.75f, bufferSource, packedLight, packedOverlay);
        }
        stack.popPose();

        super.render(pot, partialTick, stack, bufferSource, packedLight, packedOverlay);
        var direction = pot.getBlockState().getValue(CookingPotBlock.FACING);
//
//        stack.pushPose();
//        stack.translate(0.5f, 0.0f, 0.5f);
//        stack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot() + 270f));
//        RenderSystem.enableDepthTest();
//        RenderSystem.setShaderTexture(0, pot.getModelTexture());
//
//        ModelRegistry.get(pot.getModel(), "fullbright").render(new ObjectInstance(new Matrix4f(), stack.last().pose(), null), RenderSystem.getProjectionMatrix());
//        RenderSystem.disableDepthTest();
//        stack.popPose();
//
    }
}
