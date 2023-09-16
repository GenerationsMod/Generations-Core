package generations.gg.generations.core.generationscore.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class PlayerNpcItemInHandLayer<T extends PlayerNpcEntity, M extends EntityModel<T> & ArmedModel & HeadedModel> extends ItemInHandLayer<T, M> {
    private final ItemInHandRenderer itemInHandRenderer;
    private static final float X_ROT_MIN = -0.5235988F;
    private static final float X_ROT_MAX = 1.5707964F;

    public PlayerNpcItemInHandLayer(RenderLayerParent<T, M> arg, ItemInHandRenderer arg2) {
        super(arg, arg2);
        this.itemInHandRenderer = arg2;
    }

    protected void renderArmWithItem(@NotNull LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext transformType, @NotNull HumanoidArm arm, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        if (itemStack.is(Items.SPYGLASS) && livingEntity.getUseItem() == itemStack && livingEntity.swingTime == 0) {
            this.renderArmWithSpyglass(livingEntity, itemStack, arm, poseStack, buffer, packedLight);
        } else {
            super.renderArmWithItem(livingEntity, itemStack, transformType, arm, poseStack, buffer, packedLight);
        }

    }

    private void renderArmWithSpyglass(LivingEntity arg, ItemStack arg2, HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int i) {
        poseStack.pushPose();
        ModelPart modelPart = this.getParentModel().getHead();
        var xRot = modelPart.xRot;
        modelPart.xRot = Mth.clamp(modelPart.xRot, -0.5235988F, 1.5707964F);
        modelPart.translateAndRotate(poseStack);
        modelPart.xRot = xRot;
        CustomHeadLayer.translateToHead(poseStack, false);
        var isLeftHand = arm == HumanoidArm.LEFT;
        poseStack.translate((isLeftHand ? -2.5F : 2.5F) / 16.0F, -0.0625F, 0.0F);
        this.itemInHandRenderer.renderItem(arg, arg2, ItemDisplayContext.HEAD, false, poseStack, buffer, i);
        poseStack.popPose();
    }
}